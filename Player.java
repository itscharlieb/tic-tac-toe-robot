import java.util.EnumMap;


public enum Player {
	PLAYER1(TileValue.Ex),
	PLAYER2(TileValue.Oh);
	
	public final TileValue team;

	private Player(TileValue team) {
		this.team = team;
	}
	
}
