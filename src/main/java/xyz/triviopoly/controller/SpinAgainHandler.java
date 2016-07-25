package xyz.triviopoly.controller;

import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.SpinAgainPanel;

public class SpinAgainHandler implements SectorHandler {
	private static SpinAgainHandler instance;

	private SpinAgainHandler() {
	}

	public static SpinAgainHandler getInstance() {
		if (instance == null) {
			instance = new SpinAgainHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector == Sector.SPIN_AGAIN;
	}

	@Override
	public void handle(Sector sector) {
		SpinAgainPanel spinAgainPanel = new SpinAgainPanel();
		TriviopolyWindow.getInstance().displayContentPanel(spinAgainPanel);
		spinAgainPanel.startNotification(1200);
	}

	public void notificationFinished() {
		GameController.getInstance().spinFinished(false);
	}
}
