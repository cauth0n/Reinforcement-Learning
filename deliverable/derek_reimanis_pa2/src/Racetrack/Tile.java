package Racetrack;

/**
 * Simple Tile class to define a tile with a char attribute
 * 
 * @author derek.reimanis
 * 
 */
public class Tile {
	private char tileValue;

	/**
	 * Constructor -- takes in a char for this tile
	 * 
	 * @param tileValue
	 */
	public Tile(char tileValue) {
		this.tileValue = tileValue;
	}

	/**
	 * Get the char value of this tile
	 * 
	 * @return char value of this tile
	 */
	public char getTileValue() {
		return tileValue;
	}

	/**
	 * Set the char value of this tile.
	 * 
	 * @param tileValue
	 *            char to set tile value to.
	 */
	public void setTileValue(char tileValue) {
		this.tileValue = tileValue;
	}
}
