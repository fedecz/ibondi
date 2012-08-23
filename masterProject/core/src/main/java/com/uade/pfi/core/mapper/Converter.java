package com.uade.pfi.core.mapper;

public interface Converter<T1,T2> {
	
	T2 convert(T1 value);

}
