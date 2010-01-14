package ch.siagile.finance;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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
//	@Test
//	public void shouldLocateBondToArea() {
//		Area CH = Areas.find("CH");
//		
//		Bond
//	}
}
