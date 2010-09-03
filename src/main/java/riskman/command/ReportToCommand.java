package riskman.command;

import riskman.app.App;

public class ReportToCommand extends BaseCommand {


	public String describe() {
		return "	'reportTo:<filename>'			- will switch the session logging to given file";
	}

	public void execute(String command) {
		String filename = commands.contentOf(command);
		App.switchReportTo(filename);
		printlnFormatted("from now all your commands will be reported to <%1$s>",filename);
	}


}
