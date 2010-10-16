package riskman.command;

import static java.text.MessageFormat.*;
import riskman.*;
import riskman.money.*;
import riskman.position.*;
import riskman.splitter.*;

public class SplitCheckCommand extends CheckCommand {

	private Splitter splitter;
	private String content;

	public void execute(String definition) {
		this.content = commands.contentOf(definition);
		if (this.content.trim().length() > 0)
			splitter = SplitterBuilder.from(criteria(this.content));
		execute(workspace.positions());
	}

	private String criteria(String definition) {
		return definition.split(" ")[0];
	}

	public void execute(Positions positions) {
		checkLimitOn(positions);
	}

	private void checkLimitOn(Positions positions) {
		Split split = splitter.split(positions);
		split.listener(new FormatCheckListener());
		split.chechLimitOn(check(content), positions);
	}

	public String describe() {
		return "	'check:<split>'			- split and check according to criteria and print results," +
			 "\n					  i.e. splitby:owner max:5%";
	}

	private final class FormatCheckListener implements SplitCheckListener {
		public void success(Object group, Ratio ratio) {
			newLine();
			println(format("{0} OK", group));
		}

		public void failure(Object group, Ratio ratio) {
			newLine();
			println(format("{0} KO, check <{1}> but is {3} ({2})", 
					group,
					checkFrom(content), ratio, ratio.percent()));
		}
	}

}
