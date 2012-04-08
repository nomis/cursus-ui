/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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
package eu.lp0.cursus.xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.simpleframework.xml.core.Persister;

import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.ScoresXMLFile;

public class ScoresXML {
	private ScoresXMLFile data;

	public ScoresXML() {
	}

	public ScoresXML(Scores seriesScores, List<Scores> eventScores, List<Scores> raceScores) {
		data = new ScoresXMLFile(new ExportReferenceManager(), seriesScores, eventScores, raceScores);
	}

	public ScoresXMLFile getData() {
		return data;
	}

	public void setData(ScoresXMLFile data) {
		this.data = data;
	}

	public void from(InputStream stream) throws ImportException {
		try {
			new Persister().read(ScoresXMLFile.class, stream);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(OutputStream stream) throws ExportException {
		try {
			new Persister().write(data, stream);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void from(File file) throws ImportException {
		try {
			new Persister().read(ScoresXMLFile.class, file);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(File file) throws ExportException {
		try {
			new Persister().write(data, file);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void from(Reader reader) throws ImportException {
		try {
			new Persister().read(ScoresXMLFile.class, reader);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(Writer writer) throws ExportException {
		try {
			new Persister().write(data, writer);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}
}