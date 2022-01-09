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
package uk.uuid.cursus.ui.actions;

import java.awt.event.ActionEvent;
import java.util.Locale;

import uk.uuid.cursus.i18n.LanguageManager;

public class SetDefaultLanguageAction extends AbstractTranslatedAction {
	public SetDefaultLanguageAction() {
		super("menu.lang.default", true); //$NON-NLS-1$
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		LanguageManager.setPreferredLocale(Locale.ROOT);
	}
}