package xyz.triviopoly.controller;

import java.util.List;

import xyz.triviopoly.dao.CqadDao;
import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.GamePanel;
import xyz.triviopoly.view.game.JeopardyPanel;
import xyz.triviopoly.view.game.ScoreboardPanel;
import xyz.triviopoly.view.game.WheelPanel;

public class GameController {
	private static GameController instance = new GameController();

	private Game game = Game.getInstance();

	private GameController() {
	}

	public static GameController getInstance() {
		return instance;
	}

	public void initialize() {
		List<Category> categories = CqadDao.getInstance()
				.getGameboardCategories(6, null);
		game.setCategories(categories);

		ScoreboardPanel.getInstance().initialize(game.getPlayers(),
				game.getCurrentPlayer());
		JeopardyPanel.getInstance().initialize(game.getRound(), categories);

		GamePanel gamePanel = GamePanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(gamePanel);
	}

	public void spin() {
		WheelPanel.getInstance().spin();
	}

	public void spinResult(Sector sector) {
		if (sector.categorySector()) {
			// handle category
		} else if (sector == Sector.FREE_SPIN) {

		} else if (sector == Sector.LOSE_SPIN) {

		} else if (sector == Sector.SPIN_AGAIN) {

		} else if (sector == Sector.BANKRUPT) {

		} else if (sector == Sector.PLAYERS_CHOICE) {

		} else if (sector == Sector.OPPONENTS_CHOICE) {

		}
	}
}
