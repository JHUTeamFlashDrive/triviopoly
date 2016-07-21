package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SpinPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static SpinPanel instance;

	private SpinPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(WheelPanel.getInstance(), c);
		c.gridx = 1;
		add(new ImagePanel("images/arrow_scaled.png"), c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20, 0, 0, 0);
		JButton btnSpin = new JButton("Spin!");
		btnSpin.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnSpin.setOpaque(true);
		btnSpin.setBackground(Color.MAGENTA);
		btnSpin.setFont(new Font("Helvetica", 1, 16));
		btnSpin.setPreferredSize(new Dimension(100, 50));
		add(btnSpin, c);
	}

	public static SpinPanel getInstance() {
		if (instance == null) {
			instance = new SpinPanel();
		}
		return instance;
	}

}
