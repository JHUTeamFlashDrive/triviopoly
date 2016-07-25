package xyz.triviopoly.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import xyz.triviopoly.controller.SpinAgainHandler;

public class SpinAgainPanel extends NotificationPanel {
	private static final long serialVersionUID = 1L;

	public SpinAgainPanel() {
		setOpaque(true);
		setBackground(Color.RED);
		setLayout(new BorderLayout());
		JLabel lblFreeSpin = new JLabel("Spin Again!");
		lblFreeSpin.setForeground(Color.YELLOW);
		lblFreeSpin.setOpaque(false);
		lblFreeSpin.setHorizontalAlignment(JLabel.CENTER);
		lblFreeSpin.setVerticalAlignment(JLabel.CENTER);
		lblFreeSpin.setFont(new Font("Comic Sans MS", Font.BOLD, 192));
		add(lblFreeSpin, BorderLayout.CENTER);
	}

	@Override
	protected void notificationFinished() {
		SpinAgainHandler.getInstance().notificationFinished();
	}

}
