package xyz.triviopoly.view.opening;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xyz.triviopoly.controller.OpeningController;

public class PlayerNamesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PlayerNamesPanel instance;

	private JTextField[] txtPlayerNames;

	private PlayerNamesPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

	}

	public static PlayerNamesPanel getInstance() {
		if (instance == null) {
			instance = new PlayerNamesPanel();
		}
		return instance;
	}

	public void update(int numberOfPlayers) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;

		txtPlayerNames = new JTextField[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			JLabel lblPlayer = new JLabel("Player " + (i + 1));
			c.gridx = 0;
			c.gridy = i;
			add(lblPlayer, c);
			txtPlayerNames[i] = new JTextField(8);
			c.gridx = 1;
			c.gridy = i;
			add(txtPlayerNames[i], c);
		}
		JButton btnGo = new JButton("Go!");
		btnGo.setOpaque(true);
		btnGo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnGo.setBackground(Color.MAGENTA);
		btnGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> playerNames = new ArrayList<>();

				for (int i = 0; i < txtPlayerNames.length; i++) {
					String value = txtPlayerNames[i].getText();
					if (value.isEmpty()) {
						JOptionPane
								.showMessageDialog(null,
										"Please specify Player " + (i + 1)
												+ "'s name.", "",
										JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					playerNames.add(value);
				}
				OpeningController.getInstance().playerNamesSpecified(
						playerNames);
			}

		});
		c.gridx = 1;
		c.gridy = numberOfPlayers;
		c.gridwidth = 2;
		c.ipadx = 25;
		c.ipady = 10;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		add(btnGo, c);
	}
}
