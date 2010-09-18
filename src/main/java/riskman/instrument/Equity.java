package riskman.instrument;




public class Equity extends Instrument{

	public Equity(String id, String name) {
		super(id,name);
	}

	public static Equity from(String name) {
		return new Equity(name,name);
	}

	@Override
	protected boolean isNotMyType(Object obj) {
		return !Equity.class.isInstance(obj);
	}
}
