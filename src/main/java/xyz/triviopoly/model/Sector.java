package xyz.triviopoly.model;

import java.awt.Color;

public enum Sector {
	// @formatter:off
	CATEGORY1(0, "Category 1", Color.YELLOW, true, 1),
	FREE_SPIN(1, "Free Spin", Color.BLUE, false, -1),
	CATEGORY2(2, "Category 2", Color.RED, true, 2),
	LOSE_SPIN(3, "Lose Spin", Color.GREEN, false, -1),
	CATEGORY3(4, "Category 3", Color.MAGENTA, true, 4),
	SPIN_AGAIN(5, "Spin Again", Color.CYAN, false, -1),
	CATEGORY4(6, "Category 4", Color.LIGHT_GRAY, true, 4),
	BANKRUPT(7, "Bankrupt", Color.PINK, false, -1),
	CATEGORY5(8, "Category 5", Color.ORANGE, true, 5),
	PLAYERS_CHOICE(9, "Choice", Color.GRAY, false, -1),
	CATEGORY6(10, "Category 6", Color.BLUE.brighter(), true, 6),
	OPPONENTS_CHOICE(11, "Opp. Choice", Color.GREEN.brighter(), false, -1);
	// @formatter:on

	private int sectorNumber;
	private String wheelText;
	private Color wheelColor;
	private boolean categorySector;
	private int categoryNumber;

	private Sector(int sectorNumber, String wheelText, Color wheelColor,
			boolean categorySector, int categoryNumber) {
		this.sectorNumber = sectorNumber;
		this.wheelText = wheelText;
		this.wheelColor = wheelColor;
		this.categorySector = categorySector;
		this.categoryNumber = categoryNumber;
	}

	public int sectorNumber() {
		return sectorNumber;
	}

	public String wheelText() {
		return wheelText;
	}

	public Color wheelColor() {
		return wheelColor;
	}

	public boolean categorySector() {
		return categorySector;
	}

	public int categoryNumber() {
		return categoryNumber;
	}

	public static Sector valueOf(int sectorNumber) {
		for (Sector sector : values()) {
			if (sector.sectorNumber() == sectorNumber) {
				return sector;
			}
		}
		throw new IllegalArgumentException("Not a valid sector number");
	}
}
