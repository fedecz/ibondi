package com.uade.pfi.core.dao;

import com.uade.pfi.core.beans.Transport;

public interface CrudDao {

	Transport insertTransport(Transport aNewTransport);

	void updateTransport(Transport aNewTransport);

	void removeTransport(Transport transportToBeRemoved);

	Transport getByExample(Transport transportExample);

}
