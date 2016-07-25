package xyz.triviopoly.controller;

import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.LoseTurnPanel;

public class LoseTurnHandler implements SectorHandler {
	private static LoseTurnHandler instance;

	private LoseTurnHandler() {
	}

	public static LoseTurnHandler getInstance() {
		if (instance == null) {
			instance = new LoseTurnHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector == Sector.LOSE_TURN;
	}

	@Override
	public void handle(Sector sector) {
		LoseTurnPanel loseTurnPanel = new LoseTurnPanel();
		TriviopolyWindow.getInstance().displayContentPanel(loseTurnPanel);
		loseTurnPanel.startNotification(1200);
	}

	public void notificationFinished() {
		GameController.getInstance().checkFreeSpinToken();
	}
}
