/*
	cursus - Race series management program
	Copyright 2013  Simon Arlott

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
package uk.me.sa.cursus.app;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.Ignore;
import org.spka.cursus.scoring.Scorer2010;
import org.spka.cursus.scoring.Scorer2011;
import org.spka.cursus.test.AbstractSeries;

import com.google.common.base.Predicates;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.scoring.scorer.Scorer;

@Ignore
public class CreateSPKATests extends AbstractSeries {
	private String packageName;
	private String classPrefix;
	private String seriesName;
	private Scorer scorer;

	private String superClass;
	private boolean anyRaces;

	@SuppressWarnings("nls")
	public CreateSPKATests(Database db, String packageName, String classPrefix, String seriesName, Scorer scorer) {
		this.db = db;
		this.packageName = packageName;
		this.classPrefix = classPrefix;
		this.seriesName = seriesName;
		this.scorer = scorer;

		superClass = "Abstract" + classPrefix;
	}

	@SuppressWarnings("nls")
	public void generate(String className) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(new File("src/test/java/org/spka/cursus/test/" + packageName + "/" + className + ".java")));
		boolean hasRaces = false;

		db.startSession();
		try {
			DatabaseSession.begin();

			String eventMethodName = className.replace(classPrefix, "").replace("Scores", "");
			String eventFieldName = eventMethodName.toLowerCase().charAt(0) + eventMethodName.substring(1);
			String eventConstantName = eventMethodName.replace("Non", "Non_").toUpperCase();
			String eventName = eventMethodName.startsWith("Non") ? eventMethodName.replace("NonEvent", "Non-Event ") : eventMethodName.replace("Event",
					"Race Event ");

			Series series = seriesDAO.find(seriesName);
			Event fileEvent = eventDAO.find(series, eventName);

			for (Event event : series.getEvents()) {
				if (!event.getRaces().isEmpty()) {
					hasRaces = true;
				}
			}

			out.println("/*");
			out.println("	cursus - Race series management program");
			out.println("	Copyright 2013  Simon Arlott");
			out.println("");
			out.println("	This program is free software: you can redistribute it and/or modify");
			out.println("	it under the terms of the GNU General Public License as published by");
			out.println("	the Free Software Foundation, either version 3 of the License, or");
			out.println("	(at your option) any later version.");
			out.println("");
			out.println("	This program is distributed in the hope that it will be useful,");
			out.println("	but WITHOUT ANY WARRANTY; without even the implied warranty of");
			out.println("	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the");
			out.println("	GNU General Public License for more details.");
			out.println("");
			out.println("	You should have received a copy of the GNU General Public License");
			out.println("	along with this program.  If not, see <http://www.gnu.org/licenses/>.");
			out.println(" */");
			out.println("package org.spka.cursus.test." + packageName + ";");
			out.println("");
			if (!hasRaces && !anyRaces) {
				out.println("import org.junit.Assert;");
				out.println("import org.junit.Before;");
				out.println("import org.junit.Test;");
				out.println("");
				out.println("import eu.lp0.cursus.db.DatabaseSession;");
				out.println("import eu.lp0.cursus.db.data.Event;");
				out.println("import eu.lp0.cursus.db.data.Series;");
			} else {
				out.println("import java.util.ArrayList;");
				out.println("import java.util.List;");
				out.println("");
				out.println("import org.junit.Assert;");
				out.println("import org.junit.Before;");
				out.println("import org.junit.Test;");
				out.println("");
				out.println("import com.google.common.base.Predicates;");
				out.println("");
				out.println("import eu.lp0.cursus.db.DatabaseSession;");
				out.println("import eu.lp0.cursus.db.data.Event;");
				out.println("import eu.lp0.cursus.db.data.Race;");
				out.println("import eu.lp0.cursus.db.data.Series;");
				out.println("import eu.lp0.cursus.scoring.data.Scores;");
				out.println("import eu.lp0.cursus.test.util.OverallAssertUtil;");
				if (!className.contains("NonEvent") || anyRaces) {
					out.println("import eu.lp0.cursus.test.util.RaceAssertUtil;");
				}
			}
			out.println("");

			out.println("/**");
			out.println(" * Scores at the end of " + eventName.replace("Race ", "").toLowerCase() + " ("
					+ fileEvent.getDescription().split("\\(")[1].split("\\)")[0].replace(" and ", " to ") + ")");
			out.println(" */");
			out.println("public class " + className + " extends " + superClass + " {");
			if (!superClass.startsWith("Abstract")) {
				out.println("	@Override");
			}
			out.println("	@Before");
			out.println("	public void createData() throws Exception {");
			if (!superClass.startsWith("Abstract")) {
				out.println("		super.createData();");
			}
			out.println("		create" + eventMethodName + (className.contains("NonEvent") ? "Data" : "Races") + "();");
			out.println("	}");

			if (hasRaces) {
				Scores seriesScores = scorer.scoreSeries(series, getSeriesResultsPilots(series, fileEvent),
						Predicates.in(getSeriesResultsPilots(series, fileEvent)));

				out.println("");
				if (anyRaces) {
					out.println("	@Override");
				}
				out.println("	@Test");
				out.println("	public void checkSeries() throws Exception {");
				out.println("		db.startSession();");
				out.println("		try {");
				out.println("			DatabaseSession.begin();");
				out.println("");
				out.println("			Series series = seriesDAO.find(SERIES_NAME);");
				out.println("			Event " + eventFieldName + " = eventDAO.find(series, " + eventConstantName + "_NAME);");
				out.println("			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, " + eventFieldName
						+ "), Predicates.in(getSeriesResultsPilots(series, " + eventFieldName + ")));");
				out.println("			checkSeriesAt" + eventMethodName + "(scores);");
				out.println("");
				out.println("			DatabaseSession.commit();");
				out.println("		} finally {");
				out.println("			db.endSession();");
				out.println("		}");
				out.println("	}");

				out.println("");
				out.println("	@Test");
				out.println("	public final void checkSeriesAt" + eventMethodName + "() throws Exception {");
				out.println("		db.startSession();");
				out.println("		try {");
				out.println("			DatabaseSession.begin();");
				out.println("");
				out.println("			Series series = seriesDAO.find(SERIES_NAME);");
				for (Event event : series.getEvents()) {
					if (event.getRaces().isEmpty() && event != fileEvent) {
						continue;
					}

					out.println("			Event " + event.getName().replace("Race Event ", "event").replace("Non-Event ", "nonEvent") + " = eventDAO.find(series, "
							+ event.getName().replace("Race Event ", "EVENT").replace("Non-Event ", "NON_EVENT") + "_NAME);");
				}
				out.println("");
				out.println("			List<Race> races = new ArrayList<Race>();");
				for (Event event : series.getEvents()) {
					if (event.getRaces().isEmpty()) {
						continue;
					}

					out.println("			races.addAll(" + event.getName().replace("Race Event ", "event") + ".getRaces());");
				}
				out.println("");
				out.println("			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, " + eventFieldName
						+ "), getSeriesResultsEvents(series, " + eventFieldName + "),");
				out.println("					Predicates.in(getSeriesResultsPilots(series, " + eventFieldName + ")));");
				out.println("			checkSeriesAt" + eventMethodName + "(scores);");
				out.println("");
				out.println("			DatabaseSession.commit();");
				out.println("		} finally {");
				out.println("			db.endSession();");
				out.println("		}");
				out.println("	}");

				out.println("");
				out.println("	private void checkSeriesAt" + eventMethodName + "(Scores scores) throws Exception {");
				out.println("		Series series = seriesDAO.find(SERIES_NAME);");

				for (Event event : series.getEvents()) {
					if (event.getRaces().isEmpty()) {
						continue;
					}

					out.println("		Event " + event.getName().replace("Race Event ", "event") + " = eventDAO.find(series, "
							+ event.getName().replace("Race Event ", "EVENT") + "_NAME);");

					for (Race race : event.getRaces()) {
						out.println("		Race " + race.getName().replace("Race ", "race") + " = raceDAO.find(" + event.getName().replace("Race Event ", "event")
								+ ", " + race.getName().replace("Race ", "RACE") + "_NAME);");
					}
				}

				out.println("");
				out.println("		Assert.assertEquals(SERIES_FLEET_AT_" + eventConstantName + ", scores.getPilots().size());");

				for (Event event : series.getEvents()) {
					for (Race race : event.getRaces()) {
						out.print("		Assert.assertEquals(");
						if (scorer instanceof Scorer2010) {
							out.print(eventConstantName + "_FLEET");
						} else if (scorer instanceof Scorer2011) {
							out.print("SERIES_FLEET_AT_" + eventConstantName);
						} else {
							out.print(race.getName().replace("Race ", "RACE") + "_PILOTS");
						}
						out.println(", scores.getFleetSize(" + race.getName().replace("Race ", "race") + "));");
					}
				}

				for (Event event : series.getEvents()) {
					for (Race race : event.getRaces()) {
						String raceName = race.getName().replace("Race ", "race");

						out.println("");
						out.println("		RaceAssertUtil " + raceName + "AssertUtil = new RaceAssertUtil(scores, " + raceName + ");");

						int simulPilots = 0;
						for (Pilot pilot : seriesScores.getRaceOrder(race)) {
							if (seriesScores.hasSimulatedRacePoints(pilot, race)) {
								simulPilots++;
							}

							out.println("		" + raceName + "AssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + seriesScores.getLaps(pilot, race)
									+ ", " + seriesScores.getRacePenalties(pilot, race) + ", " + seriesScores.hasSimulatedRacePoints(pilot, race) + ", "
									+ seriesScores.getRacePoints(pilot, race) + ", " + seriesScores.getRacePosition(pilot, race) + ");");
						}

						out.println("		" + raceName + "AssertUtil.assertDone(" + simulPilots + ");");
					}
				}

				out.println("");
				out.println("		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);");

				for (Pilot pilot : seriesScores.getOverallOrder()) {
					out.print("		overallAssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + seriesScores.getOverallPenalties(pilot) + ", "
							+ seriesScores.getOverallPoints(pilot) + ", " + seriesScores.getOverallPosition(pilot));
					for (Race race : seriesScores.getDiscardedRaces(pilot)) {
						out.print(", " + seriesScores.getRacePoints(pilot, race));
					}
					out.println(");");
				}

				out.println("		overallAssertUtil.assertOrder();");
				out.println("	}");
			}

			if (fileEvent.getRaces().isEmpty()) {
				out.println("");
				out.println("	@Test");
				out.println("	public final void check" + eventMethodName + "() throws Exception {");
				out.println("		db.startSession();");
				out.println("		try {");
				out.println("			DatabaseSession.begin();");
				out.println("");
				out.println("			Series series = seriesDAO.find(SERIES_NAME);");
				out.println("			Event " + eventFieldName + " = eventDAO.find(series, " + eventConstantName + "_NAME);");
				out.println("			Assert.assertEquals(0, " + eventFieldName + ".getRaces().size());");
				out.println("");
				out.println("			DatabaseSession.commit();");
				out.println("		} finally {");
				out.println("			db.endSession();");
				out.println("		}");
				out.println("	}");
			} else {
				Scores eventScores = scorer.scoreRaces(fileEvent.getRaces(), Predicates.in(getEventResultsPilots(series, fileEvent)));

				out.println("");
				out.println("	@Test");
				out.println("	public final void check" + eventMethodName + "() throws Exception {");
				out.println("		db.startSession();");
				out.println("		try {");
				out.println("			DatabaseSession.begin();");
				out.println("");
				out.println("			Series series = seriesDAO.find(SERIES_NAME);");
				out.println("			Event " + eventFieldName + " = eventDAO.find(series, " + eventConstantName + "_NAME);");

				for (Race race : fileEvent.getRaces()) {
					out.println("			Race " + race.getName().replace("Race ", "race") + " = raceDAO.find(" + eventFieldName + ", "
							+ race.getName().replace("Race ", "RACE") + "_NAME);");
				}

				out.println("");
				out.println("			Scores scores = scorer.scoreEvent(" + eventFieldName + ", Predicates.in(getEventResultsPilots(series, " + eventFieldName
						+ ")));");
				out.println("			Assert.assertEquals(" + eventConstantName + "_FLEET, scores.getPilots().size());");
				for (Race race : fileEvent.getRaces()) {
					out.println("			Assert.assertEquals(" + eventConstantName + "_FLEET, scores.getFleetSize(" + race.getName().replace("Race ", "race")
							+ "));");
				}

				for (Race race : fileEvent.getRaces()) {
					String raceName = race.getName().replace("Race ", "race");

					out.println("");
					out.println("			RaceAssertUtil " + raceName + "AssertUtil = new RaceAssertUtil(scores, " + raceName + ");");

					int simulPilots = 0;
					for (Pilot pilot : eventScores.getRaceOrder(race)) {
						if (eventScores.hasSimulatedRacePoints(pilot, race)) {
							simulPilots++;
						}

						out.println("			" + raceName + "AssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + eventScores.getLaps(pilot, race)
								+ ", " + eventScores.getRacePenalties(pilot, race) + ", " + eventScores.hasSimulatedRacePoints(pilot, race) + ", "
								+ eventScores.getRacePoints(pilot, race) + ", " + eventScores.getRacePosition(pilot, race) + ");");
					}

					out.println("			" + raceName + "AssertUtil.assertDone(" + simulPilots + ");");
				}

				out.println("");
				out.println("			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);");

				for (Pilot pilot : eventScores.getOverallOrder()) {
					out.print("			overallAssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + eventScores.getOverallPenalties(pilot) + ", "
							+ eventScores.getOverallPoints(pilot) + ", " + eventScores.getOverallPosition(pilot));
					for (Race race : eventScores.getDiscardedRaces(pilot)) {
						out.print(", " + eventScores.getRacePoints(pilot, race));
					}
					out.println(");");
				}

				out.println("			overallAssertUtil.assertOrder();");
				out.println("");
				out.println("			DatabaseSession.commit();");
				out.println("		} finally {");
				out.println("			db.endSession();");
				out.println("		}");
				out.println("	}");

				for (Race race : fileEvent.getRaces()) {
					Scores raceScores = scorer.scoreRace(race, Predicates.in(getEventResultsPilots(series, fileEvent)));
					String raceName = race.getName().replace("Race ", "race");

					out.println("");
					out.println("	@Test");
					out.println("	public final void check" + race.getName().replace(" ", "") + "() throws Exception {");
					out.println("		db.startSession();");
					out.println("		try {");
					out.println("			DatabaseSession.begin();");
					out.println("");
					out.println("			Series series = seriesDAO.find(SERIES_NAME);");
					out.println("			Event " + eventFieldName + " = eventDAO.find(series, " + eventConstantName + "_NAME);");
					out.println("			Race " + raceName + " = raceDAO.find(" + eventFieldName + ", " + race.getName().replace("Race ", "RACE") + "_NAME);");
					out.println("");
					out.println("			Scores scores = scorer.scoreRace(" + raceName + ", Predicates.in(getEventResultsPilots(series, " + eventFieldName + ")));");
					out.println("			Assert.assertEquals(" + eventConstantName + "_FLEET, scores.getPilots().size());");
					out.println("			Assert.assertEquals(" + eventConstantName + "_FLEET, scores.getFleetSize(" + race.getName().replace("Race ", "race")
							+ "));");

					out.println("");
					out.println("			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, " + raceName + ");");

					int simulPilots = 0;
					for (Pilot pilot : raceScores.getRaceOrder(race)) {
						if (raceScores.hasSimulatedRacePoints(pilot, race)) {
							simulPilots++;
						}

						out.println("			raceAssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + raceScores.getLaps(pilot, race) + ", "
								+ raceScores.getRacePenalties(pilot, race) + ", " + raceScores.hasSimulatedRacePoints(pilot, race) + ", "
								+ raceScores.getRacePoints(pilot, race) + ", " + raceScores.getRacePosition(pilot, race) + ");");
					}

					out.println("			raceAssertUtil.assertDone(" + simulPilots + ");");

					out.println("");
					out.println("			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);");

					for (Pilot pilot : raceScores.getOverallOrder()) {
						out.println("			overallAssertUtil.assertPilot(" + pilot.getName().toLowerCase() + ", " + raceScores.getOverallPenalties(pilot) + ", "
								+ raceScores.getOverallPoints(pilot) + ", " + raceScores.getOverallPosition(pilot) + ");");
					}

					out.println("			overallAssertUtil.assertOrder();");
					out.println("");
					out.println("			DatabaseSession.commit();");
					out.println("		} finally {");
					out.println("			db.endSession();");
					out.println("		}");
					out.println("	}");
				}
			}

			out.print("}");
			out.close();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		closeDatabase();

		superClass = className;
		anyRaces |= hasRaces;
	}
}
