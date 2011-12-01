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
package eu.lp0.cursus.i18n;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

public abstract class WeakLanguageChangeListener<T> {
	private static final Logger log = LoggerFactory.getLogger(WeakLanguageChangeListener.class);
	private static final ReferenceQueue<? super Object> queue = new ReferenceQueue<Object>();

	static {
		Thread cleanup = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						WeakCleanupReference<?> ref = (WeakCleanupReference<?>)queue.remove();
						try {
							log.trace("Cleanup of " + ref); //$NON-NLS-1$
							ref.cleanup();
						} catch (Throwable t) {
							log.error("Error during cleanup of weak reference", t); //$NON-NLS-1$
						}
					} catch (InterruptedException e) {
						log.warn("Interrupted", e); //$NON-NLS-1$
					}
				}
			}
		}, "WeakLanguageCleanup"); //$NON-NLS-1$
		cleanup.setDaemon(true);
		cleanup.start();
	}

	private final WeakCleanupReference<T> weakObject;

	public WeakLanguageChangeListener(T object) {
		this.weakObject = new WeakCleanupReference<T>(object);
		LanguageManager.register(this, true);
	}

	@Subscribe
	public final void updateLanguage(LocaleChangeEvent lce) {
		T object = weakObject.get();
		if (object != null) {
			updateLanguage(lce, object);
		}
	}

	protected abstract void updateLanguage(LocaleChangeEvent lce, T object);

	private static class WeakCleanupReference<T> extends WeakReference<T> {
		public WeakCleanupReference(T object) {
			super(object, queue);
		}

		public void cleanup() {
			LanguageManager.unregister(this);
		}
	}
}