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
		Game game = new Game(false);
		game.playGame();
	}
	
	// plays a single game to the end
	private void playGame() {

		GameBoard board = new GameBoard();
		Human player1 = new Human();
		Human player2 = new Human();
		AI ai = new AI();
		
		boolean isOddTurn = true;
		
		// until the game is finished, keep playing turns
		while (!board.isTerminal()) {
			if (isOddTurn) {
				board.setFieldValue(player1.nextMove(), TileValue.Ex);
			} else {
				if (isMultiplayer) {
					board.setFieldValue(player2.nextMove(), TileValue.Oh);
				} else{
					board.setFieldValue(ai.nextMove(), TileValue.Oh);
				}
			}
			
			isOddTurn = !isOddTurn;
		}
	}
}