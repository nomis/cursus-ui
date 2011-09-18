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

	public MainWindow(Main main) {
		super();
		this.main = main;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (trySaveDatabase("Exit")) {
					dispose();
				}
			}
		});

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		initGUI();
		databaseClosed();
	}

	private void initGUI() {
		setTitle(Constants.APP_NAME);

		mainTabs = new JTabbedPane();
		getContentPane().add(mainTabs, BorderLayout.CENTER);

		pilotsTab = new Canvas();
		mainTabs.addTab("Pilots", null, pilotsTab, null);
		pilotsTab.setName("pilotsTab");

		lapsTab = new Canvas();
		mainTabs.addTab("Laps", null, lapsTab, null);
		lapsTab.setName("lapsTab");

		resultsTab = new Canvas();
		mainTabs.addTab("Results", null, resultsTab, null);

		ListModel raceListModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
		raceList = new JList();
		raceList.setMinimumSize(new Dimension(200, 0));
		getContentPane().add(raceList, BorderLayout.WEST);
		raceList.setModel(raceListModel);

		setSize(800, 600);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu();
		menuBar.add(fileMenu);
		fileMenu.setText("File");

		newFileMenuItem = new JMenuItem();
		newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newDatabase();
			}
		});
		fileMenu.add(newFileMenuItem);
		newFileMenuItem.setText("New");

		openFileMenuItem = new JMenuItem();
		openFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDatabase();
			}
		});
		openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		fileMenu.add(openFileMenuItem);
		openFileMenuItem.setText("Open");

		saveMenuItem = new JMenuItem();
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDatabase();
			}
		});
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileMenu.add(saveMenuItem);
		saveMenuItem.setText("Save");

		saveAsMenuItem = new JMenuItem();
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsDatabase();
			}
		});
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		fileMenu.add(saveAsMenuItem);
		saveAsMenuItem.setText("Save As...");

		closeFileMenuItem = new JMenuItem();
		closeFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		closeFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDatabase();
			}
		});
		fileMenu.add(closeFileMenuItem);
		closeFileMenuItem.setText("Close");

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
		exitMenuItem.setText("Exit");

		helpMenu = new JMenu();
		menuBar.add(helpMenu);
		helpMenu.setText("Help");

		aboutMenuItem = new JMenuItem();
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.setText("About");
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
			switch (JOptionPane.showConfirmDialog(this,
					String.format("The current race series \"%1$s\" has not been saved.\nDo you want to save your changes?", main.getDatabase().getName()),
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
		if (trySaveDatabase("New")) {
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
				JOptionPane.showMessageDialog(this, "Unable to create new database.", Constants.APP_NAME, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private synchronized boolean openDatabase() {
		// TODO open database
		JOptionPane.showMessageDialog(this, "Feature not implemented.", Constants.APP_NAME + Constants.EN_DASH + "Open", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private synchronized boolean saveDatabase() {
		// TODO save database to current or new file
		JOptionPane.showMessageDialog(this, "Feature not implemented.", Constants.APP_NAME + Constants.EN_DASH + "Save", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private synchronized boolean saveAsDatabase() {
		// TODO save database to new file
		JOptionPane.showMessageDialog(this, "Feature not implemented.", Constants.APP_NAME + Constants.EN_DASH + "Save As", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	private synchronized void closeDatabase() {
		if (trySaveDatabase("Close")) {
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