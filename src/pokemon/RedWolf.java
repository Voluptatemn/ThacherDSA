package pokemon;

public class RedWolf extends Pokemon{
	
	public RedWolf () {
		super("RedWolf", 2, 80, "red-wolf-of-radagon.png", new Move[] {
				new Move("RedWolf's Magic Glintblade", 2, 70, 0),
				new Move("RedWolf's Lunges", 2, 0, 3),
				new Move("RedWolf's comet", 2, 40, 0),
				new Move("RedWolf's swing", 0, 40, 0)
		});
	}

}
