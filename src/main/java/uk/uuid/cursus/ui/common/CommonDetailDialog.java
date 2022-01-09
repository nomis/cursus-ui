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
package uk.uuid.cursus.ui.common;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.persistence.PersistenceException;
import javax.swing.Action;
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

import uk.uuid.cursus.i18n.Messages;
import uk.uuid.cursus.ui.Constants;
import uk.uuid.cursus.ui.actions.CancelDialogAction;
import uk.uuid.cursus.ui.actions.SaveDialogAction;
import uk.uuid.cursus.ui.component.DatabaseTextField;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.component.Displayable;
import uk.uuid.cursus.ui.preferences.WindowAutoPrefs;
import uk.uuid.cursus.util.DatabaseError;
import uk.uuid.cursus.util.SwingHacks;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.util.DefaultUnitConverter;

import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.dao.NamedEntityDAO;
import uk.uuid.cursus.db.data.AbstractEntity;
import uk.uuid.cursus.db.data.NamedEntity;

public abstract class CommonDetailDialog<T extends AbstractEntity & NamedEntity> extends JDialog implements Displayable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected final DatabaseWindow win;
	private final String title;
	private final NamedEntityDAO<T> dao;
	private T origItem;
	private boolean isUpdate;

	private JTextField txtName;
	private JTextField txtDesc;

	private WindowAutoPrefs prefs = new WindowAutoPrefs(this);

	public CommonDetailDialog(DatabaseWindow win, String title, NamedEntityDAO<T> dao, T item, boolean isUpdate) {
		super(win.getFrame(), true);
		this.win = win;
		this.title = title;
		this.dao = dao;
		this.origItem = item;
		this.isUpdate = isUpdate;

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
		Dimension min = new Dimension(duc.dialogUnitXAsPixel(150, this), duc.dialogUnitYAsPixel(75, this));
		min.width += getInsets().left + getInsets().right;
		min.height += getInsets().top + getInsets().bottom;

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

		Action actCancel = new CancelDialogAction(this);
		JButton btnCancel = new JButton(actCancel);

		JButton btnSave = new JButton(new SaveDialogAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				doSave();
			}
		});

		SwingHacks.addButtonsInUIOrder(getContentPane(), Arrays.asList(btnSave, btnCancel), Arrays.asList("5, 6", "7, 6")); //$NON-NLS-1$ //$NON-NLS-2$

		getRootPane().setDefaultButton(btnSave);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CancelDialogAction.class.getName());
		getRootPane().getActionMap().put(CancelDialogAction.class.getName(), actCancel);

		pack();
		setMinimumSize(getSize());
		setSize(getSize().width * 3 / 2, getSize().height);
	}

	@SuppressWarnings("unchecked")
	private void doSave() {
		final String name = txtName.getText();
		final String desc = txtDesc.getText();
		T item;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			if (!dao.isNameOk(origItem, isUpdate, name)) {
				log.warn("Name already in use: " + name); //$NON-NLS-1$
				DatabaseError.unableToSave(this, getTitle(), Messages.getString("db.name-in-use", name)); //$NON-NLS-1$
				DatabaseSession.rollback();
				return;
			}

			if (isUpdate) {
				item = dao.get(origItem);
			} else {
				item = (T)origItem.clone();
				prePersist(item);
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

		isUpdate = false;
		origItem = item;

		postSave();
		dispose();
	}

	protected void prePersist(T item) {
	}

	protected void postSave() {
	}
}