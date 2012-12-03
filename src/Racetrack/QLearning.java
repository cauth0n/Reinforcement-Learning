package Racetrack;

import java.util.ArrayList;

import Boundaries.Boundaries;

public class QLearning extends Learner {
	private double delta;
	private double gamma;
	private int stateSize;

	public QLearning(MDP mdp, double error, double gamma, RaceTrack raceTrack, Boundaries boundaryLogic) {
		super(mdp, error, raceTrack, boundaryLogic);
		this.gamma = gamma;
		stateSize = mdp.getStates().size();
		qValues = new ArrayList<Q>(stateSize);
		logic();
	}

	public void logic() {
		int iterator = 0;
		do {

			iterator++;
		} while (iterator < 50);
	}

	public void applyQPolicy(Q qVal) {
		double maxValue = -5000;

		for (int i = 0; i < qVal.getState().getActions().size(); i++) {
			double check = getUtilitiesAfterAction(qVal, i);

			if (check > maxValue) {
				maxValue = check;
				qVal.setBestActionIndex(i);	// sets the action according to the for loop's i
			}
		}
		//return qVal.getBestActionIndex();
	}

	public double getUtilitiesAfterAction(Q qVal, int index) {
		double util = -1;
		RideableState failedState = boundaryLogic.failedTransition(qVal.getState());
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(failedState, mdp.getStates().get(i))) {
				util = (1 - transitionProb) * mdp.getStates().get(i).getUtility();
			}
		}


		return util;

	}

}
