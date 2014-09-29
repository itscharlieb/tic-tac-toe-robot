public class GameBoard{

	//There are a total of 9 squares on a tic tac toe board, making for a total of 9 possible moves
	public static final int MAX_NUMBER_OF_MOVES = 9;

	//Enum type fieldValue represents the three possible states a field can take
	private Enum fieldValue {
		Undeclared, Ex, Oh
	}

	//2D arraylist of fieldValues represents the game board
	private fieldValue[][] board;

	//constuctor creates the gameBoard
	public GameBoard(){
		board = fieldValue[3][3];
		initializeBoard();
	}

	//initializes all board fields to Undeclared
	private void initializeBoard(){
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				board[i][j] = Undeclared;
			}
		}
	}
	
	public fieldValue getFieldValue(int i, int j){
		return fieldValue[i][j];
	}

	public void setFieldValue(int i, int j, fieldValue fv){
		fieldValue[i][j] = fv;
	}

	public boolean IsTerminal() {
		
	}
}
