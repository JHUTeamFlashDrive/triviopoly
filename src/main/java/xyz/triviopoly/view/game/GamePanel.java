package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static GamePanel instance;

	private GamePanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(0, 25, 25, 25));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.weighty = 1;
		c.gridwidth = 2;
		add(ScoreboardPanel.getInstance(), c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 5;
		c.gridwidth = 1;
		add(SpinPanel.getInstance(), c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 5;
		add(JeopardyPanel.getInstance(), c);
	}

	public static GamePanel getInstance() {
		if (instance == null) {
			instance = new GamePanel();
		}
		return instance;
	}
}
