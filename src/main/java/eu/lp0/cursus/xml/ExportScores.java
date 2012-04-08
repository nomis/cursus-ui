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
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import org.simpleframework.xml.core.Persister;

import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.ScoresXMLFile;

public class ExportScores {
	private Persister persister = new Persister();
	private ScoresXMLFile data;

	public ExportScores(Scores seriesScores, List<Scores> eventScores, List<Scores> raceScores) {
		data = new ScoresXMLFile(seriesScores, eventScores, raceScores);
	}

	public void to(OutputStream stream) throws ExportException {
		try {
			persister.write(data, stream);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void to(File file) throws ExportException {
		try {
			persister.write(data, file);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}

	public void to(Writer writer) throws ExportException {
		try {
			persister.write(data, writer);
		} catch (Exception e) {
			throw new ExportException(e);
		}
	}
}