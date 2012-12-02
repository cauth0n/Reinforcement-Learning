package Racetrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private final String lTrack = "L-track.txt";
	private final String oTrack = "O-track.txt";
	private final String rTrack = "R-track.txt";
	private final String practiceTrack = "Practice-Track.txt";
	private RaceTrack racerLessBoard;

	public Main(String directory) {

		String filePath = directory += practiceTrack;

		try {
			File file = new File(filePath);
			Scanner fileIn = new Scanner(file);
			String[] size = fileIn.nextLine().split(",");
			int rows = Integer.parseInt(size[0]);
			int cols = Integer.parseInt(size[1]);
			racerLessBoard = new RaceTrack(rows, cols);
			for (int i = 0; i < rows; i++) {
				String line = fileIn.nextLine();
				for (int j = 0; j < cols; j++) {
					racerLessBoard.makeTile(i, j, line.charAt(j));
				}
			}
		} catch (FileNotFoundException e) {
			println("File not found.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			println("Read in values incorrectly -- debug line 30 from Main");
			e.printStackTrace();
		}
		new Simulator(racerLessBoard);
	}
	public RaceTrack getTrack(){
		return racerLessBoard;
	}

	public void println(String line) {
		System.out.println(line);
	}

	public void print(String line) {
		System.out.print(line);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main("");

	}

}
