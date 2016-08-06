package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Player;

public class FinalPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton btnOk;

	public FinalPanel(List<Player> players) {
		setLayout(new GridBagLayout());
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		players = rankPlayers(players);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 40, 10, 40);
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		JLabel lblWinners = new JLabel("Winners!");
		lblWinners.setBackground(Color.BLUE);
		lblWinners.setForeground(Color.WHITE);
		lblWinners.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinners.setFont(new Font("Comic Sans MS", Font.PLAIN, 128));
		add(lblWinners, c);
		c.insets = new Insets(8, 40, 10, 40);
		c.gridwidth = 1;
		String[] ranks = { "1st", "2nd", "3rd", "4th" };
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			c.gridx = 0;
			c.gridy = i + 1;
			JLabel lblRank = createLabel(ranks[i], 64);
			add(lblRank, c);
			c.gridx = 1;
			JLabel lblPlayer = createLabel(player.getName(), 64);
			add(lblPlayer, c);
			c.gridx = 2;
			JLabel lblScore = createLabel("" + player.getTotalScore(), 64);
			add(lblScore, c);
		}
		c.gridx = 0;
		c.gridy = 1 + players.size();
		c.gridwidth = 3;
		btnOk = new JButton("OK");
		btnOk.setBackground(Color.BLUE);
		btnOk.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnOk.setPreferredSize(new Dimension(200, 75));
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().finalScoreDisplayFinished();
			}

		});
		add(btnOk, c);
		btnOk.requestFocusInWindow();
	}

	private JLabel createLabel(String text, int fontSize) {
		JLabel label = new JLabel(text);
		label.setBackground(Color.BLUE);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
		return label;
	}

	private List<Player> rankPlayers(List<Player> players) {
		List<Player> rankedPlayers = new ArrayList<>(players);
		rankedPlayers.sort(new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				return -((int) Math.signum(o1.getTotalScore()
						- o2.getTotalScore()));
			}

		});
		return rankedPlayers;
	}

}
