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

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AutoPrefsWindow extends JFrame {
	private final String PREF_WIDTH = getClass().getSimpleName() + "/width"; //$NON-NLS-1$
	private final String PREF_HEIGHT = getClass().getSimpleName() + "/height"; //$NON-NLS-1$
	private final String PREF_STATE = getClass().getSimpleName() + "/state"; //$NON-NLS-1$
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final Preferences pref = Preferences.userNodeForPackage(getClass());
	private final ExecutorService delayed = Executors.newSingleThreadExecutor();

	private AtomicBoolean loaded = new AtomicBoolean(false);
	private AtomicBoolean saved = new AtomicBoolean(true);

	public AutoPrefsWindow() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				delayedSaveWindowPreference();
			}
		});

		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent we) {
				delayedSaveWindowPreference();
			}
		});
	}

	@Override
	public void dispose() {
		try {
			super.dispose();
		} finally {
			delayed.shutdownNow();
		}
	}

	public void display() {
		setLocationRelativeTo(null);
		loadSizePreference();
		setVisible(true);
		loadStatePreference();
		loaded.set(true);
	}

	private void loadSizePreference() {
		int width = pref.getInt(PREF_WIDTH, 0);
		int height = pref.getInt(PREF_HEIGHT, 0);
		if (width > 0 && height > 0) {
			setSize(width, height);
			if (log.isTraceEnabled()) {
				log.trace("Loaded size preference " + width + "x" + height); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	private void loadStatePreference() {
		setExtendedState((getExtendedState() & ~Frame.MAXIMIZED_BOTH) | (pref.getInt(PREF_STATE, Frame.NORMAL) & Frame.MAXIMIZED_BOTH));
		if (log.isTraceEnabled()) {
			log.trace("Loaded state preference " + getExtendedState()); //$NON-NLS-1$
		}
	}

	private void delayedSaveWindowPreference() {
		if (saved.getAndSet(false)) {
			if (log.isTraceEnabled()) {
				log.trace("Scheduling preferences save"); //$NON-NLS-1$
			}
			delayed.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						return;
					}

					saveWindowPreference();
				}
			});
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Preference save already scheduled"); //$NON-NLS-1$
			}
		}
	}

	private void saveWindowPreference() {
		saved.getAndSet(true);

		if (!loaded.get()) {
			return;
		}

		Dimension size;
		int state;
		synchronized (getTreeLock()) {
			synchronized (this) {
				size = getSize();
				state = getExtendedState();
			}
		}

		if (state == Frame.NORMAL || (state & Frame.MAXIMIZED_HORIZ) == 0) {
			pref.putInt(PREF_WIDTH, size.width);
			log.trace("Saved width preference " + size.width); //$NON-NLS-1$
		}
		if (state == Frame.NORMAL || (state & Frame.MAXIMIZED_VERT) == 0) {
			pref.putInt(PREF_HEIGHT, size.height);
			log.trace("Saved height preference " + size.height); //$NON-NLS-1$
		}

		pref.putInt(PREF_STATE, getExtendedState() & Frame.MAXIMIZED_BOTH);
		log.trace("Saved state preference " + getExtendedState()); //$NON-NLS-1$
	}
}