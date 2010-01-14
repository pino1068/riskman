package ch.siagile.finance;

public class EqualsCheck extends SingleCheck {
	
	public EqualsCheck(double bigDecimal) {
		super(bigDecimal);
	}

	public boolean check(Ratio value) {
		return value.isEqualsTo(this.value());
	}


}
