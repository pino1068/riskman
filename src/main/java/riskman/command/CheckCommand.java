package riskman.command;

import riskman.check.*;

public abstract class CheckCommand extends BaseCommand{

	protected Check check(String def) {
		return Check.from(checkFrom(def));
	}
	
	protected String checkFrom(String definition) {
		return definition.substring(definition.lastIndexOf(" ")+1);
	}
}
