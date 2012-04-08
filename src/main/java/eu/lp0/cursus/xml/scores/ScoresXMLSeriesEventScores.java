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
package eu.lp0.cursus.xml.scores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "event")
public class ScoresXMLSeriesEventScores {
	public ScoresXMLSeriesEventScores() {
	}

	public ScoresXMLSeriesEventScores(Scores scores, Event event, Collection<Race> races) {
		ref = Event.class.getSimpleName() + event.getId();

		this.races = new ArrayList<ScoresXMLEventRaceScores>(races.size());
		for (Race race : races) {
			this.races.add(new ScoresXMLEventRaceScores(scores, race));
		}
	}

	@Attribute
	private String ref;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@ElementList
	private List<ScoresXMLEventRaceScores> races;

	public List<ScoresXMLEventRaceScores> getRaces() {
		return races;
	}

	public void setRaces(List<ScoresXMLEventRaceScores> race) {
		this.races = race;
	}
}