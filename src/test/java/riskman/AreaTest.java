package riskman;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import riskman.location.*;

public class AreaTest {

	@Test 
	public void shouldCreateNewArea() {
		Area area = Area.from("CH");

		assertThat(area, notNullValue());
		assertThat(Area.from("CH"), is(area));
	}

	@Test 
	public void shouldHaveAllAreas() {
		Areas areas = Areas.all();

		assertThat(areas, hasItem(Area.from("CH")));
	}
}
