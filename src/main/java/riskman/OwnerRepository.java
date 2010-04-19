package riskman;

import java.util.*;

public class OwnerRepository {

	private static Set<String> owners = new HashSet<String>();

	public void add(String owner) {
		owners.add(owner);
	}

	public boolean contains(String owner) {
		return owners.contains(owner);
	}

	public void cleanup() {
		owners.clear();
	}

	public Iterable<String> owners() {
		return owners;
	}

}
