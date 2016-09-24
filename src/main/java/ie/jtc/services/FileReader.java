package ie.jtc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ie.jtc.model.GPLocation;
@Service
public class FileReader {
	public List<GPLocation> readPCRSFile(String file){
		List<GPLocation> retval = new ArrayList<GPLocation>();
		return retval;
	}
}
