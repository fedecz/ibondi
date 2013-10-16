package org.ibondi.core.mapper;

import java.util.List;

public interface ListConverter<T1, T2> {
	
	List<T2> convert(List<T1> list);

}
