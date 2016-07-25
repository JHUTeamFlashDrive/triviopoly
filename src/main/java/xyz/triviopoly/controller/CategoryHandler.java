package xyz.triviopoly.controller;

import java.util.List;

import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Game;
import xyz.triviopoly.model.Player;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Sector;
import xyz.triviopoly.view.TriviopolyWindow;
import xyz.triviopoly.view.game.JeopardyPanel;
import xyz.triviopoly.view.game.QuestionPanel;

public class CategoryHandler implements SectorHandler {
	private static CategoryHandler instance;

	private Game game = Game.getInstance();
	private boolean correct;

	private CategoryHandler() {
	}

	public static CategoryHandler getInstance() {
		if (instance == null) {
			instance = new CategoryHandler();
		}
		return instance;
	}

	@Override
	public boolean handles(Sector sector) {
		return sector.categorySector();
	}

	@Override
	public void handle(Sector sector) {
		int categoryIndex = sector.categoryNumber() - 1;
		Category category = game.getCategories().get(categoryIndex);
		List<Question> questions = category.getQuestions();
		boolean allQuestionsAnswered = true;
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			if (!question.isAnswered()) {
				allQuestionsAnswered = false;
				game.setSelectedQuestion(question);
				JeopardyPanel.getInstance().highlightQuestion(categoryIndex, i);
				break;
			}
		}
		// all questions already answered for the category so we tell the player
		// to spin again
		if (allQuestionsAnswered) {
			SpinAgainHandler.getInstance().handle(Sector.SPIN_AGAIN);
		}
	}

	public void questionHighlightFinished() {
		QuestionPanel questionPanel = QuestionPanel.getInstance();
		TriviopolyWindow window = TriviopolyWindow.getInstance();
		window.displayContentPanel(questionPanel);
		questionPanel.setQuestion(game.getSelectedQuestion());
	}

	public void answerGiven(String answer) {
		correct = answer.toLowerCase().equals(
				game.getSelectedQuestion().getAnswer().toLowerCase());
		game.getSelectedQuestion().setAnswered(true);
		int dollarAmount = game.getSelectedQuestion().getDifficulty()
				* game.getRound().dollarMultiplier();
		if (!correct) {
			dollarAmount *= -1;
		}
		Player currentPlayer = game.getCurrentPlayer();
		currentPlayer.setRoundScore(currentPlayer.getRoundScore()
				+ dollarAmount);
		currentPlayer.setTotalScore(currentPlayer.getTotalScore()
				+ dollarAmount);
		QuestionPanel.getInstance().displayResult(correct);
	}

	public void questionFinished() {
		if (correct) {
			GameController.getInstance().spinFinished(false);
		} else {
			GameController.getInstance().checkFreeSpinToken();
		}
	}
}
