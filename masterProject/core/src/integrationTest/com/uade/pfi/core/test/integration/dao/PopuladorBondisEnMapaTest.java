/**
 * 
 */
package com.uade.pfi.core.test.integration.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.api.enums.HeadingEnum;
import com.uade.pfi.api.enums.TransportTypeEnum;
import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.repositories.SessionRepository;
import com.uade.pfi.core.repositories.TransportRepository;

/**
 * @author fedec
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:core-context.xml")
public class PopuladorBondisEnMapaTest {

	@Autowired
	TransportRepository transportRepo;
	
	@Autowired
	SessionRepository sessionRepo;

	@Before
	public void borrarTodo(){
		transportRepo.deleteAll();
		sessionRepo.deleteAll();
	}
	
	@Test
	public void popularTransportesYSessiones(){
		Transport t1 = new Transport("1","A", "SR", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport t2 = new Transport("2","B", "UCA", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport t3 = new Transport("3","C", "SR", HeadingEnum.IDA, TransportTypeEnum.BUS);
		
		transportRepo.save(Arrays.asList(t1, t2, t3));

		/**
		 * 	a1 -34.499855,-58.501993
			a2 -34.499846,-58.501969
			a3 -34.506840,-58.492922
			a4 -34.498521,-58.497191
			a5 -34.497337,-58.497712
			
			b1 -34.499843,-58.501957
			b2 -34.506747,-58.492970
			b3 -34.506778,-58.492938
			b4 -34.504532,-58.491404
			b5 -34.504497,-58.491297
			b6 -34.498510,-58.497198
			
			c1 -34.504638,-58.491619
			c2 -34.506703,-58.492987
			c3 -34.497330,-58.497714
			c4 -34.498513,-58.497196
		 */
		TransportSession A1 = new TransportSession("1",new Location("-34.499855,-58.501993"),null,new Date());
		TransportSession A2 = new TransportSession("1",new Location("-34.499846,-58.501969"),null,new Date());
		TransportSession A3 = new TransportSession("1",new Location("-34.506840,-58.492922"),null,new Date());
		TransportSession A4 = new TransportSession("1",new Location("-34.498521,-58.497191"),null,new Date());
		TransportSession A5 = new TransportSession("1",new Location("-34.497337,-58.497712"),null,new Date());
		
		TransportSession B1 = new TransportSession("2",new Location("-34.499843,-58.501957"),null,new Date());
		TransportSession B2 = new TransportSession("2",new Location("-34.506747,-58.492970"),null,new Date());
		TransportSession B3 = new TransportSession("2",new Location("-34.506778,-58.492938"),null,new Date());
		TransportSession B4 = new TransportSession("2",new Location("-34.504532,-58.491404"),null,new Date());
		TransportSession B5 = new TransportSession("2",new Location("-34.504497,-58.491297"),null,new Date());
		TransportSession B6 = new TransportSession("2",new Location("-34.498510,-58.497198"),null,new Date());
		
		TransportSession C1 = new TransportSession("3",new Location("-34.504638,-58.491619"),null,new Date());
		TransportSession C2 = new TransportSession("3",new Location("-34.506703,-58.492987"),null,new Date());
		TransportSession C3 = new TransportSession("3",new Location("-34.497330,-58.497714"),null,new Date());
		TransportSession C4 = new TransportSession("3",new Location("-34.498513,-58.497196"),null,new Date());
		
		List<TransportSession> a = Arrays.asList(A1, A2,A3, A4, A5,B1, B2, B3, B4, B5,B6,C1, C2, C3,C4);
		sessionRepo.save(a);
	}
	
}
