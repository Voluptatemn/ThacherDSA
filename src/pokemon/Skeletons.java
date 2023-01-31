package pokemon;

public class Skeletons extends Pokemon {
	
	public Skeletons() {
		super ("Skeletons", 2, 10, "skeleton_scimitar_1 (1).png", new Move[] {
				new Move("Skeletons' swing", 0, 10, 0),
				new Move("Skeletons' block", 0, 0, 3),
				new Move("Skeletons'roar", 0, 0, 4),
				new Move("Skeletons' lunge", 0, 10, 0), 
		});
	}

}
