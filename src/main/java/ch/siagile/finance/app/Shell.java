package ch.siagile.finance.app;

import static ch.siagile.finance.Some.*;
import static java.text.MessageFormat.*;

import java.util.*;

import ch.siagile.finance.command.*;

public class Shell {

	CommandBuilder builder = new CommandBuilder();
	
	public String execute(Workspace data, String definition) {
		if (definition.equals(""))
			return "OK";
		return builder.build(definition).execute(data);
	}

	public String execute(Workspace data, List<String> definitions) {
		List<String> outputs = new LinkedList<String>();
		for (String definition : definitions)
			try {
				outputs.add(definition+execute(data, definition));
			} catch (Exception e) {
				e.printStackTrace();
				return format("error:{0} {1}", definition, e.getMessage());
			}
		return some(outputs).toString();
	}

}