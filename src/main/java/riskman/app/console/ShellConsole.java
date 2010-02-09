package riskman.app.console;

import java.io.*;

public class ShellConsole implements Console {

	public void print(String... strings) {
		for (String string : strings)
			System.out.print(string);
	}

	public void println(String... strings) {
		for (String string : strings)
			System.out.println(string);
	}

	public String waitForInput() {
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
		return result.replace("\n", "");//skip the last <enter>
	}

}
