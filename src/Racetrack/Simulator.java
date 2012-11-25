package Racetrack;

import java.util.ArrayList;

public class Simulator {
	private Learner leaner;
	private State racerlessBoard;
	private State currentState;
	private MDP mdp;
	private Racer racer;
	private int rows;
	private int cols;

	public Simulator(State racerLessBoard) {
		this.racerlessBoard = racerLessBoard;
		rows = racerLessBoard.getWidth();
		cols = racerLessBoard.getHeight();
		currentState = new State(racerLessBoard);
		// racerMove();
		constructMDP();
		// learn();// !
	}

	public void racerInit() {
		boolean foundStart = false;
		int i = 0;
		int j = 0;
		while (!foundStart) {
			if (j == currentState.getWidth() - 1) {
				i++;
				j = 0;
			}
			if (currentState.getTile(i, j) == 'S') {
				foundStart = true;

			} else {
				j++;
			}
		}
		XYPair racerStart = new XYPair(i, j);
		currentState.setRacerPosition(racerStart);
		racer = new Racer(racerStart);
	}

	public void constructMDP() {
		ArrayList<State> allStates = getAllRideableStates();
		mdp = new MDP(allStates);
	}

	public ArrayList<State> getAllRideableStates() {
		ArrayList<State> allStates = new ArrayList<State>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				char tile = currentState.getTile(i, j);
				if (tile == '.' || tile == 'S' || tile == 'F') {
					State temp = new State(racerlessBoard);
					temp.setTile(i, j, 'R');
					temp.buildActions();
					allStates.add(temp);
				}
			}
		}
		printStates(allStates);
		return allStates;
	}

	public void learn() {

	}

	public void printState(State state) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				print(state.getTile(i, j) + "");
			}
			println("");
		}
	}

	public void printStates(ArrayList<State> states) {
		for (State state : states) {
			printState(state);
		}
	}

	public void print(String line) {
		System.out.print(line);
	}

	public void printActions(State state) {
		println("Actions from state: ");
		printState(state);
		for (int i = 0; i < state.getActions().size(); i++) {
			println(i + ": (" + state.getActions().get(i).getXAcc() + ", "
					+ state.getActions().get(i).getYAcc() + ")");
		}
	}

	public void println(String line) {
		System.out.println(line);
	}
}
