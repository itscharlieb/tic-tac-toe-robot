import java.util.EnumMap;

import basic.Console;

/*
 * The main thread that handles the interaction between players, the board, and the display.
 */
public class Game {

	public static EnumMap<TileValue, Player> tileValueToPlayer = new EnumMap<>(TileValue.class);
	// for switching turns
	public static EnumMap<Player, Player> getNextPlayer = new EnumMap<>(Player.class);
	// don't use the ai for second player
	private final boolean isMultiplayer;
	
	// constructor
	Game (boolean pIsMultiplayer) {
		isMultiplayer = pIsMultiplayer;
		tileValueToPlayer.put(TileValue.Ex, Player.PLAYER1);
		tileValueToPlayer.put(TileValue.Oh, Player.PLAYER2);
		getNextPlayer.put(Player.PLAYER1, Player.PLAYER2);
		getNextPlayer.put(Player.PLAYER2, Player.PLAYER1);
	}
	
	// this should handle the on/off and multiplayer button
	public static void main (String args[]) {
		while (true) {
			Game game = new Game(Console.readInt("Play Multiplayer? (0 no, 1 yes)") != 0 ? true : false);
			game.playGame();
		}
	}
	
	// plays a single game to the end
	private void playGame() 
	{
		Human player1 = new Human();
		Human player2 = new Human();
		AI ai = new AI();

		GameBoard board = new GameBoard(Player.PLAYER1);
		BoardDisplay display = new BoardDisplay();
		
		// until the game is finished, keep playing turns
		while (!board.isTerminal()) 
		{
			if (board.getCurrentPlayer() == Player.PLAYER1) 
			{
				System.out.println("Player 1 Turn.");
				board = player1.nextMove(board);
			}
			else
			{
				System.out.println("Player 2 Turn.");
				if (isMultiplayer) 
				{
					board = player2.nextMove(board);
				} 
				else 
				{
					board = ai.nextMove(board);
				}
			}
			
			display.drawBoard(board);

			board.setCurrentPlayer(getNextPlayer.get(board.getCurrentPlayer()));
		}
		
		// after the game state the winner
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