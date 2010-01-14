package ch.siagile.finance;


public class MAXCheck extends SingleCheck {
	
	public MAXCheck(double bigDecimal) {
		super(bigDecimal);
	}

	public boolean check(Ratio value) {
		return value.isLowerOrEqualsTo(this.value());
	}

}
