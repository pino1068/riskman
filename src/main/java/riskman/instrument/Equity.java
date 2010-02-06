package riskman.instrument;




public class Equity extends Instrument{

	public Equity(String name) {
		super(name);
	}

	public static Equity from(String name) {
		return new Equity(name);
	}

	@Override
	protected boolean isNotMyType(Object obj) {
		return !Equity.class.isInstance(obj);
	}
}
