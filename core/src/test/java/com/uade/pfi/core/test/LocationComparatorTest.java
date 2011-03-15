/**
 * 
 */
package com.uade.pfi.core.test;

import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.uade.pfi.core.beans.Location;

/**
 * @author chiwi
 *
 */
public class LocationComparatorTest {
	
	
	@Test
	public void a(){
		Location l1 = new Location();
		l1.setTrackedOn(new Date());
		Location l2 = new Location();
		l2.setTrackedOn(new Date());
		Location l3 = new Location();
		l3.setTrackedOn(new Date());
		Location l4 = new Location();
		l4.setTrackedOn(null);
		
		
		SortedSet<Location> locations = new TreeSet<Location>();
		locations.add(l1);
		locations.add(l2);
		locations.add(l3);
		
		Location first = locations.first();
		assertEquals(l3, first);
	}
	
	
	@Test
	public void b(){
		Location l1 = new Location();
		l1.setTrackedOn(new Date(3));
		Location l2 = new Location();
		l2.setTrackedOn(new Date(1));
		Location l3 = new Location();
		l3.setTrackedOn(new Date(4));
		Location l4 = new Location();
		l4.setTrackedOn(null);
		
		
		SortedSet<Location> locations = new TreeSet<Location>();
		locations.add(l1);
		locations.add(l2);
		locations.add(l3);
		
		Location first = locations.first();
		assertEquals(l3, first);
	}

}
