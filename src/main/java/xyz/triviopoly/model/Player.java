package xyz.triviopoly.model;

public class Player {

	private String name;
	private int roundScore;
	private int totalScore;
	private int freeSpinCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoundScore() {
		return roundScore;
	}

	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getFreeSpinCount() {
		return freeSpinCount;
	}

	public void setFreeSpinCount(int freeSpinCount) {
		this.freeSpinCount = freeSpinCount;
	}

}
