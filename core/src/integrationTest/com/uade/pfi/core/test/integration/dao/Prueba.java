package com.uade.pfi.core.test.integration.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.repositories.SessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:core-context.xml")
public class Prueba {
	@Autowired
	SessionRepository sessionRepo;	
	
	@Test
	public void test(){
		String id = "5085a57f44ae5e1dcfca2cef";
		TransportSession session = sessionRepo.findOne(id);
		System.out.println(session);
	}

}
