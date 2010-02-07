package riskman.command;

import riskman.app.*;

public interface Command {
	void workspace(Workspace data);
	void execute(String definition);
	String describe();
}