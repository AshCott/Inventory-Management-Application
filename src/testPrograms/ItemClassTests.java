package testPrograms;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import program.Item;

/**
 * Item Test class
 * @author Ashley Cottrell
 *
 */
public class ItemClassTests {
	Item item;
	
	/**
	 * Constuct sample Item
	 */
	@Before
	public void constructor() {
		 item = new Item("Apple", 3, 5, 100, 300, 5);
	}
	
	/**
	 * Test getting item name
	 */
	@Test
	public void getName() {
		assertEquals("name is incorrect", "Apple",item.getName());
	}
	
	/**
	 * Test getting item Manufacture Cost
	 */
	@Test
	public void getManufactureCost() {
		assertEquals("Manufacture Cost is Incorrect",3,item.getManufactureCost(),0);
	}
	
	/**
	 * Test getting item sell price
	 */
	@Test
	public void getSellPrice() {
		assertEquals("Sell Price is Incorrect",5,item.getSellPrice(),0);
	}
	
	/**
	 * Test getting item Re-order Point
	 */
	@Test
	public void getReorderPoint() {
		assertEquals("Re-Order Point is Incorrect",100,item.getReorderPoint());
	}
	
	/**
	 * Test getting item Re-order Amount
	 */
	@Test
	public void getReorderAmount() {
		assertEquals("Re-Order Amount is Incorrect",300,item.getReorderAmount());
	}
	
	/**
	 * Test getting item Tempreture
	 */
	@Test
	public void getTempreture() {
		assertEquals("Tempreture is Incorrect",5,item.getTemperature(),0);
	}
	
	/**
	 * Test checking if item has a tempreture
	 */
	@Test
	public void hasTempreture() {
		assertEquals("hasTempreture is Incorrect",true,item.hasTempreture());
	}
}
