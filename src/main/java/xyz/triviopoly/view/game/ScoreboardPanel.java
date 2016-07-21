package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static ScoreboardPanel instance;

	private JLabel[] playerScores;

	private ScoreboardPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();

		playerScores = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			c.gridx = i * 2;
			c.gridy = 0;
			c.gridheight = 2;
			c.weightx = 0;
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 0, 0, 0);
			add(new ImagePanel("images/avatar.png", 0.5), c);
			c.gridx = i * 2 + 1;
			c.gridheight = 1;
			c.weightx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 20, 0, 0);
			JLabel playerName = new JLabel("Player " + (i + 1));
			add(playerName, c);
			c.gridy = 1;
			playerScores[i] = new JLabel("0");
			add(playerScores[i], c);
		}
	}

	public static ScoreboardPanel getInstance() {
		if (instance == null) {
			instance = new ScoreboardPanel();
		}
		return instance;
	}

}
