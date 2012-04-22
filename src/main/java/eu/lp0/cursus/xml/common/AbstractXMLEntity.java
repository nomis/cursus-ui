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
package eu.lp0.cursus.xml.common;

import com.google.common.collect.ComparisonChain;

import eu.lp0.cursus.db.data.AbstractEntity;

public abstract class AbstractXMLEntity<T extends AbstractEntity> implements Comparable<AbstractXMLEntity<T>> {
	public AbstractXMLEntity() {
	}

	public AbstractXMLEntity(T entity) {
		id = generateId(entity);
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String generateId(AbstractEntity entity) {
		return String.format("%s%x", entity.getClass().getSimpleName(), entity.getId()); //$NON-NLS-1$
	}

	@Override
	public int compareTo(AbstractXMLEntity<T> o) {
		return ComparisonChain.start().compare(getClass().getSimpleName(), o.getClass().getSimpleName()).compare(getId(), o.getId()).result();
	}
}