package pokemon;

import java.util.ArrayList;

public class TeamBeast extends PokeTeam{
	public TeamBeast() {
		super("Team Beast");
	}

	@Override
	public ArrayList<Pokemon> createTeam() {
		ArrayList<Pokemon> team = new ArrayList<Pokemon>();
		team.add(new Boar());
		team.add(new WarHawk());

		team.add(new EldenBeast());
		return team;
	}
}


