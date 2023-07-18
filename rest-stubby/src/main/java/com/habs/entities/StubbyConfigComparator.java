package com.habs.entities;
import java.util.Comparator;

public class StubbyConfigComparator implements Comparator<StubbyConfig>{

	@Override
	public int compare(StubbyConfig obj1, StubbyConfig obj2) {
        // Return a negative value if obj1 should be before obj2
        // Return a positive value if obj1 should be after obj2
        // Return 0 if obj1 and obj2 are considered equal in terms of sorting		
		return obj1.getPriority() - obj2.getPriority();
    }

}
