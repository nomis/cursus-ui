/*
	cursus - Race series management program
	Copyright 2011-2013  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.lp0.cursus.db;

import java.io.File;
import java.io.FileFilter;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.util.ProgressMonitor;

public class FileDatabase extends Database {
	public static final String FILENAME_SUFFIX = ".h2.db"; //$NON-NLS-1$
	public static final FileFilter FILE_FILTER = new FileFilter() {
		@Override
		public boolean accept(File f) {
			try {
				filenameToURL(f);
			} catch (InvalidDatabaseException e) {
				return false;
			}
			return true;
		}
	};

	private final File file;

	public FileDatabase(File file) throws SQLException, InvalidDatabaseException {
		this(file, Mode.OPEN);
	}

	protected FileDatabase(File file, Mode mode) throws SQLException, InvalidDatabaseException {
		super(filenameToName(file), "jdbc:h2:" + filenameToURL(file) + ";FILE_LOCK=FS", "SA", "", mode); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		this.file = file;
	}

	@Override
	public boolean isSaved() {
		return true;
	}

	public static String filenameToURL(File file) throws InvalidDatabaseException {
		String name = file.getAbsolutePath();
		if (name.contains(";")) { //$NON-NLS-1$
			throw new InvalidFilenameException.Semicolon(name);
		}
		if (!name.endsWith(FILENAME_SUFFIX)) {
			throw new InvalidFilenameException.Suffix(name, FILENAME_SUFFIX);
		}
		return name.substring(0, name.length() - FILENAME_SUFFIX.length());
	}

	public static String filenameToName(File file) {
		String name = file.getName();
		if (name.endsWith(FILENAME_SUFFIX)) {
			return name.substring(0, name.length() - FILENAME_SUFFIX.length());
		}
		return name;
	}

	public static File reformatForSave(File file) {
		String name = file.getAbsolutePath();
		if (name.contains(";")) { //$NON-NLS-1$
			return file;
		}
		if (name.endsWith(FILENAME_SUFFIX)) {
			return file;
		}
		return new File(name + FILENAME_SUFFIX);
	}

	public static FileDatabase save(ProgressMonitor progress, Database src, File file) throws Exception {
		FileDatabase dst = new FileDatabase(file, Mode.NO_INIT);
		Saver s = null;

		src.startSession();
		try {
			DatabaseSession.begin();

			progress.start(dst.getName(), 0, 1);

			Query q = DatabaseSession.getEntityManager().createNativeQuery("SCRIPT DROP"); //$NON-NLS-1$
			final List<?> rl = q.getResultList();

			if (progress.isCanceled()) {
				throw new InterruptedException();
			}

			s = new Saver(progress, rl, dst);
			s.start();
			s.join();

			DatabaseSession.commit();
		} finally {
			src.endSession();
		}

		if (!s.isSuccess()) {
			dst.close(true);
			dst.delete();

			Exception e = s.getError();
			if (e != null) {
				throw e;
			} else {
				throw new RuntimeException();
			}
		}

		return dst;
	}

	private static class Saver extends Thread {
		private final List<?> script;
		private final Database db;
		private boolean success = false;
		private Exception error = null;
		private ProgressMonitor progress;
		private Logger log = LoggerFactory.getLogger(getClass());

		public Saver(ProgressMonitor progress, List<?> script, Database db) {
			this.progress = progress;
			this.script = script;
			this.db = db;

			progress.setMaximum(script.size());
		}

		@Override
		public synchronized void run() {
			int i = 0;

			db.startSession();
			try {
				for (Object o : script) {
					String stmt = o.toString();

					if (stmt.startsWith("CREATE MEMORY TABLE ")) { //$NON-NLS-1$
						stmt = stmt.replaceFirst("CREATE MEMORY TABLE ", "CREATE TABLE "); //$NON-NLS-1$ //$NON-NLS-2$
					}

					log.trace(stmt);
					DatabaseSession.begin();
					DatabaseSession.getEntityManager().createNativeQuery(stmt).executeUpdate();
					DatabaseSession.commit();
					progress.setProgress(++i);

					if (progress.isCanceled()) {
						throw new InterruptedException();
					}

					Thread.sleep(100);
				}
			} catch (Exception e) {
				error = e;
				return;
			} finally {
				db.endSession();
				progress.stop();
			}

			success = true;
		}

		public synchronized boolean isSuccess() {
			return success;
		}

		public synchronized Exception getError() {
			return error;
		}
	}

	@Override
	public void delete() {
		super.delete();
		file.delete();
	}
}
