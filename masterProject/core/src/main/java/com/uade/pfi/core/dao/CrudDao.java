package com.uade.pfi.core.dao;

import com.uade.pfi.core.dto.TransportDTO;

public interface CrudDao {

	void insertTransport(TransportDTO aNewTransport);

	void updateTransport(TransportDTO aNewTransport);

	void removeTransport(TransportDTO transportToBeRemoved);

	TransportDTO getByExample(TransportDTO transportExample);

}
