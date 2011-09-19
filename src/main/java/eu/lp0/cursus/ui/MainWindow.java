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
package eu.lp0.cursus.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import eu.lp0.cursus.app.Main;
import eu.lp0.cursus.db.DatabaseVersionException;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class MainWindow extends JFrame {
	private final Executor background = Executors.newSingleThreadExecutor();
	private final Main main;

	private JMenuBar menuBar;
	private JMenu mnuFile;
	private JMenuItem mnuFileNew;
	private JMenuItem mnuFileOpen;
	private JMenuItem mnuFileSave;
	private JMenuItem mnuFileSaveAs;
	private JMenuItem mnuFileClose;
	private JSeparator mnuFileSeparator1;
	private JMenuItem mnuFileExit;
	private JMenu mnuHelp;
	private JMenuItem mnuHelpAbout;

	private JTabbedPane mainTabs;
	private JPanel pilotsTab;
	private JPanel classesTab;
	private JPanel lapsTab;
	private JPanel resultsTab;

	private JSplitPane lapsSplitPane;
	private JList raceList;
	private ListModel raceListModel;
	private JPanel raceLaps;

	public MainWindow(Main main, final String[] args) {
		super();
		this.main = main;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						MainWindow.this.startup(args);
					}
				});
			}

			@Override
			public void windowClosing(WindowEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						shutdown();
					}
				});
			}
		});

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		initGUI();
		databaseClosed();
	}

	private void startup(String[] args) {
		if (args.length == 0) {
			newDatabase();
		} else if (args.length == 1) {
			// TODO open file
		} else {
			// TODO error message
		}
	}

	private void shutdown() {
		if (trySaveDatabase(Messages.getString("menu.file.exit"))) { //$NON-NLS-1$
			dispose();
		}
	}

	private void initGUI() {
		setTitle(Constants.APP_NAME);

		mainTabs = new JTabbedPane();
		getContentPane().add(mainTabs, BorderLayout.CENTER);

		pilotsTab = new JPanel();
		mainTabs.addTab(Messages.getString("tab.pilots"), null, pilotsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(0, Messages.getKeyEvent("tab.pilots")); //$NON-NLS-1$

		classesTab = new JPanel();
		mainTabs.addTab(Messages.getString("tab.classes"), null, classesTab, null); //$NON-NLS-1$
		classesTab.setLayout(new BorderLayout(0, 0));

		lapsSplitPane = new JSplitPane();
		classesTab.add(lapsSplitPane);
		raceList = new JList();
		lapsSplitPane.setLeftComponent(raceList);
		raceList.setMinimumSize(new Dimension(150, 0));
		raceListModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" }); //$NON-NLS-1$ //$NON-NLS-2$
		raceList.setModel(raceListModel);

		raceLaps = new JPanel();
		lapsSplitPane.setRightComponent(raceLaps);
		mainTabs.setMnemonicAt(1, Messages.getKeyEvent("tab.classes")); //$NON-NLS-1$

		lapsTab = new JPanel();
		mainTabs.addTab(Messages.getString("tab.laps"), null, lapsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(2, Messages.getKeyEvent("tab.laps")); //$NON-NLS-1$

		resultsTab = new JPanel();
		mainTabs.addTab(Messages.getString("tab.results"), null, resultsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(3, Messages.getKeyEvent("tab.results")); //$NON-NLS-1$

		setSize(800, 600);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnuFile = new JMenu();
		menuBar.add(mnuFile);
		mnuFile.setText(Messages.getString("menu.file")); //$NON-NLS-1$
		mnuFile.setMnemonic(Messages.getKeyEvent("menu.file")); //$NON-NLS-1$

		mnuFileNew = new JMenuItem();
		mnuFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnuFileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						newDatabase();
					}
				});
			}
		});
		mnuFile.add(mnuFileNew);
		mnuFileNew.setText(Messages.getString("menu.file.new")); //$NON-NLS-1$
		mnuFileNew.setMnemonic(Messages.getKeyEvent("menu.file.new")); //$NON-NLS-1$

		mnuFileOpen = new JMenuItem();
		mnuFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						openDatabase();
					}
				});
			}
		});
		mnuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnuFile.add(mnuFileOpen);
		mnuFileOpen.setText(Messages.getString("menu.file.open")); //$NON-NLS-1$
		mnuFileOpen.setMnemonic(Messages.getKeyEvent("menu.file.open")); //$NON-NLS-1$

		mnuFileSave = new JMenuItem();
		mnuFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						saveDatabase();
					}
				});
			}
		});
		mnuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnuFile.add(mnuFileSave);
		mnuFileSave.setText(Messages.getString("menu.file.save")); //$NON-NLS-1$
		mnuFileSave.setMnemonic(Messages.getKeyEvent("menu.file.save")); //$NON-NLS-1$

		mnuFileSaveAs = new JMenuItem();
		mnuFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						saveAsDatabase();
					}
				});
			}
		});
		mnuFileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnuFile.add(mnuFileSaveAs);
		mnuFileSaveAs.setText(Messages.getString("menu.file.save-as")); //$NON-NLS-1$
		mnuFileSaveAs.setMnemonic(Messages.getKeyEvent("menu.file.save-as")); //$NON-NLS-1$

		mnuFileClose = new JMenuItem();
		mnuFileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mnuFileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				background.execute(new Runnable() {
					@Override
					public void run() {
						closeDatabase();
					}
				});
			}
		});
		mnuFile.add(mnuFileClose);
		mnuFileClose.setText(Messages.getString("menu.file.close")); //$NON-NLS-1$
		mnuFileClose.setMnemonic(Messages.getKeyEvent("menu.file.close")); //$NON-NLS-1$

		mnuFileSeparator1 = new JSeparator();
		mnuFile.add(mnuFileSeparator1);

		mnuFileExit = new JMenuItem();
		mnuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowEvent wev = new WindowEvent(MainWindow.this, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			}
		});
		mnuFile.add(mnuFileExit);
		mnuFileExit.setText(Messages.getString("menu.file.exit")); //$NON-NLS-1$
		mnuFileExit.setMnemonic(Messages.getKeyEvent("menu.file.exit")); //$NON-NLS-1$

		mnuHelp = new JMenu();
		menuBar.add(mnuHelp);
		mnuHelp.setText(Messages.getString("menu.help")); //$NON-NLS-1$
		mnuHelp.setMnemonic(Messages.getKeyEvent("menu.help")); //$NON-NLS-1$

		mnuHelpAbout = new JMenuItem();
		mnuHelp.add(mnuHelpAbout);
		mnuHelpAbout.setText(Messages.getString("menu.help.about")); //$NON-NLS-1$
		mnuHelpAbout.setMnemonic(Messages.getKeyEvent("menu.help.about")); //$NON-NLS-1$
	}

	/**
	 * Try and save the database if one is open
	 * 
	 * @param action
	 *            text name of the action being performed
	 * @return true if the database was saved or discarded
	 */
	private boolean trySaveDatabase(String action) {
		if (main.isOpen() && !main.getDatabase().isSaved()) {
			switch (JOptionPane.showConfirmDialog(this, String.format(Messages.getString("warn.current-db-not-saved"), main.getDatabase().getName()), //$NON-NLS-1$
					Constants.APP_NAME + Constants.EN_DASH + action, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {
			case JOptionPane.YES_OPTION:
				if (saveDatabase()) {
					return main.close();
				}
			case JOptionPane.NO_OPTION:
				main.close(true);
				break;
			case JOptionPane.CANCEL_OPTION:
				return false;
			}
		}
		return true;
	}

	private void newDatabase() {
		if (trySaveDatabase(Messages.getString("menu.file.new"))) { //$NON-NLS-1$
			boolean ok = true;
			try {
				ok = main.open();
			} catch (SQLException e) {
				// TODO log
				ok = false;
			} catch (DatabaseVersionException e) {
				// TODO log
				ok = false;
			}
			if (!ok) {
				JOptionPane.showMessageDialog(this, Messages.getString("err.unable-to-create-new-db"), Constants.APP_NAME, JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
	}

	private boolean openDatabase() {
		// TODO open database
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.open"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private boolean saveDatabase() {
		// TODO save database to current or new file
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.save"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private boolean saveAsDatabase() {
		// TODO save database to new file
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.save-as"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private void closeDatabase() {
		if (trySaveDatabase(Messages.getString("menu.file.close"))) { //$NON-NLS-1$
			main.close();
		}
	}

	public void databaseOpened() {
		setTitle(Constants.APP_NAME + Constants.EN_DASH + main.getDatabase().getName());
		syncGUI(true);
	}

	public void databaseClosed() {
		syncGUI(false);
		setTitle(Constants.APP_DESC);
	}

	private void syncGUI(boolean open) {
		mainTabs.setVisible(open);
		mnuFileSave.setEnabled(open);
		mnuFileSaveAs.setEnabled(open);
		mnuFileClose.setEnabled(open);
		getRootPane().validate();
	}
}