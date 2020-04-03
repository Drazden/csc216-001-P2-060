package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PropertyManagerTest {

	@Test
	void test() {
		PropertyManager manager = new PropertyManager();
		assertEquals(null, manager.listRentalUnits());
	}

}
