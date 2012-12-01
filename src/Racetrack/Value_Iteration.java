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
		do {
			plan = loopingPlan;// update plan with last info
			delta = 0;
			// p.printQ(plan);

			for (int i = 0; i < qSize - 1; i++) {
				Q currentUtil = loopingPlan.get(i);
				Q currentPlanUtil = plan.get(i);

				double nextMove;

				nextMove = maxAction(currentUtil);

				double bestUtilAtThisState = reward(currentUtil.getState()) + (gamma * nextMove);

				currentUtil.getState().setUtility(bestUtilAtThisState);

				if (Math.abs(currentUtil.getState().getUtility() - currentPlanUtil.getState().getUtility()) > delta) {
					delta = Math.abs(currentUtil.getState().getUtility() - currentPlanUtil.getState().getUtility());
				}
				p.println(currentUtil.getState().getUtility() - currentPlanUtil.getState().getUtility() + "");
				// p.pause();
			}
		} while (delta < error * (1 - gamma) * gamma);
		p.println("Plan generated!!!!");
		return plan;
	}

	public double maxAction(Q thisQVal) {
		double maxValue = Double.MIN_VALUE;

		for (int i = 0; i < thisQVal.getActions().size(); i++) {
			double check = getUtilitiesAfterAction(thisQVal.getActions().get(i), thisQVal);

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

	public double getUtilitiesAfterAction(Action action, Q thisQ) {
		double value = -1;
		int i = 0;

		State failedState = getStateAFterFailedAction(thisQ.getState(), action);
		while (i < stateSize) {
			if (isSameState(failedState, mdp.getStates().get(i))) {
				break;
			}
		}
		double failedAcc = (1 - transitionProb) * mdp.getStates().get(i).getUtility();
		State workingState = getStateAfterAction(thisQ.getState(), action);
		i = 0;
		while (i < stateSize) {
			if (isSameState(workingState, mdp.getStates().get(i))) {
				break;
			}
		}
		double workingAcc = transitionProb * mdp.getStates().get(i).getUtility();

		value = workingAcc + failedAcc;

		return value;
	}

	public boolean isSameState(State one, State two) {
		boolean toRet = false;
		if ((one.getPosition().getX() == two.getPosition().getX())
				&& one.getPosition().getY() == two.getPosition().getY()) {	// same position
			if ((one.getVelocity().getX() == two.getVelocity().getX())
					&& (one.getVelocity().getY() == two.getVelocity().getY())) {	// same velocity
				toRet = true;
			}
		}
		return toRet;
	}

	public State getStateAFterFailedAction(State state, Action action) {
		int newXPos = state.getPosition().getX() + state.getVelocity().getX();
		int newYPos = state.getPosition().getY() + state.getVelocity().getX();

		XYPair position = new XYPair(newXPos, newYPos);
		State newState = new State(position, state.getVelocity());
		return newState;
	}

	public State getStateAfterAction(State state, Action action) {

		int newXVel = state.getVelocity().getX() + action.getXAcc();
		int newYVel = state.getVelocity().getY() + action.getYAcc();
		int newXPos = state.getPosition().getX() + newXVel;
		int newYPos = state.getPosition().getY() + newYVel;

		XYPair velocity = new XYPair(newXVel, newYVel);
		XYPair position = new XYPair(newXPos, newYPos);
		State newState = new State(position, velocity);
		return newState;
	}

	public ArrayList<Q> getQ() {
		ArrayList<Q> getQValues = new ArrayList<Q>(qSize);
		for (int i = 0; i < mdp.getStates().size(); i++) {
			getQValues.add(new Q(mdp.getStates().get(i), mdp.getActions()));
		}
		return getQValues;
	}

}
