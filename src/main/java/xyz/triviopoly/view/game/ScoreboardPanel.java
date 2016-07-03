package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel[] playerScores;

	public ScoreboardPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		playerScores = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			c.gridx = i * 2;
			c.gridy = 0;
			c.gridheight = 2;
			JLabel playerImg = new JLabel("PlayerImg");
			add(playerImg, c);
			c.gridx = i * 2 + 1;
			c.gridheight = 1;
			JLabel playerName = new JLabel("Player " + (i + 1));
			add(playerName, c);
			c.gridy = 1;
			playerScores[i] = new JLabel("0");
			add(playerScores[i], c);
		}
	}

}
