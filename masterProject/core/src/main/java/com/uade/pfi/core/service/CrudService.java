package com.uade.pfi.core.service;


public interface CrudService<T> {
	
	T add(T t);
	
	T update(T t);
	
	void remove(T t);
	
	T get(T t);
	
}
