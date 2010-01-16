package ch.siagile.finance;


public class MaxCheck extends SingleCheck {
	
	public MaxCheck(Percent percent) {
		super(percent);
	}

	public boolean check(Ratio aRatio) {
		return aRatio.isLowerOrEqualsTo(value());
	}

}
