package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xyz.triviopoly.controller.GameController;

public class AskUseFreeSpinPanel extends NotificationPanel {
	private static final long serialVersionUID = 1L;

	public AskUseFreeSpinPanel() {
		setOpaque(true);
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30, 30, 30, 30);
		add(new ImagePanel("images/free_spin.png"), c);
		c.gridx = 0;
		c.gridy = 1;
		JLabel lblAskUseFreeSpin = new JLabel(
				"Do you want to use one of your free spin tokens?");
		lblAskUseFreeSpin.setOpaque(false);
		lblAskUseFreeSpin.setHorizontalAlignment(JLabel.CENTER);
		lblAskUseFreeSpin.setVerticalAlignment(JLabel.CENTER);
		lblAskUseFreeSpin.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		add(lblAskUseFreeSpin, c);
		c.gridx = 0;
		c.gridy = 2;
		JPanel yesNoPanel = new JPanel();
		yesNoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		yesNoPanel.setBackground(Color.WHITE);
		JButton btnYes = new JButton("Yes");
		btnYes.setBackground(Color.WHITE);
		btnYes.setForeground(Color.BLACK);
		btnYes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnYes.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnYes.setPreferredSize(new Dimension(100, 50));
		btnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().askUseFreeSpinResult(true);
			}

		});
		yesNoPanel.add(btnYes);
		JButton btnNo = new JButton("No");
		btnNo.setBackground(Color.WHITE);
		btnNo.setForeground(Color.BLACK);
		btnNo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnNo.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnNo.setPreferredSize(new Dimension(100, 50));
		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().askUseFreeSpinResult(false);
			}

		});
		yesNoPanel.add(btnNo);
		add(yesNoPanel, c);
	}

}
