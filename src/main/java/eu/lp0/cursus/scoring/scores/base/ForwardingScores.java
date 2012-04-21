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
package eu.lp0.cursus.scoring.scores.base;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import eu.lp0.cursus.scoring.data.OverallPenaltiesData;
import eu.lp0.cursus.scoring.data.OverallPointsData;
import eu.lp0.cursus.scoring.data.OverallPositionData;
import eu.lp0.cursus.scoring.data.RaceDiscardsData;
import eu.lp0.cursus.scoring.data.RaceLapsData;
import eu.lp0.cursus.scoring.data.RacePenaltiesData;
import eu.lp0.cursus.scoring.data.RacePointsData;
import eu.lp0.cursus.scoring.data.RacePositionsData;
import eu.lp0.cursus.scoring.data.ScoredData;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.scoring.data.ScoresFactorySubset;

public abstract class ForwardingScores extends AbstractForwardingScores implements ScoresFactorySubset {
	private final Supplier<RaceLapsData> raceLapsData = Suppliers.memoize(new Supplier<RaceLapsData>() {
		@Override
		public RaceLapsData get() {
			return newRaceLapsData(ForwardingScores.this);
		}
	});

	private final Supplier<RacePointsData> racePointsData = Suppliers.memoize(new Supplier<RacePointsData>() {
		@Override
		public RacePointsData get() {
			return newRacePointsData(ForwardingScores.this);
		}
	});

	private final Supplier<RacePenaltiesData> racePenaltiesData = Suppliers.memoize(new Supplier<RacePenaltiesData>() {
		@Override
		public RacePenaltiesData get() {
			return newRacePenaltiesData(ForwardingScores.this);
		}
	});

	private final Supplier<RacePositionsData> racePositionsData = Suppliers.memoize(new Supplier<RacePositionsData>() {
		@Override
		public RacePositionsData get() {
			return newRacePositionsData(ForwardingScores.this);
		}
	});

	private final Supplier<RaceDiscardsData> raceDiscardsData = Suppliers.memoize(new Supplier<RaceDiscardsData>() {
		@Override
		public RaceDiscardsData get() {
			return newRaceDiscardsData(ForwardingScores.this);
		}
	});

	private final Supplier<OverallPenaltiesData> overallPenaltiesData = Suppliers.memoize(new Supplier<OverallPenaltiesData>() {
		@Override
		public OverallPenaltiesData get() {
			return newOverallPenaltiesData(ForwardingScores.this);
		}
	});

	private final Supplier<OverallPointsData> overallPointsData = Suppliers.memoize(new Supplier<OverallPointsData>() {
		@Override
		public OverallPointsData get() {
			return newOverallPointsData(ForwardingScores.this);
		}
	});

	private final Supplier<OverallPositionData> overallPositionData = Suppliers.memoize(new Supplier<OverallPositionData>() {
		@Override
		public OverallPositionData get() {
			return newOverallPositionData(ForwardingScores.this);
		}
	});

	protected abstract Scores delegate();

	@Override
	protected ScoredData delegateScoredData() {
		return delegate();
	}

	@Override
	protected ScoresFactorySubset delegateScoresFactory() {
		return this;
	}

	@Override
	public RaceLapsData newRaceLapsData(Scores scores) {
		return delegate().newRaceLapsData(scores);
	}

	@Override
	public RacePointsData newRacePointsData(Scores scores) {
		return delegate().newRacePointsData(scores);
	}

	@Override
	public RacePenaltiesData newRacePenaltiesData(Scores scores) {
		return delegate().newRacePenaltiesData(scores);
	}

	@Override
	public RacePositionsData newRacePositionsData(Scores scores) {
		return delegate().newRacePositionsData(scores);
	}

	@Override
	public RaceDiscardsData newRaceDiscardsData(Scores scores) {
		return delegate().newRaceDiscardsData(scores);
	}

	@Override
	public OverallPenaltiesData newOverallPenaltiesData(Scores scores) {
		return delegate().newOverallPenaltiesData(scores);
	}

	@Override
	public OverallPointsData newOverallPointsData(Scores scores) {
		return delegate().newOverallPointsData(scores);
	}

	@Override
	public OverallPositionData newOverallPositionData(Scores scores) {
		return delegate().newOverallPositionData(scores);
	}

	@Override
	protected RaceLapsData delegateRaceLapsData() {
		return raceLapsData.get();
	}

	@Override
	protected RacePointsData delegateRacePointsData() {
		return racePointsData.get();
	}

	@Override
	protected RacePenaltiesData delegateRacePenaltiesData() {
		return racePenaltiesData.get();
	}

	@Override
	protected RacePositionsData delegateRacePositionsData() {
		return racePositionsData.get();
	}

	@Override
	protected RaceDiscardsData delegateRaceDiscardsData() {
		return raceDiscardsData.get();
	}

	@Override
	protected OverallPenaltiesData delegateOverallPenaltiesData() {
		return overallPenaltiesData.get();
	}

	@Override
	protected OverallPointsData delegateOverallPointsData() {
		return overallPointsData.get();
	}

	@Override
	protected OverallPositionData delegateOverallPositionData() {
		return overallPositionData.get();
	}
}