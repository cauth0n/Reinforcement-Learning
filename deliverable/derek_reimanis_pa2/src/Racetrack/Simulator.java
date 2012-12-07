package Racetrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Boundaries.Boundaries;
import Boundaries.ConcreteBoundaries;
import Boundaries.ConcreteBoundariesReset;

/**
 * Simulator class which models an environement and sets up any type of learner.
 * All constant learner values are created here.
 * 
 * @author derek.reimanis
 * 
 */
public class Simulator {
	private final int lowAcc = -1;
	private final int highAcc = 1;
	private final int lowVelCap = -5;
	private final int highVelCap = 5;
	private final double error = .5;

	private final double alpha = .5;

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
	private long startTime;
	private long stopTime;
	private double gamma = .2;
	private int iterations = 50;

	/**
	 * Constructor
	 * 
	 * @param racerLessBoard
	 *            racetrack of which this simulation will take place in.
	 * @param filePath
	 *            output file name
	 */
	public Simulator(RaceTrack racerLessBoard, String filePath) {
		this.racerlessBoard = racerLessBoard;

		p = new Printer("filePath");
		rows = racerLessBoard.getHeight();
		cols = racerLessBoard.getWidth();
		p.printTrack(racerLessBoard);
		init();
	}

	/**
	 * Locates start and finish states, and initializes the learner.
	 */
	public void init() {
		startFinishInit();

		constructMDP();
		learn();

	}

	/**
	 * Place the racer at the racer's position on the imported track
	 * 
	 * @param raceTrack
	 *            racetrack to place racer on
	 */
	public void putRacer(RaceTrack raceTrack) {
		raceTrack.setRacerPosition(racer.getPos());
		p.println("Racer moved.");
		// p.printTrack(raceTrack);
	}

	/**
	 * Choose a random start from all the possible starts.
	 * 
	 * @return location of start.
	 * 
	 */
	public XYPair pickStart() {
		Random rand = new Random();
		// return start.get(rand.nextInt(start.size()));
		return start.get(0);
	}

	/**
	 * Find the start and finish states. These are assigned to intance
	 * variables.
	 * 
	 */
	public void startFinishInit() {
		start = new ArrayList<XYPair>();
		finish = new ArrayList<XYPair>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (racerlessBoard.getTile(i, j) == 'S') {
					start.add(new XYPair(j, i));
				}
				if (racerlessBoard.getTile(i, j) == 'F') {
					finish.add(new XYPair(j, i));
				}
			}
		}
		if (start.isEmpty() || finish.isEmpty()) {
			p.println("No start or finish state found.");
			p.pause();
		}
	}

	/**
	 * Construct the MDP, consisting of all states and all action available in
	 * those states.
	 */
	public void constructMDP() {
		ArrayList<RideableState> allStates = getAllRideableStates();
		p.println("Total number of states: " + allStates.size());
		// p.printStates(allStates);
		mdp = new MDP(allStates);
	}

	/**
	 * Get all actions available for a given state.
	 * 
	 * @param state
	 *            state we want actions for
	 * @return the actions of this state
	 */
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

	/**
	 * Simple euclidian distance on Cartesian 2-D plane.
	 * 
	 * @param x1
	 *            first x
	 * @param y1
	 *            first y
	 * @param x2
	 *            second x
	 * @param y2
	 *            second y
	 * @return Euclidian distance
	 */
	public double getEuclidianDistance(int x1, int y1, int x2, int y2) {
		double toRet;
		toRet = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return toRet;
	}

	/**
	 * Get all the states for the racetrack. Some tricky logic is used to limit
	 * velocities on states close to start.
	 * 
	 * @return all states on this racetrack
	 */
	public ArrayList<RideableState> getAllRideableStates() {
		ArrayList<RideableState> allStates = new ArrayList<RideableState>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				double minDistFromStart = minDistanceFromStart(j, i);
				if (minDistFromStart == 0) {
					RideableState temp = new RideableState(new XYPair(j, i),
							new XYPair(0, 0));
					allStates.add(temp);
				} else if (minDistFromStart < highVelCap) {
					for (int k = (highVelCap + lowVelCap)
							- (int) minDistFromStart; k < (lowVelCap + highVelCap)
							+ (int) (minDistFromStart + 1); k++) {
						for (int l = (highVelCap + lowVelCap)
								- (int) minDistFromStart; l < (lowVelCap + highVelCap)
								+ (int) (minDistFromStart + 1); l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(
										new XYPair(j, i), new XYPair(k, l));
								allStates.add(temp);
							}
						}
					}

				} else {
					for (int k = lowVelCap; k <= highVelCap; k++) {
						for (int l = lowVelCap; l <= highVelCap; l++) {
							if (!(racerlessBoard.getTile(i, j) == '#')) {
								RideableState temp = new RideableState(
										new XYPair(j, i), new XYPair(k, l));
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

	/**
	 * Get the smallest distance from a starting point Used to determine
	 * velocities of states close to start.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return smallest distance of x,y and start
	 */
	public double minDistanceFromStart(int x, int y) {
		double min = 9999;
		for (int i = 0; i < start.size(); i++) {
			double pointDistance = getEuclidianDistance(y, x, start.get(i)
					.getY(), start.get(i).getX());
			if (pointDistance < min) {
				min = pointDistance;
			}
		}
		return min;
	}

	/**
	 * Build the actions of all the states.
	 * 
	 * @param allStates
	 *            all states in scope
	 * @return all the states in scope.
	 */
	public ArrayList<RideableState> buildActions(
			ArrayList<RideableState> allStates) {
		ArrayList<RideableState> trimmedStates = allStates;
		for (int i = 0; i < trimmedStates.size(); i++) {
			if (racerlessBoard.getTile(allStates.get(i).getPosition()) != '#') {
				trimmedStates.get(i).setActions(
						getAllActions(trimmedStates.get(i)));
			}
		}
		return trimmedStates;
	}

	/**
	 * Run the racer. Statistics are gathered as the racer progresses. As it is
	 * set up right now, this iterates 100 times, to get an accurate
	 * representation of racer's moves and the like.
	 * 
	 * @param qValues
	 *            Q values of all the states.
	 */
	public void runRacer(ArrayList<Q> qValues) {
		int counter = 0;
		int minSteps = 2000;
		int maxSteps = -2000;
		char lagptr;
		List<Integer> moveList = new ArrayList<Integer>();
		XYPair start = pickStart();

		try {
			for (int k = 0; k < 100; k++) {
				RaceTrack raceTrack = new RaceTrack(racerlessBoard);
				racer = new Racer(start, boundaryLogic);
				putRacer(raceTrack);
				p.printTrack(raceTrack);
				counter = 0;

				while (racerlessBoard.getTile(racer.getPos()) != 'F') {
					XYPair vel = racer.getVel();
					XYPair pos = racer.getPos();
					int i = 0;
					p.printRacer(racer);
					if (racer.getPos().getX() == start.getX()
							&& racer.getPos().getY() == start.getY()) {
						racer.reset();
						p.println("Moved to: ");
						p.printRacer(racer);
					}
					while (i < qValues.size()) {
						if (learner.comparer.isSamePosition(qValues.get(i)
								.getState(), pos)) {
							if (learner.comparer.isSameVelocity(qValues.get(i)
									.getState(), vel)) {
								break;
							}
						}
						i++;
					}

					Action bestAction = qValues.get(i).getState()
							.getAction(qValues.get(i).getBestActionIndex());
					p.printAction(bestAction);
					lagptr = racerlessBoard.getTile(racer.getPos());
					XYPair prevPos = racer.getPos();
					racer.move(bestAction);
					counter++;
					putRacer(raceTrack);// racer goes to x and y spots on track.
					if (!(prevPos.getX() == racer.getPos().getX() && prevPos
							.getY() == racer.getPos().getY())) {
						raceTrack.setTile(prevPos, lagptr);
					}
					p.printTrack(raceTrack);
				}
				moveList.add(counter);
				if (counter < minSteps) {
					minSteps = counter;
				}
				if (counter > maxSteps) {
					maxSteps = counter;
				}

			}
		} catch (IndexOutOfBoundsException e) {
			p.println("Found an invalid state. Could be a bug regarding a failed acc before finish.");
			p.println("Instead of repeating the excercise, let's go back to start.");
			p.println("But first, here are the first 20 q values we have.");
			p.printQs(qValues);
			Scanner in = new Scanner(System.in);
			p.println("Press 1 to try again");
			if (in.nextInt() == 1) {
				runRacer(qValues);
			}
		}

		p.println("Max steps: " + maxSteps);
		p.println("Min steps: " + minSteps);
		p.println("Average steps: " + mean(moveList));
		p.println("Standard deviation: " + sd(moveList));
	}

	/**
	 * Get the mean of a list of ints
	 * 
	 * @param list
	 *            list of ints
	 * @return mean of list
	 */
	public double mean(List<Integer> list) {
		int sum = 0;
		for (Integer n : list) {
			sum += n.intValue();
		}
		return ((double) sum / list.size());
	}

	/**
	 * get the standard deviation of a list of ints.
	 * 
	 * @param list
	 *            list of ints
	 * @return standard deviation of lists
	 */
	public double sd(List<Integer> list) {
		int sum = 0;
		double mean = mean(list);
		for (Integer n : list) {
			sum += Math.pow((n.intValue() - mean), 2);
		}
		return Math.sqrt((double) sum / (list.size() - 1));
	}

	/**
	 * Learn method -- This calls the specific reinforcement learner and
	 * initializes it. Then, it calls racer.
	 * 
	 * As it is set up now, it loops 5 times, altering gamma valus each time.
	 */
	public void learn() {
		boolean startOver = true;
		for (int i = 0; i < 1; i++) {
			if (i == 1) {
				gamma = .5;
			} else if (i == 2) {
				gamma = .7;
			} else if (i == 3) {
				gamma = .8;
			} else if (i == 4) {
				gamma = .9;
			}
			if (startOver) {
				boundaryLogic = new ConcreteBoundariesReset(racerlessBoard,
						start.get(0));
			} else {
				boundaryLogic = new ConcreteBoundaries(racerlessBoard);
			}
			startTime = System.currentTimeMillis();
			learner = new Value_Iteration(mdp, error, gamma, racerlessBoard,
					boundaryLogic, iterations);
			// learner = new QLearning(mdp, error, gamma, racerlessBoard,
			// boundaryLogic, alpha);
			ArrayList<Q> qValues = learner.getqValues();

			p.newFile("1Track" + i);
			runRacer(qValues);
			stopTime = System.currentTimeMillis();
			p.println("Total time: " + (stopTime - startTime));
			p.println("Gamma : " + gamma);
			p.println("Iterations: " + iterations);
		}
		p.closeWriter();

	}
}