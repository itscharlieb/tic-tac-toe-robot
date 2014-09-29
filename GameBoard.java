

public class GameBoard{

	//There are a total of 9 squares on a tic tac toe board, making for a total of 9 possible moves
	private static final int MAX_NUMBER_OF_MOVES = 9;

	//Enum type FieldValue represents the three possible states a field can take
	private enum FieldValue {
		Undeclared, Ex, Oh,
	};

	//2D arraylist of FieldValues represents the game board
	private FieldValue[][] board;

	//constuctor creates the gameBoard
	public GameBoard(){
		board = new FieldValue[3][3];
		initializeBoard();
	}

	//initializes all board fields to Undeclared
	private void initializeBoard(){
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				board[i][j] = FieldValue.Undeclared;
			}
		}
	}
	
	public FieldValue getFieldValue(int pRow, int pColumn){
		return board[pRow][pColumn];
	}

	public void setFieldValue(int pRow, int pColumn, FieldValue fv){
		board[pRow][pColumn] = fv;
	}
}
