package Racetrack;

public class RaceTrack {

	private final char racer = 'R';
	private Tile[][] tilesInState;
	private int widthOfState;
	private int heightOfState;

	public RaceTrack(int rows, int cols) {
		tilesInState = new Tile[rows][cols];
		widthOfState = cols;
		heightOfState = rows;
	}

	public RaceTrack(RaceTrack another) {
		this(another.getHeight(), another.getWidth());
		for (int i = 0; i < heightOfState; i++) {
			for (int j = 0; j < widthOfState; j++) {
				this.makeTile(i, j, another.getTile(i, j));
			}
		}
	}

	public void setRacerPosition(XYPair pos) {
		tilesInState[pos.getY()][pos.getX()].setTileValue(racer);
	}

	public char getTile(int xLoc, int yLoc) {
		return tilesInState[xLoc][yLoc].getTileValue();
	}

	public char getTile(XYPair pos) {
		return tilesInState[pos.getY()][pos.getX()].getTileValue();
	}

	public void setTile(XYPair pos, char value) {
		tilesInState[pos.getY()][pos.getX()].setTileValue(value);
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

}
