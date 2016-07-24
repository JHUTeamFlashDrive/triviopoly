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
import java.awt.Stroke;
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

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Sector;

public class WheelPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static WheelPanel instance;

	private enum States {
		SPINNING, STOPPED
	};

	private States state;

	private int spinTime;
	private int currentTime;
	private double wheelAngle;

	private Timer timer;
	private AffineTransform viewTransform;
	private double viewWidth;
	private double viewHeight;
	private double xTranslate;
	private double yTranslate;
	private double viewSize;

	private GeneralPath[] sectorShapes;
	private Shape[] sectorTextShapes;
	private Ellipse2D centerCircle;
	private Stroke sectorStroke = new BasicStroke(2.0f);
	private Stroke textStroke = new BasicStroke(1.0f);
	private Stroke centerCircleStroke = new BasicStroke(2.0f);

	private WheelPanel() {
		setPreferredSize(new Dimension(450, 450));
		setBackground(Color.WHITE);
		timer = new Timer(20, this);
		timer.start();
	}

	public static WheelPanel getInstance() {
		if (instance == null) {
			instance = new WheelPanel();
		}
		return instance;
	}

	private void createViewTransformation() {
		viewWidth = getWidth();
		viewHeight = getHeight();
		xTranslate = viewWidth / 2;
		yTranslate = viewHeight / 2;
		viewSize = Math.min(viewWidth, viewWidth);

		// heightScale negative to flip view over x axis. In a swing panel
		// positive values go down and to the right. We want normal axis
		// where positive values are up and to the right.
		viewTransform = new AffineTransform(1, 0, 0, -1, xTranslate, yTranslate);
	}

	public void spin() {
		spinTime = 99 + new Random().nextInt(1);
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
				Sector sector = calculateSector();
				GameController.getInstance().spinResult(sector);
			} else {
				wheelAngle += PI / 64;
				currentTime += 1;
			}
		}

		repaint();
	}

	private Sector calculateSector() {
		double numberOfSectors = (wheelAngle + PI / 12) / (PI / 6);
		double sectorNumber = numberOfSectors % 12.0d;
		return Sector.valueOf((int) sectorNumber);
	}

	private void createWheelShapes() {
		Sector[] sectors = Sector.values();
		double angle = 2 * PI / (double) (sectors.length);
		double halfA = angle / 2;
		double outerRadius = viewSize / 2 - 2;
		double innerRadius = viewSize / 8;
		double textRadius = innerRadius + 10;
		double x[] = new double[6];
		double y[] = new double[6];
		x[0] = cos(halfA) * innerRadius;
		y[0] = -sin(halfA) * innerRadius;
		x[1] = cos(halfA) * outerRadius;
		y[1] = -sin(halfA) * outerRadius;
		double lambda = (4 - cos(halfA)) / 3;
		double mu = (1 - cos(halfA)) * (cos(halfA) - 3) / 3 / sin(halfA);
		x[2] = lambda * outerRadius;
		y[2] = mu * outerRadius;
		x[3] = lambda * outerRadius;
		y[3] = -mu * outerRadius;
		x[4] = cos(halfA) * outerRadius;
		y[4] = sin(halfA) * outerRadius;
		x[5] = cos(halfA) * innerRadius;
		y[5] = sin(halfA) * innerRadius;

		sectorShapes = new GeneralPath[sectors.length];
		sectorTextShapes = new Shape[sectors.length];
		for (int i = 0; i < sectors.length; i++) {
			double sectorA = (double) i * angle;
			double[] sectorX = rotateX(x, y, sectorA);
			double[] sectorY = rotateY(x, y, sectorA);
			GeneralPath sector = new GeneralPath();
			sector.moveTo(sectorX[0], sectorY[0]);
			sector.lineTo(sectorX[1], sectorY[1]);
			sector.curveTo(sectorX[2], sectorY[2], sectorX[3], sectorY[3],
					sectorX[4], sectorY[4]);
			sector.lineTo(sectorX[5], sectorY[5]);
			sector.closePath();
			sectorShapes[i] = sector;

			TextLayout sectorText = new TextLayout(sectors[i].wheelText(),
					new Font("Helvetica", 1, 24), new FontRenderContext(null,
							false, false));
			double textWidthR = (double) sectorText.getBounds().getWidth() / 2;
			double textHeightR = (double) sectorText.getBounds().getHeight() / 2;
			double m00 = cos(sectorA);
			double m10 = sin(sectorA);
			double m01 = sin(sectorA);
			double m11 = -cos(sectorA);
			double m02 = -textWidthR * cos(sectorA) + textHeightR
					* sin(sectorA) + (textRadius + textWidthR) * cos(sectorA);
			double m12 = -textWidthR * sin(sectorA) - textHeightR
					* cos(sectorA) + (textRadius + textWidthR) * sin(sectorA);
			AffineTransform textTransform = new AffineTransform(m00, m10, m01,
					m11, m02, m12);
			sectorTextShapes[i] = sectorText.getOutline(textTransform);
		}

		centerCircle = new Ellipse2D.Double(-innerRadius, -innerRadius,
				innerRadius * 2, innerRadius * 2);
	}

	private void drawWheelShapes(Graphics2D g) {
		Sector[] sectors = Sector.values();
		for (int i = 0; i < sectors.length; i++) {
			g.setStroke(sectorStroke);
			g.setColor(sectors[i].wheelColor());
			g.fill(sectorShapes[i]);
			g.setColor(Color.BLACK);
			g.draw(sectorShapes[i]);

			g.fill(sectorTextShapes[i]);
			g.setColor(Color.WHITE);
			g.setStroke(textStroke);
			g.draw(sectorTextShapes[i]);
		}

		g.setStroke(centerCircleStroke);
		g.setColor(Color.GREEN);
		g.fill(centerCircle);
		g.setColor(Color.BLACK);
		g.draw(centerCircle);
	}

	private void updateViewTransform(Graphics2D g) {
		double m00 = cos(wheelAngle);
		double m10 = sin(wheelAngle);
		double m01 = sin(wheelAngle);
		double m11 = -cos(wheelAngle);
		double m02 = xTranslate;
		double m12 = yTranslate;
		viewTransform.setTransform(m00, m10, m01, m11, m02, m12);
		g.setTransform(viewTransform);
	}

	private void doDrawing(Graphics2D g) {
		if (getWidth() != viewWidth || getHeight() != viewHeight) {
			createViewTransformation();
			createWheelShapes();
		}
		updateViewTransform(g);
		drawWheelShapes(g);
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
