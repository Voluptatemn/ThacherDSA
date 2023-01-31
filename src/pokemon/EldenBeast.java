package pokemon;

public class EldenBeast extends Pokemon{
	public EldenBeast () {
		super("EldenBeast", 0, 205, "Elden-beast-boss-elden-ring-wiki-guide.png", new Move[] {
				new Move("Elden Star", 0, 135, 0),
				new Move("Wave of Gold", 1, 100, 4),
				new Move("Nebula", 2, 100, 1),
				new Move("Divine Punishment", 0, 210, 0)
				
		});
	}
}
