package xyz.triviopoly;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static Game instance = new Game();

	private List<Player> players;

	public static Game getInstance() {
		return instance;
	}

	private Game() {
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		players = new ArrayList<>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player());
		}
	}

	public List<Player> getPlayers() {
		return players;
	}
}
