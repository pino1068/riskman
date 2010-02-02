package ch.siagile.finance.app;

import java.util.*;

public class RiskMan {

	String command;

	private final Map<String, Runnable> apps = new HashMap<String, Runnable>();

	public Runnable app(final String[] args) {
		Runnable tester = new Runnable() {
			public void run() {
				new Tester(args, System.out).test();
			}
		};
		Runnable shell = new Runnable() {
			public void run() {
				App.main(args);
			}
		};
		Runnable batch = new Runnable() {
			public void run() {
				new Execute(args, System.out).batch();
			}
		};
		Runnable help = new Runnable() {
			public void run() {
				System.out.println("riskman COMMAND [ARGS]");
				System.out.println("The most commonly used riskman commands are:");
				System.out.println("  batch       execute risk management filter and constraint");
				System.out.println("  shell       allow user to interact with portfolio using filter and constraint");
				System.out.println("  tester      compare filter and constraint over an expected output");
			}
		};

		app("tester", tester);
		app("shell", shell);
		app("batch", batch);
		app("help", help);

		if (args.length > 0 && apps.containsKey(args[0])) {
			this.command = args[0];
			return apps.get(args[0]);
		}
		if (args.length == 0) {
			this.command = "shell";
			return shell;
		}
		if (args.length == 1) {
			this.command = "batch";
			return batch;
		}

		this.command = "shell";
		return batch;
	}

	private void app(String name, Runnable runnable) {
		apps.put(name, runnable);
	}

	public static final void main(String[] args) {
		Runnable app = new RiskMan().app(args);
		app.run();
	}

}
