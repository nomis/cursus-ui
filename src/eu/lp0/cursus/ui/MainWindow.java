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
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import eu.lp0.cursus.app.Main;
import eu.lp0.cursus.db.DatabaseVersionException;
import eu.lp0.cursus.util.Constants;

public class MainWindow extends JFrame {
	private final Main main;

	private JMenuItem aboutMenuItem;
	private Canvas pilotsTab;
	private JTabbedPane mainTabs;
	private JMenu helpMenu;
	private Canvas lapsTab;
	private Canvas resultsTab;
	private JList raceList;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu fileMenu;
	private JMenuBar menuBar;

	public MainWindow(Main main, final String[] args) {
		super();
		this.main = main;

		addWindowListener(new WindowAdapter() {
			// FIXME this is called too early; before the window components get rendered
			@Override
			public void windowOpened(WindowEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						MainWindow.this.startup(args);
					}
				});
			}

			@Override
			public void windowClosing(WindowEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
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

	private synchronized void startup(String[] args) {
		if (args.length == 0) {
			newDatabase();
		} else if (args.length == 1) {
			// TODO open file
		} else {
			// TODO error message
		}
	}

	private synchronized void shutdown() {
		if (trySaveDatabase(Messages.getString("menu.file.exit"))) { //$NON-NLS-1$
			dispose();
		}
	}

	private void initGUI() {
		setTitle(Constants.APP_NAME);

		mainTabs = new JTabbedPane();
		getContentPane().add(mainTabs, BorderLayout.CENTER);

		pilotsTab = new Canvas();
		mainTabs.addTab(Messages.getString("tab.pilots"), null, pilotsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(0, Messages.getKeyEvent("tab.pilots")); //$NON-NLS-1$

		lapsTab = new Canvas();
		mainTabs.addTab(Messages.getString("tab.laps"), null, lapsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(1, Messages.getKeyEvent("tab.laps")); //$NON-NLS-1$

		resultsTab = new Canvas();
		mainTabs.addTab(Messages.getString("tab.results"), null, resultsTab, null); //$NON-NLS-1$
		mainTabs.setMnemonicAt(2, Messages.getKeyEvent("tab.results")); //$NON-NLS-1$

		ListModel raceListModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" }); //$NON-NLS-1$ //$NON-NLS-2$
		raceList = new JList();
		raceList.setMinimumSize(new Dimension(200, 0));
		getContentPane().add(raceList, BorderLayout.WEST);
		raceList.setModel(raceListModel);

		setSize(800, 600);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu();
		menuBar.add(fileMenu);
		fileMenu.setText(Messages.getString("menu.file")); //$NON-NLS-1$
		fileMenu.setMnemonic(Messages.getKeyEvent("menu.file")); //$NON-NLS-1$

		newFileMenuItem = new JMenuItem();
		newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						newDatabase();
					}
				});
			}
		});
		fileMenu.add(newFileMenuItem);
		newFileMenuItem.setText(Messages.getString("menu.file.new")); //$NON-NLS-1$
		newFileMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.new")); //$NON-NLS-1$

		openFileMenuItem = new JMenuItem();
		openFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						openDatabase();
					}
				});
			}
		});
		openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		fileMenu.add(openFileMenuItem);
		openFileMenuItem.setText(Messages.getString("menu.file.open")); //$NON-NLS-1$
		openFileMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.open")); //$NON-NLS-1$

		saveMenuItem = new JMenuItem();
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						saveDatabase();
					}
				});
			}
		});
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		saveMenuItem.setText(Messages.getString("menu.file.save")); //$NON-NLS-1$
		saveMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.save")); //$NON-NLS-1$

		saveAsMenuItem = new JMenuItem();
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						saveAsDatabase();
					}
				});
			}
		});
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		fileMenu.add(saveAsMenuItem);
		saveAsMenuItem.setText(Messages.getString("menu.file.save-as")); //$NON-NLS-1$
		saveAsMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.save-as")); //$NON-NLS-1$

		closeFileMenuItem = new JMenuItem();
		closeFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		closeFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						closeDatabase();
					}
				});
			}
		});
		fileMenu.add(closeFileMenuItem);
		closeFileMenuItem.setText(Messages.getString("menu.file.close")); //$NON-NLS-1$
		closeFileMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.close")); //$NON-NLS-1$

		jSeparator2 = new JSeparator();
		fileMenu.add(jSeparator2);

		exitMenuItem = new JMenuItem();
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowEvent wev = new WindowEvent(MainWindow.this, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			}
		});
		fileMenu.add(exitMenuItem);
		exitMenuItem.setText(Messages.getString("menu.file.exit")); //$NON-NLS-1$
		exitMenuItem.setMnemonic(Messages.getKeyEvent("menu.file.exit")); //$NON-NLS-1$

		helpMenu = new JMenu();
		menuBar.add(helpMenu);
		helpMenu.setText(Messages.getString("menu.help")); //$NON-NLS-1$
		helpMenu.setMnemonic(Messages.getKeyEvent("menu.help")); //$NON-NLS-1$

		aboutMenuItem = new JMenuItem();
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.setText(Messages.getString("menu.help.about")); //$NON-NLS-1$
		aboutMenuItem.setMnemonic(Messages.getKeyEvent("menu.help.about")); //$NON-NLS-1$
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

	private synchronized void newDatabase() {
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

	private synchronized boolean openDatabase() {
		// TODO open database
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.open"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private synchronized boolean saveDatabase() {
		// TODO save database to current or new file
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.save"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private synchronized boolean saveAsDatabase() {
		// TODO save database to new file
		JOptionPane.showMessageDialog(this, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.save-as"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		return false;
	}

	private synchronized void closeDatabase() {
		if (trySaveDatabase(Messages.getString("menu.file.close"))) { //$NON-NLS-1$
			main.close();
		}
	}

	public synchronized void databaseOpened() {
		setTitle(Constants.APP_NAME + Constants.EN_DASH + main.getDatabase().getName());
		raceList.setVisible(true);
		mainTabs.setVisible(true);
		getRootPane().validate();
	}

	public synchronized void databaseClosed() {
		raceList.setVisible(false);
		mainTabs.setVisible(false);
		getRootPane().validate();
		setTitle(Constants.APP_DESC);
	}
}