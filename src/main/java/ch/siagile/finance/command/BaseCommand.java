package ch.siagile.finance.command;


public abstract class BaseCommand implements Command {

	private final String content;

	public BaseCommand(String content) {
		this.content = content;
	}
	
	protected String content() {
		return content;
	}

}