package xyz.triviopoly.view.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WheelPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private enum States {
		SPINNING, STOPPED
	};

	private States state;

	private double spinTime;
	private double currentTime;
	private double wheelAngle;

	private Timer timer;
	private AffineTransform viewTransform;

	public WheelPanel() {
		computeCoordinateSpaceTransformations(getWidth(), getHeight());

		timer = new Timer(20, this);
		timer.start();
	}

	private void computeCoordinateSpaceTransformations(double viewWidth,
			double viewHeight) {
		double viewRatio = viewWidth / viewHeight;
		double scale;
		double xTranslate = viewWidth / 2;
		double yTranslate = viewHeight / 2;

		// heightScale negative to flip view over x axis. In a swing panel
		// positive values go down and to the right. We want normal axis
		// where positive values are up and to the right.
		viewTransform = new AffineTransform(1, 0, 0, -1, xTranslate, yTranslate);
	}

	private void spin() {
		spinTime = (new Random().nextDouble() * 10);
		currentTime = 0;
		state = States.SPINNING;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing((Graphics2D) g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (state == States.SPINNING) {
			if (currentTime >= spinTime) {
				state = States.STOPPED;
			} else {

			}
		}

		repaint();
	}

	private void doDrawing(Graphics2D g) {
		double viewWidth = getWidth();
		double viewHeight = getHeight();

		g.transform(viewTransform);

		Ellipse2D wheel = new Ellipse2D.Double(-viewWidth / 2, viewHeight / 2,
				viewHeight, viewWidth);
		g.draw(wheel);
	}
}
