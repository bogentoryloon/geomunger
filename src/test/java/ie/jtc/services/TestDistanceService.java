package ie.jtc.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ie.jtc.model.LatitudeAndLongitude;
import ie.jtc.model.Location;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDistanceService {
	static Logger log = Logger.getLogger(TestDistanceService.class);
	@Autowired
	DistanceService distanceService;
	private double TOLERANCE=100; 
	@Test
	public void test() {
		
		assertThat(distanceService,instanceOf(DistanceService.class));

		LatitudeAndLongitude doc=new LatitudeAndLongitude(53.286296,-6.1194238);
		LatitudeAndLongitude myhouse=new LatitudeAndLongitude(53.36511119999999,-6.2404521 );
		long start = System.currentTimeMillis();
		double metres = distanceService.drivingDistanceMeters(myhouse,doc);
		long duration  = System.currentTimeMillis() - start;
		log.info("distance query took "+duration);
		assertEquals(14972,metres,TOLERANCE);
	}

}
