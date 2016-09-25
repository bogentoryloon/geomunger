package ie.jtc.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;

import ie.jtc.model.GPLocation;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFileReader {
	@Autowired
	FileAccessor fileReader;
	@Test
	public void testReadPCRSFile() throws JsonParseException, IOException {
		List<GPLocation> gps = fileReader.readPCRSFile("UGP_GPlist_by_LHO.json");
		assertEquals(2749,gps.size() );
	}

}
