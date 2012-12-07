package Boundaries;

import Racetrack.Action;
import Racetrack.RideableState;
import Racetrack.XYPair;

/**
 * Interface to build Boundaries for any State
 * 
 * @author derek.reimanis
 *  
 */
public interface Boundaries {

	/**
	 * Returns state after a failed Transition -- Velocity stays the same. Position changes.
	 * 
	 * @param state
	 *            current state
	 * 
	 * @return state after failed transition
	 */
	public RideableState failedTransition(RideableState state);

	/**
	 * Returns state after a failed Transition -- Velocity stays the same. Position changes.
	 * 
	 * @param pos
	 *            of state
	 * @param vel
	 *            of state
	 * 
	 * @return state after failed transition
	 */
	public RideableState failedTransisiton(XYPair pos, XYPair vel);

	/**
	 * Returns state after a successful Transition -- Velocity and position change.
	 * 
	 * @param state
	 *            current state
	 * @param actionIndex
	 *            action to apply to state
	 * 
	 * @return state after successful transition
	 */
	public RideableState transition(RideableState state, int actionIndex);

	/**
	 * Returns state after a successful Transition -- Velocity and position change
	 * 
	 * @param state
	 *            current state
	 * @param action
	 *            action applied to current state
	 * 
	 * @return state after action is applied to current state.
	 */
	public RideableState transition(RideableState state, Action action);

	/**
	 * Returns state after a successful transition -- Velocity and position change.
	 * 
	 * @param pos
	 *            current position
	 * @param vel
	 *            current velocity
	 * @param accel
	 *            acceleration to apply
	 * 
	 * @return state after acceleration is applied
	 */
	public RideableState transition(XYPair pos, XYPair vel, XYPair accel);

	/**
	 * Finds the length between valid and invalid y points. Stops at a hashtag.
	 * 
	 * @param yOld
	 *            starting point for y.
	 * @param yNew
	 *            ending point for y.
	 * @param x
	 *            value at which to check y's (pivot)
	 * 
	 * @return distance from y old to the next hashtag
	 */
	public int findYDifference(int yOld, int yNew, int x);

	/**
	 * Finds the length between valid and invalid x points. Stops at a hashtag.
	 * 
	 * @param xOld
	 *            starting point for x
	 * @param xNew
	 *            starting point for y
	 * @param y
	 *            value at which to check x's (pivot)
	 * 
	 * @return distance from x old to the next hashtag.
	 */
	public int findXDifference(int xOld, int xNew, int y);

	/**
	 * Checks if there is a smooth (non-hashtag strewn) path from yold to ynew
	 * 
	 * @param yOld
	 *            starting point for y
	 * @param yNew
	 *            ending point for y
	 * @param xOld
	 *            value at which to check y's (pivot)
	 * 
	 * @return true if a path, false otherwise
	 */
	public boolean smoothYPath(int yOld, int yNew, int xOld);

	/**
	 * Checks if there is a smooth (non-hashtag strewn) path from xold to xnew
	 * 
	 * @param xOld
	 *            starting point for x
	 * @param xNew
	 *            ending point for x
	 * @param yOld
	 *            value at which to check x's (pivot)
	 * 
	 * @return true if a path, false otherwise.
	 */
	public boolean smoothXPath(int xOld, int xNew, int yOld);

	/**
	 * Checks if an inputted state in on a finish tile.
	 * 
	 * @param state
	 *            current state
	 * 
	 * @return true if on a finished state, false otherwise.
	 */
	public boolean isFinished(RideableState state);

}
