package pokemon;

public class ErdTreeAvatar extends Pokemon{
	
	public ErdTreeAvatar() {
		super("ErdTreeAvatar", 3, 120, "Elden_WestLiurnia_ErdtreeAvatar.png", new Move[] {
				new Move("Avatar's Swing", 0, 40, 0),
				new Move("Avatar's Golden Land", 3, 70, 0),
				new Move("Avatar's Holy beam", 3, 20, 4),
				new Move("Avatar's Stomp", 0, 30, 4)
		});
	}
}
