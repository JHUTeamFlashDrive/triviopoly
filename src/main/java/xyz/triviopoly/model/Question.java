package xyz.triviopoly.model;

public class Question {

	private String question;
	private String questionType;
	private String answer;
	private int difficulty;

	public Question(String question, String questionType, String answer,
			int difficulty) {
		this.question = question;
		this.questionType = questionType;
		this.answer = answer;
		this.difficulty = difficulty;
	}

	public String getQuestion() {
		return question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public String getAnswer() {
		return answer;
	}

	public int getDifficulty() {
		return difficulty;
	}

}
