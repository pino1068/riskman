package riskman.repository;

import java.util.*;

import riskman.instrument.Bond;

public class BondRepository {

	private static Set<Bond> bonds = new HashSet<Bond>();

	public void add(Bond bond) {
		bonds .add(bond);
	}
	
	public Iterable<Bond> bonds(){
		return bonds;
	}

}
