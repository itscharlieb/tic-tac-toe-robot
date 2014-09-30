import java.util.LinkedList;


public class GameBoard {

	//number of rows and columns for the board
	private static final int BOARD_SIDE_LENGTH = 3;

	//2D arraylist of FieldValues represents the game board
	private Tile[][] board;

	//constuctor creates the gameBoard
	public GameBoard(){
		board = new Tile[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
		initializeBoard();
	}

	//initializes all board fields to Undeclared
	private void initializeBoard() {
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Tile tile = new Tile(new Position(i,j), TileValue.Undeclared);
				board[i][j] = tile;
			}
		}
	}
	
	public Tile getTile(Position pPosition) {
		return board[pPosition.row][pPosition.column];
	}
	
	public TileValue getFieldValue(Position pPosition) {
		return board[pPosition.row][pPosition.column].value;
	}

	public void setFieldValue(Position pPosition, TileValue fv) {
		Tile tile = new Tile(pPosition, fv);
		board[pPosition.row][pPosition.column] = tile;
	}

	// returns a list of all the tiles on the board
	public LinkedList<Tile> getTiles() {
		
		LinkedList<Tile> tiles = new LinkedList<Tile>();
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				tiles.add(board[i][j]);
			}
		}
		
		return tiles;
	}
	
	// returns a list of all the empty tiles on the board
	public LinkedList<Tile> getEmptyTiles() {
		
		LinkedList<Tile> emptyTiles = new LinkedList<Tile>();
		
		for (Tile tile : getTiles()) {
			if (tile.value == TileValue.Undeclared) {
				emptyTiles.add(tile);
			}
		}
		
		return emptyTiles;
	}
	
	// is the game over due to a completed row or full board?
	public boolean isTerminal() {
		
		if (getEmptyTiles().size() == 0) {
			return true;
		} else {
			
			
		}
	}
	
	// if the three tiles in a line all belong to a team, return that team
	public TileValue getRowOwner(Tile[] line) {
		
		if (
			(line[0].value == line[1].value) && 
			(line[0].value == line[2].value) &&
			(line[0].value != TileValue.Undeclared))
		{
			return line[0].value;
		} else {
			return TileValue.Undeclared;
		}
	}
}
