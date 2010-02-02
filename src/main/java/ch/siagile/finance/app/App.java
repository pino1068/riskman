package ch.siagile.finance.app;

import java.io.*;

import ch.siagile.finance.position.*;

public class App {
	private final static Commands commands = new Commands(new HelpMenu(),
			new PositionMenu(), new ExecutionMenu(), new FilterMenu(), new SplitByMenu(), new RemoveFilterMenu(), new QuitMenu(),
			new EmptyMenu(), new NullMenu());

	public static void main(String args[]) {
		new App().start();
	}

	public void start() {
		ContextData data = new ContextData(new Positions(), commands, System.getProperty("user.dir"));
		printHeader();
		String line = null;
		do {
			line = waitingForInput(data);
			for (Menu item : commands.list()) {
				if (item.canExecute(line)) {
					item.execute(data, line);
					break;
				}
			}
		} while (!new QuitMenu().canExecute(line));
	}

	private void printHeader() {
		for (int i = 0; i < 10; i++) 
			println("");
		println("Enter a command or 'h' for help or 'quit' to exit");
	}

	private void print(String... strings) {
		for (String string : strings) 
			System.out.print(string);
	}

	private void println(String... strings) {
		for (String string : strings) 
			System.out.println(string);
	}

	private String waitingForInput(ContextData data) {
		printPrefix(data);
		String result = "";
		int bytesRead = 0;
		byte name[] = new byte[100];
		try {
			bytesRead = System.in.read(name);
			OutputStream os = new ByteArrayOutputStream(100);
			os.write(name, 0, bytesRead);
			result = os.toString();
		} catch (IOException e) {
			println("Sorry: ");
			e.printStackTrace();
		}
		return result.replace("\n", "");
	}

	private void printPrefix(ContextData data) {
		println("");
		print(data.path());
		print(" > ");
	}
}
