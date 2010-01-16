package ch.siagile.finance;

public class EqualsCheck extends SingleCheck {
	
	public EqualsCheck(Percent percent) {
		super(percent);
	}

	public boolean check(Ratio value) {
		return value.isEqualTo(value());
	}


}
