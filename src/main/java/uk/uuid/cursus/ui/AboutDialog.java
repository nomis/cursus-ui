/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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
package uk.uuid.cursus.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.cursus.app.Main;
import uk.uuid.cursus.i18n.Messages;
import uk.uuid.cursus.ui.actions.CloseDialogAction;
import uk.uuid.cursus.ui.component.Displayable;
import uk.uuid.cursus.ui.component.LinkJButton;
import uk.uuid.cursus.ui.preferences.WindowAutoPrefs;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.util.DefaultUnitConverter;

public class AboutDialog extends JDialog implements Displayable {
	private static final String TEXT_SPLIT = "\n------------------------------------------------------------------------\n\n"; //$NON-NLS-1$

	private final Logger log = LoggerFactory.getLogger(getClass());
	private WindowAutoPrefs prefs = new WindowAutoPrefs(this);

	public AboutDialog(Frame owner) {
		super(owner, true);

		initialise();
	}

	public void display() {
		assert (SwingUtilities.isEventDispatchThread());

		prefs.display(getOwner());
	}

	private void initialise() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(Messages.getString("about.title", Constants.APP_DESC)); //$NON-NLS-1$
		DefaultUnitConverter duc = DefaultUnitConverter.getInstance();

		FormLayout layout = new FormLayout("2dlu, pref, fill:pref:grow, max(30dlu;pref), 2dlu", //$NON-NLS-1$
				"2dlu, max(15dlu;pref), 2dlu, max(15dlu;pref), 2dlu, fill:max(100dlu;pref):grow, 2dlu, max(16dlu;pref), 2dlu"); //$NON-NLS-1$
		getContentPane().setLayout(layout);

		JLabel lblName = new JLabel(Constants.APP_NAME + ": " + Messages.getString("about.description")); //$NON-NLS-1$ //$NON-NLS-2$
		getContentPane().add(lblName, "2, 2, 3, 1"); //$NON-NLS-1$

		getContentPane().add(new LinkJButton(Constants.APP_URL), "2, 4"); //$NON-NLS-1$

		JScrollPane scrCopying = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrCopying, "2, 6, 3, 1"); //$NON-NLS-1$

		JTextArea txtCopying = new JTextArea(loadResources("COPYRIGHT", "LICENCE")); //$NON-NLS-1$ //$NON-NLS-2$
		txtCopying.setFont(Font.decode(Font.MONOSPACED));
		txtCopying.setEditable(false);
		scrCopying.setViewportView(txtCopying);
		scrCopying.setPreferredSize(new Dimension(scrCopying.getPreferredSize().width, duc.dialogUnitYAsPixel(100, scrCopying)));

		Action actClose = new CloseDialogAction(this);
		JButton btnClose = new JButton(actClose);
		getContentPane().add(btnClose, "4, 8"); //$NON-NLS-1$

		getRootPane().setDefaultButton(btnClose);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CloseDialogAction.class.getName());
		getRootPane().getActionMap().put(CloseDialogAction.class.getName(), actClose);

		pack();
		setMinimumSize(getSize());
		setSize(getSize().width, getSize().height * 3 / 2);
		btnClose.requestFocusInWindow();
	}

	private String loadResources(String... names) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = Arrays.asList(names).iterator();
		while (it.hasNext()) {
			String name = it.next();

			String content;
			try {
				URL resource = Main.class.getResource(name);
				if (resource == null) {
					throw new IOException("Not found"); //$NON-NLS-1$
				}
				content = IOUtils.toString(resource, "UTF-8"); //$NON-NLS-1$
			} catch (IOException e) {
				log.error("Unable to load " + name + " resource", e); //$NON-NLS-1$ //$NON-NLS-2$
				content = "Error loading " + name + " resource: " + e.getLocalizedMessage() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			sb.append(content);

			if (it.hasNext()) {
				sb.append(TEXT_SPLIT);
			}
		}
		return sb.toString();
	}
}