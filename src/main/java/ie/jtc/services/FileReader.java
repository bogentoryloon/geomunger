package ie.jtc.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;

import ie.jtc.json.JacksonReader;
import ie.jtc.model.GPLocation;
@Service
public class FileReader {
	@Autowired 
	JacksonReader jsonParser;
	public List<GPLocation> readPCRSFile(String fileName) throws JsonParseException, IOException{				
		File file = new File(fileName); 
		return jsonParser.getJsonPeople(file);
	}
}
