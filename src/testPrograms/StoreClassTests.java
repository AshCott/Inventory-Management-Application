package testPrograms;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import program.Store;

public class StoreClassTests {
	Store a;
	
	@Before
	public void constructor() {
		a = new Store();
		a.setCapital(1000000.00);
		a.setName("Woolworths");
	}
	
	@Test
	public void GetStoreCapital() {
		assertEquals("$1,000,000.00", a.getStoreCapital());
	}
	
	@Test
	public void GetStoreName() {
		assertEquals("Woolworths", a.getStoreName());
	}

}
