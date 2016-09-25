package ie.jtc.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLocationJSON {

	@Test
	public void test() throws JsonProcessingException {
		GPLocation gp = new GPLocation();
		gp.setId("1234");
		gp.setName("DR. ROBERTS");
		gp.setAddress("FUNKYTOWN, LA");
		gp.getLatitudeAndLongitude().setLatitude(10.00001);
		gp.getLatitudeAndLongitude().setLongitude(-17.00999);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonGp=mapper.writeValueAsString(gp);
		// not sure how robust this test is, I suspect the values could change position
		assertEquals("{\"address\":\"FUNKYTOWN, LA\",\"latitudeAndLongitude\":{\"longitude\":-17.00999,\"latitude\":10.00001},\"id\":\"1234\",\"name\":\"DR. ROBERTS\"}",
				jsonGp);
	}

}
