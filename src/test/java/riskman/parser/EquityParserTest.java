package riskman.parser;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.*;
import riskman.instrument.*;
import riskman.location.*;

public class EquityParserTest {

	private String equityWithArea = "159;1;Adidas Salomon;059053EU;DAXEU;0;0;ADS.XE;;;;;Azioni;;;DE0005003404;C0815097-53D1-41C0-B57E-2B43662B58A5;Consumer Cyclical;UE";
	private String equity = "1870;29;Orpheus NV Fund Reserve Inv USD;10020132US;NYSE;0;0;10020132US;;;;;Investimenti alternativi;;;ANN681723162;88C7A0E3-18AC-4C07-9AFB-DB95575F2351";
	private EquityParser importer;
	private Equity parsedEquity;

	@Before
	public void setUp() {
		importer = new EquityParser(equity);
		parsedEquity = importer.parse();
	}

	@Test
	public void shouldParseCreateEquities() {
		assertTrue(parsedEquity.isCalled("Orpheus NV Fund Reserve Inv USD"));
	}
	
	@Test
	public void shouldParseArea() {
		assertTrue(new EquityParser(equityWithArea).parse().isLocatedIn("UE"));
	}

	@Test
	public void shouldParseEquityIdentity() {
		assertTrue(Identities.from(parsedEquity).isIdentifiedBy("1870"));
	}

	@Test
	public void shouldEquityBeLocatedInUSA() {
		assertThat(Location.from(parsedEquity).area(), is(Area.from("USA")));
		assertThat(Location.from(parsedEquity).area(), is(not(Area.from("UE"))));
	}

}
