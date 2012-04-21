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
package eu.lp0.cursus.test.ui;

import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.SimpleTimeLimiter;

import eu.lp0.cursus.app.Main;
import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.test.db.AbstractDataTest;
import eu.lp0.cursus.ui.MainWindow;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.menu.MainMenu;
import eu.lp0.cursus.ui.util.AccessibleComponents;
import eu.lp0.cursus.util.Background;

public class AbstractUITest extends AbstractDataTest {
	private static final int CALL_TIMEOUT = 15000;
	protected Main main;
	protected Accessible mainWindow;
	protected Accessible menuBar;
	protected Accessible raceTree;
	protected Accessible tabbedPane;
	protected SimpleTimeLimiter limit = new SimpleTimeLimiter();

	@Before
	public void startApplication() throws Exception {
		// Start the application
		main = new Main(new String[] {}) {
			@Override
			protected Database createEmptyDatabase() throws InvalidDatabaseException, SQLException {
				return AbstractUITest.this.createEmptyDatabase(super.createEmptyDatabase());
			}
		};
		executeWithTimeout(new Runnable() {
			@Override
			public void run() {
				main.setWindow(new MainWindow(main));
			}
		});

		// Open the default database
		Assert.assertTrue(executeWithTimeout(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return main.open();
			}
		}));

		// Obtain the main window, menu bar, race tree and tabbed pane... this is considered accessible?
		mainWindow = main.getWindow();
		Accessible rootPane = findAccessibleChildByType(mainWindow, JRootPane.class);
		Accessible layeredPane = findAccessibleChildByType(rootPane, JLayeredPane.class);
		menuBar = findAccessibleChildByType(layeredPane, MainMenu.class);
		Accessible contentPane = findAccessibleChildByType(layeredPane, JPanel.class);
		Accessible splitPane = findAccessibleChildByType(contentPane, JSplitPane.class);
		Accessible leftPane = findAccessibleChildByIndex(splitPane, 0);
		Accessible leftViewport = findAccessibleChildByType(leftPane, JViewport.class);
		raceTree = findAccessibleChild(leftViewport, AccessibleComponents.RACE_TREE);
		tabbedPane = findAccessibleChildByIndex(splitPane, 1);
	}

	protected Database createEmptyDatabase(Database database) throws InvalidDatabaseException, SQLException {
		return database;
	}

	@After
	public void closeApplication() throws Exception {
		executeWithTimeout(new Runnable() {
			@Override
			public void run() {
				main.close(true);
				((Window)mainWindow).dispose();
			}
		});
	}

	// @AfterClass
	// public static void shutdownExecutor() throws InterruptedException {
	// Background.shutdownAndWait(CALL_TIMEOUT, TimeUnit.MILLISECONDS);
	// }

	public void syncOnEventThread() throws Exception {
		runFromEventThread(new Runnable() {
			@Override
			public void run() {
			}
		});
	}

	public void syncOnBackgroundExecutor() throws Exception {
		executeWithTimeout(new Runnable() {
			@Override
			public void run() {
			}
		});
	}

	public void syncOnDatabaseRefresh() throws Exception {
		// Wait for the initiation of the database refresh to complete
		syncOnEventThread();

		// Wait for the database refresh to complete
		syncOnBackgroundExecutor();

		// Wait for the GUI update from the database refresh to complete
		syncOnEventThread();
	}

	public void syncOnDatabaseChange() throws Exception {
		// Wait for the change to complete
		syncOnBackgroundExecutor();

		syncOnDatabaseRefresh();
	}

	public void executeWithTimeout(Runnable run) throws Exception {
		final FutureTask<Void> task = new FutureTask<Void>(run, null);
		Background.execute(task);
		task.get(CALL_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public <V> V executeWithTimeout(Callable<V> call) throws Exception {
		final FutureTask<V> task = new FutureTask<V>(call);
		Background.execute(task);
		return task.get(CALL_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public <V> V callFromEventThread(final Callable<V> callable) throws Exception {
		final CountDownLatch latch = new CountDownLatch(1);
		final AtomicReference<V> value = new AtomicReference<V>();
		final AtomicReference<Throwable> error = new AtomicReference<Throwable>();

		Assert.assertFalse(SwingUtilities.isEventDispatchThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					value.set(callable.call());
				} catch (Throwable t) {
					error.set(t);
				} finally {
					latch.countDown();
				}
			}
		});

		latch.await(CALL_TIMEOUT, TimeUnit.MILLISECONDS);
		Throwable t = error.get();
		if (t != null) {
			Throwables.propagate(t);
		}
		return value.get();
	}

	public void runFromEventThread(final Runnable runnable) throws Exception {
		final CountDownLatch latch = new CountDownLatch(1);
		final AtomicReference<Throwable> error = new AtomicReference<Throwable>();

		Assert.assertFalse(SwingUtilities.isEventDispatchThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Throwable t) {
					error.set(t);
				} finally {
					latch.countDown();
				}
			}
		});

		latch.await(CALL_TIMEOUT, TimeUnit.MILLISECONDS);
		Throwable t = error.get();
		if (t != null) {
			throw new Exception(t);
		}
	}

	public RaceEntity getSelectedRaceEntity() throws Exception {
		return callFromEventThread(new Callable<RaceEntity>() {
			@Override
			public RaceEntity call() throws Exception {
				return ((DatabaseWindow)mainWindow).getSelected();
			}
		});
	}

	public static String accessibleToString(Accessible accessible) {
		AccessibleContext context = accessible.getAccessibleContext();

		StringBuilder sb = new StringBuilder();
		sb.append(accessible.getClass().getSimpleName());
		sb.append(" [").append(context.getAccessibleChildrenCount()).append("]"); //$NON-NLS-1$ //$NON-NLS-2$

		String name = context.getAccessibleName();
		if (name == null) {
			sb.append(" null"); //$NON-NLS-1$
		} else {
			sb.append(" \"").append(name).append("\""); //$NON-NLS-1$ //$NON-NLS-2$
		}

		String desc = context.getAccessibleDescription();
		if (desc == null) {
			sb.append(" (null)"); //$NON-NLS-1$
		} else {
			sb.append(" (\"").append(desc).append("\")"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return sb.toString();
	}

	private Accessible findAccessibleChildFromEventThread(Accessible parent, String name, Predicate<Accessible> predicate) {
		Assert.assertTrue(SwingUtilities.isEventDispatchThread());

		AccessibleContext context = parent.getAccessibleContext();
		List<Accessible> found = new ArrayList<Accessible>();
		log.debug("Accessible " + accessibleToString(parent)); //$NON-NLS-1$ 
		for (int i = 0; i < context.getAccessibleChildrenCount(); i++) {
			Accessible child = context.getAccessibleChild(i);
			log.debug(" Child " + i + ": " + accessibleToString(child)); //$NON-NLS-1$ //$NON-NLS-2$ 
			if (predicate.apply(child)) {
				found.add(child);
			}
		}
		if (found.isEmpty()) {
			log.error("Cannot find " + name + " in " + accessibleToString(parent));  //$NON-NLS-1$//$NON-NLS-2$ 
			Assert.fail("Cannot find " + name + " in " + accessibleToString(parent));  //$NON-NLS-1$//$NON-NLS-2$ 
			return null;
		} else {
			for (int i = 0; i < found.size(); i++) {
				log.debug("Found " + i + ": " + accessibleToString(found.get(i))); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (found.size() > 1) {
				log.error("Found more than one matching child (" + found.size() + ") for " + name); //$NON-NLS-1$ //$NON-NLS-2$
				Assert.fail("Found more than one matching child (" + found.size() + ") for " + name); //$NON-NLS-1$ //$NON-NLS-2$
				return null;
			}
		}
		return found.get(0);
	}

	private Accessible findAccessibleChild(final Accessible parent, final String name, final Predicate<Accessible> predicate) throws Exception {
		if (SwingUtilities.isEventDispatchThread()) {
			return findAccessibleChildFromEventThread(parent, name, predicate);
		} else {
			return callFromEventThread(new Callable<Accessible>() {
				@Override
				public Accessible call() throws Exception {
					return findAccessibleChildFromEventThread(parent, name, predicate);
				}
			});
		}
	}

	public Accessible findAccessibleChild(Accessible parent, String key) throws Exception {
		return findAccessibleChildByName(parent, Messages.getAccessibleName(key));
	}

	public Accessible findAccessibleChild(Accessible parent, String key, Object... args) throws Exception {
		return findAccessibleChildByName(parent, Messages.getAccessibleName(key, args));
	}

	public Accessible findAccessibleChildByName(Accessible parent, final String name) throws Exception {
		return findAccessibleChild(parent, "\"" + name + "\"", new Predicate<Accessible>() { //$NON-NLS-1$ //$NON-NLS-2$
					@Override
					public boolean apply(Accessible input) {
						return Objects.equal(input.getAccessibleContext().getAccessibleName(), name);
					}
				});
	}

	public Accessible findAccessibleChildByIndex(Accessible parent, final int index) throws Exception {
		return findAccessibleChild(parent, String.valueOf(index), new Predicate<Accessible>() {
			@Override
			public boolean apply(Accessible input) {
				return Objects.equal(input.getAccessibleContext().getAccessibleIndexInParent(), index);
			}
		});
	}

	public Accessible findAccessibleChildByType(Accessible parent, final Class<? extends Accessible> type) throws Exception {
		return findAccessibleChild(parent, type.getSimpleName(), new Predicate<Accessible>() {
			@Override
			public boolean apply(Accessible input) {
				return type.isAssignableFrom(input.getClass());
			}
		});
	}

	public void accessibleSelect(final Accessible child, final boolean select) throws Exception {
		callFromEventThread(new Callable<Void>() {
			@Override
			public Void call() {
				Accessible parent = child.getAccessibleContext().getAccessibleParent();
				AccessibleSelection selection = parent.getAccessibleContext().getAccessibleSelection();
				log.info("Selecting " + accessibleToString(child) //$NON-NLS-1$ 
						+ " in " + accessibleToString(parent)); //$NON-NLS-1$ 

				int index = child.getAccessibleContext().getAccessibleIndexInParent();

				boolean selected = selection.isAccessibleChildSelected(index);
				log.debug(" Before: " + selected); //$NON-NLS-1$

				if (select) {
					selection.addAccessibleSelection(index);
				} else {
					selection.removeAccessibleSelection(index);
				}

				selected = selection.isAccessibleChildSelected(index);
				log.debug(" After: " + selected); //$NON-NLS-1$

				Assert.assertEquals(select, selected);
				return null;
			}
		});
	}

	public boolean accessibleAction(final Accessible accessible, final int index) throws Exception {
		return callFromEventThread(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				log.debug("Accessible " + accessibleToString(accessible)); //$NON-NLS-1$ 
				AccessibleAction action = accessible.getAccessibleContext().getAccessibleAction();
				for (int i = 0; i < action.getAccessibleActionCount(); i++) {
					log.debug(" Action " + i + ": \"" + action.getAccessibleActionDescription(i) + "\"");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				log.info("Performing " + index + ":" //$NON-NLS-1$ //$NON-NLS-2$
						+ " \"" + action.getAccessibleActionDescription(index) + "\"" //$NON-NLS-1$ //$NON-NLS-2$ 
						+ " on " + accessibleToString(accessible)); //$NON-NLS-1$ 
				return action.doAccessibleAction(index);
			}
		});
	}
}