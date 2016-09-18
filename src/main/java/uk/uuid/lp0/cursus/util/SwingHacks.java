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
package uk.uuid.lp0.cursus.util;

import java.awt.Container;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.UIManager;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class SwingHacks {
	private static boolean isYesLast() {
		return UIManager.getBoolean("OptionPane.isYesLast"); //$NON-NLS-1$
	}

	public static void addButtonsInUIOrder(Container container, List<? extends JButton> buttons, List<? extends Object> constraints) {
		Preconditions.checkArgument(buttons.size() == constraints.size());

		if (isYesLast()) {
			constraints = Lists.reverse(constraints);
		}

		Iterator<? extends JButton> bIter = buttons.iterator();
		Iterator<? extends Object> cIter = constraints.iterator();
		while (bIter.hasNext()) {
			container.add(bIter.next(), cIter.next());
		}
	}

	public static Icon getYesIcon() {
		return UIManager.getIcon("OptionPane.yesIcon"); //$NON-NLS-1$
	}

	public static Icon getNoIcon() {
		return UIManager.getIcon("OptionPane.noIcon"); //$NON-NLS-1$
	}

	public static Icon getOKIcon() {
		return UIManager.getIcon("OptionPane.okIcon"); //$NON-NLS-1$
	}

	public static Icon getCancelIcon() {
		return UIManager.getIcon("OptionPane.cancelIcon"); //$NON-NLS-1$
	}
}