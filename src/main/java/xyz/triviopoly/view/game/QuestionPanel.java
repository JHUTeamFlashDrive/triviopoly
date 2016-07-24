package xyz.triviopoly.view.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import xyz.triviopoly.controller.GameController;
import xyz.triviopoly.model.Question;

public class QuestionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static QuestionPanel instance;
	private JLabel lblQuestion;
	private JLabel lblQuestionType;
	private JTextField txtAnswer;

	private QuestionPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.weighty = 4;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		lblQuestion = new JLabel();
		lblQuestion.setBackground(Color.BLUE);
		lblQuestion.setForeground(Color.WHITE);
		lblQuestion.setFont(new Font("Comic Sans MS", Font.PLAIN, 64));
		add(lblQuestion, c);
		c.gridy = 1;
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
		txtAnswer = new JTextField(10);
		txtAnswer.setCaretColor(Color.WHITE);
		txtAnswer.setBackground(Color.BLUE);
		txtAnswer.setForeground(Color.WHITE);
		txtAnswer.setBorder(null);
		txtAnswer.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		txtAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().answerGiven(txtAnswer.getText());
			}

		});
		add(txtAnswer, c);
		c.gridx = 2;
		JLabel lblQuestionMark = new JLabel("?");
		lblQuestionMark.setBackground(Color.BLUE);
		lblQuestionMark.setForeground(Color.WHITE);
		lblQuestionMark.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		add(lblQuestionMark, c);
	}

	public void setQuestion(Question question) {
		lblQuestion.setText("<html><center>" + question.getQuestion()
				+ "</center></html>");
		lblQuestionType.setText(question.getQuestionType());
		txtAnswer.requestFocusInWindow();
	}

	public void displayResult(boolean correct) {

	}

	public static QuestionPanel getInstance() {
		if (instance == null) {
			instance = new QuestionPanel();
		}
		return instance;
	}

}
