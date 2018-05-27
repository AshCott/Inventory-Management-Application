package testPrograms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import program.IOCSV;
import program.Manifest;
import program.Store;
import program.Item;

/**
 * Item Test class
 * 
 * @author Ashley Cottrell
 *
 */
public class ItemClassTests {
	IOCSV importer;
	Store superMart;
	Manifest manifest;
	Item item;

	/**
	 * Construct sample Item
	 */
	@Before
	public void constructor() {
		importer = new IOCSV();
		superMart = Store.getInstance();
		manifest = new Manifest();
		item = new Item("Apple", 3, 5, 100, 300, 5);
	}

	/**
	 * Test getting item name
	 */
	@Test
	public void getName() {
		assertEquals("name is incorrect", "Apple", item.getName());
	}

	/**
	 * Test getting item Manufacture Cost
	 */
	@Test
	public void getManufactureCost() {
		assertEquals("Manufacture Cost is Incorrect", 3, item.getManufactureCost(), 0);
	}

	/**
	 * Test getting item sell price
	 */
	@Test
	public void getSellPrice() {
		assertEquals("Sell Price is Incorrect", 5, item.getSellPrice(), 0);
	}

	/**
	 * Test getting item Re-order Point
	 */
	@Test
	public void getReorderPoint() {
		assertEquals("Re-Order Point is Incorrect", 100, item.getReorderPoint());
	}

	/**
	 * Test getting item Re-order Amount
	 */
	@Test
	public void getReorderAmount() {
		assertEquals("Re-Order Amount is Incorrect", 300, item.getReorderAmount());
	}

	/**
	 * Test getting item Temperature
	 */
	@Test
	public void getTempreture() {
		assertEquals("Temperature is Incorrect", 5, item.getTemperature(), 0);
	}

	/**
	 * Test checking if item has a Temperature
	 */
	@Test
	public void hasTempreture() {
		assertEquals("hasTempreture is Incorrect", true, item.hasTempreture());
	}

}
