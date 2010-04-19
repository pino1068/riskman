package riskman;

import static org.junit.Assert.*;

import org.junit.*;

public class OwnerRepositoryTest {
	@Test 
	public void shouldChargeRepository() {
		OwnerRepository repo = new OwnerRepository();
		repo.add("pippo2");
		repo.add("pippo23");
		
		assertTrue(repo.contains("pippo2"));
		assertTrue(repo.contains("pippo23"));
		assertFalse(repo.contains("aaa14"));
	}
	

}
