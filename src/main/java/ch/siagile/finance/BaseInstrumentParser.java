package ch.siagile.finance;


public abstract class BaseInstrumentParser extends Parser {
	
	protected static final int CURRENCY = 2;
	private static final int INSTRUMENT = 1;
	private static final int NAME = 5;
	private static final int OWNER = 0;

	protected String name() {
		return f(NAME);
	}

	protected String owner() {
		return f(OWNER);
	}

	public boolean recognizePosition(String string) {
		return f(INSTRUMENT).equals(discriminator());
	}

	protected abstract String discriminator();

}
