package xyz.triviopoly.view.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import xyz.triviopoly.controller.GameController;

public abstract class NotificationPanel extends JPanel implements
		ActionListener {
	private static final long serialVersionUID = 1L;

	private static final int TICK_DURATION = 100;

	private Timer timer;
	private int timeElapsed;
	private int duration;

	public NotificationPanel() {
		timer = new Timer(TICK_DURATION, this);
	}

	public void startNotification(int duration) {
		timeElapsed = 0;
		this.duration = duration;
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timeElapsed += TICK_DURATION;
		if (timeElapsed >= duration) {
			timer.stop();
			notificationFinished();
		}
	}

	protected void notificationFinished() {
		GameController.getInstance().notificationFinished();
	}
}
