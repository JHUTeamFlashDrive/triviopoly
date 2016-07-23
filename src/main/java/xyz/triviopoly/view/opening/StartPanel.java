package xyz.triviopoly.view.opening;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import xyz.triviopoly.controller.OpeningController;

public class StartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static StartPanel instance;

	private StartPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
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
				OpeningController.getInstance().startGame();
			}

		});
		add(btnStart, c);
	}

	public static StartPanel getInstance() {
		if (instance == null) {
			instance = new StartPanel();
		}
		return instance;
	}
}
