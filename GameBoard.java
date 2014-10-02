import java.util.LinkedList;

/*
 * Holds the state of a game board, and can return information about that state.
 */
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
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				Tile tile = new Tile(new Position(i,j), TileValue.Undeclared);
				board[i][j] = tile;
			}
		}
	}
	
	// returns a tile as a given position on the board
	public Tile getTile(Position pPosition) {
		return board[pPosition.row][pPosition.column];
	}
	
	// returns the TileValue of a tile from a given position on the board
	public TileValue getFieldValue(Position pPosition) {
		return board[pPosition.row][pPosition.column].value;
	}
	
	// gets the board
	public Tile[][] getBoard() {
		return board;
	}

	// sets the value of a tile from a given position on the board
	public void setFieldValue(Position pPosition, TileValue fv) {
		Tile tile = new Tile(pPosition, fv);
		board[pPosition.row][pPosition.column] = tile;
	}

	// returns a list of all the tiles on the board
	public LinkedList<Tile> getTiles() {
		
		LinkedList<Tile> tiles = new LinkedList<Tile>();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
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
	
	// returns a list of board states that could result from the current players turn
	public LinkedList<GameBoard> getSuccessorStates(TileValue turn) {
		
		LinkedList<GameBoard> successors = new LinkedList<GameBoard>();
		
		for (Tile tile : getEmptyTiles()) {
			GameBoard successor = this;
			successor.setFieldValue(tile.position, turn);
			successors.add(successor);
		}
		
		return successors;
	}
	
	// is the game over due to a completed row or full board?
	public boolean isTerminal() {
		
		return (getEmptyTiles().size() == 0 || checkForLineWin() != TileValue.Undeclared) ;
	}
	
	// if there is a full line, return the winners TileValue, otherwise return Undeclared
	public TileValue checkForLineWin() {
		
		// horizontal rows
		for (int row = 0; row < board.length; row++) {

			LinkedList<Tile> lineRow = new LinkedList<Tile>();

			for (int col = 0; col < board[0].length; col++) {
				lineRow.add(getTile(new Position(row, col)));
			}

			TileValue rowOwner = getRowOwner(lineRow);
			if (rowOwner != TileValue.Undeclared) {
				return rowOwner;
			}
		}

		// vertical columns
		for (int col = 0; col < board[0].length; col++) {

			LinkedList<Tile> lineColumn = new LinkedList<Tile>();

			for (int row = 0; row < board.length; row++) {
				lineColumn.add(getTile(new Position(row, col)));
			}

			TileValue columnOwner = getRowOwner(lineColumn);
			if (columnOwner != TileValue.Undeclared) {
				return columnOwner;
			}
		}

		// diagonals
		if (board.length == board[0].length) {

			LinkedList<Tile> diagonal0 = new LinkedList<Tile>();
			for (int row = 0; row < board.length; row++) {
				diagonal0.add(getTile(new Position(row, row)));
			}

			TileValue diagonal0Owner = getRowOwner(diagonal0);
			if (diagonal0Owner != TileValue.Undeclared) {
				return diagonal0Owner;
			}

			LinkedList<Tile> diagonal1 = new LinkedList<Tile>();
			for (int row = 0; row < board.length; row++) {
				diagonal1.add(getTile(new Position(row, (board.length - 1) - row)));
			}

			TileValue diagonal1Owner = getRowOwner(diagonal1);
			if (diagonal1Owner != TileValue.Undeclared) {
				return diagonal1Owner;
			}
		}
		
		return TileValue.Undeclared;
	}
	
	// if the three tiles in a line all belong to a team, return that team
	public TileValue getRowOwner(LinkedList<Tile> line) {
		
		TileValue comparissonValue = line.getFirst().value;
		
		for (Tile tile : line) {
			if (tile.value != comparissonValue) {
				return TileValue.Undeclared;
			}
		}
		
		return comparissonValue;
	}
}
