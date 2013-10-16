package org.ibondi.core.service;


public interface CrudService<T> {
	
	T add(T t);
	
	T update(T t);
	
	void remove(T t);
	
	T get(T t);
	
}
