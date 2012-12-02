package Racetrack;

import java.util.ArrayList;

import Boundaries.Boundaries;

public class Value_Iteration extends Learner {
	private double delta;
	private double gamma;
	private int stateSize;

	public Value_Iteration(MDP mdp, double error, double gamma, RaceTrack raceTrack, Boundaries boundaryLogic) {
		super(mdp, error, raceTrack, boundaryLogic);
		this.gamma = gamma;
		stateSize = mdp.getStates().size();
		qValues = new ArrayList<Q>(stateSize);
		qValues = logic();
	}

	public ArrayList<Q> logic() {
		ArrayList<Q> plan = new ArrayList<Q>(stateSize);// stateSize = qSize
		plan = getQ();// all zero utilities.
		int iterator = 0;
		do {
			delta = 0;
			for (Q current : plan) {
				double nextMove = maxAction(current);
				double bestUtilAtState = reward(current.getState()) + (gamma * nextMove);
				current.getState().setUtility(bestUtilAtState);
			}
			iterator++;
			p.println(iterator + "");
		} while (iterator < 50);
		// (delta < error * (1 - gamma) * gamma);
		p.println("Plan generated!!!!");
		return plan;
	}

	public double maxAction(Q stateActionUtil) {
		double maxValue = -5000;

		for (int i = 0; i < stateActionUtil.getState().getActions().size(); i++) {
			double check = getUtilitiesAfterAction(stateActionUtil, i);

			if (check > maxValue) {
				maxValue = check;
				stateActionUtil.setBestActionIndex(i);	// sets the action according to the for loop's i
			}
		}
		return maxValue;
	}

	public double getUtilitiesAfterAction(Q stateActionUtil, int actionIndex) {
		double failedAcc = -1;
		double successAcc = -1;
		RideableState failedState = boundaryLogic.failedTransition(stateActionUtil.getState());
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(failedState, mdp.getStates().get(i))) {
				failedAcc = (1 - transitionProb) * mdp.getStates().get(i).getUtility();
			}
		}
		RideableState successState = boundaryLogic.transition(stateActionUtil.getState(), actionIndex);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(successState, mdp.getStates().get(i))) {
				successAcc = transitionProb * mdp.getStates().get(i).getUtility();
			}
		}

		return failedAcc + successAcc;
	}

	public ArrayList<Q> getQ() {
		ArrayList<Q> getQValues = new ArrayList<Q>(stateSize);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			getQValues.add(new Q(mdp.getStates().get(i)));
		}
		return getQValues;
	}

}
