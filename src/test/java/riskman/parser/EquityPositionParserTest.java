package riskman.parser;

import static riskman.money.Money.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.position.*;

public class EquityPositionParserTest {

	private PositionParser importer;

	private String bond = "pippo1;Oacc;EUR;0;3495227;20111024 - 4.875% Procter & Gamble 24-10-11 Pro-rata;;;540.924682617188;540.924682617188;;D;Obbligazioni e simili          ;798.231870875702;540.924682617188;784.503067199707;1043;2;;;;";
	private String equity1 = "pippo8;A;EUR;180;1335786EU;Sparinvest Sicav Global Value;105.019996643066;140.4;25272;37293.391278;0.336888254502429;E;Azioni e simili;37293.391278;25272;36651.9816;1841;14;;;;";
	private String equity2 = "pippo13;A;CHF;330;827766CH;Siemens N chf;99.3000030517578;98.6;32538;32538;-0.00704937593398602;E;Azioni e simili;32538;22049.4920901733;31978.3783783784;880;1;;SMI.S;;";
	
	@Before
	public void setUp(){
		importer = new EquityPositionParser();
	}
	@Test  
	public void shouldRecognizeEquity() {
		assertTrue(importer.recognize(equity1));
		assertTrue(importer.recognize(equity2));
		assertFalse(importer.recognize(bond));
	}
	@Test 
	public void shouldImportAccount$Type() {
		assertThat(parse(equity1), is(instanceOf(EquityPosition.class)));
		assertThat(parse(equity2), is(instanceOf(EquityPosition.class)));
	}

	
	@Test  
	public void shouldParseEquityField() {
		assertTrue(parse(equity1).isCalled("Sparinvest Sicav Global Value"));
	}
	
	@Test  
	public void shouldMoneyBeCorrect() {
		assertThat(parse(equity1).balance(), is(equalTo(EUR(180*140.4))));
		assertThat(parse(equity2).balance(), is(equalTo(CHF(330*98.6))));
	}
	
	@Test  
	public void shouldOwnerBeCorrect() {
		assertTrue(parse(equity1).isOwnedBy("pippo8"));
		assertTrue(parse(equity2).isOwnedBy("pippo13"));
	}
	
	
	private EquityPosition parse(String string) {
		return (EquityPosition)importer.parse(string);
	}
	
}
