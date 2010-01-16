package ch.siagile.finance;

public class MinCheck extends SingleCheck {

	public MinCheck(Percent value) {
		super(value);
	}

	@Override
	public boolean check(Ratio aRatio) {
		return aRatio.isGreaterTheOrEqualsTo(value());
	}

}
