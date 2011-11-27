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
import java.util.Collection;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.google.common.base.Objects;

public class MutableListComboBoxModel<T> extends AbstractListModel implements ComboBoxModel {
	private final ArrayList<T> list = new ArrayList<T>();
	private T selected;

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public Object getElementAt(int index) {
		return list.get(index);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setSelectedItem(Object item) {
		if (!Objects.equal(selected, item)) {
			selected = (T)item;
			fireContentsChanged(this, -1, -1);
		}
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

	public void replaceAll(Collection<T> newCollection) {
		if (!list.isEmpty()) {
			int size = list.size();
			list.clear();
			fireIntervalRemoved(this, 0, size - 1);
		}

		list.addAll(newCollection);
		if (!list.isEmpty()) {
			fireIntervalAdded(this, 0, list.size() - 1);
		}
		list.trimToSize();
	}
}