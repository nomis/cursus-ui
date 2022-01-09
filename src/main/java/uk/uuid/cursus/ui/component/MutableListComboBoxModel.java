/*
	cursus - Race series management program
	Copyright 2011, 2014, 2022  Simon Arlott

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
package uk.uuid.cursus.ui.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.google.common.base.Objects;

public class MutableListComboBoxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {
	private final ArrayList<T> list = new ArrayList<T>();
	private T selected;

	public MutableListComboBoxModel() {
	}

	public MutableListComboBoxModel(List<T> list) {
		this.list.addAll(list);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public T getElementAt(int index) {
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
