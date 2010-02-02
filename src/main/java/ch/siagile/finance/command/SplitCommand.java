package ch.siagile.finance.command;

import static java.text.MessageFormat.*;
import ch.siagile.finance.*;
import ch.siagile.finance.app.*;
import ch.siagile.finance.check.*;
import ch.siagile.finance.money.*;
import ch.siagile.finance.position.*;
import ch.siagile.finance.splitter.*;

public class SplitCommand extends BaseCommand {

	private Splitter splitter;

	public SplitCommand(String definition) {
		super(definition);
		if (definition.trim().length() > 0)
			splitter = SplitterBuilder.from(criteria(definition));
	}

	private String criteria(String definition) {
		return definition.split(" ")[0];
	}
	
	public String execute(String dirname, Positions positions) {
		return checkLimitOn(positions);
	}

	public String execute(ContextData data) {
		return execute(data.workingDir(),data.positions());
	}

	private String checkLimitOn(Positions positions) {
		StringBuffer result = new StringBuffer("\n");
		Split split = splitter.split(positions);
		for (Object group : split.groups()) {
			result.append("\n");
			Ratio ratio = split.positions(group).divideBy(positions);
			if (checker().check(ratio))
				result.append(format("{0} OK", group));
			else
				result.append(format("{0} KO, check <{1}> but is {3} ({2})", group, check(), ratio, ratio.percent()));
		}
		return result.toString();
	}

	private Check checker() {
		return Check.from(check());
	}

	private String checkFrom(String definition) {
		return definition.substring(definition.lastIndexOf(" ") + 1);
	}

	private String check() {
		return checkFrom(content());
	}

	public String describe() {
		return "	'splitby:<criteria>'		- split according to criteria and print results, i.e. splitby:owner";
	}

}
