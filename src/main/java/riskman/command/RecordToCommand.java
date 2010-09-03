package riskman.command;

import riskman.app.App;

public class RecordToCommand extends BaseCommand {


	public String describe() {
		return "	'recordTo:<filename>'			- will switch the session logging to given file";
	}

	public void execute(String command) {
		String filename = commands.contentOf(command);
		App.recordTo(filename);
		printlnFormatted("from now all your commands will be reported to <%1$s>",filename);
	}


}
