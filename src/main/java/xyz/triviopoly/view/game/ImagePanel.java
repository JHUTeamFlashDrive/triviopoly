package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image image;

	public ImagePanel(String imagePath) {
		this(imagePath, 1.0d);
	}

	public ImagePanel(String imagePath, double scalingFactor) {
		setBackground(Color.WHITE);
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		if (scalingFactor != 1.0d) {
			width = (int) ((double) width * scalingFactor);
			height = (int) ((double) height * scalingFactor);
			image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}

}
