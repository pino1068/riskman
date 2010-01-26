package ch.siagile.finance;

import java.util.*;

public class Identities {

	private static Map<Object,Identity> identities = new HashMap<Object, Identity>();

	public static Identity from(Object obj) {
		if(!identities.containsKey(obj))
			identities.put(obj,new Identity());
		return identities.get(obj);
	}
}
