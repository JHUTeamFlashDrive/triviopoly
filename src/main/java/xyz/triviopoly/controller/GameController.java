package xyz.triviopoly.controller;

import java.util.List;

import xyz.triviopoly.dao.CqadDao;
import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Round;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.AskUseFreeSpinPanel;
import xyz.triviopoly.view.game.FinalPanel;
import xyz.triviopoly.view.game.GamePanel;
import xyz.triviopoly.view.game.JeopardyPanel;
import xyz.triviopoly.view.game.RoundPanel;
import xyz.triviopoly.view.game.ScoreboardPanel;
import xyz.triviopoly.view.game.WheelPanel;

public class GameController {
	private static GameController instance = new GameController();

	private static SectorHandler[] sectorHandlers = {
			CategoryHandler.getInstance(), FreeSpinHandler.getInstance(),
			LoseTurnHandler.getInstance(), SpinAgainHandler.getInstance(),
			BankruptHandler.getInstance(), ChoiceHandler.getInstance() };

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

		RoundPanel.getInstance().initialize(game.getRound(),
				game.getSpinCount());
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
		for (SectorHandler sectorHandler : sectorHandlers) {
			if (sectorHandler.handles(sector)) {
				sectorHandler.handle(sector);
				break;
			}
		}
	}

	public void checkFreeSpinToken() {
		Player currentPlayer = game.getCurrentPlayer();
		if (currentPlayer.getFreeSpinCount() > 0) {
			TriviopolyWindow window = TriviopolyWindow.getInstance();
			window.displayContentPanel(new AskUseFreeSpinPanel());
		} else {
			spinFinished(true);
		}
	}

	public void askUseFreeSpinResult(boolean useIt) {
		boolean nextPlayersTurn;
		if (useIt) {
			Player currentPlayer = game.getCurrentPlayer();
			currentPlayer
					.setFreeSpinCount(currentPlayer.getFreeSpinCount() - 1);
			nextPlayersTurn = false;
		} else {
			nextPlayersTurn = true;
		}
		spinFinished(nextPlayersTurn);
	}

	public void spinFinished(boolean nextPlayersTurn) {
		if (nextPlayersTurn) {
			nextPlayersTurn();
		}
		game.setSpinCount(game.getSpinCount() + 1);
		if (game.getSpinCount() == game.getRound().spinLimit() || questionsAllAnswered()) {
			if (game.getRound() == Round.SINGLE_TRIVIOPOLY) {
				doubleTriviopoly();
			} else {
				finishGame();
				return;
			}
		}
		GamePanel gamePanel = GamePanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(gamePanel);
		JeopardyPanel.getInstance().update(game.getRound(),
				game.getCategories());
		ScoreboardPanel.getInstance().update(game.getPlayers(),
				game.getCurrentPlayer());
		RoundPanel.getInstance().update(game.getRound(), game.getSpinCount());
	}
	
	private boolean questionsAllAnswered() {
		for(Category category : game.getCategories()) {
			for(Question question : category.getQuestions()) {
				if(!question.isAnswered()) {
					return false;
				}
			}
		}
		return true;
	}

	private void doubleTriviopoly() {
		game.setRound(Round.DOUBLE_TRIVIOPOLY);
		game.setSpinCount(0);
		List<Category> categories = CqadDao.getInstance()
				.getGameboardCategories(6, game.getCategories());
		game.setCategories(categories);
		for (Player player : game.getPlayers()) {
			player.setRoundScore(0);
		}
	}

	private void finishGame() {
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(new FinalPanel(game.getPlayers()));
	}

	private void nextPlayersTurn() {
		List<Player> players = game.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.equals(game.getCurrentPlayer())) {
				int currentPlayerIndex = (i + 1) % players.size();
				game.setCurrentPlayer(players.get(currentPlayerIndex));
				break;
			}
		}
	}

	public void finalScoreDisplayFinished() {
		OpeningController.getInstance().initialize();
	}
}
