package xyz.triviopoly.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import xyz.triviopoly.controller.LoseTurnHandler;

public class LoseTurnPanel extends NotificationPanel {
	private static final long serialVersionUID = 1L;

	public LoseTurnPanel() {
		setOpaque(true);
		setBackground(Color.RED);
		setLayout(new BorderLayout());
		JLabel lblLoseTurn = new JLabel("Lost Turn");
		lblLoseTurn.setForeground(Color.YELLOW);
		lblLoseTurn.setOpaque(false);
		lblLoseTurn.setHorizontalAlignment(JLabel.CENTER);
		lblLoseTurn.setVerticalAlignment(JLabel.CENTER);
		lblLoseTurn.setFont(new Font("Comic Sans MS", Font.BOLD, 192));
		add(lblLoseTurn, BorderLayout.CENTER);
	}

	@Override
	protected void notificationFinished() {
		LoseTurnHandler.getInstance().notificationFinished();
	}

}
