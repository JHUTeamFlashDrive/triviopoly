package xyz.triviopoly.view.game;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WheelPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static Color[] SECTOR_COLORS = { Color.YELLOW, Color.BLUE,
			Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN,
			Color.LIGHT_GRAY, Color.PINK, Color.ORANGE, Color.GRAY,
			Color.BLUE.brighter(), Color.GREEN.brighter() };

	private static String[] SECTOR_NAMES = { "Category 1", "Free Spin",
			"Category 2", "Lose Spin", "Category 3", "Spin Again",
			"Category 4", "Bankrupt", "Category 5", "Choice", "Category 6",
			"Opp. Choice" };

	private enum States {
		SPINNING, STOPPED
	};

	private States state;

	private double spinTime;
	private double currentTime;
	private double wheelAngle;

	private Timer timer;
	private AffineTransform viewTransform;
	private double viewWidth;
	private double viewHeight;

	public WheelPanel() {
		setPreferredSize(new Dimension(450, 450));
		setBackground(Color.WHITE);
		timer = new Timer(20, this);
		timer.start();
	}

	private void computeCoordinateSpaceTransformations() {
		viewWidth = getWidth();
		viewHeight = getHeight();
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
		if (getWidth() != viewWidth || getHeight() != viewHeight) {
			computeCoordinateSpaceTransformations();
		}
		g.setTransform(viewTransform);

		// g.draw(wheel);

		double a = PI / 12;
		double outerRadius = viewWidth / 2 - 2;
		double innerRadius = viewWidth / 8;
		double textRadius = innerRadius + 10;
		double x[] = new double[6];
		double y[] = new double[6];
		x[0] = cos(a) * innerRadius;
		y[0] = -sin(a) * innerRadius;
		x[1] = cos(a) * outerRadius;
		y[1] = -sin(a) * outerRadius;
		double lambda = (4 - cos(a)) / 3;
		double mu = (1 - cos(a)) * (cos(a) - 3) / 3 / sin(a);
		x[2] = lambda * outerRadius;
		y[2] = mu * outerRadius;
		x[3] = lambda * outerRadius;
		y[3] = -mu * outerRadius;
		x[4] = cos(a) * outerRadius;
		y[4] = sin(a) * outerRadius;
		x[5] = cos(a) * innerRadius;
		y[5] = sin(a) * innerRadius;

		for (int i = 0; i < 12; i++) {
			double angle = (double) i * PI / 6.0d;
			double[] sectorX = rotateX(x, y, angle);
			double[] sectorY = rotateY(x, y, angle);
			GeneralPath sector = new GeneralPath();
			sector.moveTo(sectorX[0], sectorY[0]);
			sector.lineTo(sectorX[1], sectorY[1]);
			sector.curveTo(sectorX[2], sectorY[2], sectorX[3], sectorY[3],
					sectorX[4], sectorY[4]);
			sector.lineTo(sectorX[5], sectorY[5]);
			sector.closePath();
			g.setStroke(new BasicStroke(2));
			g.setColor(SECTOR_COLORS[i]);
			g.fill(sector);
			g.setColor(Color.BLACK);
			g.draw(sector);

			TextLayout sectorText = new TextLayout(SECTOR_NAMES[i], new Font(
					"Helvetica", 1, 24), new FontRenderContext(null, false,
					false));
			double textAngle = angle + PI / 72;
			double textWidthR = (double) sectorText.getBounds().getWidth();
			double textHeightR = (double) sectorText.getBounds().getHeight() / 2;
			double m00 = cos(textAngle);
			double m10 = sin(textAngle);
			double m01 = sin(textAngle);
			double m11 = -cos(textAngle);
			double m02 = -textWidthR * cos(textAngle) + textHeightR
					* sin(textAngle) + (textRadius + textWidthR)
					* cos(textAngle);
			double m12 = -textWidthR * sin(textAngle) - textHeightR
					* cos(textAngle) + (textRadius + textWidthR)
					* sin(textAngle);
			AffineTransform textTransform = new AffineTransform(m00, m10, m01,
					m11, m02, m12);
			Shape textShape = sectorText.getOutline(textTransform);
			g.fill(textShape);
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(1.0f));
			g.draw(textShape);
		}

		Ellipse2D innerCircle = new Ellipse2D.Double(-innerRadius,
				-innerRadius, innerRadius * 2, innerRadius * 2);
		g.setStroke(new BasicStroke(2.0f));
		g.setColor(Color.GREEN);
		g.fill(innerCircle);
		g.setColor(Color.BLACK);
		g.draw(innerCircle);
	}

	double[] rotateX(double[] x, double[] y, double angle) {
		double[] xNew = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			xNew[i] = cos(angle) * x[i] - sin(angle) * y[i];
		}
		return xNew;
	}

	double[] rotateY(double[] x, double[] y, double angle) {
		double[] yNew = new double[y.length];
		for (int i = 0; i < x.length; i++) {
			yNew[i] = sin(angle) * x[i] + cos(angle) * y[i];
		}
		return yNew;
	}
}
