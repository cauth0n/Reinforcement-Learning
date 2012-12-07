package Boundaries;

import Racetrack.RaceTrack;
import Racetrack.XYPair;

/**
 * Concrete class for reset track boundaries
 * 
 * @author derek.reimanis
 * 
 */
public class ConcreteBoundariesReset extends TrackBoundariesReset {

	/**
	 * Constructor -- provides ability to instantiate the abstract definition.
	 * 
	 * @param raceTrack
	 *            track
	 * @param reset
	 *            values of reset
	 */
	public ConcreteBoundariesReset(RaceTrack raceTrack, XYPair reset) {
		super(raceTrack, reset);
	}

}
