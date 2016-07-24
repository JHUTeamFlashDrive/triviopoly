package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import xyz.triviopoly.model.Player;

public class ScoreboardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static ScoreboardPanel instance;

	private ImagePanel[] playerIcons;
	private JLabel[] lblPlayerScores;
	private JLabel[] lblPlayerTurns;
	private JLabel[] lblFreeSpinCounts;

	private ScoreboardPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
	}

	public void initialize(List<Player> players, Player currentPlayer) {
		GridBagConstraints c = new GridBagConstraints();

		playerIcons = new ImagePanel[players.size()];
		lblPlayerScores = new JLabel[players.size()];
		lblPlayerTurns = new JLabel[players.size()];
		lblFreeSpinCounts = new JLabel[players.size()];
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			c.gridx = i * 4;
			c.gridy = 0;
			c.gridheight = 2;
			c.weightx = 0;
			c.fill = GridBagConstraints.NONE;
			c.insets = new Insets(0, 0, 0, 0);
			playerIcons[i] = new ImagePanel("images/avatar.png", 0.5);
			if (player.equals(currentPlayer)) {
				playerIcons[i].setBackground(Color.YELLOW);
			}
			add(playerIcons[i], c);
			c.gridx = i * 4 + 1;
			c.gridheight = 1;
			c.insets = new Insets(0, 20, 0, 0);
			JLabel playerName = new JLabel(player.getName());
			playerName.setFont(new Font("Helvetica", 1, 16));
			add(playerName, c);
			c.gridy = 1;
			lblPlayerScores[i] = new JLabel("Score: " + player.getRoundScore());
			lblPlayerScores[i].setFont(new Font("Helvetica", 1, 14));
			add(lblPlayerScores[i], c);
			c.gridx = i * 4 + 2;
			c.gridy = 0;
			c.weightx = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 2;
			lblPlayerTurns[i] = new JLabel();
			lblPlayerTurns[i].setForeground(Color.RED);
			if (player.equals(currentPlayer)) {
				lblPlayerTurns[i].setText("Your Turn!");
			}
			add(lblPlayerTurns[i], c);
			c.gridy = 1;
			c.weightx = 0;
			c.fill = GridBagConstraints.NONE;
			c.gridwidth = 1;
			add(new ImagePanel("images/free_spin.png", 0.1), c);
			c.gridx = i * 4 + 3;
			c.weightx = 1;
			c.insets = new Insets(0, 10, 0, 0);
			c.fill = GridBagConstraints.HORIZONTAL;
			lblFreeSpinCounts[i] = new JLabel("" + player.getFreeSpinCount());
			lblFreeSpinCounts[i].setFont(new Font("Helvetica", 1, 14));
			add(lblFreeSpinCounts[i], c);
		}
	}

	public void update(List<Player> players, Player currentPlayer) {
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.equals(currentPlayer)) {
				playerIcons[i].setBackground(Color.YELLOW);
				lblPlayerTurns[i].setText("Your Turn!");
			} else {
				playerIcons[i].setBackground(Color.WHITE);
				lblPlayerTurns[i].setText("");
			}
			lblPlayerScores[i].setText("Score: " + player.getRoundScore());
			lblFreeSpinCounts[i].setText("" + player.getFreeSpinCount());
		}
	}

	public static ScoreboardPanel getInstance() {
		if (instance == null) {
			instance = new ScoreboardPanel();
		}
		return instance;
	}

}
