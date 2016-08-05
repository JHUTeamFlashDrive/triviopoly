package xyz.triviopoly.view.opening;

import java.awt.Color;
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

import xyz.triviopoly.controller.OpeningController;

public class NumberOfPlayersPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static NumberOfPlayersPanel instance;

	private NumberOfPlayersPanel() {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 0;
		JLabel lblHowManyPlayers = new JLabel("How many players? (2-4)");
		add(lblHowManyPlayers, c);
		c.gridx = 1;
		JTextField txtHowManyPlayers = new JTextField(5);
		add(txtHowManyPlayers, c);
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
					OpeningController.getInstance().numberOfPlayersSpecified(
							numPlayers);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		add(btnGo, c);
	}

	public static NumberOfPlayersPanel getInstance() {
		if (instance == null) {
			instance = new NumberOfPlayersPanel();
		}
		return instance;
	}

}
