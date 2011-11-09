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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class Scores implements RaceLapsData, RacePenaltiesData, RacePointsData, RaceOrderData, RacePositionData, RaceDiscardsData, OverallPenaltiesData,
		OverallPointsData, OverallOrderData, OverallPositionData {
	protected final Collection<Pilot> pilots;
	protected final List<Race> races;

	protected final RaceLapsData raceLapsData;
	protected final RacePenaltiesData racePenaltiesData;
	protected final RacePointsData racePointsData;
	protected final RaceOrderData raceOrderData;
	protected final RacePositionData racePositionData;
	protected final RaceDiscardsData raceDiscardsData;

	protected final OverallPenaltiesData overallPenaltiesData;
	protected final OverallPointsData overallPointsData;
	protected final OverallOrderData overallOrderData;
	protected final OverallPositionData overallPositionData;

	public Scores(Collection<Pilot> pilots, List<Race> races, ScoresFactory scoresDataFactory) {
		this.pilots = pilots;
		this.races = races;

		raceLapsData = scoresDataFactory.newRaceLapsData(this);
		racePenaltiesData = scoresDataFactory.newRacePenaltiesData(this);
		racePointsData = scoresDataFactory.newRacePointsData(this);
		raceOrderData = scoresDataFactory.newRaceOrderData(this);
		racePositionData = scoresDataFactory.newRacePositionData(this);
		raceDiscardsData = scoresDataFactory.newRaceDiscardsData(this);

		overallPenaltiesData = scoresDataFactory.newOverallPenaltiesData(this);
		overallPointsData = scoresDataFactory.newOverallPointsData(this);
		overallPositionData = scoresDataFactory.newOverallPositionData(this);
		overallOrderData = scoresDataFactory.newOverallOrderData(this);
	}

	public Collection<Pilot> getPilots() {
		return Collections.unmodifiableCollection(pilots);
	}

	public List<Race> getRaces() {
		return Collections.unmodifiableList(races);
	}

	// RaceLapsData
	@Override
	public Integer getLaps(Pilot pilot, Race race) {
		return raceLapsData.getLaps(pilot, race);
	}

	@Override
	public Map<Race, Integer> getLaps(Pilot pilot) {
		return raceLapsData.getLaps(pilot);
	}

	@Override
	public Map<Pilot, Integer> getLaps(Race race) {
		return raceLapsData.getLaps(race);
	}

	@Override
	public void completeRaceLap(Pilot pilot, Race race) {
		raceLapsData.completeRaceLap(pilot, race);
	}

	// RacePenaltiesData
	@Override
	public Table<Pilot, Race, Integer> getRacePenalties() {
		return racePenaltiesData.getRacePenalties();
	}

	@Override
	public Map<Race, Integer> getRacePenalties(Pilot pilot) {
		return racePenaltiesData.getRacePenalties(pilot);
	}

	@Override
	public Map<Pilot, Integer> getRacePenalties(Race race) {
		return racePenaltiesData.getRacePenalties(race);
	}

	@Override
	public Integer getRacePenalties(Pilot pilot, Race race) {
		return racePenaltiesData.getRacePenalties(pilot, race);
	}

	// RacePointsData
	@Override
	public Table<Pilot, Race, Integer> getRacePoints() {
		return racePointsData.getRacePoints();
	}

	@Override
	public Integer getRacePoints(Pilot pilot, Race race) {
		return racePointsData.getRacePoints(pilot, race);
	}

	@Override
	public Map<Race, Integer> getRacePoints(Pilot pilot) {
		return racePointsData.getRacePoints(pilot);
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		return racePointsData.getRacePoints(race);
	}

	// RaceOrderData
	@Override
	public Map<Race, List<Pilot>> getRaceOrder() {
		return raceOrderData.getRaceOrder();
	}

	@Override
	public List<Pilot> getRaceOrder(Race race) {
		return raceOrderData.getRaceOrder(race);
	}

	// RacePositionData
	@Override
	public Table<Pilot, Race, Integer> getRacePosition() {
		return racePositionData.getRacePosition();
	}

	@Override
	public Map<Race, Integer> getRacePosition(Pilot pilot) {
		return racePositionData.getRacePosition(pilot);
	}

	@Override
	public Map<Pilot, Integer> getRacePosition(Race race) {
		return racePositionData.getRacePosition(race);
	}

	@Override
	public Integer getRacePosition(Pilot pilot, Race race) {
		return racePositionData.getRacePosition(pilot, race);
	}

	// RaceDiscardsData
	@Override
	public Table<Pilot, Integer, Integer> getRaceDiscards() {
		return raceDiscardsData.getRaceDiscards();
	}

	@Override
	public Map<Integer, Integer> getRaceDiscards(Pilot pilot) {
		return raceDiscardsData.getRaceDiscards(pilot);
	}

	@Override
	public Map<Pilot, Integer> getRaceDiscards(int discard) {
		return raceDiscardsData.getRaceDiscards(discard);
	}

	@Override
	public Integer getRaceDiscard(Pilot pilot, int discard) {
		return raceDiscardsData.getRaceDiscard(pilot, discard);
	}

	@Override
	public Map<Integer, Race> getDiscardedRaces(Pilot pilot) {
		return raceDiscardsData.getDiscardedRaces(pilot);
	}

	// OverallPenaltiesData
	@Override
	public Map<Pilot, Integer> getOverallPenalties() {
		return overallPenaltiesData.getOverallPenalties();
	}

	@Override
	public Integer getOverallPenalties(Pilot pilot) {
		return overallPenaltiesData.getOverallPenalties(pilot);
	}

	// OverallPointsData
	@Override
	public Map<Pilot, Integer> getOverallPoints() {
		return overallPointsData.getOverallPoints();
	}

	@Override
	public Integer getOverallPoints(Pilot pilot) {
		return getOverallPoints(pilot);
	}

	// OverallOrderData
	@Override
	public List<Pilot> getOverallOrder() {
		return overallOrderData.getOverallOrder();
	}

	// OverallPositionData
	@Override
	public Map<Pilot, Integer> getOverallPosition() {
		return overallPositionData.getOverallPosition();
	}

	@Override
	public Integer getOverallPosition(Pilot pilot) {
		return overallPositionData.getOverallPosition(pilot);
	}
}