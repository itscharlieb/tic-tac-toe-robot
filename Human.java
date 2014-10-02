/*
 * Handles all aspects of player input when choosing a square
 */
public class Human implements IPlayer {
	
	public Position nextMove() {
		return new Position(1,1);
	}
}
