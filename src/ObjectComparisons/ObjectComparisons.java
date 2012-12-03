package ObjectComparisons;

import Racetrack.RideableState;
import Racetrack.State;
import Racetrack.XYPair;

/**
 * Interface for comparing the values of objects
 * 
 * @author derek.reimanis
 *
 */

public interface ObjectComparisons {

	/**
	 * Checks if two states have the same x position
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true is same x position, false otherwise
	 */
	public boolean isSameXPosition(State one, State two);

	
	/**
	 * Checks if two states have the same y position
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same y position, false otherwise.
	 */
	public boolean isSameYPosition(State one, State two);

	/**
	 * Checks if two states have the same position.
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same position, false otherwise
	 */
	public boolean isSamePosition(State one, State two);

	/**
	 * Checks if a state and an XYPair have the same x position.
	 * 
	 * @param one current state
	 * @param two XYpair of state comparing to
	 * 
	 * @return true if same x position, false otherwise
	 */
	public boolean isSameXPosition(State one, XYPair two);

	/**
	 * Checks if a state and an XYPair have the same y position.
	 * 
	 * @param one current state
	 * @param two XYPair of state comparing to.
	 * 
	 * @return true if same y position, false otherwise
	 */
	public boolean isSameYPosition(State one, XYPair two);

	/**
	 * Checks if a state and an XYPair have the same position
	 * 
	 * @param one current state
	 * @param two XYPair of state comparing to.
	 * 
	 * @return true if same position, false otherwise
	 */
	public boolean isSamePosition(State one, XYPair two);

	/**
	 * Checks if two states have the same x velocity
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same x velocity, false otherwise
	 */
	public boolean isSameXVelocity(RideableState one, RideableState two);

	/**
	 * Checks if two states have the same y velocity
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same y velocity, false otherwise
	 */
	public boolean isSameYVelocity(RideableState one, RideableState two);

	/**
	 * Checks if two states have the same velocity
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same velocity, false otherwise
	 */
	public boolean isSameVelocity(RideableState one, RideableState two);

	/**
	 * Checks if two states are the same. I.e, same pos and vel.
	 * 
	 * @param one current state
	 * @param two state to compare to
	 * 
	 * @return true if same state, false otherwise
	 */
	public boolean isSameRideableState(RideableState one, RideableState two);

	/**
	 * Checks if a state and an XYPair have the same velocity
	 * 
	 * @param one current state
	 * @param two velocity to compare to.
	 * 
	 * @return true if same velocity, false otherwise
	 */
	public boolean isSameVelocity(RideableState one, XYPair two);

	/**
	 * Checks if a state and an XYPair have the same x velocity
	 * 
	 * @param one current state
	 * @param two velocity to compare to
	 * 
	 * @return true if same x velocity, false otherwise
	 */
	public boolean isSameXVelocity(RideableState one, XYPair two);

	/**
	 * Checks if a state and an XYPair have the same y velocity
	 * 
	 * @param one current state
	 * @param two velocity to compare to
	 * 
	 * @return true if same y velocity, false otherwise
	 */
	public boolean isSameYVelocity(RideableState one, XYPair two);

}
