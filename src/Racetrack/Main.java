package Racetrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Program to read in a file in form :
 * <x,y>
 * where x is the number of rows in the file, y is the cols
 * 
 * This file is represented as a racetrack, and a racer car 
 * must apply accelerations to alter its position and velocity 
 * to make it to the finish line. 
 * 
 * @author derek.reimanis
 *
 */
public class Main {

	private final String lTrack = "L-track.txt";
	private final String oTrack = "O-track.txt";
	private final String rTrack = "R-track.txt";
	private final String practiceTrack = "Practice-Track.txt";
	private RaceTrack racerLessBoard;

	/**
	 * Constructor -- reads in file, creates race track, and starts the 
	 * simulator on that track.
	 * 
	 */
	public Main() {

		String filePath = rTrack;

		try {
			File file = new File(filePath);
			Scanner fileIn = new Scanner(file);
			String[] size = fileIn.nextLine().split(",");
			int rows = Integer.parseInt(size[0]);
			int cols = Integer.parseInt(size[1]);
			racerLessBoard = new RaceTrack(rows, cols);
			for (int i = 0; i < rows; i++) {		//y
				String line = fileIn.nextLine();
				for (int j = 0; j < cols; j++) {		//x
					racerLessBoard.makeTile(i, j, line.charAt(j));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Read in values incorrectly -- debug line 30 from Main");
			e.printStackTrace();
		}
		new Simulator(racerLessBoard, filePath);
	}
	
	/**
	 * Returns the raceTrack with no racer on it.
	 * 
	 * @return track with no racer
	 */
	public RaceTrack getTrack(){
		return racerLessBoard;
	}


	/**
	 * public static void main,
	 * initializes Main() constructor.
	 * @param argss
	 */
	public static void main(String[] args) {
		new Main();

	}

}
