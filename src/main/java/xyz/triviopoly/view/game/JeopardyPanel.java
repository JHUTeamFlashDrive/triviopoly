package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JeopardyPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel[] categories;
	private JLabel[][] questions;

	public JeopardyPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		for (int i = 0; i < 5; i++) {
			c.gridx = i;
			JLabel categoryNumber = new JLabel("" + (i + 1));
			categoryNumber.setOpaque(true);
			categoryNumber.setBackground(Color.BLUE);
			categoryNumber.setForeground(Color.GREEN);
			add(categoryNumber, c);
		}
		c.weighty = 2;
		c.gridy = 1;
		categories = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			c.gridx = i;
			categories[i] = new JLabel("Category " + (i + 1));
			categories[i].setOpaque(true);
			categories[i].setBackground(Color.BLUE);
			categories[i].setForeground(Color.GREEN);
			add(categories[i], c);
		}
		c.weighty = 3;
		questions = new JLabel[5][5];
		for (int i = 0; i < 5; i++) {
			questions[i] = new JLabel[5];
			for (int j = 0; j < 5; j++) {
				c.gridx = j;
				c.gridy = i + 2;
				questions[i][j] = new JLabel("$" + ((i + 1) * 100));
				questions[i][j].setOpaque(true);
				questions[i][j].setBackground(Color.BLUE);
				questions[i][j].setForeground(Color.GREEN);
				add(questions[i][j], c);
			}
		}
	}

}
