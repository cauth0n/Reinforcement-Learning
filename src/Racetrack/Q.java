package Racetrack;

/**
 * Class Q --
 * This class holds a state and the best action to perform at that state.
 * 
 * @author derek.reimanis
 *
 */
public class Q {

	private RideableState state;
	private int bestActionIndex = -1;	// used to return argmax over actions

	/**
	 * Constructor -- initiate a state with no best action yet.
	 * @param state
	 */
	public Q(RideableState state) {
		this.state = state;
	}

	/**
	 * Constructor -- copies the java class to another instance..
	 * Forgot what this is called at this time... dammit!
	 * 
	 * @param another state we want to duplicate
	 */
	public Q(Q another) {
		this(another.getState());
	}

	/**
	 * gets the state in this object
	 * 
	 * @return state
	 */
	public RideableState getState() {
		return state;
	}

	/**
	 * gets the best action index number of the current state
	 * 
	 * @return best action index 
	 */
	public int getBestActionIndex() {
		return bestActionIndex;
	}

	/**
	 * sets the best action index number for the current state
	 * 
	 * @param updatedActionIndex new index of best action
	 */
	public void setBestActionIndex(int updatedActionIndex) {
		bestActionIndex = updatedActionIndex;
	}

}
