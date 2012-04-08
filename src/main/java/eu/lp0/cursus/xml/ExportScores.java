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

import java.io.ByteArrayOutputStream;

import org.simpleframework.xml.core.Persister;

import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.ScoresXMLSeries;

public class ExportScores {
	private Scores scores;

	public ExportScores(Scores scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			new Persister().write(new ScoresXMLSeries(scores), baos);
			return baos.toString("UTF-8"); //$NON-NLS-1$
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}