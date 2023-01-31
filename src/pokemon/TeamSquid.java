package pokemon;

import java.util.ArrayList;

public class TeamSquid extends PokeTeam {

	public TeamSquid() {
		super("Team Squid");
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new ShaunTheSheep());
		team.add(new ShaunTheSheep());

		team.add(new ShaunTheSheep());
		return team;
	}

}
