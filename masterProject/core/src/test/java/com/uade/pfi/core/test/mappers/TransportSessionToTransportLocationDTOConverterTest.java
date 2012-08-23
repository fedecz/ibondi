package com.uade.pfi.core.test.mappers;

import static junit.framework.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.uade.pfi.core.beans.Location;
import com.uade.pfi.core.beans.TransportSession;
import com.uade.pfi.core.dto.TransportLocationDTO;
import com.uade.pfi.core.mapper.TransportSessionToTransportLocationDTOConverter;

public class TransportSessionToTransportLocationDTOConverterTest {
	
	@Test
	public void elConverterDebeConvertirADTOYNoDebeSerNulo(){
		TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();
		TransportLocationDTO locationDTO = converter.convert(new TransportSession());
		assertNotNull(locationDTO);
	}
	
	@Test
	public void elConverterDebePopularElIdDeSession(){
		TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();
		TransportSession value = new TransportSession();
		value.setId("123");
		TransportLocationDTO locationDTO = converter.convert(value);
		assertEquals("123", locationDTO.getSessionId());
	}
	
	@Test
	public void elConverterDebePopularElName(){
		TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();
		TransportSession value = new TransportSession();
		value.setName("name");
		TransportLocationDTO locationDTO = converter.convert(value);
		assertEquals("name", locationDTO.getName());
	}
	
	@Test
	public void elConverterDebePopularElLastKnownLocation(){
		TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();
		TransportSession value = new TransportSession();
		value.setLastKnownLocation(new Location(123,456));
		TransportLocationDTO locationDTO = converter.convert(value);
		assertTrue(locationDTO.getLatitude().equals(Float.valueOf(123)));
		assertTrue(locationDTO.getLongitude().equals(Float.valueOf(456)));
	}
	
	@Test
	public void elConverterDebeConvertirUnaLista(){
		TransportSessionToTransportLocationDTOConverter converter = new TransportSessionToTransportLocationDTOConverter();
		TransportSession value1 = new TransportSession();
		value1.setName("uno");
		TransportSession value2 = new TransportSession();
		value2.setName("dos");
		List<TransportSession> sessions = Arrays.asList(value1, value2);
		List<TransportLocationDTO> list = converter.convert(sessions);
		assertEquals("uno", list.get(0).getName());
		assertEquals("dos", list.get(1).getName());
	}

}
