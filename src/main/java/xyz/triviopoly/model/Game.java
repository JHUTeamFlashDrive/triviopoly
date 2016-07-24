package xyz.triviopoly.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static Game instance = new Game();

	private List<Player> players;
	private Player currentPlayer;
	private Round round = Round.SINGLE_TRIVIOPOLY;
	private int spinCount;
	private List<Category> categories;
	private Question selectedQuestion;

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

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public int getSpinCount() {
		return spinCount;
	}

	public void setSpinCount(int spinCount) {
		this.spinCount = spinCount;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}
}
