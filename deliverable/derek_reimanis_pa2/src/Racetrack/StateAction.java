package Racetrack;

/**
 * State Action class -- represents state action pairs in Q Learning.
 * 
 * @author derek.reimanis
 * 
 */
public class StateAction {
	private RideableState state;
	private Action action;
	private double stateActionVal;

	/**
	 * Cloneable Constructor -- copies another stateaction and returns a new
	 * reference.
	 * 
	 * @param another
	 *            StaeteAction to copy
	 */
	public StateAction(StateAction another) {
		this(another.getState(), another.getAction());
	}

	/**
	 * Constructor
	 * 
	 * @param state
	 *            state assigned to this StateAction. An action is applied as
	 *            (0,0)
	 */
	public StateAction(RideableState state) {
		this.state = state;
		action = new Action(new XYPair(0, 0));
	}

	/**
	 * Constructor
	 * 
	 * @param state
	 *            state assigned to this StateAction
	 * @param a
	 *            Action of this State.
	 */
	public StateAction(RideableState state, Action a) {
		this.state = state;
		this.action = a;
	}

	/**
	 * Set the state of this pair
	 * 
	 * @param state
	 *            state to set
	 */
	public void setState(RideableState state) {
		this.state = state;
	}

	/**
	 * Set the value of this pair
	 * 
	 * @param in
	 *            value of this pair
	 */
	public void setStateActionVal(double in) {
		stateActionVal = in;
	}

	/**
	 * Set the action of this pair
	 * 
	 * @param a
	 *            action to set to
	 */
	public void setAction(Action a) {
		this.action = a;
	}

	/**
	 * Get the value of this pair.
	 * 
	 * @return value of this pair.
	 */
	public double getStateActionVal() {
		return stateActionVal;
	}

	/**
	 * Get the State of this pair.
	 * 
	 * @return state of this pair
	 */
	public RideableState getState() {
		return state;
	}

	/**
	 * Get the action of this pair.
	 * 
	 * @return action of this pair.
	 */
	public Action getAction() {
		return action;
	}

}
