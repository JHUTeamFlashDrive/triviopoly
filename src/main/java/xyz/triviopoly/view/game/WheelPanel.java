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
			"Category 4", "Bankrupt", "Category 5", "Player Choice",
			"Category 6", "Opponent Choice" };

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
		setPreferredSize(new Dimension(400, 400));
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
		g.setStroke(new BasicStroke(2));
		// g.draw(wheel);

		double a = PI / 12;
		double innerRadius = 50;
		double outerRadius = 198;
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
			double[] sectorX = rotateX(x, y, i * PI / 6);
			double[] sectorY = rotateY(x, y, i * PI / 6);
			GeneralPath sector = new GeneralPath();
			sector.moveTo(sectorX[0], sectorY[0]);
			sector.lineTo(sectorX[1], sectorY[1]);
			sector.curveTo(sectorX[2], sectorY[2], sectorX[3], sectorY[3],
					sectorX[4], sectorY[4]);
			sector.lineTo(sectorX[5], sectorY[5]);
			sector.closePath();
			g.setColor(SECTOR_COLORS[i]);
			g.fill(sector);
			g.setColor(Color.BLACK);
			g.draw(sector);
		}

		Ellipse2D innerCircle = new Ellipse2D.Double(-innerRadius,
				-innerRadius, innerRadius * 2, innerRadius * 2);
		g.setColor(Color.GREEN);
		g.fill(innerCircle);
		g.setColor(Color.BLACK);
		g.draw(innerCircle);

		TextLayout testText = new TextLayout("Test Text", new Font("Helvetica",
				1, 48), new FontRenderContext(null, false, false));
		AffineTransform textAt = new AffineTransform();
		textAt.translate(0, (float) testText.getBounds().getHeight());
		g.draw(testText.getOutline(textAt));
		// double h = 198;
		// double x1 = cos(a) * h;
		// double y1 = -sin(a) * h;
		// System.out.println("" + x1 + " " + y1);

		// double lambda = a / 3 + Math.pow(a, 3) / 9 + Math.pow(a, 5) / 360;

		// double mu = 1 + a * a / 6 + Math.pow(a, 4) / 72;
		// double x2 = lambda * h;
		// double y2 = -mu * h;
		// double x3 = lambda * h;
		// double y3 = mu * h;
		// double x4 = cos(a) * h;
		// double y4 = sin(a) * h;
		// GeneralPath path = new GeneralPath();
		// path.moveTo(x1, y1);
		// path.lineTo(x2, y2);
		// path.lineTo(x3, y3);
		// path.lineTo(x4, y4);
		// path.moveTo(0, 0);
		// path.lineTo(x1, y1);
		// path.curveTo(x3, y3, x2, y2, x4, y4);
		// path.closePath();
		// g.draw(path);

		// for (int i = 0; i < 12; i++) {
		// double x1 = Math.cos(i * Math.PI / 6);
		// double y1 = Math.sin(i * Math.PI / 6);
		// double x2 = Math.cos((i + 1) * Math.PI / 6);
		// double y2 = Math.sin((i + 1) * Math.PI / 6);
		// double x3 = Math.cos((2 * i + 1) * Math.PI / 12);
		// double y3 = Math.sin((2 * i + 1) * Math.PI / 12);
		// GeneralPath sector = new GeneralPath();
		// sector.moveTo(x1 * 50, y1 * 50);
		// sector.lineTo(x1 * 198, y1 * 198);
		// sector.curveTo(x1 * 198, y1 * 198, x2 * 198, y2 * 198, x3 * 198,
		// y3 * 198);
		// sector.lineTo(x2 * 50, y2 * 50);
		// sector.closePath();
		// g.setColor(SECTOR_COLORS[i]);
		// g.fill(sector);
		// }
		// for (int i = 0; i < 12; i++) {
		// double x = Math.cos(i * Math.PI / 6);
		// double y = Math.sin(i * Math.PI / 6);
		// g.draw(new Line2D.Double(x * 50, y * 50, x * 198, y * 198));
		// GeneralPath sector = new GeneralPath();
		// sector.moveTo(x * 50, y * 50);
		// sector.lineTo(x * 198, y * 198);
		// sector.curveTo(x * 198, y1, x2, y2, x3, y3);
		//
		// }
		// g.draw(new Ellipse2D.Double(-198, -198, 396, 396));
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
