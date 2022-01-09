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
package uk.uuid.cursus.ui.preferences;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class JFrameAutoPrefs extends WindowAutoPrefs {
	private final JFrame frame;
	private final String prefState;

	public JFrameAutoPrefs(JFrame frame) {
		super(frame);
		this.frame = frame;
		prefState = makePrefName("/state"); //$NON-NLS-1$

		frame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent we) {
				delayedSaveWindowPreference();
			}
		});
	}

	@Override
	public void postVisible() {
		loadStatePreference();
	}

	private void loadStatePreference() {
		int state = pref.getInt(prefState, -1);

		if (state != -1) {
			state &= Frame.MAXIMIZED_BOTH;
			frame.setExtendedState((frame.getExtendedState() & ~Frame.MAXIMIZED_BOTH) | state);
			log.trace("Loaded state preference {}", state); //$NON-NLS-1$
		}
	}

	@Override
	protected void savePreferences() {
		Dimension size;
		int state;

		synchronized (frame.getTreeLock()) {
			synchronized (frame) {
				size = frame.getSize();
				state = frame.getExtendedState();
			}
		}

		if (state == Frame.NORMAL || (state & Frame.MAXIMIZED_HORIZ) == 0) {
			saveWidth(size.width);
		}

		if (state == Frame.NORMAL || (state & Frame.MAXIMIZED_VERT) == 0) {
			saveHeight(size.height);
		}

		state &= Frame.MAXIMIZED_BOTH;
		pref.putInt(prefState, state);
		log.trace("Saved state preference " + state); //$NON-NLS-1$
	}
}