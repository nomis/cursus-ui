/*
	cursus - Race series management program
	Copyright 2013-2014  Simon Arlott

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
package eu.lp0.cursus.ui.util;

import java.awt.Component;

import eu.lp0.cursus.util.ProgressMonitor;

public class ProgressMonitorWrapper implements ProgressMonitor {
	private Component component;
	private javax.swing.ProgressMonitor monitor;

	public ProgressMonitorWrapper(Component component) {
		this.component = component;
	}

	@Override
	public void start(String message, int min, int max) {
		if (monitor != null) {
			stop();
		}
		monitor = new javax.swing.ProgressMonitor(component, buildMessage(message), null, min, max);
	}

	protected String buildMessage(String message) {
		return message;
	}

	@Override
	public void setMinimum(int min) {
		monitor.setMinimum(min);
	}

	@Override
	public void setMaximum(int max) {
		monitor.setMaximum(max);
	}

	@Override
	public void setProgress(int value) {
		monitor.setProgress(value);

	}

	@Override
	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	@Override
	public void stop() {
		monitor.close();
	}
}
