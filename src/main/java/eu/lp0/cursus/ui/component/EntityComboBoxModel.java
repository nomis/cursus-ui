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
package eu.lp0.cursus.ui.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import eu.lp0.cursus.db.data.AbstractEntity;

public class EntityComboBoxModel<E extends AbstractEntity> extends AbstractListModel implements ComboBoxModel {
	private List<E> items = new ArrayList<E>();
	private E selectedItem = null;

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public Object getElementAt(int index) {
		return index < items.size() ? items.get(index) : null;
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = (E)anItem;

		if (anItem != null && !items.contains(anItem)) {
			selectedItem = null;
		}

		if (selectedItem != anItem) {
			selectedItem = (E)anItem;
			fireContentsChanged(this, -1, -1);
		}
	}

	public void updateModel(List<E> updatedItems) {
		E updatedSelectedItem = null;

		if (selectedItem != null) {
			for (E item : updatedItems) {
				if (selectedItem.getId() == item.getId()) {
					updatedSelectedItem = item;
					break;
				}
			}
		}

		items = updatedItems;
		selectedItem = updatedSelectedItem;
		fireContentsChanged(this, 0, getSize() - 1);
	}
}