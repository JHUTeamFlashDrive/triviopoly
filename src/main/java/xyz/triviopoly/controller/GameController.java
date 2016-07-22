package xyz.triviopoly.controller;

import xyz.triviopoly.view.game.WheelPanel;

public class GameController {
	private static GameController instance;

	private GameController() {
	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	public void spin() {
		WheelPanel.getInstance().spin();
	}

}
