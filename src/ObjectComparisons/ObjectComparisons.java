package ObjectComparisons;

import Racetrack.RideableState;
import Racetrack.State;
import Racetrack.XYPair;

public interface ObjectComparisons {

	public boolean isSameXPosition(State one, State two);

	public boolean isSameYPosition(State one, State two);

	public boolean isSamePosition(State one, State two);

	public boolean isSameXPosition(State one, XYPair two);

	public boolean isSameYPosition(State one, XYPair two);

	public boolean isSamePosition(State one, XYPair two);

	public boolean isSameXVelocity(RideableState one, RideableState two);

	public boolean isSameYVelocity(RideableState one, RideableState two);

	public boolean isSameVelocity(RideableState one, RideableState two);

	public boolean isSameRideableState(RideableState one, RideableState two);

	public boolean isSameVelocity(RideableState one, XYPair two);

	public boolean isSameXVelocity(RideableState one, XYPair two);

	public boolean isSameYVelocity(RideableState one, XYPair two);

}
