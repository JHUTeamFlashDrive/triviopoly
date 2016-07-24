package xyz.triviopoly.model;

public enum Round {
	// @formatter:off
	SINGLE_TRIVIOPOLY("Triviopoly!", 100, 50),
	DOUBLE_TRIVIOPOLY("Double Triviopoly!", 200, 50);
	// @formatter:on

	private String fancyName;
	private int dollarMultiplier;
	private int spinLimit;

	private Round(String fancyName, int dollarMultiplier, int spinLimit) {
		this.fancyName = fancyName;
		this.dollarMultiplier = dollarMultiplier;
		this.spinLimit = spinLimit;
	}

	public String fancyName() {
		return fancyName;
	}

	public int dollarMultiplier() {
		return dollarMultiplier;
	}

	public int spinLimit() {
		return spinLimit;
	}
}
