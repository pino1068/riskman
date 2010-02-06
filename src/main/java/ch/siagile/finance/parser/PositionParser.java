package ch.siagile.finance.parser;

import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;

public abstract class PositionParser {

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

	protected double fDouble(int quantity2) {
		return Double.parseDouble(f(quantity2));
	}

	protected String f(int fieldPosition) {
		if(fieldPosition > fields.length) return "";
		return fields[fieldPosition];
	}

	public boolean recognize(String string){
		fields(string);
		return recognizePosition(string);
	}

	protected abstract boolean recognizePosition(String string);

	public Position parse(String string){
		fields(string);
		return parsePosition(string);
	}

	protected abstract Position parsePosition(String string);

	protected int fInt(int fieldPosition) {
		return Integer.valueOf(f(fieldPosition));
	}

}
