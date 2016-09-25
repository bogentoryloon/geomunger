package ie.jtc.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ie.jtc.model.Location;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGeoService {

	@Autowired
	GeoService geoService;
	
	private double TOLERANCE=0.0001; // c. 10m
	@Test
	public void test(){
		assertThat(geoService,instanceOf(GeoService.class));
	}
	@Test
	public void testGetGeoCode() throws Exception {
		Location dr = new Location("1 MOUNT PLEASANT, SANDYCOVE ROAD,SANDYCOVE");
		geoService.getGeoCode(dr);
		assertEquals( 53.286296,dr.getLongitudeAndLatitude().getLatitude(),TOLERANCE);
		assertEquals( -6.1194238,dr.getLongitudeAndLatitude().getLongitude(),TOLERANCE);		
	}

	@Test
	public void testMyHouse() throws Exception {
		Location myHouse = new Location("42 Melrose Ave.,Fairview,Dublin 3");
		geoService.getGeoCode(myHouse);
		assertEquals( 53.36511119999999,myHouse.getLongitudeAndLatitude().getLatitude(),TOLERANCE);
		assertEquals( -6.2404521,myHouse.getLongitudeAndLatitude().getLongitude(),TOLERANCE);		
	}

}
