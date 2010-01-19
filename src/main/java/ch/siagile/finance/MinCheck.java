package ch.siagile.finance;

public class MinCheck  extends SingleCheck{

	public MinCheck(double value) {
		super(value);
	}

	public boolean check(Ratio value) {
		return value.isGreaterTheOrEqualsTo(value());
	}

}
