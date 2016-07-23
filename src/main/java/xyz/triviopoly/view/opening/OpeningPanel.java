package xyz.triviopoly.view.opening;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static OpeningPanel instance;

	private GridBagConstraints contentPanelConstraints;
	private JPanel contentPanel;

	private OpeningPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		createFillerPanels();

		contentPanelConstraints = new GridBagConstraints();
		contentPanelConstraints.insets = new Insets(0, 0, 0, 0);
		contentPanelConstraints.fill = GridBagConstraints.BOTH;
		contentPanelConstraints.gridx = 1;
		contentPanelConstraints.gridy = 1;
		contentPanelConstraints.weightx = 1;
		contentPanelConstraints.weighty = 3;
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		add(contentPanel, contentPanelConstraints);

	}

	public static OpeningPanel getInstance() {
		if (instance == null) {
			instance = new OpeningPanel();
		}
		return instance;
	}

	public void displayContentPanel(JPanel contentPanel) {
		remove(this.contentPanel);
		this.contentPanel = contentPanel;
		add(this.contentPanel, contentPanelConstraints);
		revalidate();
		repaint();
	}

	private void createFillerPanels() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 40, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 3;
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		titlePanel.setBackground(Color.WHITE);
		JLabel lblTriviopoly = new JLabel("Triviopoly!");
		lblTriviopoly.setFont(new Font("Arial", Font.BOLD, 96));
		titlePanel.add(lblTriviopoly);
		add(lblTriviopoly, c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 3;
		c.gridwidth = 1;
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		leftSidePanel.setBackground(Color.WHITE);
		add(leftSidePanel, c);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 5;
		JPanel rightSidePanel = new JPanel();
		rightSidePanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		rightSidePanel.setBackground(Color.WHITE);
		add(rightSidePanel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 15;
		c.weighty = 2;
		c.gridwidth = 3;
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		bottomPanel.setBackground(Color.WHITE);
		add(bottomPanel, c);
	}
}
