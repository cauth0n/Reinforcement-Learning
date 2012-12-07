package Racetrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Printer class to facilitate a creation of printer shortcuts.
 * 
 * @author derek.reimanis
 * 
 */
public class Printer {
	PrintWriter out;
	File output;

	/**
	 * Constructor -- creates a file to write to given the name.
	 * 
	 * @param fName
	 *            File to write to.
	 */
	public Printer(String fName) {
		try {
			output = new File(fName);
			out = new PrintWriter(output);
		} catch (FileNotFoundException e) {

		}
	}

	/**
	 * Close the existing file and open a new one with name as the file name.
	 * 
	 * @param name
	 *            name of new file
	 */
	public void newFile(String name) {
		try {
			out.close();
			output = new File(name);
			out = new PrintWriter(output);
		} catch (FileNotFoundException e) {

		}
	}

	/**
	 * Close the writer.
	 * 
	 */
	public void closeWriter() {
		out.close();
	}

	/**
	 * shortcut print statement for writing a character to file.
	 * 
	 * @param c
	 *            char to be printed.
	 */
	public void write(char c) {
		out.print(c);
	}

	/**
	 * shortcut print statement for writing a line to file.
	 * 
	 * @param line
	 *            string to be printed.
	 */
	public void write(String line) {
		out.print(line);
	}

	/**
	 * shortcut print statement for printing a line.
	 * 
	 * @param line
	 *            string to be printed.
	 */
	public void print(String line) {
		write(line);
		System.out.print(line);
	}

	/**
	 * shortcut print statement for printing a char.
	 * 
	 * @param c
	 *            char to be printed
	 */
	public void print(char c) {
		write(c);
		System.out.print(c);
	}

	/**
	 * shortcut print statement for printing a line.
	 * 
	 * @param line
	 *            string to be printed.
	 */
	public void println(String line) {
		write(line + "\n");
		System.out.println(line);
	}

	/**
	 * shortcut print statement for printing a char.
	 * 
	 * @param c
	 *            char to be printed
	 */
	public void println(char c) {
		write(c + "\n");
		System.out.println(c);
	}

	/**
	 * Prints a state by printing position, velocity, then the actions that can
	 * be taken at that state.
	 * 
	 * @param state
	 *            state to print.
	 */
	public void printState(RideableState state) {
		println("Racer Position: (" + state.getPosition().getX() + ", "
				+ state.getPosition().getY() + ")");
		println("Racer Velocity: (" + state.getVelocity().getX() + ", "
				+ state.getVelocity().getY() + ")");
		println("Racer's actions: ");
		printActions(state.getActions());
	}

	/**
	 * Print all the states in an array list of states.
	 * 
	 * @param states
	 *            arraylist of states to print
	 */
	public void printStates(ArrayList<RideableState> states) {
		int i = 0;
		for (RideableState state : states) {
			println("Action " + i);
			printState(state);
			i++;
		}
	}

	/**
	 * Prints the racer's current position and current velocity.
	 * 
	 * @param racer
	 *            racer of which values to print
	 */
	public void printRacer(Racer racer) {
		println("Racer pos: " + racer.getPos().getX() + " "
				+ racer.getPos().getY());
		println("Racer vel: " + racer.getVel().getX() + " "
				+ racer.getVel().getY());
	}

	/**
	 * Print the racetrack, char by char.
	 * 
	 * @param raceTrack
	 *            RaceTrack to print
	 */
	public void printTrack(RaceTrack raceTrack) {
		for (int i = 0; i < raceTrack.getHeight(); i++) {
			for (int j = 0; j < raceTrack.getWidth(); j++) {
				print(raceTrack.getTile(i, j) + "");
			}
			println("");
		}
	}

	/**
	 * Prints all the actions in an arraylist of actions.
	 * 
	 * @param actions
	 *            arraylist of actions
	 */
	public void printActions(ArrayList<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			print(" " + i + ": ");
			printAction(actions.get(i));
		}
	}

	/**
	 * Prints a single action
	 * 
	 * @param action
	 *            action of which to print.
	 */
	public void printAction(Action action) {
		println("(" + action.getXAcc() + ", " + action.getYAcc() + ")");
	}

	/**
	 * Prints a single Q, consisting of State, actions, best action so far, and
	 * Q value.
	 * 
	 * @param qValues
	 *            Q object of which to print.
	 */
	public void printQ(Q qValues) {
		print("State: ");
		printState(qValues.getState());
		println("Actions: ");
		printAction(qValues.getState().getAction(qValues.getBestActionIndex()));
		print("Best Action so far: ");
		println(qValues.getBestActionIndex() + "");
		print("Value: ");
		println(qValues.getState().getUtility() + "\n");
	}

	/**
	 * Prints an arraylist of Q values.
	 * 
	 * @param qValues
	 *            arraylist of q values of which to print.
	 */
	public void printQs(ArrayList<Q> qValues) {
		println("Printing Q value pairs");
		for (Q qValue : qValues) {
			printQ(qValue);
		}
	}

	/**
	 * Pause method which simply pauses the output stream, as well as the
	 * program, and prompts the user to press 1 to continue. The user must enter
	 * 1 to continue.
	 * 
	 */
	public void pause() {
		try {
			Scanner in = new Scanner(System.in);
			println("paused. 1 to continue.");
			int value = 0;
			while (value != 1) {
				value = in.nextInt();
			}
		} catch (InputMismatchException e) {
			println("Wrong input -- continuing");
		}
	}

	/**
	 * Prints a sequence of stateAction objects.
	 * 
	 * @param sequence
	 *            linkedlist of state action pairs
	 */
	public void printSequence(LinkedList<StateAction> sequence) {
		for (StateAction stateAction : sequence) {
			println("State:");
			printState(stateAction.getState());
			println("Action:");
			printAction(stateAction.getAction());
		}
	}

}
