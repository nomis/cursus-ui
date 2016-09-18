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
package uk.uuid.lp0.cursus.ui.actions;

import javax.swing.AbstractAction;
import javax.swing.Action;

import uk.uuid.lp0.cursus.i18n.LanguageManager;
import uk.uuid.lp0.cursus.i18n.LocaleChangeEvent;
import uk.uuid.lp0.cursus.i18n.Messages;

import com.google.common.eventbus.Subscribe;

public abstract class AbstractTranslatedAction extends AbstractAction {
	protected final String messagesKey;
	protected final boolean hasMnemonic;

	public AbstractTranslatedAction(String messagesKey, boolean hasMnemonic) {
		this.messagesKey = messagesKey;
		this.hasMnemonic = hasMnemonic;
		LanguageManager.register(this, true);
	}

	@Subscribe
	public final void updateLanguage(LocaleChangeEvent lce) {
		putValue(Action.NAME, Messages.getString(messagesKey));
		if (hasMnemonic) {
			putValue(Action.MNEMONIC_KEY, Messages.getKeyEvent(messagesKey));
		}
	}
}