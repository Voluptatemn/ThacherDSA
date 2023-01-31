package pokemon;

public class Radahn extends Pokemon{
	
	public Radahn() {
		super("Radahn", 0, 160, "elden-ring-starscourge-radahn.png", new Move[] {
				new Move("Radahn Gravity Arrow", 0, 60, 0),
				new Move("Radahn's Collapsing Star", 0, 130, 0),
				new Move("Radahn's quick Swings", 0, 30, 0),
				new Move("Radahn's Starcaller Cry", 0, 40, 4),
		});
	}

}
