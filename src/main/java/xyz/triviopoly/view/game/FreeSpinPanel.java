package xyz.triviopoly.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FreeSpinPanel extends NotificationPanel {
	private static final long serialVersionUID = 1L;

	public FreeSpinPanel() {
		setOpaque(true);
		setBackground(Color.GREEN);
		setLayout(new BorderLayout());
		JLabel lblFreeSpin = new JLabel("Free Spin!");
		lblFreeSpin.setForeground(Color.YELLOW);
		lblFreeSpin.setOpaque(false);
		lblFreeSpin.setHorizontalAlignment(JLabel.CENTER);
		lblFreeSpin.setVerticalAlignment(JLabel.CENTER);
		lblFreeSpin.setFont(new Font("Comic Sans MS", Font.BOLD, 192));
		add(lblFreeSpin, BorderLayout.CENTER);
	}
}
