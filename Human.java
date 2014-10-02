import basic.Console;

/*
 * Handles all aspects of player input when choosing a square
 */
public class Human implements IPlayer {
	
	public Position nextMove(GameBoard board) 
	{
		while (true) 
		{
			int chosenSquare = Console.readInt("Choose a square:");
			Position chosenPosition = new Position(((2) - ((chosenSquare - 1) / 3)), (chosenSquare - 1) % 3);

			if (board.getTile(chosenPosition).value == TileValue.Undeclared) {
				return chosenPosition;
			}
		}
	}
}


