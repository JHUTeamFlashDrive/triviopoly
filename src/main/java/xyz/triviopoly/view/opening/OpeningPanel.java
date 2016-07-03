package xyz.triviopoly.view.opening;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xyz.triviopoly.Triviopoly;

public class OpeningPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Triviopoly triviopoly;
	private GridBagConstraints contentPanelConstraints;
	private JPanel contentPanel;

	public OpeningPanel(Triviopoly triviopoly) {
		this.triviopoly = triviopoly;
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		createFillerPanels();

		contentPanelConstraints = new GridBagConstraints();
		contentPanelConstraints.insets = new Insets(0, 0, 0, 0);
		contentPanelConstraints.fill = GridBagConstraints.BOTH;
		contentPanelConstraints.gridx = 1;
		contentPanelConstraints.gridy = 1;
		contentPanelConstraints.weightx = 1;
		contentPanelConstraints.weighty = 3;
		contentPanel = createStartPanel();
		add(contentPanel, contentPanelConstraints);

	}

	private void createFillerPanels() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 40, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 3;
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		titlePanel.setBackground(Color.WHITE);
		JLabel lblTriviopoly = new JLabel("Triviopoly!");
		lblTriviopoly.setFont(new Font("Arial", Font.BOLD, 96));
		titlePanel.add(lblTriviopoly);
		add(lblTriviopoly, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 3;
		c.gridwidth = 1;
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		leftSidePanel.setBackground(Color.WHITE);
		add(leftSidePanel, c);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 5;
		JPanel rightSidePanel = new JPanel();
		rightSidePanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		rightSidePanel.setBackground(Color.WHITE);
		add(rightSidePanel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 15;
		c.weighty = 2;
		c.gridwidth = 3;
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		bottomPanel.setBackground(Color.WHITE);
		add(bottomPanel, c);
	}

	private JPanel createStartPanel() {
		JPanel startPanel = new JPanel();
		startPanel.setLayout(new GridBagLayout());
		startPanel.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);

		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 25;
		c.ipady = 25;

		JButton btnStart = new JButton("Let's Play!");
		btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnStart.setOpaque(true);
		btnStart.setBackground(Color.MAGENTA);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(contentPanel);
				contentPanel = createNumberPlayersPanel();
				add(contentPanel, contentPanelConstraints);
				revalidate();
				repaint();
			}

		});
		startPanel.add(btnStart, c);

		return startPanel;
	}

	private JPanel createNumberPlayersPanel() {
		JPanel numberPlayersPanel = new JPanel();
		numberPlayersPanel.setBackground(Color.WHITE);
		numberPlayersPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		JLabel lblHowManyPlayers = new JLabel("How many players? (1-4)");
		numberPlayersPanel.add(lblHowManyPlayers, c);
		c.gridx = 1;
		JTextField txtHowManyPlayers = new JTextField(5);
		numberPlayersPanel.add(txtHowManyPlayers, c);
		c.gridy = 1;
		c.ipadx = 25;
		c.ipady = 10;
		JButton btnGo = new JButton("Go!");
		btnGo.setOpaque(true);
		btnGo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnGo.setBackground(Color.MAGENTA);
		btnGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String value = txtHowManyPlayers.getText();
					if (value.isEmpty()) {
						throw new NumberFormatException(
								"Please enter how many people are playing");
					}
					int numPlayers;
					try {
						numPlayers = Integer.parseInt(value);
					} catch (NumberFormatException ex) {
						throw new Exception("Please enter a valid number");
					}
					if (numPlayers < 2 || numPlayers > 4) {
						throw new Exception("Only 2 to 4 players is supported");
					}
					remove(contentPanel);
					contentPanel = createPlayerNamesPanel(numPlayers);
					add(contentPanel, contentPanelConstraints);
					revalidate();
					repaint();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		numberPlayersPanel.add(btnGo, c);

		return numberPlayersPanel;
	}

	private JPanel createPlayerNamesPanel(int numberPlayers) {
		JPanel playerNamesPanel = new JPanel();
		playerNamesPanel.setLayout(new GridBagLayout());
		playerNamesPanel.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;

		for (int i = 0; i < numberPlayers; i++) {
			JLabel lblPlayer = new JLabel("Player " + (i + 1));
			c.gridx = 0;
			c.gridy = i;
			playerNamesPanel.add(lblPlayer, c);
			JTextField txtPlayer = new JTextField(8);
			c.gridx = 1;
			c.gridy = i;
			playerNamesPanel.add(txtPlayer, c);
		}
		JButton btnGo = new JButton("Go!");
		btnGo.setOpaque(true);
		btnGo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnGo.setBackground(Color.MAGENTA);
		btnGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				triviopoly.showGameView();
			}

		});
		c.gridx = 1;
		c.gridy = numberPlayers;
		c.gridwidth = 2;
		c.ipadx = 25;
		c.ipady = 10;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		playerNamesPanel.add(btnGo, c);

		return playerNamesPanel;
	}

	private String parseString(JTextField txtField, String fieldName) {
		String value = txtField.getText();
		if (value.isEmpty()) {
			JOptionPane.showMessageDialog(null, fieldName + " is required.",
					"", JOptionPane.INFORMATION_MESSAGE);
		}
		return value;
	}
}
