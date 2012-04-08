/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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
package eu.lp0.cursus.xml;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.AbstractEntity;

public class ImportReferenceManager {
	private Map<String, AbstractXMLEntity<?>> map = new HashMap<String, AbstractXMLEntity<?>>();

	@SuppressWarnings("unchecked")
	public <E extends AbstractEntity, T extends AbstractXMLEntity<E>, R extends AbstractXMLRef<E>> T get(R entity) {
		String key = entity.getRef();
		Preconditions.checkState(map.containsKey(key), "Map does not contain entity " + key); //$NON-NLS-1$
		return (T)map.get(key);
	}

	public <E extends AbstractEntity, T extends AbstractXMLEntity<E>> T put(T entity) {
		Preconditions.checkState(!map.containsKey(entity.getId()), "Map already contains entity " + entity.getId()); //$NON-NLS-1$
		map.put(entity.getId(), entity);
		return entity;
	}
}