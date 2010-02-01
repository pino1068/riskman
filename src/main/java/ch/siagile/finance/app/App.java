package ch.siagile.finance.app;

import java.io.*;

import ch.siagile.finance.position.*;

public class App {
	private final static MenuList menu = new MenuList(new HelpMenu(),
			new PositionMenu(), new ExecutionMenu(), new FilterMenu(), new RemoveFilterMenu(), new QuitMenu(),
			new EmptyMenu(), new NullMenu());

	public static void main(String args[]) {
		new App().start();
	}

	public void start() {
		AppContext context = new AppContext("", new Positions());
		printHeader();
		String line = null;
		do {
			line = waitingForInput(context);
			for (Menu item : menu.list()) {
				if (item.isCalled(line)) {
					item.perform(context, line);
					break;
				}
			}
		} while (!new QuitMenu().isCalled(line));
	}

	private void printHeader() {
		for (int i = 0; i < 10; i++) 
			println("");
		println("Enter a command or 'h' for help or 'quit' to exit");
	}

	private void print(String... strings) {
		for (String string : strings) {
			System.out.print(string);
		}
	}

	private void println(String... strings) {
		for (String string : strings) {
			System.out.println(string);
		}
	}

	private String waitingForInput(AppContext context) {
		printPrefix(context);
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

	private void printPrefix(AppContext context) {
		println("");
		print(context.path());
		print(" > ");
	}
}
