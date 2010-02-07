package riskman;

import java.util.*;

public class Some<T> {

	public static final <T> Some<T> some(List<T> someT) {
		return new Some<T>(someT);
	}

	private final List<T> someT;

	public Some(List<T> someT) {
		this.someT = someT;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (T t : someT) 
			builder.append(t).append("\n");
		return builder.toString();
	}

}
