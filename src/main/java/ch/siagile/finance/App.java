package ch.siagile.finance;

import java.io.*;

import ch.siagile.finance.position.*;

public class App {
	public static void main(String args[]) {
		Positions positions = positions();
		Shell shell = new Shell();
		printPositions(positions);
		String line = null;
		do {
			printHeader();
			line = acceptString();
			if (!"quit".equals(line)) {
				executeAndPrintResult(positions, shell, line);
			}
		} while (!"quit".equals(line));
	}

	private static Positions positions() {
		Positions positions = new Positions(account("name", Money.CHF(30)),
				account("name2", Money.USD(70)));
		return positions;
	}

	private static void printHeader() {
		System.out.print("Enter commands  (or 'quit' to exit):   ");
	}

	private static void executeAndPrintResult(Positions positions, Shell shell,
			String line) {
		System.out.print("Result :");
		System.out.println(shell.execute(positions, line));
	}

	private static void printPositions(Positions positions) {
		System.out.println("Our positions are:");
		System.out.println(positions);
	}

	private static Position account(String name, Money balance) {
		return new AccountPosition(name, balance);
	}

	private static String acceptString() {
		String result = "";
		int bytesRead = 0;
		byte name[] = new byte[100];
		try {
			bytesRead = System.in.read(name);
			OutputStream os = new ByteArrayOutputStream(100);
			os.write(name, 0, bytesRead);
			result = os.toString();
		} catch (IOException e) {
			System.out.println("Sorry: ");
			e.printStackTrace();
		}
		return result.replace("\n", "");
	}

}
