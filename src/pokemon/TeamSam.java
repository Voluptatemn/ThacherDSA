package pokemon;

import java.util.ArrayList;

public class TeamSam extends PokeTeam {

	public TeamSam() {
		super("Team Sam");
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new Rani());
		team.add(new Radagon());

		team.add(new Placidusax());
		return team;
	}

}