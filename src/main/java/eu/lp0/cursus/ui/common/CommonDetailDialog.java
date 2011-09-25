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
package eu.lp0.cursus.ui.common;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.persistence.PersistenceException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.util.DefaultUnitConverter;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.RaceHierarchyDAO;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.ui.component.DatabaseTextField;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.preferences.WindowAutoPrefs;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;
import eu.lp0.cursus.util.Messages;

public abstract class CommonDetailDialog<O extends Frame & DatabaseWindow, T extends AbstractEntity & RaceHierarchy> extends JDialog implements Displayable,
		ActionListener {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final O win;
	private final String title;
	private final RaceHierarchyDAO<T> dao;
	private final T origItem;

	private JTextField txtName;
	private JTextField txtDesc;
	private JButton btnCancel;
	private JButton btnSave;

	private WindowAutoPrefs prefs = new WindowAutoPrefs(this);

	public CommonDetailDialog(O win, String title, RaceHierarchyDAO<T> dao, T item) {
		super(win, true);
		this.win = win;
		this.title = title;
		this.dao = dao;
		this.origItem = item;

		initialise();
	}

	public void display() {
		assert (SwingUtilities.isEventDispatchThread());

		prefs.display(getOwner());
	}

	private void initialise() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(title);
		DefaultUnitConverter duc = DefaultUnitConverter.getInstance();
		setSize(new Dimension(duc.dialogUnitXAsPixel(150, this), duc.dialogUnitYAsPixel(70, this)));
		setMinimumSize(new Dimension(duc.dialogUnitXAsPixel(100, this), duc.dialogUnitYAsPixel(70, this)));

		FormLayout layout = new FormLayout("2dlu, right:max(30dlu;pref), 2dlu, fill:max(0dlu;pref):grow, max(30dlu;pref), 2dlu, max(30dlu;pref), 2dlu", //$NON-NLS-1$
				"2dlu, max(15dlu;pref), 2dlu, max(15dlu;pref), 2dlu:grow, max(16dlu;pref), 2dlu"); //$NON-NLS-1$
		getContentPane().setLayout(layout);

		JLabel lblName = new JLabel(Messages.getString("entity.name") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
		getContentPane().add(lblName, "2, 2"); //$NON-NLS-1$

		txtName = new DatabaseTextField(origItem.getName(), Constants.MAX_STRING_LEN);
		getContentPane().add(txtName, "4, 2, 4, 1"); //$NON-NLS-1$

		JLabel lblDesc = new JLabel(Messages.getString("entity.description") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
		getContentPane().add(lblDesc, "2, 4"); //$NON-NLS-1$

		txtDesc = new DatabaseTextField(origItem.getDescription(), Constants.MAX_STRING_LEN);
		getContentPane().add(txtDesc, "4, 4, 4, 1"); //$NON-NLS-1$

		btnCancel = new JButton(Messages.getString("dialog.cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(this);
		getContentPane().add(btnCancel, "5, 6"); //$NON-NLS-1$

		btnSave = new JButton(Messages.getString("dialog.save")); //$NON-NLS-1$
		btnSave.addActionListener(this);
		getContentPane().add(btnSave, "7, 6"); //$NON-NLS-1$

		getRootPane().setDefaultButton(btnSave);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), btnCancel.getText());
		getRootPane().getActionMap().put(btnCancel.getText(), new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				btnCancel.doClick();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnSave) {
			doSave();
		} else if (ae.getSource() == btnCancel) {
			doCancel();
		}
	}

	@SuppressWarnings("unchecked")
	private void doSave() {
		final String name = txtName.getText();
		final String desc = txtDesc.getText();

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			if (!dao.isNameOk(origItem, name)) {
				log.warn("Name already in use: " + name); //$NON-NLS-1$
				DatabaseError.unableToSave(this, getTitle(), String.format(Messages.getString("db.name-in-use"), name)); //$NON-NLS-1$
				DatabaseSession.rollback();
				return;
			}

			T item;
			if (origItem.isTransient()) {
				item = (T)origItem.clone();
				prePersist(item);
			} else {
				item = dao.get(origItem);
			}

			item.setName(name);
			item.setDescription(desc);
			dao.persist(item);

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			log.error("Unable to save changes", e); //$NON-NLS-1$
			DatabaseError.errorSaving(this, getTitle(), e);
			return;
		} finally {
			win.getDatabase().endSession();
		}

		win.refreshRaceList();
		dispose();
	}

	private void doCancel() {
		dispose();
	}

	protected void prePersist(T item) {
	}
}