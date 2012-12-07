package Racetrack;

import java.util.ArrayList;

/**
 * Rideable state class -- This class is the basis for all possible states. The
 * class holds a position, a velocity, and an ArrayList of all possible actions
 * to perform while in the state.
 * 
 * 
 * 
 * 
 */
public class RideableState extends State {

	private ArrayList<Action> actionsInState;
	private XYPair velocity;

	/**
	 * Constructor
	 * 
	 * @param position
	 *            position of this state
	 * @param velocity
	 *            velocity of this state
	 */
	public RideableState(XYPair position, XYPair velocity) {
		super(position);
		this.velocity = velocity;
		actionsInState = new ArrayList<Action>();
	}

	/**
	 * Cloneable constructor to create a new instance of the same state.
	 * 
	 * @param another
	 *            state that you want a copy of
	 */
	public RideableState(RideableState another) {
		this(another.getPosition(), another.getVelocity());
	}

	/**
	 * Set the actions available to this state. Actions are created outside.
	 * 
	 * @param actions
	 *            arraylist of actions this state will hold
	 */
	public void setActions(ArrayList<Action> actions) {
		actionsInState = actions;
	}

	/**
	 * add an action to the arraylist of actions
	 * 
	 * @param a
	 *            action to add
	 */
	public void addAction(Action a) {
		actionsInState.add(a);
	}

	/**
	 * Get the action at the ith spot.
	 * 
	 * @param i
	 *            index for the action you want
	 * @return action at the ith spot
	 */
	public Action getAction(int i) {
		return actionsInState.get(i);
	}

	/**
	 * get all actions available for a state
	 * 
	 * @return all actions available for the state.
	 */
	public ArrayList<Action> getActions() {
		return actionsInState;
	}

	/**
	 * get the velocity of the state
	 * 
	 * @return the velocity of this state
	 */
	public XYPair getVelocity() {
		return velocity;
	}

	/**
	 * set the utility of the state
	 * 
	 * @param utility
	 *            utility to set to
	 */
	public void setUtility(double utility) {
		this.utility = utility;
	}

}
