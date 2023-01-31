package pokemon;

public class WarHawk extends Pokemon{
	
	public WarHawk() {
		super("WarHawk", 0, 5, "warhawk_enemies_elden_ring_wiki_600px.png", new Move[] {
				new Move("Charge", 0, 5, 0),
				new Move("fly", 0, 0, 0),
				new Move("Bite", 0, 0, 0),
				new Move("Shiver", 0, 0, 0)
		});
	}
}
