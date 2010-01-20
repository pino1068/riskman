package ch.siagile.finance;

import static java.text.MessageFormat.*;

import java.util.*;

import ch.siagile.finance.position.*;

public class PositionsParser {

	private final List<Parser> parsers = new LinkedList<Parser>() {
		{
			add(new BondParser());
			add(new AccountParser());
		}
	};

	public Position parse(String string) {
		for (Parser parser : parsers) {
			if (parser.recognize(string))
				return parser.parse(string);
		}
		throw new RuntimeException(format("unable to parse {0}", string));
	}

}
