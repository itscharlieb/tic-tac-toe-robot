/*
 * The main thread that handles the interaction between players, the board, and the display.
 */
public class Game {

	public static void main (String args[]) {
		Game game = new Game();
		game.playGame();
	}
	
	private void playGame() {

		GameBoard board = new GameBoard();
		Human player1 = new Human();
		AI player2 = new AI();
		
		boolean isOddTurn = true;
		
		// until the game is finished, keep playing turns
		while (!board.isTerminal()) {
			
			if (isOddTurn) {
				board.setFieldValue(player1.nextMove(), TileValue.Ex);
			} else {
				board.setFieldValue(player2.nextMove(), TileValue.Oh);
			}
			
			isOddTurn = !isOddTurn;
		}
	}
}
