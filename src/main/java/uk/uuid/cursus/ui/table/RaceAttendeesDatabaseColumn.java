/*
	cursus - Race series management program
	Copyright 2011-2012, 2014  Simon Arlott

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
package uk.uuid.cursus.ui.table;

import java.util.Collections;

import javax.swing.JComboBox;

import uk.uuid.cursus.ui.component.DatabaseColumn;
import uk.uuid.cursus.ui.component.DatabaseTableCellEditor;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.component.MutableListComboBoxModel;
import uk.uuid.cursus.ui.component.StringDatabaseTableCellRenderer;

import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;

import uk.uuid.cursus.db.dao.EntityDAO;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.util.PilotRaceNumberComparator;

public class RaceAttendeesDatabaseColumn extends DatabaseColumn<RaceAttendeePenalty, PilotWrapper> {
	private final MutableListComboBoxModel<PilotWrapper> pilots = new MutableListComboBoxModel<PilotWrapper>();

	public RaceAttendeesDatabaseColumn(String name) {
		super(name);
		cellRenderer = new StringDatabaseTableCellRenderer<RaceAttendeePenalty, PilotWrapper>(this);
	}

	public RaceAttendeesDatabaseColumn(String name, DatabaseWindow win, EntityDAO<RaceAttendeePenalty> dao) {
		super(name, win, dao);
		cellRenderer = new StringDatabaseTableCellRenderer<RaceAttendeePenalty, PilotWrapper>(this);
		cellEditor = new DatabaseTableCellEditor<RaceAttendeePenalty, PilotWrapper>(this, new JComboBox(pilots));
	}

	@Override
	protected PilotWrapper getValue(RaceAttendeePenalty row, boolean editing) {
		return new PilotWrapper(row.getPilot());
	}

	@Override
	protected boolean setValue(RaceAttendeePenalty row, PilotWrapper value) {
		if (value == null) {
			row.setPilot(null);
		} else {
			row.setPilot(value.getPilot());
		}
		return true;
	}

	public void setRace(Race race) {
		if (race != null) {
			pilots.replaceAll(Collections2.transform(Ordering.from(new PilotRaceNumberComparator()).immutableSortedCopy(race.getAttendees().keySet()),
					PilotWrapper.getFunction()));
		} else {
			pilots.replaceAll(Collections.<PilotWrapper>emptySet());
		}
	}
}