package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.position.*;

public abstract class Command {

	private final String content;

	public Command(String content) {
		this.content = content;
	}
	
	protected String content() {
		return content;
	}

	public abstract boolean canExecute(String string);

	
	private Command clone(String definition) {
		try {
			return this.getClass().getConstructor(String.class).newInstance(definition);
		} catch (Exception e) {
			throw new RuntimeException(format("cannot create comman {0} with definition {1}",getClass(),definition));
		}
	}

	public Command createFrom(String definition) {
		return clone(definition);
	}

	public abstract String execute(String dirname, Positions positions);

}