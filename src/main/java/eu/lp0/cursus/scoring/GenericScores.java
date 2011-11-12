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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericScores extends AbstractScores {
	protected final RaceLapsData raceLapsData;
	protected final RacePenaltiesData racePenaltiesData;
	protected final RacePointsData racePointsData;
	protected final RacePositionsData racePositionsData;
	protected final RaceDiscardsData raceDiscardsData;

	protected final OverallPenaltiesData overallPenaltiesData;
	protected final OverallPointsData overallPointsData;
	protected final OverallPositionData overallPositionData;

	public GenericScores(Set<Pilot> pilots, List<Race> races, ScoresFactory scoresDataFactory) {
		super(pilots, races);

		raceLapsData = scoresDataFactory.newRaceLapsData(this);
		racePenaltiesData = scoresDataFactory.newRacePenaltiesData(this);
		racePointsData = scoresDataFactory.newRacePointsData(this);
		racePositionsData = scoresDataFactory.newRacePositionsData(this);
		raceDiscardsData = scoresDataFactory.newRaceDiscardsData(this);

		overallPenaltiesData = scoresDataFactory.newOverallPenaltiesData(this);
		overallPointsData = scoresDataFactory.newOverallPointsData(this);
		overallPositionData = scoresDataFactory.newOverallPositionData(this);
	}

	// RaceLapsData
	@Override
	public int getLaps(Pilot pilot, Race race) {
		return raceLapsData.getLaps(pilot, race);
	}

	@Override
	public Map<Race, Integer> getLaps(Pilot pilot) {
		return Collections.unmodifiableMap(raceLapsData.getLaps(pilot));
	}

	@Override
	public Map<Pilot, Integer> getLaps(Race race) {
		return Collections.unmodifiableMap(raceLapsData.getLaps(race));
	}

	@Override
	public List<Pilot> getLapOrder(Race race) {
		return Collections.unmodifiableList(raceLapsData.getLapOrder(race));
	}

	// RacePenaltiesData
	@Override
	public Table<Pilot, Race, Integer> getRacePenalties() {
		return racePenaltiesData.getRacePenalties();
	}

	@Override
	public Map<Race, Integer> getRacePenalties(Pilot pilot) {
		return Collections.unmodifiableMap(racePenaltiesData.getRacePenalties(pilot));
	}

	@Override
	public Map<Pilot, Integer> getRacePenalties(Race race) {
		return Collections.unmodifiableMap(racePenaltiesData.getRacePenalties(race));
	}

	@Override
	public int getRacePenalties(Pilot pilot, Race race) {
		return racePenaltiesData.getRacePenalties(pilot, race);
	}

	// RacePointsData
	@Override
	public Table<Pilot, Race, Integer> getRacePoints() {
		return racePointsData.getRacePoints();
	}

	@Override
	public int getRacePoints(Pilot pilot, Race race) {
		return racePointsData.getRacePoints(pilot, race);
	}

	@Override
	public Map<Race, Integer> getRacePoints(Pilot pilot) {
		return Collections.unmodifiableMap(racePointsData.getRacePoints(pilot));
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		return Collections.unmodifiableMap(racePointsData.getRacePoints(race));
	}

	@Override
	public int getFleetSize(Race race) {
		return racePointsData.getFleetSize(race);
	}

	// RacePositionsData
	@Override
	public Map<Race, Map<Pilot, Integer>> getRacePositions() {
		return Collections.unmodifiableMap(racePositionsData.getRacePositions());
	}

	@Override
	public Map<Pilot, Integer> getRacePositions(Race race) {
		return Collections.unmodifiableMap(racePositionsData.getRacePositions(race));
	}

	@Override
	public Map<Race, LinkedListMultimap<Integer, Pilot>> getRacePositionsWithOrder() {
		return Collections.unmodifiableMap(racePositionsData.getRacePositionsWithOrder());
	}

	@Override
	public LinkedListMultimap<Integer, Pilot> getRacePositionsWithOrder(Race race) {
		return racePositionsData.getRacePositionsWithOrder(race);
	}

	@Override
	public int getRacePosition(Pilot pilot, Race race) {
		return racePositionsData.getRacePosition(pilot, race);
	}

	@Override
	public Map<Race, List<Pilot>> getRaceOrders() {
		return Collections.unmodifiableMap(racePositionsData.getRaceOrders());
	}

	@Override
	public List<Pilot> getRaceOrder(Race race) {
		return Collections.unmodifiableList(racePositionsData.getRaceOrder(race));
	}

	// RaceDiscardsData
	@Override
	public Table<Pilot, Integer, Integer> getRaceDiscards() {
		return raceDiscardsData.getRaceDiscards();
	}

	@Override
	public Map<Integer, Integer> getRaceDiscards(Pilot pilot) {
		return Collections.unmodifiableMap(raceDiscardsData.getRaceDiscards(pilot));
	}

	@Override
	public Map<Pilot, Integer> getRaceDiscards(int discard) {
		return Collections.unmodifiableMap(raceDiscardsData.getRaceDiscards(discard));
	}

	@Override
	public int getRaceDiscard(Pilot pilot, int discard) {
		return raceDiscardsData.getRaceDiscard(pilot, discard);
	}

	@Override
	public Map<Integer, Race> getDiscardedRaces(Pilot pilot) {
		return Collections.unmodifiableMap(raceDiscardsData.getDiscardedRaces(pilot));
	}

	// OverallPenaltiesData
	@Override
	public Map<Pilot, Integer> getOverallPenalties() {
		return Collections.unmodifiableMap(overallPenaltiesData.getOverallPenalties());
	}

	@Override
	public int getOverallPenalties(Pilot pilot) {
		return overallPenaltiesData.getOverallPenalties(pilot);
	}

	// OverallPointsData
	@Override
	public Map<Pilot, Integer> getOverallPoints() {
		return Collections.unmodifiableMap(overallPointsData.getOverallPoints());
	}

	@Override
	public int getOverallPoints(Pilot pilot) {
		return getOverallPoints(pilot);
	}

	@Override
	public int getOverallFleetSize() {
		return overallPointsData.getOverallFleetSize();
	}

	// OverallPositionData
	@Override
	public Map<Pilot, Integer> getOverallPositions() {
		return Collections.unmodifiableMap(overallPositionData.getOverallPositions());
	}

	@Override
	public LinkedListMultimap<Integer, Pilot> getOverallPositionsWithOrder() {
		return overallPositionData.getOverallPositionsWithOrder();
	}

	@Override
	public int getOverallPosition(Pilot pilot) {
		return overallPositionData.getOverallPosition(pilot);
	}

	@Override
	public List<Pilot> getOverallOrder() {
		return Collections.unmodifiableList(overallPositionData.getOverallOrder());
	}
}