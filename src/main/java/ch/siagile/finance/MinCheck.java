package ch.siagile.finance;

public class MINCheck extends SingleCheck {

	public MINCheck(double value) {
		super(value);
	}

	@Override
	public boolean check(Ratio value) {
		return value.isGreaterTheOrEqualsTo(this.value());
	}

}
