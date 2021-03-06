package riskman.app;

import static java.text.MessageFormat.*;

import java.util.*;

import riskman.command.*;

public class Shell {

	public void execute(Workspace workspace, String definition) {
		if (definition.equals(""))
			workspace.console.println("OK");
		Commands commands = new Commands();
		Command command = commands.build(definition);
		command.workspace(workspace);
		command.execute(definition);
	}

	public void execute(Workspace data, List<String> definitions) {
		for (String definition : definitions)
			try {
				execute(data, definition);
			} catch (Exception e) {
				data.console.println(format("error:{0} {1}", definition, e
						.getMessage()));
			}
	}

}