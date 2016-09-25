package ie.jtc.mode.bingjson;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBingJson {

	@Test
	public void test() throws Exception, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("myhousetodoc.json");
		assertNotNull(is);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Route route=(Route)objectMapper.readValue(is,Route.class);
		assertNotNull(route);
		/*
		JsonFactory factory = new JsonFactory();	
		JsonParser jsonParser = factory.createJsonParser(is);
		ObjectMapper mapper = new ObjectMapper();
		MappingIterator<BingRouteJson> jsonIter= mapper.readValues( jsonParser,BingRouteJson.class);
		while(jsonIter.hasNext()){	
			BingRouteJson dto=jsonIter.nextValue();
		}
		*/
	}

}
