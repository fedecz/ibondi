package com.uade.pfi.core.service;


public interface CrudService<T> {
	
	void add(T t);
	
	void update(T t);
	
	void remove(T t);
	
	T get(T t);
	
}
