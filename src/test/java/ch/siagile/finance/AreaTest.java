package ch.siagile.finance;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class AreaTest {

	@Test
	public void shouldCreateNewArea() {
		Area area = Area.from("CH");

		assertThat(area, notNullValue());
		assertThat(Area.from("CH"), is(area));
	}

	@Test
	public void shouldHaveAllAreas() {
		Areas areas = Areas.list();

		assertThat(areas.size(), is(6));
	}
	//	
	// @Test
	// public void shouldLocateBondToArea() {
	// Area CH = Areas.find("CH");
	//		
	// Bond
	// }
}
