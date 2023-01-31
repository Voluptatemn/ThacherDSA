package pokemon;

public class Rykard extends Pokemon{
	public Rykard () {
		super("Rykard", 1, 120, "download (1).png", new Move[] {
				new Move("Rykard's Magma Leap", 1, 50, 0),
				new Move("Rykard's GroundShock", 0, 30, 4),
				new Move("Rykard's Rancor", 1, 30, 3),
				new Move("Rykard's Taker's Flame", 1, 80, 0)
		});
	}
}
