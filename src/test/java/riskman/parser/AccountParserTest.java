package riskman.parser;

import static riskman.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.position.*;

public class AccountParserTest {

	private String account1 = "pippo1;L;EUR;1041804.04494558;;EUR-0456-733008-92;;;1041804.04494558;1041804.04494558;;A;Saldi in Cto. Corrente;1537369.6534963;1041804.04494558;1510928.40638457;;;;;;";
	private String account2 = "pippo2;L;EUR;1041801.62494558;;EUR-0456-389835-82;;;1041801.62494558;1041801.62494558;;A;Saldi in Cto. Corrente;1537366.08235009;1041801.62494558;1510924.89665857;;;;;;";

	private AccountPositionParser importAccount;

	@Before
	public void setUp() {
		importAccount = new AccountPositionParser();
	}

	@Test 
	public void shouldImportAccount$Balance() {
		assertThat(parsedPosition(account1).balance(), is(equalTo(EUR(1041804.04494558))));
		assertThat(parsedPosition(account2).balance(), is(equalTo(EUR(1041801.62494558))));
	}

	@Test 
	public void shouldImportAccount$Type() {
		assertThat(parsedPosition(account1), is(instanceOf(AccountPosition.class)));
	}

	@Test 
	public void shouldImportAccount$Owner() {
		assertTrue(parsedPosition(account1).isOwnedBy("pippo1"));
		assertTrue(parsedPosition(account2).isOwnedBy("pippo2"));
	}

	@Test 
	public void shouldImportAccount$Name() {
		assertTrue(parsedPosition(account1).isCalled("EUR-0456-733008-92"));
		assertTrue(parsedPosition(account2).isCalled("EUR-0456-389835-82"));
	}

	private AccountPosition parsedPosition(String account) {
		return (AccountPosition)importAccount.parse(account);
	}
}
