package Racetrack;

/**
 * RaceTrack class. Holds a 2-D array of Tile object, with chars.
 * 
 * Racer's position is also tracked.
 * 
 * I had a lot of issues here with what exactly 'X' and 'Y' meant... In the
 * cartesian sense, they are self-explanatory. But in the double for loops
 * sense, i is x and j is y.. this is bad!!
 * 
 * @author derek.reimanis
 * 
 */
public class RaceTrack {

	private final char racer = 'R';
	private Tile[][] tilesInState;
	private int widthOfState;
	private int heightOfState;

	/**
	 * Constructor
	 * 
	 * @param rows
	 *            height of track
	 * @param cols
	 *            width of track
	 */
	public RaceTrack(int rows, int cols) {
		tilesInState = new Tile[rows][cols];
		widthOfState = cols;
		heightOfState = rows;
	}

	/**
	 * Cloner method for instantiating another new racetrack object with the
	 * same values.
	 * 
	 * @param another
	 *            racetrack, the one we want to clone.
	 */
	public RaceTrack(RaceTrack another) {
		this(another.getHeight(), another.getWidth());
		for (int i = 0; i < heightOfState; i++) {
			for (int j = 0; j < widthOfState; j++) {
				this.makeTile(i, j, another.getTile(i, j));
			}
		}
	}

	/**
	 * Set the position of the racer on the track.
	 * 
	 * @param pos
	 *            new position of racer.
	 */
	public void setRacerPosition(XYPair pos) {
		tilesInState[pos.getY()][pos.getX()].setTileValue(racer);
	}

	/**
	 * gets the tile at the specified location. This is used in for loops.
	 * 
	 * @param xLoc
	 *            x location of track
	 * @param yLoc
	 *            y location of track.
	 * @return character of tile's value.
	 */
	public char getTile(int xLoc, int yLoc) {
		return tilesInState[xLoc][yLoc].getTileValue();
	}

	/**
	 * Get the tile at the specified location. This is used in the transitional
	 * model.
	 * 
	 * @param pos
	 *            position of tile to get.
	 * @return char value of tile
	 */
	public char getTile(XYPair pos) {
		return tilesInState[pos.getY()][pos.getX()].getTileValue();
	}

	/**
	 * Set the tile to a new character. This is used in loops.
	 * 
	 * @param pos
	 *            position of track to change
	 * @param value
	 *            new char.
	 */
	public void setTile(XYPair pos, char value) {
		tilesInState[pos.getY()][pos.getX()].setTileValue(value);
	}

	/**
	 * Set the tile to a new character. This is used in the transitional model.
	 * 
	 * @param xLoc
	 *            x position of track to change.
	 * @param yLoc
	 *            y position of track to change.
	 * @param value
	 *            new character value
	 */
	public void setTile(int xLoc, int yLoc, char value) {
		tilesInState[xLoc][yLoc].setTileValue(value);
	}

	/**
	 * Makes a new tile object -- used in initializing the track.
	 * 
	 * @param xLoc
	 *            x position of new tile
	 * @param yLoc
	 *            y position of new tle
	 * @param value
	 *            char value of new tile
	 */
	public void makeTile(int xLoc, int yLoc, char value) {
		tilesInState[xLoc][yLoc] = new Tile(value);
	}

	/**
	 * get width of track
	 * 
	 * @return width
	 */
	public int getWidth() {
		return widthOfState;
	}

	/**
	 * get height of track
	 * 
	 * @return height
	 */
	public int getHeight() {
		return heightOfState;
	}

}
