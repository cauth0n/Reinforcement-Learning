package Racetrack;

public class Q {
	private State state;
	private Action action;
	private double utility = 0;

//	public Q(State state, Action action) {
//		this.state = state;
//		this.action = action;
//	}
	public Q(State state){
		this.state = state;
		action = null;
	}
	public void setAction(Action a){
		action = a;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
	}

	public State getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}

}
