package ch.siagile.finance;

public class MaxCheck extends SingleCheck {

	public MaxCheck(double value) {
		super(value);
	}

	public boolean check(Ratio value) {
		return value.isLowerOrEqualsTo(value());
	}

}
