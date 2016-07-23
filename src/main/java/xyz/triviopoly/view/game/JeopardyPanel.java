package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JeopardyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static JeopardyPanel instance;

	private JLabel[] categories;
	private JLabel[][] questions;

	private JeopardyPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 4;
		for (int i = 0; i < 6; i++) {
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
		categories = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			c.gridx = i;
			categories[i] = new JLabel("Category " + (i + 1));
			categories[i].setOpaque(true);
			categories[i].setBorder(BorderFactory.createLineBorder(Color.GREEN,
					3));
			categories[i].setBackground(Color.BLUE);
			categories[i].setForeground(Color.GREEN);
			categories[i].setHorizontalAlignment(SwingConstants.CENTER);
			categories[i].setVerticalAlignment(SwingConstants.CENTER);
			add(categories[i], c);
		}
		c.weighty = 16;
		questions = new JLabel[5][6];
		for (int i = 0; i < 5; i++) {
			questions[i] = new JLabel[6];
			for (int j = 0; j < 6; j++) {
				c.gridx = j;
				c.gridy = i + 2;
				questions[i][j] = new JLabel("$" + ((i + 1) * 100));
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
