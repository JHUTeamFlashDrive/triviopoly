package xyz.triviopoly.controller;

import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.BankruptPanel;
import xyz.triviopoly.view.game.ScoreboardPanel;

public class BankruptHandler implements SectorHandler {
	private static BankruptHandler instance;

	private Game game = Game.getInstance();

	private BankruptHandler() {
	}

	public static BankruptHandler getInstance() {
		if (instance == null) {
			instance = new BankruptHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector == Sector.BANKRUPT;
	}

	@Override
	public void handle(Sector sector) {
		Player currentPlayer = game.getCurrentPlayer();
		if (currentPlayer.getRoundScore() > 0) {
			currentPlayer.setTotalScore(currentPlayer.getTotalScore()
					- currentPlayer.getRoundScore());
			currentPlayer.setRoundScore(0);
		}
		ScoreboardPanel.getInstance().update(game.getPlayers(), currentPlayer);
		BankruptPanel bankruptPanel = new BankruptPanel();
		TriviopolyWindow.getInstance().displayContentPanel(bankruptPanel);
		bankruptPanel.startNotification(1200);
	}

	public void notificationFinished() {
		GameController.getInstance().spinFinished(true);
	}
}
