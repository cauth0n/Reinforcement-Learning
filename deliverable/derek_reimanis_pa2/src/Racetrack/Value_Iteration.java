package Racetrack;

import java.util.ArrayList;

import Boundaries.Boundaries;

/**
 * Value Iteration Class.
 * 
 * Handles creating a plan of utilty vectors to supply to simulation when
 * called.
 * 
 * @author derek.reimanis
 * 
 */
public class Value_Iteration extends Learner {
	private double gamma;
	private int stateSize;
	private int iterations;

	/**
	 * Constructor
	 * 
	 * @param mdp
	 *            MDP
	 * @param error
	 *            acceptable error rate on which to exit
	 * @param gamma
	 *            learning discount rate
	 * @param raceTrack
	 *            track to apply logic to
	 * @param boundaryLogic
	 *            boundary rules to apply logic with
	 * @param iterations
	 *            number of iterations before exit
	 */
	public Value_Iteration(MDP mdp, double error, double gamma,
			RaceTrack raceTrack, Boundaries boundaryLogic, int iterations) {
		super(mdp, error, raceTrack, boundaryLogic);
		this.gamma = gamma;
		this.iterations = iterations;

		stateSize = mdp.getStates().size();
		qValues = new ArrayList<Q>(stateSize);

		qValues = logic();
	}

	/**
	 * Defines the underlying logic of VI. Main loop is handled here.
	 * 
	 * @return arraylist of q values for the racer to run through.
	 */
	public ArrayList<Q> logic() {
		ArrayList<Q> plan = new ArrayList<Q>(stateSize);// stateSize = qSize
		plan = getQ();// all zero utilities.
		int iterator = 0;
		do {
			System.out.println(iterator + "");
			for (Q current : plan) {
				double nextMove = maxAction(current);
				double bestUtilAtState = reward(current.getState())
						+ (gamma * nextMove);
				current.getState().setUtility(bestUtilAtState);
			}
			iterator++;

		} while (iterator < iterations);
		return plan;
	}

	/**
	 * Gets the best action that can be performed at a state. This is based on
	 * the next state's utility. Note that the best action per state is also set
	 * here, depending on when the max is reset.
	 * 
	 * @param stateActionUtil
	 *            Q value of state in now
	 * 
	 * @return best utility of applying action a
	 */
	public double maxAction(Q stateActionUtil) {
		double maxValue = -500000;

		for (int i = 0; i < stateActionUtil.getState().getActions().size(); i++) {
			double check = getUtilitiesAfterAction(stateActionUtil, i);

			if (check > maxValue) {
				maxValue = check;
				stateActionUtil.setBestActionIndex(i); // sets the action
														// according to the for
														// loop's i
			}
		}
		return maxValue;
	}

	/**
	 * Get the cumulative utilities after any action. failed and success states
	 * are checked, and these are summed.
	 * 
	 * @param stateActionUtil
	 *            Q value of current state and action
	 * @param actionIndex
	 *            index of action we are applying
	 * @return expected utility from actions
	 */
	public double getUtilitiesAfterAction(Q stateActionUtil, int actionIndex) {
		double failedAcc = -1;
		double successAcc = -1;
		RideableState failedState = boundaryLogic
				.failedTransition(stateActionUtil.getState());
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(failedState, mdp.getStates()
					.get(i))) {
				failedAcc = (1 - transitionProb)
						* mdp.getStates().get(i).getUtility();
			}
		}
		RideableState successState = boundaryLogic.transition(
				stateActionUtil.getState(), actionIndex);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(successState,
					mdp.getStates().get(i))) {
				successAcc = transitionProb
						* mdp.getStates().get(i).getUtility();
			}
		}

		return failedAcc + successAcc;
	}

}
