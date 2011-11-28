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
package eu.lp0.cursus.test.ui;

import javax.accessibility.Accessible;

import junit.framework.Assert;

import org.junit.Test;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.i18n.Messages;

public class SelectRaceEntityTests extends AbstractUITest {
	@Test
	public void selectSeries() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, Messages.getString(Database.UNTITLED_SERIES));

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(seriesNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Series.class, raceEntity.getClass());

		Series series = (Series)raceEntity;
		Assert.assertEquals(Messages.getString(Database.UNTITLED_SERIES), series.getName());
	}

	@Test
	public void selectEvent() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, Messages.getString(Database.UNTITLED_SERIES));
		Accessible eventNode = findAccessibleChildByName(seriesNode, Messages.getString(Database.UNTITLED_EVENT));

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(eventNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Event.class, raceEntity.getClass());

		Event event = (Event)raceEntity;
		Assert.assertEquals(Messages.getString(Database.UNTITLED_EVENT), event.getName());
	}

	@Test
	public void selectRace() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, Messages.getString(Database.UNTITLED_SERIES));
		Accessible eventNode = findAccessibleChildByName(seriesNode, Messages.getString(Database.UNTITLED_EVENT));
		Accessible raceNode = findAccessibleChildByName(eventNode, Messages.getString(Database.UNTITLED_RACE));

		Assert.assertNull(getSelectedRaceEntity());
		accessibleSelect(raceNode, true);

		RaceEntity raceEntity = getSelectedRaceEntity();
		Assert.assertNotNull(raceEntity);
		Assert.assertEquals(Race.class, raceEntity.getClass());

		Race race = (Race)raceEntity;
		Assert.assertEquals(Messages.getString(Database.UNTITLED_RACE), race.getName());
	}
}