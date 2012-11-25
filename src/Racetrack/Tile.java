package Racetrack;

public class Tile {
	private char tileValue;
	private int utility;

	public Tile(char tileValue) {
		this.tileValue = tileValue;
	}

	public int getUtility() {
		if (tileValue == 'F') {
			return 0;
		}
		return -1;
	}

	public void setUtility(int newUtility) {
		utility = newUtility;
	}

	public char getTileValue() {
		return tileValue;
	}

	public void setTileValue(char tileValue) {
		this.tileValue = tileValue;
	}
}
