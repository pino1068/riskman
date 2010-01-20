package ch.siagile.finance.command;

import java.util.*;

import ch.siagile.finance.constraint.*;

public class CurrencyCommand extends Command {

	public CurrencyCommand(String definition) {
		super(definition);
	}

	@Override
	public Constraint constraint() {
		return new CurrencyConstraint(check(), values("unkown command for currency:"));
	}

	@Override
	public boolean canExecute(String string) {
		return isCurrency(string.split(" |,")[0]);
	}

	private boolean isCurrency(String currencyCode) {
		try {
			Currency.getInstance(currencyCode);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Command createFrom(String definition) {
		return new CurrencyCommand(definition);
	}
}
