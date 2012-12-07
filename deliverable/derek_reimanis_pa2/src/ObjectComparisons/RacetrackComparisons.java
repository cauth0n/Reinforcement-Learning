package ObjectComparisons;

import Racetrack.RideableState;
import Racetrack.State;
import Racetrack.XYPair;

/**
 * Abstract class to implement ObjectComparisons in accordance with the race track problem.
 * 
 * @author derek.reimanis
 *
 */ 
public abstract class RacetrackComparisons implements ObjectComparisons {

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameXPosition(Racetrack.State, Racetrack.State)
	 */
	public boolean isSameXPosition(State one, State two) {
		boolean valid = false;
		if (one.getPosition().getX() == two.getPosition().getX()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameYPosition(Racetrack.State, Racetrack.State)
	 */
	public boolean isSameYPosition(State one, State two) {
		boolean valid = false;
		if (one.getPosition().getY() == two.getPosition().getY()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSamePosition(Racetrack.State, Racetrack.State)
	 */
	public boolean isSamePosition(State one, State two) {
		boolean valid = false;
		if (isSameXPosition(one, two) && isSameYPosition(one, two)) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameXPosition(Racetrack.State, Racetrack.XYPair)
	 */
	public boolean isSameXPosition(State one, XYPair two) {
		boolean valid = false;
		if (one.getPosition().getX() == two.getX()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameYPosition(Racetrack.State, Racetrack.XYPair)
	 */
	public boolean isSameYPosition(State one, XYPair two) {
		boolean valid = false;
		if (one.getPosition().getY() == two.getY()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSamePosition(Racetrack.State, Racetrack.XYPair)
	 */
	public boolean isSamePosition(State one, XYPair two) {
		boolean valid = false;
		if (isSameXPosition(one, two) && isSameYPosition(one, two)) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameXVelocity(Racetrack.RideableState, Racetrack.RideableState)
	 */
	public boolean isSameXVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (one.getVelocity().getX() == two.getVelocity().getX()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameYVelocity(Racetrack.RideableState, Racetrack.RideableState)
	 */
	public boolean isSameYVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (one.getVelocity().getY() == two.getVelocity().getY()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameVelocity(Racetrack.RideableState, Racetrack.RideableState)
	 */
	public boolean isSameVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (isSameXVelocity(one, two) && isSameYVelocity(one, two)) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameRideableState(Racetrack.RideableState, Racetrack.RideableState)
	 */
	public boolean isSameRideableState(RideableState one, RideableState two) {
		boolean toRet = false;
		if (isSamePosition(one, two) && isSameVelocity(one, two)) {
			toRet = true;
		}
		return toRet;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameVelocity(Racetrack.RideableState, Racetrack.XYPair)
	 */
	public boolean isSameVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (isSameXVelocity(one, two) && isSameYVelocity(one, two)) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameXVelocity(Racetrack.RideableState, Racetrack.XYPair)
	 */
	public boolean isSameXVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (one.getVelocity().getX() == two.getX()) {
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see ObjectComparisons.ObjectComparisons#isSameYVelocity(Racetrack.RideableState, Racetrack.XYPair)
	 */
	public boolean isSameYVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (one.getVelocity().getY() == two.getY()) {
			valid = true;
		}
		return valid;
	}

}
