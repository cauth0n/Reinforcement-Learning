package Racetrack;

import java.util.ArrayList;
import java.util.LinkedList;

import Boundaries.Boundaries;

public class QLearning extends Learner {
	private double gamma;
	private int stateSize;
	private double alpha;
	private RideableState init;
	private ArrayList<StateAction> pairs;
	Racer racer;
	Printer p;

	public QLearning(MDP mdp, double error, double gamma, RaceTrack raceTrack, Boundaries boundaryLogic, double alpha) {
		super(mdp, error, raceTrack, boundaryLogic);
		this.gamma = gamma;
		stateSize = mdp.getStates().size();
		init = mdp.getStates().get(0);
		qValues = new ArrayList<Q>(stateSize);
		this.alpha = alpha;
		p = new Printer("q");

		//racer = new Racer(init.getPosition(), boundaryLogic);
		putRacer(raceTrack);
		p.printTrack(raceTrack);

		qValues = getQ();
		logic();
	}

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

	public LinkedList<StateAction> BuildSequence(RideableState init, Q qVal) {
		LinkedList<StateAction> sequence = new LinkedList<StateAction>();
		sequence.addFirst(new StateAction(init));
		int iterator = 0;
		while (!boundaryLogic.isFinished(sequence.getLast().getState())) { // p.printSequence(sequence); //p.pause();
			Action a = applyQPolicy(qVal, sequence.getLast().getState());
			sequence.get(iterator).setAction(a);
			RideableState s = boundaryLogic.transition(sequence.getLast().getState(), a);
			iterator++;
			sequence.add(iterator, new StateAction(s));
		} // sequence.add()

		return sequence;
	}

	public ArrayList<Q> updateQValues(LinkedList<StateAction> states, ArrayList<Q> qValues) {
		StateAction stateAction = states.get(0);
		int iterator = 1;
		RideableState resultingState = boundaryLogic.transition(stateAction.getState(), stateAction.getAction());
		p.printState(resultingState);
		p.pause();
		while (boundaryLogic.isFinished(resultingState)) {
			StateAction next = states.get(iterator);
			if (!boundaryLogic.isFinished(next.getState())) {
				double updateQ = (1 - alpha) * qValues.get(iterator).getState().getUtility();

				double bestUtilAfterAction = maxAction(qValues.get(iterator + 1), next.getState());

				updateQ += alpha * (reward(stateAction.getState()) + gamma * bestUtilAfterAction);
				qValues.get(iterator).getState().setUtility(updateQ);
			} else {
				double updateQ = (1 - alpha) * qValues.get(iterator).getState().getUtility();
				updateQ += (alpha * reward(stateAction.getState()));
				qValues.get(iterator).getState().setUtility(updateQ);
			}
			stateAction.setAction(next.getAction());//
			stateAction.setState(next.getState());
			iterator++;
		}
		return qValues;
	}

	public double maxAction(Q qVal, RideableState state) {
		double maxValue = -5000;
		for (int i = 0; i < state.getActions().size(); i++) {
			double check = getUtilitiesAfterAction(qVal, i);

			if (check > maxValue) {
				maxValue = check;
				qVal.setBestActionIndex(i); // sets the action according to the for loop's i
			}
		}
		return maxValue;
	}

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

	public void putRacer(RaceTrack raceTrack) {
		raceTrack.setRacerPosition(racer.getPos());
		p.println("Racer moved.");
		// p.printTrack(raceTrack);
	}

}
