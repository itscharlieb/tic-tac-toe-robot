import basic.Console;

/*
 * The main thread that handles the interaction between players, the board, and the display.
 */
public class Game {

	private final boolean isMultiplayer;
	
	// constructor
	Game (boolean pIsMultiplayer) {
		isMultiplayer = pIsMultiplayer;
	}
	
	// this should handle the on/off and multiplayer button
	public static void main (String args[]) {
		while (true) {
			Game game = new Game(Console.readInt("Play Multiplayer? (0 no, 1 yes)") == 1 ? true : false);
			game.playGame();
		}
	}
	
	// plays a single game to the end
	private void playGame() {

		GameBoard board = new GameBoard();
		BoardDisplay display = new BoardDisplay();
		Human player1 = new Human();
		Human player2 = new Human();
		AI ai = new AI();
		
		boolean isOddTurn = true;
		
		// until the game is finished, keep playing turns
		while (!board.isTerminal()) 
		{
			if (isOddTurn) 
			{
				System.out.println("Player 1 Turn.");
				board.setFieldValue(player1.nextMove(board), TileValue.Ex);
			}
			else
			{
				System.out.println("Player 2 Turn.");
				
				if (isMultiplayer) 
				{
					board.setFieldValue(ai.nextMove(board), TileValue.Oh);
				} 
				else 
				{
					board.setFieldValue(player2.nextMove(board), TileValue.Oh);
				}
			}
			
			display.drawBoard(board);
			
			isOddTurn = !isOddTurn;
		}
		
		TileValue winner = board.checkForLineWin();
		
		if (winner == TileValue.Undeclared) {
			System.out.println("The game is a tie!");
		} else if (winner == TileValue.Ex) {
			System.out.println("Player 1 has won!");
		} else if (winner == TileValue.Oh) {
			System.out.println("Player 2 has won!");
		}
	}
}