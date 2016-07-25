package xyz.triviopoly.controller;

import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.game.JeopardyPanel;

public class ChoiceHandler implements SectorHandler {
	private static ChoiceHandler instance;

	private ChoiceHandler() {
	}

	public static ChoiceHandler getInstance() {
		if (instance == null) {
			instance = new ChoiceHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector == Sector.PLAYERS_CHOICE
				|| sector == Sector.OPPONENTS_CHOICE;
	}

	@Override
	public void handle(Sector sector) {
		JeopardyPanel.getInstance().selectCategory();
	}

	public void categorySelected(int categoryIndex) {
		Sector category = Sector.category(categoryIndex + 1);
		CategoryHandler.getInstance().handle(category);
	}

}