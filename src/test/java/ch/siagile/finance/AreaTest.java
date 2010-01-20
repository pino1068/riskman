package ch.siagile.finance;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import ch.siagile.finance.location.*;

public class AreaTest {

	@Test
	public void shouldCreateNewArea() {
		Area area = Area.from("CH");

		assertThat(area, notNullValue());
		assertThat(Area.from("CH"), is(area));
	}

	@Test
	public void shouldHaveAllAreas() {
		Areas areas = Areas.defaultAreas;

		assertThat(areas, hasItem(Area.from("CH")));
	}
}
