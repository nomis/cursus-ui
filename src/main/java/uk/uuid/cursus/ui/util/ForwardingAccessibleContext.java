/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

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
package uk.uuid.cursus.ui.util;

import java.awt.IllegalComponentStateException;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleEditableText;
import javax.accessibility.AccessibleIcon;
import javax.accessibility.AccessibleRelationSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleTable;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;

public abstract class ForwardingAccessibleContext extends AccessibleContext {
	protected abstract AccessibleContext delegate();

	@Override
	public String getAccessibleName() {
		return delegate().getAccessibleName();
	}

	@Override
	public void setAccessibleName(String s) {
		delegate().setAccessibleName(s);
	}

	@Override
	public String getAccessibleDescription() {
		return delegate().getAccessibleDescription();
	}

	@Override
	public void setAccessibleDescription(String s) {
		delegate().setAccessibleDescription(s);
	}

	@Override
	public AccessibleRole getAccessibleRole() {
		return delegate().getAccessibleRole();
	}

	@Override
	public AccessibleStateSet getAccessibleStateSet() {
		return delegate().getAccessibleStateSet();
	}

	@Override
	public Accessible getAccessibleParent() {
		return delegate().getAccessibleParent();
	}

	@Override
	public void setAccessibleParent(Accessible a) {
		delegate().setAccessibleParent(a);
	}

	@Override
	public int getAccessibleIndexInParent() {
		return delegate().getAccessibleIndexInParent();
	}

	@Override
	public int getAccessibleChildrenCount() {
		return delegate().getAccessibleChildrenCount();
	}

	@Override
	public Accessible getAccessibleChild(int i) {
		return delegate().getAccessibleChild(i);
	}

	@Override
	public Locale getLocale() throws IllegalComponentStateException {
		return delegate().getLocale();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		delegate().addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		delegate().removePropertyChangeListener(listener);
	}

	@Override
	public AccessibleAction getAccessibleAction() {
		return delegate().getAccessibleAction();
	}

	@Override
	public AccessibleComponent getAccessibleComponent() {
		return delegate().getAccessibleComponent();
	}

	@Override
	public AccessibleSelection getAccessibleSelection() {
		return delegate().getAccessibleSelection();
	}

	@Override
	public AccessibleText getAccessibleText() {
		return delegate().getAccessibleText();
	}

	@Override
	public AccessibleEditableText getAccessibleEditableText() {
		return delegate().getAccessibleEditableText();
	}

	@Override
	public AccessibleValue getAccessibleValue() {
		return delegate().getAccessibleValue();
	}

	@Override
	public AccessibleIcon[] getAccessibleIcon() {
		return delegate().getAccessibleIcon();
	}

	@Override
	public AccessibleRelationSet getAccessibleRelationSet() {
		return delegate().getAccessibleRelationSet();
	}

	@Override
	public AccessibleTable getAccessibleTable() {
		return delegate().getAccessibleTable();
	}

	@Override
	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		delegate().firePropertyChange(propertyName, oldValue, newValue);
	}
}