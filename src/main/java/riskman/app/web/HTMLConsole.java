package riskman.app.web;

import riskman.app.console.*;

public class HTMLConsole implements Console {

	private final BatchConsole batchConsole;

	public HTMLConsole(BatchConsole batchConsole) {
		this.batchConsole = batchConsole;
	}

	@Override
	public void println(String... strings) {
		for (String string : strings) {
			batchConsole.println("<p class=line >");
			batchConsole.println(toHtml(string));
			batchConsole.print("</p>");
		}
	}

	private String toHtml(String string) {
		return string.replaceAll("\n", "<br />");
	}

	@Override
	public void print(String... strings) {
		for (String string : strings)
			batchConsole.print(toHtml(string));
	}

	@Override
	public String waitForInput() {
		return null;
	}

	@Override
	public boolean canRestart() {
		return false;
	}

}
