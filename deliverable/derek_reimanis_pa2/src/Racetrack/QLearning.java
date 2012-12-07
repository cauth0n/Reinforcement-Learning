package Racetrack;

import java.util.ArrayList;
import java.util.LinkedList;

import Boundaries.Boundaries;

/**
 * Q Learning class -- This class is not fully implemented.
 * 
 * I need to include racer movements, as well as debug a missing state action
 * recognition.
 * 
 * 
 * 
 * @author derek.reimanis
 * 
 */
public class QLearning extends Learner {
	private double gamma;
	private int stateSize;
	private double alpha;
	private RideableState init;
	private ArrayList<StateAction> pairs;
	Racer racer;
	Printer p;

	/**
	 * Constructor
	 * 
	 * @param mdp
	 *            markov decision process -- used to identify all states and
	 *            actions
	 * @param error
	 *            max error allowed between successive iterations
	 * @param gamma
	 *            a value closer to 0 signifies more focus on short term
	 *            rewards, while 1 signifies long term reward focus.
	 * @param raceTrack
	 *            racetrack in which the algorithm will work on
	 * @param boundaryLogic
	 *            class of rules, coming from the Boundaries package.
	 * @param alpha
	 *            a value closer to 0 signifies focus on local states,
	 *            memorizing the track. A value closer to 1 means explore more!
	 */
	public QLearning(MDP mdp, double error, double gamma, RaceTrack raceTrack,
			Boundaries boundaryLogic, double alpha) {
		super(mdp, error, raceTrack, boundaryLogic);
		this.gamma = gamma;
		stateSize = mdp.getStates().size();
		init = mdp.getStates().get(0);
		qValues = new ArrayList<Q>(stateSize);
		this.alpha = alpha;
		p = new Printer("q");

		// racer = new Racer(init.getPosition(), boundaryLogic);
		putRacer(raceTrack);
		p.printTrack(raceTrack);

		qValues = getQ();
		logic();
	}

	/**
	 * Logic, builds the algorithm
	 * 
	 * @return Linkedlist of state actions, in a sequence.
	 */
	public LinkedList<StateAction> logic() {
		// int iterator = 0;
		LinkedList<StateAction> sequence = new LinkedList<StateAction>();
		// do {
		StateAction first = new StateAction(init);
		sequence.add(first);
		qValues = updateQValues(sequence, qValues);
		sequence = BuildSequence(init, qValues.get(0));
		p.printSequence(sequence);

		// iterator++;
		// } while (iterator < 50);
		return sequence;
	}

	/**
	 * Builds a sequence of states to travel through.
	 * 
	 * @param init
	 *            initial state of seqence -- the racer will be here.
	 * @param qVal
	 *            Q value associated with the initial state
	 * 
	 * @return linked list of stateaction
	 */
	public LinkedList<StateAction> BuildSequence(RideableState init, Q qVal) {
		LinkedList<StateAction> sequence = new LinkedList<StateAction>();
		sequence.addFirst(new StateAction(init));
		int iterator = 0;
		while (!boundaryLogic.isFinished(sequence.getLast().getState())) { // p.printSequence(sequence);
																			// //p.pause();
			Action a = applyQPolicy(qVal, sequence.getLast().getState());
			sequence.get(iterator).setAction(a);
			RideableState s = boundaryLogic.transition(sequence.getLast()
					.getState(), a);
			iterator++;
			sequence.add(iterator, new StateAction(s));
		} // sequence.add()

		return sequence;
	}

	/**
	 * Updates Q values with a new sequence.
	 * 
	 * @param states
	 *            linked list of state actions, in a sequence
	 * @param qValues
	 *            q values associated with the sequence.
	 * 
	 * @return an arraylist of Q, specifying the q values of that sequence.
	 */
	public ArrayList<Q> updateQValues(LinkedList<StateAction> states,
			ArrayList<Q> qValues) {
		StateAction stateAction = states.get(0);
		int iterator = 1;
		RideableState resultingState = boundaryLogic.transition(
				stateAction.getState(), stateAction.getAction());
		p.printState(resultingState);
		p.pause();
		while (boundaryLogic.isFinished(resultingState)) {
			StateAction next = states.get(iterator);
			if (!boundaryLogic.isFinished(next.getState())) {
				double updateQ = (1 - alpha)
						* qValues.get(iterator).getState().getUtility();

				double bestUtilAfterAction = maxAction(
						qValues.get(iterator + 1), next.getState());

				updateQ += alpha
						* (reward(stateAction.getState()) + gamma
								* bestUtilAfterAction);
				qValues.get(iterator).getState().setUtility(updateQ);
			} else {
				double updateQ = (1 - alpha)
						* qValues.get(iterator).getState().getUtility();
				updateQ += (alpha * reward(stateAction.getState()));
				qValues.get(iterator).getState().setUtility(updateQ);
			}
			stateAction.setAction(next.getAction());//
			stateAction.setState(next.getState());
			iterator++;
		}
		return qValues;
	}

	/**
	 * Returns the value of the maximum action for any given state and q value.
	 * 
	 * Note that the best index for any action is set at the state level here.
	 * 
	 * @param qVal
	 *            Q value associated with state.
	 * @param state
	 *            state with which we wish to find a new action value for.
	 * 
	 * @return value of applying action.
	 */
	public double maxAction(Q qVal, RideableState state) {
		double maxValue = -5000;
		for (int i = 0; i < state.getActions().size(); i++) {
			double check = getUtilitiesAfterAction(qVal, i);

			if (check > maxValue) {
				maxValue = check;
				qVal.setBestActionIndex(i); // sets the action according to the
											// for loop's i
			}
		}
		return maxValue;
	}

	/**
	 * Apply Q policy, returning the best action.
	 * 
	 * This is nearly identical to maxAction, however this method returns an
	 * action.
	 * 
	 * @param qVal
	 *            Q value associated with state
	 * @param state
	 *            state with which we wish to check action values for.
	 * 
	 * @return best action.
	 */
	public Action applyQPolicy(Q qVal, RideableState state) {
		double maxValue = -5000;
		for (int i = 0; i < state.getActions().size(); i++) {
			double check = getUtilitiesAfterAction(qVal, i);

			if (check > maxValue) {
				maxValue = check;
				qVal.setBestActionIndex(i); // sets the action according to the
											// for loop's i
			}
		}
		return qVal.getState().getAction(qVal.getBestActionIndex());
	}

	/**
	 * Get utilities after an action. This is our transition for Q value.
	 * 
	 * @param qVal
	 *            Q value with state.
	 * @param index
	 *            index of action to apply.
	 * 
	 * @return utility value of future state.
	 */
	public double getUtilitiesAfterAction(Q qVal, int index) {
		double util = -1;
		RideableState state = boundaryLogic.transition(qVal.getState(), index);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			if (comparer.isSameRideableState(state, mdp.getStates().get(i))) {
				util = mdp.getStates().get(i).getUtility();
			}
		}
		return util;
	}

	/**
	 * Puts the racer on the track in the racer's position.
	 * 
	 * @param raceTrack
	 *            track of which we want to place racer on.
	 */
	public void putRacer(RaceTrack raceTrack) {
		raceTrack.setRacerPosition(racer.getPos());
		p.println("Racer moved.");
		// p.printTrack(raceTrack);
	}

}
