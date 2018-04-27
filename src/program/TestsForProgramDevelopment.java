package program;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestsForProgramDevelopment {

	@Test
	public void GetStoreCapital() {
		Store a = new Store();
		System.out.println(a.getStoreCapital());
		assertEquals("$1,000,000.00", a.getStoreCapital());
	}

}
