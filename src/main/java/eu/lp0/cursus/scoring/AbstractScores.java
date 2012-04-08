/*
	cursus - Race series management program
	Copyright 2011  Simon Arlott

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
package eu.lp0.cursus.scoring;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

public abstract class AbstractScores extends AbstractForwardingScores {
	protected final Set<Pilot> pilots;
	protected final List<Race> races;
	protected final Series series;
	protected final Set<Pilot> fleet;
	protected final String scorer;
	private final ScoresFactory scoresFactory;

	public AbstractScores(Set<Pilot> pilots, List<Race> races, Predicate<Pilot> fleetFilter, ScoresFactory scoresFactory, Scorer scorer) {
		this.pilots = ImmutableSet.copyOf(pilots);
		this.races = ImmutableList.copyOf(races);
		this.scoresFactory = scoresFactory;
		this.scorer = scorer.getUUID();

		Preconditions.checkArgument(!this.pilots.isEmpty(), "No pilots"); //$NON-NLS-1$
		Preconditions.checkArgument(!this.races.isEmpty(), "No races"); //$NON-NLS-1$

		Set<Series> checkSeries = new HashSet<Series>(2);
		for (Pilot pilot : pilots) {
			checkSeries.add(pilot.getSeries());
		}
		for (Race race : races) {
			checkSeries.add(race.getEvent().getSeries());
		}
		Preconditions.checkArgument(checkSeries.size() == 1, "Multiple series not allowed"); //$NON-NLS-1$
		series = checkSeries.iterator().next();

		fleet = ImmutableSet.copyOf(Sets.filter(series.getPilots(), fleetFilter));
	}

	// ForwardingScores
	@Override
	protected ScoredData delegateScoredData() {
		return this;
	}

	@Override
	protected ScoresFactory delegateScoresFactory() {
		return scoresFactory;
	}

	// ScoredData
	@Override
	public Set<Pilot> getPilots() {
		return pilots;
	}

	@Override
	public List<Race> getRaces() {
		return races;
	}

	@Override
	public Series getSeries() {
		return series;
	}

	@Override
	public Set<Pilot> getFleet() {
		return fleet;
	}

	@Override
	public String getScorer() {
		return scorer;
	}
}