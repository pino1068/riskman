package riskman.parser;

import static java.text.MessageFormat.*;

import java.util.*;

import riskman.position.*;

public class PositionsParser {

	private final List<PositionParser> parsers = new LinkedList<PositionParser>() {
		private static final long serialVersionUID = 1L;
		{
			add(new BondPositionParser());
			add(new AccountPositionParser());
			add(new EquityPositionParser());
		}
	};

	private OperationListener listener = new OperationListener() {// no listener
		public void success(String string, Position position) {
		}

		public void failure(String string) {
		}
	};

	public void listener(OperationListener listener) {
		this.listener = listener;
	}

	public Position parse(String string) {
		for (PositionParser parser : parsers) 
			if (parser.recognize(string)) 
				return parser.parse(string);
		
		throw new RuntimeException(format("unable to parse {0}", string));
	}

	public Positions parse(List<String> strings) {
		Positions positions = new Positions();
		for (String string : strings){
			try {
				Position position = parse(string);
				positions.add(position);
				listener.success(string, position);
			} catch (Exception e) {
				listener.failure(string);
			}
		}
		return positions;
	}

}
