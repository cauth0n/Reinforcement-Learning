package Racetrack;

/**
 * Abstract State class. Creates a definition for all states in the system.
 * 
 * @author derek.reimanis
 * 
 */
public abstract class State {
	protected XYPair position;
	protected double utility = 0;

	/**
	 * Constructor
	 * 
	 * @param position
	 *            position of state
	 */
	public State(XYPair position) {
		this.position = position;
	}

	/**
	 * Get position of state
	 * 
	 * @return positions
	 */
	public XYPair getPosition() {
		return position;
	}

	/**
	 * Get utility of state.
	 * 
	 * @return utility of state
	 */
	public double getUtility() {
		return utility;
	}

}
