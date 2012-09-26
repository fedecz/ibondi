/**
 * 
 */
package com.uade.pfi.core.test.integration.dao;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dao.repositories.SessionRepository;
import com.uade.pfi.core.dao.repositories.TransportRespository;
import com.uade.pfi.core.enums.HeadingEnum;
import com.uade.pfi.core.enums.TransportTypeEnum;

/**
 * @author chiwi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:core-context.xml")
public class PopuladorTest {
	
	@Autowired
	TransportRespository transportRepo;
	
	@Autowired
	SessionRepository sessionRepo;

	@Before
	public void borrarTodo(){
		transportRepo.deleteAll();
		sessionRepo.deleteAll();
	}
	
	@Test
	public void popularTransportesYSessiones(){
		Transport t1 = new Transport("1","152", "SR", HeadingEnum.IDA, TransportTypeEnum.BUS);
//		Transport t5 = new Transport("5","152", "SR", HeadingEnum.VUELTA, TransportTypeEnum.BUS);
		Transport t2 = new Transport("2","152", "UCA", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport t3 = new Transport("3","59", "SR", HeadingEnum.IDA, TransportTypeEnum.BUS);
//		Transport t4 = new Transport("4","314", "Rojo", HeadingEnum.VUELTA, TransportTypeEnum.BUS);
		
		transportRepo.save(Arrays.asList(t1, t2, t3));

		TransportSession A1 = new TransportSession("1",new Location(10f,5f),null,new Date());
		TransportSession A2 = new TransportSession("1",new Location(80f,150f),null,new Date());
		TransportSession A3 = new TransportSession("1",new Location(80f,150f),null,new Date());
		TransportSession A4 = new TransportSession("1",new Location(80f,150f),null,new Date());
		TransportSession A5 = new TransportSession("1",new Location(80f,150f),null,new Date());
		TransportSession A6 = new TransportSession("1",new Location(150f,50f),null,new Date());
		
		TransportSession B1 = new TransportSession("2",new Location(10f,5f),null,new Date());
		TransportSession B2 = new TransportSession("2",new Location(80f,150f),null,new Date());
		TransportSession B3 = new TransportSession("2",new Location(150f,150f),null,new Date());
		TransportSession B4 = new TransportSession("2",new Location(180f,5f),null,new Date());
		
		TransportSession C1 = new TransportSession("3",new Location(10f,180f),null,new Date());
		TransportSession C2 = new TransportSession("3",new Location(90f,5f),null,new Date());
		TransportSession C3 = new TransportSession("3",new Location(150f,50f),null,new Date());
		
		sessionRepo.save(Arrays.asList(A1, A2,A3, A4, A5,A6,B1, B2, B3, B4, C1, C2, C3));
	}
	
	
	
}
