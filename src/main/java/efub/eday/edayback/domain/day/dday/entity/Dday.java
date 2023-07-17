package efub.eday.edayback.domain.day.dday.entity;

public enum Dday {
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ONE(1);

	private final int remainingDays;

	Dday(int remainingDays) {
		this.remainingDays = remainingDays;
	}

	public int getRemainingDays() {
		return remainingDays;
	}
}
