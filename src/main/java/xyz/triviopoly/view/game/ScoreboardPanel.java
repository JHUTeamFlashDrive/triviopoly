package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel[] playerScores;

	public ScoreboardPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		BufferedImage playerIcon;
		try {
			playerIcon = ImageIO.read(new File("images/avatar.png"));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		playerScores = new JLabel[4];
		for (int i = 0; i < 4; i++) {
			c.gridx = i * 2;
			c.gridy = 0;
			c.gridheight = 2;
			add(new ImagePanel("images/avatar.png", 0.5), c);
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
