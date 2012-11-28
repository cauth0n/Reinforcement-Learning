package Racetrack;

import java.util.ArrayList;

public class Value_Iteration extends Learner {
	private double delta;
	private double gamma;
	private int stateSize;
	private int actionSize;
	private int qSize;

	public Value_Iteration(MDP mdp, double error, double gamma, Race_Track raceTrack) {
		super(mdp, error, raceTrack);
		this.gamma = gamma;
		stateSize = mdp.getStates().size();
		actionSize = mdp.getActions().size();
		qSize = stateSize;
		qValues = new ArrayList<Q>(qSize);
		qValues = logic();
	}

	public ArrayList<Q> logic() {
		ArrayList<Q> plan = new ArrayList<Q>(qSize);
		ArrayList<Q> loopingPlan = new ArrayList<Q>(qSize);
		plan = getQ();// all zero utilities.
		loopingPlan = plan;
		ArrayList<State> states = mdp.getStates();
		ArrayList<Action> actions = mdp.getActions();
		int k = 0;
		do {
			plan = loopingPlan;// update plan with last info
			delta = 0;
			// p.printQ(plan);

			for (int i = 0; i < qSize - 1; i++) {
				Q currentUtil = loopingPlan.get(i);
				Q currentPlanUtil = plan.get(i);

				double nextMove;

				nextMove = maxAction(actions, currentUtil);

				nextMove = reward(currentUtil.getState()) + (gamma * nextMove);

				currentUtil.setUtility(nextMove);

				if (Math.abs(currentUtil.getUtility() - currentPlanUtil.getUtility()) > delta) {
					p.println("Reached here");
					delta = Math.abs(currentUtil.getUtility() - currentPlanUtil.getUtility());
				}
				// p.println(currentUtil.getUtility() - currentPlanUtil.getUtility() + "");
				// p.pause();
			}
			k++;
		} while (k < 5);// (delta < error * (1 - gamma) * gamma);
		return plan;
	}

	public double maxAction(ArrayList<Action> actions, Q thisQVal) {
		double thisUtility = thisQVal.getUtility();
		// double nextUtility = nextQVal.getUtility();
		double maxValue = Double.MIN_VALUE;
		for (int i = 0; i < thisQVal.getActions().size(); i++) {
			double check = applyAction(thisQVal.getActions().get(i), thisQVal.getState());
			// Problem is here. ^ the equation is P(s'|s,a).. I'm just checking the exact same summation.
			// I need to deal with the next state as a local var here!!!
			p.println(check + "");
			if (check > maxValue) {
				maxValue = check;
				thisQVal.setBestActionIndex(i);	// sets the action according to the for loop's i
				p.printAction(thisQVal.getActions().get(i));
				p.println("Set new best action  " + i);
			}
		}
		p.println("");
		return maxValue;
	}
	
	public double applyAction(Action action, State state){
		double value = -1;
		//here I need to apply the action to the state, and then find the nextState from my ArrayList<Q>. 
		//Then, apply the transition logic.
		
		return value;
	}

	public ArrayList<Q> getQ() {
		ArrayList<Q> getQValues = new ArrayList<Q>(qSize);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			getQValues.add(new Q(mdp.getStates().get(i), mdp.getActions()));
		}
		return getQValues;
	}

	public void printScores() {

	}

}
