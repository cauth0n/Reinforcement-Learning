package Racetrack;

import java.util.ArrayList;

public class Value_Iteration extends Learner {
	private double delta;
	private double gamma;
	private Printer p;
	private int stateSize;
	private int actionSize;
	private int qSize;

	public Value_Iteration(MDP mdp, double error, double gamma) {
		super(mdp, error);
		this.gamma = gamma;
		p = new Printer();
		stateSize = mdp.getStates().size();
		actionSize = mdp.getActions().size();
		qSize = stateSize;
		qValues = new ArrayList<Q>(qSize);
		qValues = logic();
		p.printQ(qValues);
	}

	public ArrayList<Q> logic() {
		ArrayList<Q> getQValues = new ArrayList<Q>(qSize);
		ArrayList<Q> updater = new ArrayList<Q>(qSize);
		getQValues = getQ();
		updater = getQValues;
		ArrayList<State> states = mdp.getStates();
		ArrayList<Action> actions = mdp.getActions();
		do {
			getQValues = updater;
			delta = 0;
			for (int i = 0; i < states.size() - 1; i++) {
				DoubleWrapper probAndAction = maxAction(actions, getQValues.get(i).getUtility(), getQValues.get(i + 1)
						.getUtility());
				double maxAction = probAndAction.getOne();
				updater.get(i).setAction(actions.get(probAndAction.getTwo()));
				double nextMove = reward(states.get(i)) + (gamma * maxAction);

				updater.get(i).setUtility(nextMove);

				if (updater.get(i).getUtility() - getQValues.get(i).getUtility() > delta) {
					delta = updater.get(i).getUtility() - getQValues.get(i).getUtility();
				}
				p.println(delta + "");
			}

		} while (delta < error * (1 - gamma) * gamma);
		return getQValues;
	}

	public DoubleWrapper maxAction(ArrayList<Action> actions, double thisUtility, double nextUtility) {
		double maxValue = Double.MIN_VALUE;
		int maxIterator = Integer.MIN_VALUE;
		for (int i = 0; i < actions.size(); i++) {
			double check = (transitionProb * nextUtility) + (1 - transitionProb * thisUtility);
			if (check > maxValue) {
				maxValue = check;
				maxIterator = i;
			}

		}
		DoubleWrapper probAndAction = new DoubleWrapper(maxValue, maxIterator);
		return probAndAction;
	}

	public ArrayList<Q> getQ() {
		ArrayList<Q> getQValues = new ArrayList<Q>(mdp.getStates().size() * mdp.getActions().size());
		for (int i = 0; i < mdp.getStates().size(); i++) {
			// for (int j = 0; j < mdp.getActions().size(); j++) {
			// getQValues.add(new Q(mdp.getStates().get(i), mdp.getActions()
			// .get(j)));
			getQValues.add(new Q(mdp.getStates().get(i)));
			// }
		}
		return getQValues;
	}

	public void printScores() {

	}

}
