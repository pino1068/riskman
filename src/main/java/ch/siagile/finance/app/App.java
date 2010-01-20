package ch.siagile.finance.app;

import java.io.*;

import ch.siagile.finance.base.*;
import ch.siagile.finance.position.*;

public class App {
	private final static MenuList menu = new MenuList(new HelpMenu(),
			new PositionMenu(), new ExecutionMenu(), new QuitMenu(),
			new EmptyMenu(), new NullMenu());

	public static void main(String args[]) {
		new App().start();
	}

	public void start() {
		Positions positions = positions();
		printHeader();
		String line = null;
		do {
			line = waitingForInput();
			for (Menu item : menu.list()) {
				if (item.isCalled(line)) {
					item.perform(positions, line);
					break;
				}
			}
		} while (!new QuitMenu().isCalled(line));
	}

	private Positions positions() {
		return new Positions(account("name", Money.CHF(30)), account("name2",
				Money.USD(70)));
	}

	private void printHeader() {
		for (int i = 0; i < 10; i++) {
			println("");
		}
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

	private Position account(String name, Money balance) {
		return new AccountPosition(name, balance);
	}

	private String waitingForInput() {
		print("> ");
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
}
