package Racetrack;

public class Q {

	private RideableState state;
	private int bestActionIndex = -1;	// used to return argmax over actions

	public Q(RideableState state) {
		this.state = state;
	}

	public Q(Q another) {
		this(another.getState());
	}

	public RideableState getState() {
		return state;
	}

	public int getBestActionIndex() {
		return bestActionIndex;
	}

	public void setBestActionIndex(int updatedActionIndex) {
		bestActionIndex = updatedActionIndex;
	}

}
