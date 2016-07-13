package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SpinPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public SpinPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		add(new WheelPanel(), c);
		c.gridy = 1;
		add(new JButton("Spin!"), c);
	}

}
