package pokemon;

import java.util.ArrayList;

public class Team3 extends PokeTeam{
	public Team3() {
		super("Team 3");
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new Rykard());
		team.add(new ErdTreeAvatar());

		team.add(new Skeletons());
		return team;
	}
}
