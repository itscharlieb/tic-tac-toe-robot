import java.util.EnumMap;
import java.util.LinkedList;

/*
 * Handles the computer's choice of squares
 */
public class AI implements IPlayer {

	private static final double	FUDGE = 0.001;
	
	public GameBoard nextMove(GameBoard board) 
	{
		LinkedList<GameBoard> bestMoves = new LinkedList<GameBoard>();

		EnumMap<Player, Double> bestUtilities = makeMinimumValueMap();
		EnumMap<Player, Double> pruneUtilities = makeMinimumValueMap();

		for (GameBoard potentialState : board.getSuccessorStates()) {
			EnumMap<Player, Double> potentialStateUtilities = calculateEventualUtilities(potentialState, pruneUtilities);

			if ((bestUtilities.get(board.getCurrentPlayer()) + FUDGE) < potentialStateUtilities.get(board.getCurrentPlayer())) {
				bestUtilities = potentialStateUtilities;
				bestMoves.clear();
			}

			if (Math.abs(bestUtilities.get(board.getCurrentPlayer()) - potentialStateUtilities.get(board.getCurrentPlayer())) < FUDGE) {
				bestMoves.add(potentialState);
			}
		}
		
		return bestMoves.get(0);
	}

	private static EnumMap<Player, Double> calculateEventualUtilities(GameBoard board, EnumMap<Player, Double> parentPruneUtilities) {

		if (board.isTerminal()) {
			return board.getUtilities();
		}

		EnumMap<Player, Double> pruneUtilities = parentPruneUtilities.clone();
		EnumMap<Player, Double> utilities = makeMinimumValueMap();

		for (GameBoard potentialState : board.getSuccessorStates()) {

			EnumMap<Player, Double> potentialStateUtilities = calculateEventualUtilities(potentialState, pruneUtilities);

			if ((utilities.get(board.getCurrentPlayer()) + FUDGE) < potentialStateUtilities.get(board.getCurrentPlayer())) {
				utilities = potentialStateUtilities;
			}

			for (Player player : pruneUtilities.keySet()) {
				if ((player != board.getCurrentPlayer()) && ((utilities.get(player) + FUDGE) < pruneUtilities.get(player))) {
					return utilities;
				}
			}

			pruneUtilities.put(board.getCurrentPlayer(), Math.max(pruneUtilities.get(board.getCurrentPlayer()), utilities.get(board.getCurrentPlayer())));
		}
		
		return utilities;
	}

	private static EnumMap<Player, Double> makeMinimumValueMap() 
	{
		EnumMap<Player, Double> utilities = new EnumMap<>(Player.class);

		utilities.put(Player.PLAYER1, Double.NEGATIVE_INFINITY);
		utilities.put(Player.PLAYER2, Double.NEGATIVE_INFINITY);

		return utilities;
	}
}
