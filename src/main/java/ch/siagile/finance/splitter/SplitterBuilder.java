package ch.siagile.finance.splitter;

import java.security.*;
import java.util.*;
import static java.text.MessageFormat.*;

public class SplitterBuilder {

	private static final List<KeySelector> selectors = new ArrayList<KeySelector>(){
		private static final long serialVersionUID = 1L;
		{
			add(new CurrencyKeySelector());
			add(new PositionKeySelector());
			add(new OwnerKeySelector());
			add(new TypeKeySelector());
		}
	};
	
	public static Splitter from(String criteria) {
		for (KeySelector selector : selectors) 
			if(selector.isCalled(criteria))
				return new Splitter(selector);
		throw new InvalidParameterException(format("split criteria <{0}> not valid", criteria));
	}
}
