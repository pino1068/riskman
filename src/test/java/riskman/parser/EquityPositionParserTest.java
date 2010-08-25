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
	private String equityWithQuantityWithComma = "pippo53;A;EUR;7200.0;322646EU;ALLIANZ AG;128.47809946695963;84.219999999999999;606384.0;801466.27068672015;-0.3444797179486716;E;Azioni e simili;801466.27068672015;606384.0;777141.73440000007;189;1;;SX5E.STX;;";
	private String equityWithError = "pippo53;A;EUR;7200.0;322646EU;";
	
	// variables used in shouldSumTwoEquitiesInxxx
	private String StrPosCHF1 = "pippo53;A;CHF;5000.0;1241051CH;BALOISE HDL N;118.55906066894531;82.5;412500.0;412500.0;-0.30414428442237496;E;Azioni e simili;412500.0;312094.73080592434;399980.60700087267;239;1;;SMI.S;;";
	private String StrPosCHF2 = "pippo53;A;CHF;7000.0;3785164CH;Nobel Biocare;77.421428571428578;16.469999999999999;115289.99999999999;115289.99999999999;-0.78726819817326321;E;Azioni e simili;115289.99999999999;87227.640035430333;111790.94346940752;1515;1;;SMI.S;;";
	private String StrPosEUR1 = "pippo53;A;EUR;7200.0;322646EU;ALLIANZ AG;128.47809946695963;84.219999999999999;606384.0;801466.27068672015;-0.3444797179486716;E;Azioni e simili;801466.27068672015;606384.0;777141.73440000007;189;1;;SX5E.STX;;";
	private String StrPosEUR2 = "pippo53;A;EUR;6176.0;487663EU;Groupe Danone Common Stock;52.253518371384381;41.789999999999999;258095.04000000001;341127.84834616329;-0.20024524084706463;E;Azioni e simili;341127.84834616329;258095.04000000004;330774.60326400009;384;1;;;;";
	
	private EquityPosition position1;
	private EquityPosition position2;
	
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

	@Test  
	public void shouldParseQuantityWithComma() {
		assertThat(parse(equityWithQuantityWithComma).balance(),is(EUR(606.384)));
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldParseProducesError() {
		assertThat(parse(equityWithError).balance(),is(EUR(606.384)));
	}
	

	@Test 
	public void shouldSumTwoEquitiesInCHF() {

		position1 = parse(StrPosCHF1);
		position2 = parse(StrPosCHF2);
		
		Positions myPositions = new Positions(position1,position2);
		
		assertThat(myPositions.value(), is(equalTo(CHF(5000*82.5+7000*16.47))));
	}	

	
	@Ignore
	public void shouldSumTwoEquitiesInEUR() {
		position1 = parse(StrPosEUR1);
		position2 = parse(StrPosEUR2);
		
		Positions myPositions = new Positions(position1,position2);
		
		assertThat(myPositions.value(), is(equalTo(EUR(7200*84.22+6176*41.79))));
	}

	private EquityPosition parse(String string) {
		return (EquityPosition)importer.parse(string);
	}
	
}
