package riskman.app;

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
				print("riskman COMMAND [ARGS]");
				print("The most commonly used riskman commands are:");
				print("  batch       execute risk management filter and constraint");
				print("  shell       allow user to interact with portfolio using filter and constraint");
				print("  tester      compare filter and constraint over an expected output");
			}

			private void print(String x) {
				System.out.println(x);
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
