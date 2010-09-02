package riskman.command;

import static java.lang.String.*;

public class NoCommand extends BaseCommand {

	public String describe() {
		return null;
	}

	public void execute(String definition) {
		print(format("<%1$s> command not found", definition));
	}

}
