package xyz.triviopoly;

import java.util.List;

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Round;

public class TrivipolyFinallyDebug {

	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.setNumberOfPlayers(4);
		List<Player> players = game.getPlayers();
		players.get(0).setName("Joe");
		players.get(0).setRoundScore(1000);
		players.get(0).setTotalScore(3000);
		players.get(1).setName("Steve");
		players.get(1).setRoundScore(5000);
		players.get(1).setTotalScore(10000);
		players.get(2).setName("Mary");
		players.get(2).setRoundScore(3500);
		players.get(2).setTotalScore(7500);
		players.get(3).setName("Bob");
		players.get(3).setRoundScore(7000);
		players.get(3).setTotalScore(13500);
		game.setCurrentPlayer(players.get(0));
		game.setRound(Round.DOUBLE_TRIVIOPOLY);
		game.setSpinCount(48);
		GameController.getInstance().initialize();
	}
}
