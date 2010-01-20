package ch.siagile.finance;

import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;

public abstract class Parser {

	protected String[] fields;

	protected void fields(String string) {
		fields = string.split(",");
	}

	protected Money fMoney(int quantity2, int currency2) {
		return Money.from(fDouble(quantity2), f(currency2));
	}

	protected String f(int field, String orDefault) {
		String price = f(field);
		if ("".equals(price)) {
			price = orDefault;
		}
		return price;
	}

	private double fDouble(int quantity2) {
		return Double.parseDouble(f(quantity2));
	}

	protected String f(int fieldPosition) {
		if(fieldPosition > fields.length) return "";
		return fields[fieldPosition];
	}

	public abstract boolean recognize(String string);

	public abstract Position parse(String string);

}
