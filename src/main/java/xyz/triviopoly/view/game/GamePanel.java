package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static GamePanel instance;

	private GamePanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		// setBorder(BorderFactory.createEmptyBorder(0, 25, 25, 25));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.gridwidth = 2;
		add(RoundPanel.getInstance(), c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 25, 10, 25);
		add(ScoreboardPanel.getInstance(), c);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, 25, 25, 10);
		add(SpinPanel.getInstance(), c);
		c.gridx = 1;
		c.weightx = 1;
		c.insets = new Insets(10, 10, 25, 25);
		JPanel jeopardyContainer = new JPanel(new GridBagLayout());
		jeopardyContainer.setBackground(Color.WHITE);
		GridBagConstraints jc = new GridBagConstraints();
		jeopardyContainer.add(JeopardyPanel.getInstance(), jc);
		add(jeopardyContainer, c);
	}

	public static GamePanel getInstance() {
		if (instance == null) {
			instance = new GamePanel();
		}
		return instance;
	}
}
