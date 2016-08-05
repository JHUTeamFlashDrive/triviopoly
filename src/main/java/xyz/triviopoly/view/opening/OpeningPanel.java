package xyz.triviopoly.view.opening;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import xyz.triviopoly.view.game.ImagePanel;

public class OpeningPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static OpeningPanel instance;

	private JPanel contentPanelContainer;
	private JPanel contentPanel;
	private GridBagConstraints contentPanelConstraints;

	private OpeningPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		createPanels();

		contentPanelConstraints = new GridBagConstraints();
		contentPanelConstraints.fill = GridBagConstraints.BOTH;
		contentPanelConstraints.gridx = 0;
		contentPanelConstraints.gridy = 0;
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanelContainer.add(contentPanel, contentPanelConstraints);

	}

	public static OpeningPanel getInstance() {
		if (instance == null) {
			instance = new OpeningPanel();
		}
		return instance;
	}

	public void displayContentPanel(JPanel contentPanel) {
		contentPanelContainer.remove(this.contentPanel);
		this.contentPanel = contentPanel;
		contentPanelContainer.add(this.contentPanel, contentPanelConstraints);
		revalidate();
		repaint();
	}

	private void createPanels() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(40, 40, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = 3;
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		JLabel lblTriviopoly = new JLabel("Triviopoly!");
		lblTriviopoly.setFont(new Font("Arial", Font.BOLD, 96));
		titlePanel.add(lblTriviopoly);
		add(lblTriviopoly, c);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 0;
		c.weighty = 5;
		c.gridheight = 2;
		c.gridwidth = 1;
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(20, 20));
		leftSidePanel.setBackground(Color.WHITE);
		add(leftSidePanel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridheight = 1;
		contentPanelContainer = new JPanel();
		contentPanelContainer.setBackground(Color.WHITE);
		contentPanelContainer.setPreferredSize(new Dimension(420, 420));
		contentPanelContainer.setLayout(new GridBagLayout());
		add(contentPanelContainer, c);
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 5;
		c.gridheight = 2;
		JPanel rightSidePanel = new JPanel(new BorderLayout());
		rightSidePanel.setBackground(Color.WHITE);
		ImagePanel image = new ImagePanel("images/opening_image.jpg");
		rightSidePanel.add(image, BorderLayout.SOUTH);
		add(rightSidePanel, c);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.weighty = 2;
		c.gridheight = 1;
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		add(bottomPanel, c);
	}
}
