/*
 * Defines a common set of functionalities that will be implemented by both
 * a human player and an AI player
 */
public interface IPlayer {
	
	//returns a Position corresponding to the current Players next move
	Position nextMove(GameBoard board);

}
