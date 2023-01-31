package pokemon;

import java.util.ArrayList;

public class TeamShaun extends PokeTeam {

	public TeamShaun() {
		super("Team Shaun");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new Squidward());
		team.add(new Squidward());

		team.add(new Squidward());
		return team;
	}

}
