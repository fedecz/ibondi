package com.uade.pfi.core.repositories.mapreduce;

public class MapReduceSessionOutput{
	String id;
	MapReduceValue value;
	public void setValue(MapReduceValue value) {
		this.value = value;
	}
	public void setId(String id) {
		this.id = id;
	}
			
}
