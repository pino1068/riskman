package riskman.parser;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import riskman.position.*;



public class PositionsParserTest {
	
	private String account1 = "pippo1;L;EUR;1041804.04494558;;EUR-0456-733008-92;;;1041804.04494558;1041804.04494558;;A;Saldi in Cto. Corrente;1537369.6534963;1041804.04494558;1510928.40638457;;;;;;";
	private String bond1 = "pippo1;Oacc;EUR;0;3495227;20111024 - 4.875% Procter & Gamble 24-10-11 Pro-rata;;;540.924682617188;540.924682617188;;D;Obbligazioni e simili          ;798.231870875702;540.924682617188;784.503067199707;1043;2;;;;";

	private Position position;
	
	@Test 
	public void shouldRecognizeAccount() {
		position = new PositionsParser().parse(account1);
		assertThat(position, is(instanceOf(AccountPosition.class)));
	}

	@Test 
	public void shouldRecognizeBond() {
		position = new PositionsParser().parse(bond1);
		assertThat(position, is(instanceOf(BondPosition.class)));
	}
}
