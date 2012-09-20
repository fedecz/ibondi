package com.uade.pfi.core.dao.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
	T findOne(ID id);
	T save(T entity);
}
