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
package eu.lp0.cursus.ui.menu;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.google.common.eventbus.Subscribe;

import eu.lp0.cursus.i18n.LanguageManager;
import eu.lp0.cursus.i18n.LocaleChangeEvent;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.i18n.SupportedLanguages;
import eu.lp0.cursus.ui.AboutDialog;
import eu.lp0.cursus.ui.DatabaseManager;

public class MainMenu extends JMenuBar {
	private final Frame win;
	private final DatabaseManager dbMgr;

	private JMenu mnuFile;
	private JMenuItem mnuFileNew;
	private JMenuItem mnuFileOpen;
	private JMenuItem mnuFileSave;
	private JMenuItem mnuFileSaveAs;
	private JMenuItem mnuFileClose;
	private JSeparator mnuFileSeparator1;
	private JMenuItem mnuFileExit;
	private JMenu mnuLang;
	private JRadioButtonMenuItem mnuLangDefault;
	private JSeparator mnuLangSeparator1;
	private Map<SupportedLanguages, JRadioButtonMenuItem> mnuLangItems = new EnumMap<SupportedLanguages, JRadioButtonMenuItem>(SupportedLanguages.class);
	private ButtonGroup mnuLangGroup;
	private JMenu mnuHelp;
	private JMenuItem mnuHelpAbout;

	public MainMenu(Frame win, DatabaseManager dbMgr) {
		this.win = win;
		this.dbMgr = dbMgr;

		initialise();
		LanguageManager.register(this, true);
	}

	@Subscribe
	public final void updateLocale(LocaleChangeEvent lce) {
		mnuFile.setText(Messages.getString("menu.file")); //$NON-NLS-1$
		mnuFile.setMnemonic(Messages.getKeyEvent("menu.file")); //$NON-NLS-1$

		mnuFileNew.setText(Messages.getString("menu.file.new")); //$NON-NLS-1$
		mnuFileNew.setMnemonic(Messages.getKeyEvent("menu.file.new")); //$NON-NLS-1$

		mnuFileOpen.setText(Messages.getString("menu.file.open")); //$NON-NLS-1$
		mnuFileOpen.setMnemonic(Messages.getKeyEvent("menu.file.open")); //$NON-NLS-1$

		mnuFileSave.setText(Messages.getString("menu.file.save")); //$NON-NLS-1$
		mnuFileSave.setMnemonic(Messages.getKeyEvent("menu.file.save")); //$NON-NLS-1$

		mnuFileSaveAs.setText(Messages.getString("menu.file.save-as")); //$NON-NLS-1$
		mnuFileSaveAs.setMnemonic(Messages.getKeyEvent("menu.file.save-as")); //$NON-NLS-1$

		mnuFileClose.setText(Messages.getString("menu.file.close")); //$NON-NLS-1$
		mnuFileClose.setMnemonic(Messages.getKeyEvent("menu.file.close")); //$NON-NLS-1$

		mnuFileExit.setText(Messages.getString("menu.file.exit")); //$NON-NLS-1$
		mnuFileExit.setMnemonic(Messages.getKeyEvent("menu.file.exit")); //$NON-NLS-1$

		mnuLang.setText(Messages.getString("menu.lang")); //$NON-NLS-1$
		mnuLang.setMnemonic(Messages.getKeyEvent("menu.lang")); //$NON-NLS-1$

		mnuLangDefault.setText(Messages.getString("menu.lang.default")); //$NON-NLS-1$
		mnuLangDefault.setMnemonic(Messages.getKeyEvent("menu.lang.default")); //$NON-NLS-1$
		if (lce.getNewLocale().equals(Locale.ROOT)) {
			mnuLangGroup.setSelected(mnuLangDefault.getModel(), true);
		}

		for (SupportedLanguages locale : SupportedLanguages.values()) {
			JRadioButtonMenuItem item = mnuLangItems.get(locale);
			item.setText(locale.getLocale().getDisplayName(lce.getNewLocale()));
			if (lce.getNewLocale().equals(locale.getLocale())) {
				mnuLangGroup.setSelected(item.getModel(), true);
			}
		}

		mnuHelp.setText(Messages.getString("menu.help")); //$NON-NLS-1$
		mnuHelp.setMnemonic(Messages.getKeyEvent("menu.help")); //$NON-NLS-1$

		mnuHelpAbout.setText(Messages.getString("menu.help.about")); //$NON-NLS-1$
		mnuHelpAbout.setMnemonic(Messages.getKeyEvent("menu.help.about")); //$NON-NLS-1$
	}

	private void initialise() {
		mnuFile = new JMenu();
		add(mnuFile);

		mnuFileNew = new JMenuItem();
		mnuFile.add(mnuFileNew);
		mnuFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

		mnuFileNew.setActionCommand(DatabaseManager.Commands.NEW.toString());
		mnuFileNew.addActionListener(dbMgr);
		mnuFileNew.setEnabled(false);

		mnuFileOpen = new JMenuItem();
		mnuFile.add(mnuFileOpen);
		mnuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

		mnuFileOpen.setActionCommand(DatabaseManager.Commands.OPEN.toString());
		mnuFileOpen.addActionListener(dbMgr);
		mnuFileOpen.setEnabled(false);

		mnuFileSave = new JMenuItem();
		mnuFile.add(mnuFileSave);
		mnuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnuFileSave.setActionCommand(DatabaseManager.Commands.SAVE.toString());
		mnuFileSave.addActionListener(dbMgr);
		mnuFileSave.setEnabled(false);

		mnuFileSaveAs = new JMenuItem();
		mnuFile.add(mnuFileSaveAs);
		mnuFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));

		mnuFileSaveAs.setActionCommand(DatabaseManager.Commands.SAVE_AS.toString());
		mnuFileSaveAs.addActionListener(dbMgr);
		mnuFileSaveAs.setEnabled(false);

		mnuFileClose = new JMenuItem();
		mnuFile.add(mnuFileClose);
		mnuFileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mnuFileClose.setActionCommand(DatabaseManager.Commands.CLOSE.toString());
		mnuFileClose.addActionListener(dbMgr);
		mnuFileClose.setEnabled(false);

		mnuFileSeparator1 = new JSeparator();
		mnuFile.add(mnuFileSeparator1);

		mnuFileExit = new JMenuItem();
		mnuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				WindowEvent wev = new WindowEvent(win, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			}
		});
		mnuFile.add(mnuFileExit);

		mnuLang = new JMenu();
		add(mnuLang);

		mnuLangGroup = new ButtonGroup();
		mnuLangDefault = new JRadioButtonMenuItem();
		mnuLangGroup.add(mnuLangDefault);
		mnuLang.add(mnuLangDefault);
		mnuLangDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Messages.changeLocale(Locale.ROOT);
			}
		});

		mnuLangSeparator1 = new JSeparator();
		mnuLang.add(mnuLangSeparator1);

		for (final SupportedLanguages locale : SupportedLanguages.values()) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem();
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					Messages.changeLocale(locale.getLocale());
				}
			});
			mnuLangItems.put(locale, item);
			mnuLangGroup.add(item);
			mnuLang.add(mnuLangItems.get(locale));
		}

		mnuHelp = new JMenu();
		add(mnuHelp);

		mnuHelpAbout = new JMenuItem();
		mnuHelp.add(mnuHelpAbout);

		mnuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new AboutDialog(win).display();
			}
		});
	}

	public void enableOpen(boolean enabled) {
		assert (SwingUtilities.isEventDispatchThread());

		mnuFileNew.setEnabled(enabled);
		mnuFileOpen.setEnabled(enabled);
	}

	public void sync(boolean open) {
		assert (SwingUtilities.isEventDispatchThread());

		enableOpen(true);
		mnuFileSave.setEnabled(open);
		mnuFileSaveAs.setEnabled(open);
		mnuFileClose.setEnabled(open);
	}
}