package com.uade.pfi.core.test.integration.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.core.beans.Transport;
import com.uade.pfi.core.dao.MongoDBCrudDao;
import com.uade.pfi.core.enums.TransportTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:core-context.xml",
		"classpath:core-dao-mongo.xml",
		"classpath:core-ds-mongo.xml",
		"classpath:core-service.xml"
})
public class CrudDaoTest {

	@Autowired
	private MongoDBCrudDao dao;

	@Test
	public void verifyThatDaoIsWorking() {
		Transport insertedTransport = dao.insertTransport(new Transport("152", "SEMIRAPIDO", "IDA", TransportTypeEnum.BUS));

		Assert.assertNotNull(insertedTransport);
		Assert.assertNotNull(insertedTransport.getId());
	}

}
