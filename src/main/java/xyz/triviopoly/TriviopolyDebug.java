package xyz.triviopoly;

import java.util.List;

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;

public class TriviopolyDebug {

	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.setNumberOfPlayers(3);
		List<Player> players = game.getPlayers();
		players.get(0).setName("Joe");
		players.get(1).setName("Steve");
		players.get(2).setName("Mary");
		for (Player player : players) {
			player.setFreeSpinCount(10);
		}
		game.setCurrentPlayer(players.get(0));
		GameController.getInstance().initialize();
	}

}
