package xyz.triviopoly.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import xyz.triviopoly.controller.BankruptHandler;

public class BankruptPanel extends NotificationPanel {
	private static final long serialVersionUID = 1L;

	public BankruptPanel() {
		setOpaque(true);
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		JLabel lblFreeSpin = new JLabel("Bankrupt!");
		lblFreeSpin.setForeground(Color.WHITE);
		lblFreeSpin.setOpaque(false);
		lblFreeSpin.setHorizontalAlignment(JLabel.CENTER);
		lblFreeSpin.setVerticalAlignment(JLabel.CENTER);
		lblFreeSpin.setFont(new Font("Comic Sans MS", Font.BOLD, 192));
		add(lblFreeSpin, BorderLayout.CENTER);
	}

	@Override
	protected void notificationFinished() {
		BankruptHandler.getInstance().notificationFinished();
	}

}
