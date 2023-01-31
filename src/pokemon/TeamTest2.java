package pokemon;

import java.util.ArrayList;

public class TeamTest2 extends PokeTeam{
	
	public TeamTest2() {
		super("Team Test2");
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new Skeletons());
		team.add(new RedWolf());

		team.add(new Radahn());
		return team;
	}
}
