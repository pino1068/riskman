package ch.siagile.finance.command;

import ch.siagile.finance.app.*;

public interface Command {
	void workspace(Workspace data);
	void execute(String definition);
	String describe();
}