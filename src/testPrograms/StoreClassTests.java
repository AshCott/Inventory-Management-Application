package testPrograms;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import program.Store;

public class StoreClassTests {
	Store superMart;
	
	@Before
	public void constructor() {
		superMart = new Store();
	}
	
	@Test
	public void GetStoreCapital() {
		assertEquals("$100,000.00", superMart.getStoreCapital());
	}
	
	@Test
	public void GetStoreName() {
		assertEquals("SuperMart", superMart.getStoreName());
	}

}
