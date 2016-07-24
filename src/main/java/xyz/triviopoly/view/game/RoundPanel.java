package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import xyz.triviopoly.model.Round;

public class RoundPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static RoundPanel instance;

	private JLabel lblRoundName;
	private JLabel lblSpinsRemaining;

	private RoundPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 1;
		c.insets = new Insets(10, 10, 5, 10);
		c.anchor = GridBagConstraints.WEST;
		lblRoundName = new JLabel("");
		lblRoundName.setFont(new Font("Helvetica", Font.BOLD, 48));
		lblRoundName.setHorizontalAlignment(JLabel.LEFT);
		add(lblRoundName, c);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.SOUTHEAST;
		lblSpinsRemaining = new JLabel("Spins Remaining in Round: ");
		lblSpinsRemaining.setFont(new Font("Helvetica", Font.BOLD, 12));
		lblSpinsRemaining.setHorizontalAlignment(JLabel.RIGHT);
		lblSpinsRemaining.setVerticalAlignment(JLabel.BOTTOM);
		add(lblSpinsRemaining, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		JPanel lineBreakPanel = new JPanel();
		lineBreakPanel.setBackground(Color.BLACK);
		lineBreakPanel.setPreferredSize(new Dimension(200, 3));
		add(lineBreakPanel, c);

	}

	public void initialize(Round round, int spinCount) {
		lblRoundName.setText(round.fancyName());
		int spinsRemaining = round.spinLimit() - spinCount;
		lblSpinsRemaining
				.setText("Spins Remaining in Round: " + spinsRemaining);
	}

	public static RoundPanel getInstance() {
		if (instance == null) {
			instance = new RoundPanel();
		}
		return instance;
	}

}
