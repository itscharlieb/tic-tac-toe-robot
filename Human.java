import basic.Console;

/*
 * Handles all aspects of player input when choosing a square
 */
public class Human implements IPlayer {
	
	public GameBoard nextMove(GameBoard board) 
	{
		while (true) 
		{
			// get the position of the square they pick
			int chosenSquare = Console.readInt("Choose a square:");
			Position chosenPosition = new Position(((2) - ((chosenSquare - 1) / 3)), (chosenSquare - 1) % 3);

			// if it is free use it, otherwise loop again
			if (board.getTile(chosenPosition).value == TileValue.Undeclared) 
			{
				board.setFieldValue(chosenPosition, board.getCurrentPlayer().team);
				return board;
			}
		}
	}
}


