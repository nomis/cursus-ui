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
package eu.lp0.cursus.xml.data;

import java.util.ArrayList;
import java.util.List;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.xml.data.entity.DataXMLSeries;

public class DataXML {
	public DataXML() {
	}

	public DataXML(List<Series> seriesList) {
		generator = Constants.APP_DESC;

		this.series = new ArrayList<DataXMLSeries>(seriesList.size());
		for (Series series_ : seriesList) {
			this.series.add(new DataXMLSeries(series_));
		}
	}

	private String generator;

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	private ArrayList<DataXMLSeries> series;

	public ArrayList<DataXMLSeries> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<DataXMLSeries> series) {
		this.series = series;
	}
}