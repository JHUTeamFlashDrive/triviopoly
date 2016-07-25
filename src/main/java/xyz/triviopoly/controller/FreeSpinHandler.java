package xyz.triviopoly.controller;

import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.FreeSpinPanel;
import xyz.triviopoly.view.game.ScoreboardPanel;

public class FreeSpinHandler implements SectorHandler {
	private static FreeSpinHandler instance;

	private Game game = Game.getInstance();

	private FreeSpinHandler() {
	}

	public static FreeSpinHandler getInstance() {
		if (instance == null) {
			instance = new FreeSpinHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector == Sector.FREE_SPIN;
	}

	@Override
	public void handle(Sector sector) {
		Player player = game.getCurrentPlayer();
		player.setFreeSpinCount(player.getFreeSpinCount() + 1);
		ScoreboardPanel.getInstance().update(game.getPlayers(), player);
		FreeSpinPanel freeSpinPanel = new FreeSpinPanel();
		TriviopolyWindow.getInstance().displayContentPanel(freeSpinPanel);
		freeSpinPanel.startNotification(1200);
	}

	public void notificationFinished() {
		GameController.getInstance().spinFinished(false);
	}
}
