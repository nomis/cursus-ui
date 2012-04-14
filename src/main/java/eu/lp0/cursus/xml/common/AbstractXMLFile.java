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
package eu.lp0.cursus.xml.common;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;

import com.google.common.base.Throwables;

import eu.lp0.cursus.xml.ExportException;
import eu.lp0.cursus.xml.ImportException;

public abstract class AbstractXMLFile<T> {
	private static Registry registry = new Registry();
	private static RegistryStrategy strategy = new RegistryStrategy(registry);

	static {
		try {
			registry.bind(String.class, StringConverter.class);
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	private final Class<T> type;
	private T data;

	public AbstractXMLFile(Class<T> type) {
		this.type = type;
	}

	public AbstractXMLFile(Class<T> type, T data) {
		this.type = type;
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	protected Persister newPersister() {
		return new Persister(strategy);
	}

	public void from(InputStream stream) throws ImportException {
		try {
			data = newPersister().read(type, stream);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(OutputStream stream) throws ExportException {
		try {
			newPersister().write(data, stream);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void from(File file) throws ImportException {
		try {
			data = newPersister().read(type, file);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(File file) throws ExportException {
		try {
			newPersister().write(data, file);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void from(Reader reader) throws ImportException {
		try {
			data = newPersister().read(type, reader);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(Writer writer) throws ExportException {
		try {
			newPersister().write(data, writer);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}
}