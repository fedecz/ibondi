package com.uade.pfi.core.test.integration.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.enums.HeadingEnum;
import com.uade.pfi.core.enums.TransportTypeEnum;
import com.uade.pfi.core.service.TransportCrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:core-context.xml")
/**
 * Verify Transport Crud Service Integration with mongoDB  
 *
 */
public class TransportCrudServiceTest {

	@Autowired
	private TransportCrudService crudService;
	
	@Before
	public void cleanMongoBeforeStart() {
		crudService.getRepo().deleteAll();
	}
	
	@Test
	public void verifyAddingATransport(){
		Transport transport = new Transport("152", "SemiRapido", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport result = crudService.add(transport);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(transport, result);
	}
	
	@Test
	public void verifyRetrievingATransport(){
		Transport transport = new Transport("152", "SemiRapido", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport result = crudService.add(transport);
		
		
		Transport retrievedTransport = crudService.get(result);
		
		Assert.assertNotNull(retrievedTransport);
		Assert.assertEquals(result.getId(), retrievedTransport.getId());
	}
	
	@Test
	public void verifyRemovingATransport(){
		Transport transport = new Transport("152", "SemiRapido", HeadingEnum.IDA, TransportTypeEnum.BUS);
		Transport result = crudService.add(transport);
		
		
		Transport retrievedTransport = crudService.get(result);
		
		Assert.assertNotNull(retrievedTransport);
		Assert.assertEquals(result.getId(), retrievedTransport.getId());
		
		crudService.remove(retrievedTransport);
		
		Transport retrievedAfterRemoving = crudService.get(retrievedTransport);
		Assert.assertNull(retrievedAfterRemoving);
	}
}
