package Racetrack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import Boundaries.Boundaries;
import Boundaries.ConcreteBoundaries;

public class Simulator {
	private final int lowAcc = -1;
	private final int highAcc = 1;
	private final int lowVelCap = -5;
	private final int highVelCap = 5;
	private final double gamma = .9;
	private final double error = .5;

	private Boundaries boundaryLogic;
	private Printer p;
	private Learner learner;
	private RaceTrack racerlessBoard;
	private ArrayList<XYPair> start;
	private ArrayList<XYPair> finish;
	private MDP mdp;
	private Racer racer;
	private int rows;
	private int cols;

	public Simulator(RaceTrack racerLessBoard) {
		this.racerlessBoard = racerLessBoard;
		boundaryLogic = new ConcreteBoundaries(racerlessBoard);
		p = new Printer();
		rows = racerLessBoard.getWidth();
		cols = racerLessBoard.getHeight();
		p.printTrack(racerLessBoard);
		init();
	}

	public void init() {
		startFinishInit();
		constructMDP();
		learn();
	}

	public void putRacer(RaceTrack raceTrack) {
		raceTrack.setRacerPosition(racer.getPos());
		p.println("Racer moved.");
		// p.printTrack(raceTrack);
	}

	public XYPair pickStart() {
		Random rand = new Random();
		return start.get(rand.nextInt(start.size()));
	}

	public void startFinishInit() {
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
		ArrayList<RideableState> allStates = getAllRideableStates();
		p.println(allStates.size() + "");
		p.pause();
		p.printStates(allStates);
		mdp = new MDP(allStates);
	}

	public ArrayList<Action> getAllActions(RideableState state) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = lowAcc; i <= highAcc; i++) {
			if (!(Math.abs(state.getVelocity().getX() + i) > 5)) {
				for (int j = lowAcc; j <= highAcc; j++) {
					if (!(Math.abs(state.getVelocity().getY() + j) > 5)) {
						actions.add(new Action(new XYPair(i, j)));
					}
				}
			}
		}
		return actions;
	}

	public XYPair getClosestSpot(int row, int col) {
		XYPair toRet;
		double minDistance = Double.MAX_VALUE;
		int minRow = row;
		int maxRow = row;
		int minCol = col;
		int maxCol = col;
		int saveRow = -1;
		int saveCol = -1;
		while (minRow > 0 && (row - minRow) <= highVelCap) {
			minRow--;
		}
		while (maxRow < rows && (maxRow - row) <= highVelCap) {
			maxRow++;
		}
		while (minCol > 0 && (col - minCol) <= highVelCap) {
			minCol--;
		}
		while (maxCol < cols && (maxCol - col) <= highVelCap) {
			maxCol++;
		}
		for (int i = minRow; i < maxRow; i++) {
			for (int j = minCol; j < maxCol; j++) {
				if (racerlessBoard.getTile(i, j) != '#') {
					double distance = getEuclidianDistance(i, j, row, col);
					if (distance < minDistance) {
						minDistance = distance;
						saveRow = i;
						saveCol = j;
					}
				}
			}
		}
		toRet = new XYPair(saveRow, saveCol);
		return toRet;
	}

	public double getEuclidianDistance(int x1, int y1, int x2, int y2) {
		double toRet;
		toRet = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return toRet;
	}

	public ArrayList<RideableState> getAllRideableStates() {
		ArrayList<RideableState> allStates = new ArrayList<RideableState>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				double minDistFromStart = minDistanceFromStart(i, j);
				if (minDistFromStart == 0) {
					RideableState temp = new RideableState(new XYPair(i, j), new XYPair(0, 0));
					allStates.add(temp);
				} else if (minDistFromStart < highVelCap) {
					for (double k = (highVelCap + lowVelCap) - minDistFromStart; k < (lowVelCap + highVelCap)
							+ minDistFromStart; k++) {
						for (double l = (highVelCap + lowVelCap) - minDistFromStart; l < (lowVelCap + highVelCap)
								+ minDistFromStart; l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(new XYPair(i, j), new XYPair((int) k, (int) l));
								allStates.add(temp);
							}
						}
					}

				} else {
					for (int k = lowVelCap; k <= highVelCap; k++) {
						for (int l = lowVelCap; l <= highVelCap; l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(new XYPair(i, j), new XYPair(k, l));
								allStates.add(temp);
							}
						}
					}
				}
			}
		}
		allStates = buildActions(allStates);
		return allStates;
	}

	public double minDistanceFromStart(int x, int y) {
		double min = Double.MAX_VALUE;

		for (int i = 0; i < start.size(); i++) {
			double pointDistance = getEuclidianDistance(x, y, start.get(i).getX(), start.get(i).getY());
			if (pointDistance < min) {
				min = pointDistance;
			}
		}
		return min;
	}

	public ArrayList<RideableState> buildActions(ArrayList<RideableState> allStates) {
		ArrayList<RideableState> trimmedStates = allStates;
		for (int i = 0; i < trimmedStates.size(); i++) {
			if (racerlessBoard.getTile(allStates.get(i).getPosition().getX(), allStates.get(i).getPosition().getY()) != '#') {
				trimmedStates.get(i).setActions(getAllActions(trimmedStates.get(i)));
			}
		}
		return trimmedStates;
	}

	public void runRacer(ArrayList<Q> qValues) {
		RaceTrack raceTrack = new RaceTrack(racerlessBoard);
		XYPair start = pickStart();
		racer = new Racer(start, boundaryLogic);
		putRacer(raceTrack);
		p.printTrack(raceTrack);
		while (raceTrack.getTile(racer.getPos().getX(), racer.getPos().getY()) != 'F') {
			XYPair vel = racer.getVel();
			XYPair pos = racer.getPos();
			int i = 0;
			while (i < qValues.size()) {
				if (learner.comparer.isSamePosition(qValues.get(i).getState(), pos)) {
					if (learner.comparer.isSameVelocity(qValues.get(i).getState(), vel)) {
						p.println("found a matching state pair!");
						p.printState(qValues.get(i).getState());
						break;
					}
				}
				i++;
			}
			if (i == qValues.size()) {
				p.println("Problem.");
			}

			Action bestAction = qValues.get(i).getState().getAction(qValues.get(i).getBestActionIndex());
			p.printAction(bestAction);

			racer.move(bestAction);
			putRacer(raceTrack);// racer goes to x and y spots on track.
			p.printTrack(raceTrack);
			p.pause();
		}
	}

	public void learn() {
		learner = new Value_Iteration(mdp, error, gamma, racerlessBoard, boundaryLogic);
		ArrayList<Q> qValues = learner.getqValues();
		try {
			Scanner in = new Scanner(System.in);
			p.println("Press 1 to run the racer.");
			if (in.nextInt() == 1) {
				runRacer(qValues);
			}
			for (int i = 0; i < 5; i++) {
				p.printQ(qValues.get(i));
			}
		} catch (InputMismatchException e) {
			p.println("Wrong input -- continuing");
		}
	}

}
