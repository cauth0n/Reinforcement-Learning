package ObjectComparisons;

import Racetrack.RideableState;
import Racetrack.State;
import Racetrack.XYPair;

public abstract class RacetrackComparisons implements ObjectComparisons {

	public boolean isSameXPosition(State one, State two) {
		boolean valid = false;
		if (one.getPosition().getX() == two.getPosition().getX()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameYPosition(State one, State two) {
		boolean valid = false;
		if (one.getPosition().getY() == two.getPosition().getY()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSamePosition(State one, State two) {
		boolean valid = false;
		if (isSameXPosition(one, two) && isSameYPosition(one, two)) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameXPosition(State one, XYPair two) {
		boolean valid = false;
		if (one.getPosition().getX() == two.getX()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameYPosition(State one, XYPair two) {
		boolean valid = false;
		if (one.getPosition().getY() == two.getY()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSamePosition(State one, XYPair two) {
		boolean valid = false;
		if (isSameXPosition(one, two) && isSameYPosition(one, two)) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameXVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (one.getVelocity().getX() == two.getVelocity().getX()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameYVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (one.getVelocity().getY() == two.getVelocity().getY()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameVelocity(RideableState one, RideableState two) {
		boolean valid = false;
		if (isSameXVelocity(one, two) && isSameYVelocity(one, two)) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameRideableState(RideableState one, RideableState two) {
		boolean toRet = false;
		if (isSamePosition(one, two) && isSameVelocity(one, two)) {
			toRet = true;
		}
		return toRet;
	}

	public boolean isSameVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (isSameXVelocity(one, two) && isSameYVelocity(one, two)) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameXVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (one.getVelocity().getX() == two.getX()) {
			valid = true;
		}
		return valid;
	}

	public boolean isSameYVelocity(RideableState one, XYPair two) {
		boolean valid = false;
		if (one.getVelocity().getY() == two.getY()) {
			valid = true;
		}
		return valid;
	}

}
