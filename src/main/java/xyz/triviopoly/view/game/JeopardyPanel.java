package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xyz.triviopoly.model.Category;
import xyz.triviopoly.model.Question;
import xyz.triviopoly.model.Round;

public class JeopardyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static JeopardyPanel instance;

	private JLabel[] lblCategories;
	private JLabel[][] questions;

	private JeopardyPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
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
		questions = new JLabel[categories.size()][5];
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			questions[i] = new JLabel[5];
			for (int j = 0; j < 5; j++) {
				c.gridx = i;
				c.gridy = j + 2;
				Question question = category.getQuestions().get(j);
				questions[i][j] = new JLabel("$"
						+ (question.getDifficulty() * round.dollarMultiplier()));
				questions[i][j].setOpaque(true);
				questions[i][j].setBorder(BorderFactory.createLineBorder(
						Color.GREEN, 3));
				questions[i][j].setBackground(Color.BLUE);
				questions[i][j].setForeground(Color.GREEN);
				questions[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				questions[i][j].setVerticalAlignment(SwingConstants.CENTER);
				add(questions[i][j], c);
			}
		}
	}

	public static JeopardyPanel getInstance() {
		if (instance == null) {
			instance = new JeopardyPanel();
		}
		return instance;
	}

}
