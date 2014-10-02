/*
 * Represents a board square and its state
 */
public class Tile {
	
	public final Position position;
	public final TileValue value;
	
	Tile (Position pPosition, TileValue pValue) {
		position = pPosition;
		value = pValue;
	}
}
