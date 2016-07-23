package xyz.triviopoly.controller;

import java.util.List;

import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.opening.NumberOfPlayersPanel;
import xyz.triviopoly.view.opening.OpeningPanel;
import xyz.triviopoly.view.opening.PlayerNamesPanel;
import xyz.triviopoly.view.opening.StartPanel;

public class OpeningController {
	private static OpeningController instance;

	private Game game = Game.getInstance();

	private OpeningController() {
	}

	public static OpeningController getInstance() {
		if (instance == null) {
			instance = new OpeningController();
		}
		return instance;
	}

	public void initialize() {
		OpeningPanel openingPanel = OpeningPanel.getInstance();
		openingPanel.displayContentPanel(StartPanel.getInstance());
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(openingPanel);
	}

	public void startGame() {
		OpeningPanel.getInstance().displayContentPanel(
				NumberOfPlayersPanel.getInstance());
	}

	public void numberOfPlayersSpecified(int numberOfPlayers) {
		game.setNumberOfPlayers(numberOfPlayers);
		PlayerNamesPanel playerNamesPanel = PlayerNamesPanel.getInstance();
		playerNamesPanel.update(numberOfPlayers);
		OpeningPanel.getInstance().displayContentPanel(playerNamesPanel);
	}

	public void playerNamesSpecified(List<String> playerNames) {
		List<Player> players = game.getPlayers();
		for (int i = 0; i < playerNames.size(); i++) {
			Player player = players.get(i);
			player.setName(playerNames.get(i));
		}
		GameController.getInstance().initialize();
	}

}
