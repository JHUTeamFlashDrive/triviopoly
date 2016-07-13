package xyz.triviopoly;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import xyz.triviopoly.view.game.GamePanel;

public class Triviopoly {
	private JFrame window;
	private JPanel mainPanel;

	public Triviopoly() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Triviopoly!");
		window.setBounds(50, 50, 1150, 760);
		window.setResizable(false);
		window.setBackground(Color.WHITE);

		// mainPanel = new OpeningPanel(this);
		mainPanel = new GamePanel();
		window.add(mainPanel);

		window.setVisible(true);
	}

	public void showGameView() {
		window.remove(mainPanel);
		mainPanel = new GamePanel();
		window.add(mainPanel);
		window.revalidate();
		window.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Triviopoly();
			}
		});
	}
}
