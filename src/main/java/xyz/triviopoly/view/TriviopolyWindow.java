package xyz.triviopoly.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TriviopolyWindow {
	private static TriviopolyWindow instance;

	private JFrame window;
	private JPanel mainPanel;

	private TriviopolyWindow() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Triviopoly!");
		window.setBounds(50, 50, 1200, 760);
		window.setResizable(false);
		window.setBackground(Color.WHITE);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		window.add(mainPanel);

		window.setVisible(true);
	}

	public static TriviopolyWindow getInstance() {
		if (instance == null) {
			instance = new TriviopolyWindow();
		}
		return instance;
	}

	public void displayContentPanel(JPanel contentPanel) {
		window.remove(mainPanel);
		mainPanel = contentPanel;
		window.add(mainPanel);
		window.revalidate();
		window.repaint();
	}
}
