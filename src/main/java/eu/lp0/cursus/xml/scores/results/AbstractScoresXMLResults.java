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
package eu.lp0.cursus.xml.scores.results;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;

import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEventRef;

public abstract class AbstractScoresXMLResults {
	public AbstractScoresXMLResults() {
	}

	public AbstractScoresXMLResults(Scores scores) {
		this();
		scorer = scores.getScorer();
	}

	@Attribute
	private String scorer;

	public String getScorer() {
		return scorer;
	}

	public void setScorer(String scorer) {
		this.scorer = scorer;
	}

	public abstract ArrayList<ScoresXMLEventRef> getEvents();

	public abstract ArrayList<ScoresXMLOverallScore> getOverallPilots();
}