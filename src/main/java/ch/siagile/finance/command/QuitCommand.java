package ch.siagile.finance.command;



public class QuitCommand extends BaseCommand {

	public void execute(String line) {
		println("");
		println("It was nice to meet you!");
		println("");
		println("Cheers,");
		println("          RiskMan Team");
	}

	public String describe() {
		return "\n	'q' or 'quit'			- to exit this program";
	}
}
