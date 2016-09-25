package ie.jtc.mode.bingjson;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.jtc.model.bingjson.Route;
import ie.jtc.model.bingjson.RouteSlim;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBingJson {

	private static Logger log = Logger.getLogger(TestBingJson.class);
	@Test
	public void test() throws Exception, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("myhousetodoc.json");
		assertNotNull(is);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		long start=System.currentTimeMillis();
		Route route=(Route)objectMapper.readValue(is,Route.class);
		long duration=System.currentTimeMillis()-start;
		/*
		 * it get 156ms consistently
		 */
		log.info("jackson parse took "+duration);
		assertNotNull(route);
		assertEquals(1, route.resourceSets[0].estimatedTotal);
		assertEquals("Kilometer", route.resourceSets[0].resources[0].distanceUnit);
		assertEquals(15.5, route.resourceSets[0].resources[0].travelDistance,0.1);
		// it appears jackson closes the stream
		is = classLoader.getResourceAsStream("myhousetodoc.json");
		RouteSlim slim=(RouteSlim)objectMapper.readValue(is,RouteSlim.class);
		duration=System.currentTimeMillis()-start;
		/*
		 * and completely counter to expectations, this takes 3500.
		 * so much for that "optimisation"
		 */
		log.info("jackson parse took, slim "+duration);
		assertNotNull(slim);
		assertEquals(1, slim.resourceSets[0].estimatedTotal);
		assertEquals("Kilometer", slim.resourceSets[0].resources[0].distanceUnit);
		assertEquals(15.5, slim.resourceSets[0].resources[0].travelDistance,0.1);

	}

}
