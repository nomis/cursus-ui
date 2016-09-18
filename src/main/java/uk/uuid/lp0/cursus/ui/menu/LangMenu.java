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
package uk.uuid.lp0.cursus.ui.menu;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import uk.uuid.lp0.cursus.i18n.LanguageManager;
import uk.uuid.lp0.cursus.i18n.LocaleChangeEvent;
import uk.uuid.lp0.cursus.i18n.Messages;
import uk.uuid.lp0.cursus.i18n.SupportedLanguages;
import uk.uuid.lp0.cursus.ui.actions.SetDefaultLanguageAction;
import uk.uuid.lp0.cursus.ui.actions.SetLanguageAction;

import com.google.common.eventbus.Subscribe;

public class LangMenu extends JMenu {
	private static final String messagesKey = "menu.lang"; //$NON-NLS-1$
	private final Map<Locale, JRadioButtonMenuItem> mnuLangItems = new HashMap<Locale, JRadioButtonMenuItem>();
	private final ButtonGroup mnuLangGroup;

	public LangMenu() {
		mnuLangGroup = new ButtonGroup();
		JRadioButtonMenuItem mnuLangDefault = new JRadioButtonMenuItem(new SetDefaultLanguageAction());
		add(mnuLangDefault);
		mnuLangItems.put(Locale.ROOT, mnuLangDefault);
		mnuLangGroup.add(mnuLangDefault);
		addSeparator();

		for (final SupportedLanguages locale : SupportedLanguages.values()) {
			JRadioButtonMenuItem mnuLangItem = new JRadioButtonMenuItem(new SetLanguageAction(locale.getLocale()));
			mnuLangItems.put(locale.getLocale(), mnuLangItem);
			mnuLangGroup.add(mnuLangItem);
			add(mnuLangItem);
		}

		LanguageManager.register(this, true);
	}

	@Subscribe
	public final void updateLanguage(LocaleChangeEvent lce) {
		setText(Messages.getString(messagesKey));
		setMnemonic(Messages.getKeyEvent(messagesKey));
	}

	@Subscribe
	public final void selectedLanguage(LocaleChangeEvent lce) {
		JRadioButtonMenuItem mnuLangItem = mnuLangItems.get(lce.getSelectedLocale());
		if (mnuLangItem != null) {
			mnuLangGroup.setSelected(mnuLangItem.getModel(), true);
		} else {
			mnuLangGroup.clearSelection();
		}
	}
}