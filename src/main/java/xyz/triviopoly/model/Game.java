package xyz.triviopoly.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static Game instance = new Game();

	private List<Player> players;
	private int currentPlayersTurn;
	private int round;
	private int spinCount;

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

	public int getCurrentPlayersTurn() {
		return currentPlayersTurn;
	}

	public void setCurrentPlayersTurn(int currentPlayersTurn) {
		this.currentPlayersTurn = currentPlayersTurn;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getSpinCount() {
		return spinCount;
	}

	public void setSpinCount(int spinCount) {
		this.spinCount = spinCount;
	}
}
