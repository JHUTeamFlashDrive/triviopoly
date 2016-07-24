package xyz.triviopoly.controller;

import java.util.List;

import xyz.triviopoly.dao.CqadDao;
import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.BankruptPanel;
import xyz.triviopoly.view.game.FreeSpinPanel;
import xyz.triviopoly.view.game.GamePanel;
import xyz.triviopoly.view.game.JeopardyPanel;
import xyz.triviopoly.view.game.QuestionPanel;
import xyz.triviopoly.view.game.ScoreboardPanel;
import xyz.triviopoly.view.game.SpinAgainPanel;
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
			int categoryIndex = sector.categoryNumber() - 1;
			Category category = game.getCategories().get(categoryIndex);
			List<Question> questions = category.getQuestions();
			for (int i = 0; i < questions.size(); i++) {
				Question question = questions.get(i);
				if (!question.isAnswered()) {
					game.setSelectedQuestion(question);
					JeopardyPanel.getInstance()
							.selectQuestion(categoryIndex, i);
					break;
				}
			}
		} else if (sector == Sector.FREE_SPIN) {
			Player player = game.getCurrentPlayer();
			player.setFreeSpinCount(player.getFreeSpinCount() + 1);
			ScoreboardPanel.getInstance().update(game.getPlayers(), player);
			FreeSpinPanel freeSpinPanel = new FreeSpinPanel();
			TriviopolyWindow.getInstance().displayContentPanel(freeSpinPanel);
			freeSpinPanel.startNotification(1200);
		} else if (sector == Sector.LOSE_SPIN) {

		} else if (sector == Sector.SPIN_AGAIN) {
			SpinAgainPanel spinAgainPanel = new SpinAgainPanel();
			TriviopolyWindow.getInstance().displayContentPanel(spinAgainPanel);
			spinAgainPanel.startNotification(1200);
		} else if (sector == Sector.BANKRUPT) {
			Player player = game.getCurrentPlayer();
			player.setTotalScore(player.getTotalScore()
					- player.getRoundScore());
			player.setRoundScore(0);
			ScoreboardPanel.getInstance().update(game.getPlayers(), player);
			BankruptPanel bankruptPanel = new BankruptPanel();
			TriviopolyWindow.getInstance().displayContentPanel(bankruptPanel);
			bankruptPanel.startNotification(1200);
		} else if (sector == Sector.PLAYERS_CHOICE) {

		} else if (sector == Sector.OPPONENTS_CHOICE) {

		}
	}

	public void selectionFinished() {
		QuestionPanel questionPanel = QuestionPanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(questionPanel);
		questionPanel.setQuestion(game.getSelectedQuestion());
	}

	public void answerGiven(String answer) {
		boolean correct = answer.toLowerCase().equals(
				game.getSelectedQuestion().getAnswer().toLowerCase());
		QuestionPanel.getInstance().displayResult(correct);
	}

	public void questionFinished() {
		GamePanel gamePanel = GamePanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(gamePanel);
	}

	public void notificationFinished() {
		GamePanel gamePanel = GamePanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(gamePanel);
	}
}
