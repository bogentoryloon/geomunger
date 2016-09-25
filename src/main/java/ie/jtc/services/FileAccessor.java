package ie.jtc.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;

import ie.jtc.json.JsonReaderWriter;
import ie.jtc.model.GPLocation;
@Service
public class FileAccessor{
	@Autowired 
	JsonReaderWriter jsonService;
	public List<GPLocation> readPCRSFile(String fileName) throws JsonParseException, IOException{				
		File file = new File(fileName); 
		return jsonService.getJsonPeople(file);
	}
	public void writePCRSFile(String fileName,List<GPLocation> gps) throws IOException{
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);	
		jsonService.writeJsonData(gps,fop);
		fop.close();		
	}
}
