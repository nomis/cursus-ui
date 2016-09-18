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
package uk.uuid.lp0.cursus.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Primary background execution thread used to serialise all actions
 */
public class Background {
	private static final ThreadLocal<Boolean> IN_EXECUTOR = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			IN_EXECUTOR.set(true);
			super.beforeExecute(t, r);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);
			IN_EXECUTOR.set(false);
		}
	};

	public static void execute(Runnable command) {
		EXECUTOR_SERVICE.execute(command);
	}

	public static boolean isExecutorThread() {
		return IN_EXECUTOR.get();
	}

	public static void shutdownNow() {
		EXECUTOR_SERVICE.shutdownNow();
	}

	public static void shutdownAndWait(long timeout, TimeUnit unit) throws InterruptedException {
		EXECUTOR_SERVICE.shutdown();
		EXECUTOR_SERVICE.awaitTermination(timeout, unit);
	}
}