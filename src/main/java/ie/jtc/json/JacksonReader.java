package ie.jtc.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.jtc.model.GPLocation;

@Service
public class JacksonReader {	
	static JsonFactory factory = new JsonFactory();		
	public List<GPLocation> getJsonPeople(File file) throws JsonParseException, IOException{
	List<GPLocation> dataSet=new ArrayList<GPLocation>();
	ObjectMapper mapper = new ObjectMapper();
		
	JsonParser jsonParser = factory.createParser(file);
	MappingIterator<GPLocation> jsonIter= mapper.readValues( jsonParser,GPLocation.class);
	while(jsonIter.hasNext()){	
		GPLocation gp=jsonIter.nextValue();
		dataSet.add(gp);
	}
	return dataSet;
	}
}