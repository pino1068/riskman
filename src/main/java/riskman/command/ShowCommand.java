package riskman.command;

import java.util.*;

import riskman.OwnerRepository;
import riskman.instrument.rating.MoodyRatings;
import riskman.location.Areas;
import riskman.repository.*;

public class ShowCommand extends BaseCommand {

	public String describe() {
		return "	'show:<data>'			- print the values related to <data>";
	}

	public void execute(String definition) {
		definition = commands.contentOf(definition);
		printlnFormatted("Available 1%$s:",definition);
		if("areas".equals(definition))
			printAll(Areas.all());
		if ("owners".equals(definition))
			printAll(new OwnerRepository().owners());
		if ("equities".equals(definition)) 
			printAll(new EquityRepository().equities());
		if ("bonds".equals(definition)) 
			printAll(new BondRepository().bonds());
		if ("moody".equals(definition)) 
			printAll(new MoodyRatings().ratings());
	}

	private <T> void printAll(Iterable<T> values) {
		int counter = 0;
		println("----------------");
		Iterable<String> sorted = sort(values);
		for (String string : sorted) {
			println(string);
			counter++;
		}
		println("----------------");
		printlnFormatted("1%$s element(s) found.",counter);
	}
	
	private <T> Iterable<String> sort(Iterable<T> values){
		List<String> strings = new LinkedList<String>();
		for (T value : values) 
			strings.add(value.toString());
		Collections.sort(strings);
		return strings;
	}

}
