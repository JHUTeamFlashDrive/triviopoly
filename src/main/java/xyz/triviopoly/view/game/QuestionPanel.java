package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import xyz.triviopoly.controller.CategoryHandler;
import xyz.triviopoly.model.Question;

public class QuestionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static QuestionPanel instance;
	private JLabel lblQuestion;
	private JLabel lblQuestionType;
	private JTextField txtAnswer;
	private JLabel lblQuestionMark;
	private JButton btnOk;

	private QuestionPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 3;
		c.weighty = 4;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		lblQuestion = new JLabel();
		lblQuestion.setBackground(Color.BLUE);
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 64));
		add(lblQuestion, c);
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		lblQuestionType = new JLabel();
		lblQuestionType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuestionType.setBackground(Color.BLUE);
		lblQuestionType.setForeground(Color.WHITE);
		lblQuestionType.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		add(lblQuestionType, c);
		c.gridx = 1;
		c.insets = new Insets(0, 10, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		txtAnswer = new JTextField(10);
		txtAnswer.setCaretColor(Color.WHITE);
		txtAnswer.setBackground(Color.BLUE);
		txtAnswer.setForeground(Color.WHITE);
		txtAnswer.setBorder(null);
		txtAnswer.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		txtAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryHandler.getInstance().answerGiven(txtAnswer.getText());
			}

		});
		add(txtAnswer, c);
		c.gridx = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		lblQuestionMark = new JLabel("?");
		lblQuestionMark.setBackground(Color.BLUE);
		lblQuestionMark.setForeground(Color.WHITE);
		lblQuestionMark.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		add(lblQuestionMark, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weightx = 3;
		c.weighty = 1;
		JPanel okPanel = new JPanel(new FlowLayout());
		okPanel.setBackground(Color.BLUE);
		btnOk = new JButton("OK");
		btnOk.setBackground(Color.BLUE);
		btnOk.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnOk.setVisible(false);
		btnOk.setPreferredSize(new Dimension(200, 75));
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryHandler.getInstance().questionFinished();
			}
		});
		okPanel.add(btnOk);
		add(okPanel, c);
	}

	public void setQuestion(Question question) {
		lblQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 64));
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setText("<html><center>" + question.getQuestion()
				+ "</center></html>");
		lblQuestionType.setText(question.getQuestionType());
		lblQuestionType.setVisible(true);
		txtAnswer.setText("");
		txtAnswer.setVisible(true);
		txtAnswer.requestFocusInWindow();
		lblQuestionMark.setVisible(true);
		btnOk.setVisible(false);
	}

	public void displayResult(boolean correct) {
		lblQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 128));
		if (correct) {
			lblQuestion.setForeground(Color.GREEN);
			lblQuestion.setText("<html><center>CORRECT!</center></html>");
		} else {
			lblQuestion.setForeground(Color.RED);
			lblQuestion.setText("<html><center>SORRY</center></html>");
		}
		lblQuestionType.setVisible(false);
		txtAnswer.setVisible(false);
		lblQuestionMark.setVisible(false);
		btnOk.setVisible(true);
		btnOk.requestFocusInWindow();
	}

	public static QuestionPanel getInstance() {
		if (instance == null) {
			instance = new QuestionPanel();
		}
		return instance;
	}

}
