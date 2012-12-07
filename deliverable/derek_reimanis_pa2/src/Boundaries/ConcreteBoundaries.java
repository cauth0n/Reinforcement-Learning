package Boundaries;

import Racetrack.RaceTrack;

/**
 * Concrete class to instantiate the abstract track boundaries
 * 
 * @author derek.reimanis
 *
 */ 

public class ConcreteBoundaries extends TrackBoundaries {

/**
 * empty constructor
 * 
 * @param raceTrack track of which to test.
 */
	public ConcreteBoundaries(RaceTrack raceTrack) {
		super(raceTrack);
	}

}
