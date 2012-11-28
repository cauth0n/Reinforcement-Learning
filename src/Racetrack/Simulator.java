package Racetrack;

import java.util.ArrayList;

public class Simulator {
	private final int lowAcc = -1;
	private final int highAcc = 1;
	private final double gamma = .9;
	private final double error = .5;

	private Printer p;
	private Learner learner;
	private Race_Track racerlessBoard;
	private Race_Track currentState;
	private ArrayList<XYPair> start;
	private ArrayList<XYPair> finish;
	private MDP mdp;
	private Racer racer;
	private int rows;
	private int cols;

	public Simulator(Race_Track racerLessBoard) {
		this.racerlessBoard = racerLessBoard;
		p = new Printer();
		rows = racerLessBoard.getWidth();
		cols = racerLessBoard.getHeight();
		currentState = new Race_Track(racerLessBoard);
		p.printTrack(racerLessBoard);
		startFinishInit();
		// racerMove();
		constructMDP();
		learn();// !
	}

	public void startFinishInit() {
		boolean foundStart = false;
		start = new ArrayList<XYPair>();
		finish = new ArrayList<XYPair>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (racerlessBoard.getTile(i, j) == 'S') {
					start.add(new XYPair(i, j));
				}
				if (racerlessBoard.getTile(i, j) == 'F') {
					finish.add(new XYPair(i, j));
				}
			}
		}
		if (start.isEmpty() || finish.isEmpty()) {
			p.println("No start or finish state found.");
		}

	}

	public void constructMDP() {
		ArrayList<State> allStates = getAllRideableStates();
		ArrayList<Action> allActions = getAllActions();
		mdp = new MDP(allStates, allActions);
	}

	public ArrayList<Action> getAllActions() {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = lowAcc; i <= highAcc; i++) {
			for (int j = lowAcc; j <= highAcc; j++) {
				actions.add(new Action(new XYPair(i, j)));
			}
		}
		return actions;
	}

	public ArrayList<State> getAllRideableStates() {
		ArrayList<State> allStates = new ArrayList<State>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				char tile = currentState.getTile(i, j);
				//if (tile == '.' || tile == 'S' || tile == 'F') {
					State temp = new State(new XYPair(i, j), new XYPair(0, 0));
					allStates.add(temp);
				//}
			}
		}
		return allStates;
	}

	public void learn() {
		learner = new Value_Iteration(mdp, error, gamma, racerlessBoard);
	}

}
