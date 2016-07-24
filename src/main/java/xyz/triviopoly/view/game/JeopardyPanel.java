package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Round;

public class JeopardyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static final int SELECTION_NUMBER_OF_TICKS = 12;

	private static JeopardyPanel instance;

	private JLabel[] lblCategories;
	private JLabel[][] lblQuestions;
	private Timer timer;
	private JLabel selectedQuestion;
	private int selectionTick;

	private JeopardyPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		setBackground(Color.BLUE);
		timer = new Timer(100, this);
	}

	public void initialize(Round round, List<Category> categories) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 4;
		for (int i = 0; i < categories.size(); i++) {
			c.gridx = i;
			JLabel categoryNumber = new JLabel("" + (i + 1));
			categoryNumber.setOpaque(true);
			categoryNumber.setBorder(BorderFactory.createLineBorder(
					Color.GREEN, 3));
			categoryNumber.setBackground(Color.BLUE);
			categoryNumber.setForeground(Color.GREEN);
			categoryNumber.setHorizontalAlignment(SwingConstants.CENTER);
			categoryNumber.setVerticalAlignment(SwingConstants.CENTER);
			add(categoryNumber, c);
		}
		c.weighty = 8;
		c.gridy = 1;
		lblCategories = new JLabel[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			c.gridx = i;
			lblCategories[i] = new JLabel(category.getName());
			lblCategories[i].setOpaque(true);
			lblCategories[i].setBorder(BorderFactory.createLineBorder(
					Color.GREEN, 3));
			lblCategories[i].setBackground(Color.BLUE);
			lblCategories[i].setForeground(Color.GREEN);
			lblCategories[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblCategories[i].setVerticalAlignment(SwingConstants.CENTER);
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

	public void selectQuestion(int categoryNumber, int questionNumber) {
		selectedQuestion = lblQuestions[categoryNumber][questionNumber];
		selectionTick = 0;
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (selectionTick % 2 == 0) {
			selectedQuestion.setBackground(Color.WHITE);
		} else {
			selectedQuestion.setBackground(Color.BLUE);
		}
		if (selectionTick >= SELECTION_NUMBER_OF_TICKS) {
			selectedQuestion.setBackground(Color.BLUE);
			timer.stop();
			GameController.getInstance().selectionFinished();
		}
		selectionTick += 1;
	}

	public static JeopardyPanel getInstance() {
		if (instance == null) {
			instance = new JeopardyPanel();
		}
		return instance;
	}

}
