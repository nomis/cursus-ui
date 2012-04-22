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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import eu.lp0.cursus.xml.ExportException;
import eu.lp0.cursus.xml.ImportException;

public abstract class AbstractXMLFile<T> {
	private static StreamFactory factory = StreamFactory.newInstance();

	static {
		factory.loadResource("eu/lp0/cursus/beanio.xml"); //$NON-NLS-1$
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

	public void from(InputStream stream) throws ImportException {
		from(new InputStreamReader(stream, Charset.forName("UTF-8"))); //$NON-NLS-1$
	}

	public void to(OutputStream stream) throws ExportException {
		to(new OutputStreamWriter(stream, Charset.forName("UTF-8"))); //$NON-NLS-1$
	}

	public void from(File file) throws ImportException {
		try {
			from(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ImportException(e);
		}
	}

	public void to(File file) throws ExportException {
		try {
			to(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new ExportException(e);
		}
	}

	public void from(Reader reader) throws ImportException {
		try {
			BeanReader in = factory.createReader(type.getSimpleName(), reader);
			data = type.cast(in.read());
			in.close();
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	public void to(Writer writer) throws ExportException {
		try {
			BeanWriter out = factory.createWriter(type.getSimpleName(), writer);
			out.write(data);
			out.close();
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}
}
