/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.cursus.test.ui;

import java.sql.SQLException;

import javax.accessibility.Accessible;

import org.junit.Assert;
import org.junit.Test;

import uk.uuid.cursus.db.Database;
import uk.uuid.cursus.db.InvalidDatabaseException;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.RaceEntity;
import uk.uuid.cursus.db.data.Series;

public class SelectRaceEntityTests extends AbstractUITest {
	@Override
	protected Database createEmptyDatabase(Database database) throws InvalidDatabaseException, SQLException {
		populateDefaultData(database);
		return database;
	}

	@Test
	public void selectSeries() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, DEFAULT_SERIES);

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(seriesNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Series.class, raceEntity.getClass());

		Series series = (Series)raceEntity;
		Assert.assertEquals(DEFAULT_SERIES, series.getName());
	}

	@Test
	public void selectEvent() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, DEFAULT_SERIES);
		Accessible eventNode = findAccessibleChildByName(seriesNode, DEFAULT_EVENT);

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(eventNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Event.class, raceEntity.getClass());

		Event event = (Event)raceEntity;
		Assert.assertEquals(DEFAULT_EVENT, event.getName());
	}

	@Test
	public void selectRace() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, DEFAULT_SERIES);
		Accessible eventNode = findAccessibleChildByName(seriesNode, DEFAULT_EVENT);
		Accessible raceNode = findAccessibleChildByName(eventNode, DEFAULT_RACE);

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(raceNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Race.class, raceEntity.getClass());

		Race race = (Race)raceEntity;
		Assert.assertEquals(DEFAULT_RACE, race.getName());
	}
}