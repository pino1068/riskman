package ch.siagile.finance.command;

import ch.siagile.finance.app.*;

public interface Command {

	String describe();

	String execute(Workspace data);
}