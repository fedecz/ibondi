package com.uade.pfi.core.test.integration.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.api.enums.HeadingEnum;
import com.uade.pfi.api.enums.TransportTypeEnum;
import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.repositories.TransportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:core-context.xml")
public class TransportRepositoryTest {

	@Autowired
	private TransportRepository repository;
	
	@Before
	public void clean(){
		repository.deleteAll();
	}

	@Test
	public void verifyThatRepositoryIsInsertedCorrectly() {
		Transport insertedTransport = repository.save(new Transport("152", "SEMIRAPIDO", HeadingEnum.IDA, TransportTypeEnum.BUS));

		Assert.assertNotNull(insertedTransport);
		Assert.assertNotNull(insertedTransport.getId());
		
		Transport transport = repository.findOne(insertedTransport.getId());
		Assert.assertNotNull(transport);
	}
	
	@Test
	public void verifyThatATransportIsSavedCorrectly(){
		Transport sesenta = repository.save(new Transport("60", "escobar", HeadingEnum.IDA, TransportTypeEnum.BUS));
		Transport nuevoSesenta = new Transport(sesenta.getId(),sesenta.getName(), "TIGRE" , sesenta.getHeading(), sesenta.getTransportType());
		
		repository.save(nuevoSesenta);
		Transport sesentaDespuesDeCambiado = repository.findOne(nuevoSesenta.getId());
		
		Assert.assertEquals("TIGRE", sesentaDespuesDeCambiado.getBranch());
		Assert.assertEquals(1, repository.count());
	}

}
