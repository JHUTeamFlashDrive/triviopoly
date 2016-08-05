package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import xyz.triviopoly.controller.CategoryHandler;
import xyz.triviopoly.controller.ChoiceHandler;
import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Round;

public class JeopardyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static final int SELECTION_NUMBER_OF_TICKS = 12;

	private static JeopardyPanel instance;

	private enum State {
		NORMAL, HIGHLIGHTING_QUESTION, SELECTING_CATEGORY
	}

	private State state = State.NORMAL;
	private JButton[] btnCategoryNumbers;
	private JLabel[] lblCategories;
	private JLabel[][] lblQuestions;
	private Timer timer;
	private JLabel selectedQuestion;
	private int tick;

	private JeopardyPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(630,546));
		timer = new Timer(100, this);
	}

	private class CategoryPickedActionListener implements ActionListener {

		private int categoryNumber;

		public CategoryPickedActionListener(int categoryNumber) {
			this.categoryNumber = categoryNumber;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (state == State.SELECTING_CATEGORY) {
				state = State.NORMAL;
				btnCategoryNumbers[(tick + 1) % btnCategoryNumbers.length]
						.setBackground(Color.BLUE);
				timer.stop();
				ChoiceHandler.getInstance().categorySelected(categoryNumber);
			}
		}
	}

	public void initialize(Round round, List<Category> categories) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 4;
		btnCategoryNumbers = new JButton[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			c.gridx = i;
			btnCategoryNumbers[i] = new JButton("" + (i + 1));
			btnCategoryNumbers[i].setOpaque(true);
			btnCategoryNumbers[i].setBorder(BorderFactory.createLineBorder(
					Color.GREEN, 3));
			btnCategoryNumbers[i].setBackground(Color.BLUE);
			btnCategoryNumbers[i].setForeground(Color.GREEN);
			btnCategoryNumbers[i].setHorizontalAlignment(SwingConstants.CENTER);
			btnCategoryNumbers[i].setVerticalAlignment(SwingConstants.CENTER);
			btnCategoryNumbers[i]
					.addActionListener(new CategoryPickedActionListener(i));
			add(btnCategoryNumbers[i], c);
		}
		c.weighty = 8;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		lblCategories = new JLabel[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			c.gridx = i;
			lblCategories[i] = new JLabel();
			lblCategories[i].setOpaque(true);
			lblCategories[i].setBorder(BorderFactory.createLineBorder(
					Color.GREEN, 3));
			lblCategories[i].setBackground(Color.BLUE);
			lblCategories[i].setForeground(Color.GREEN);
			lblCategories[i].setText("<html><body style='width: 60px; text-align: center'>" + category.getName() + "</body></html>");
			lblCategories[i].setHorizontalAlignment(JLabel.CENTER);
			add(lblCategories[i], c);
		}
		c.weighty = 16;
		lblQuestions = new JLabel[categories.size()][5];
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			lblQuestions[i] = new JLabel[5];
			for (int j = 0; j < 5; j++) {
				c.gridx = i;
				c.gridy = j + 2;
				Question question = category.getQuestions().get(j);
				lblQuestions[i][j] = new JLabel("$"
						+ (question.getDifficulty() * round.dollarMultiplier()));
				lblQuestions[i][j].setOpaque(true);
				lblQuestions[i][j].setBorder(BorderFactory.createLineBorder(
						Color.GREEN, 3));
				lblQuestions[i][j].setBackground(Color.BLUE);
				lblQuestions[i][j].setForeground(Color.GREEN);
				lblQuestions[i][j]
						.setHorizontalAlignment(SwingConstants.CENTER);
				lblQuestions[i][j].setVerticalAlignment(SwingConstants.CENTER);
				add(lblQuestions[i][j], c);
			}
		}
	}

	public void update(Round round, List<Category> categories) {
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			List<Question> questions = category.getQuestions();
			for (int j = 0; j < questions.size(); j++) {
				Question question = questions.get(j);
				if (question.isAnswered()) {
					lblQuestions[i][j].setText("");
				} else {
					lblQuestions[i][j].setText("$"
							+ (question.getDifficulty() * round
									.dollarMultiplier()));
				}
			}
		}
	}

	public void highlightQuestion(int categoryNumber, int questionNumber) {
		selectedQuestion = lblQuestions[categoryNumber][questionNumber];
		tick = 0;
		state = State.HIGHLIGHTING_QUESTION;
		timer.start();
	}

	public void selectCategory() {
		tick = 0;
		state = State.SELECTING_CATEGORY;
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tick += 1;
		if (state == State.HIGHLIGHTING_QUESTION) {
			highlightSelectedQuestion();
		} else if (state == State.SELECTING_CATEGORY) {
			highlightCategories();
		}
	}

	private void highlightCategories() {
		int lastHighlightedCategory = tick % btnCategoryNumbers.length;
		int highlightCategory = (tick + 1) % btnCategoryNumbers.length;
		btnCategoryNumbers[lastHighlightedCategory].setBackground(Color.BLUE);
		btnCategoryNumbers[highlightCategory].setBackground(Color.WHITE);
	}

	private void highlightSelectedQuestion() {
		if (tick % 2 == 0) {
			selectedQuestion.setBackground(Color.WHITE);
		} else {
			selectedQuestion.setBackground(Color.BLUE);
		}
		if (tick >= SELECTION_NUMBER_OF_TICKS) {
			selectedQuestion.setBackground(Color.BLUE);
			timer.stop();
			state = State.NORMAL;
			CategoryHandler.getInstance().questionHighlightFinished();
		}
	}

	public static JeopardyPanel getInstance() {
		if (instance == null) {
			instance = new JeopardyPanel();
		}
		return instance;
	}

}
