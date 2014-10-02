/*
 * Draws the board and handles all display functionality
 */
public class BoardDisplay {
	
	public void drawBoard(GameBoard state) {
		
		Tile[][] board = state.getBoard();
		
		for (int i = 0; i < board.length; i++) 
		{
			String row = "";
			
			for (int j = 0; j < board.length; j++) 
			{
				TileValue value = state.getTile(new Position(i,j)).value;
				
				if (value == TileValue.Undeclared) {
					row += "^";
				} else if (value == TileValue.Ex) {
					row += "X";
				} else if (value == TileValue.Oh) {
					row += "O";
				}
			}
			
			System.out.println(row);
		}
	}
}
