package Racetrack;

import java.util.ArrayList;

public class State {

	private double utility = 0;
	private final int lowAcc = -1;
	private final int highAcc = 1;
	private char racer = 'R';
	private Tile[][] tilesInState;
	private int widthOfState;
	private int heightOfState;
	private ArrayList<Action> actionsFromState;

	public State(int xLength, int yLength) {
		tilesInState = new Tile[xLength][yLength];
		widthOfState = xLength;
		heightOfState = yLength;
	}

	public void buildActions() {
		actionsFromState = new ArrayList<Action>();
		for (int i = lowAcc; i <= highAcc; i++) {
			for (int j = lowAcc; j <= highAcc; j++) {
				actionsFromState.add(new Action(new XYPair(i, j)));
			}
		}
	}
	public ArrayList<Action> getActions(){
		return actionsFromState;
	}

	public State(State another) {
		this(another.getWidth(), another.getHeight());
		for (int i = 0; i < widthOfState; i++) {
			for (int j = 0; j < heightOfState; j++) {
				this.makeTile(i, j, another.getTile(i, j));
			}
		}
	}

	public void setRacerPosition(XYPair pos) {
		tilesInState[pos.getX()][pos.getY()].setTileValue(racer);
	}

	public XYPair getRacerPosition() {
		for (int i = 0; i < widthOfState; i++) {
			for (int j = 0; j < heightOfState; j++) {
				if (tilesInState[i][j].getTileValue() == racer) {
					return new XYPair(i, j);
				}
			}
		}
		System.out.println("Didn't find a racer.");
		return null;
	}

	public char getTile(int xLoc, int yLoc) {
		return tilesInState[xLoc][yLoc].getTileValue();
	}

	public void setTile(int xLoc, int yLoc, char value) {
		tilesInState[xLoc][yLoc].setTileValue(value);
	}

	public void makeTile(int xLoc, int yLoc, char value) {
		tilesInState[xLoc][yLoc] = new Tile(value);
	}

	public int getWidth() {
		return widthOfState;
	}

	public int getHeight() {
		return heightOfState;
	}

	public double getUtility() {
		return utility;
	}

	public void setUtility(int in) {
		utility = in;
	}
}
