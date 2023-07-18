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

	public static Dday fromRemainingDays(int remainingDays) { // 문자열 값을 열거형으로 변환하는 메소드를 추가
		for (Dday dday : Dday.values()) {
			if (dday.getRemainingDays() == remainingDays) {
				return dday;
			}
		}
		throw new IllegalArgumentException("Invalid remaining days value: " + remainingDays);
	}
}
