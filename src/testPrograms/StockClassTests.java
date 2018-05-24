package testPrograms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import program.Stock;
@SuppressWarnings("rawtypes")

/**
 * Tests for stock class
 * @author Ashley Cottrell
 *
 */
public class StockClassTests {
	
	ArrayList<List> inventory;
	Stock stock;
	
	/**
	 * Setup an array for importing into stock
	 */
	@Before
	public void constructor() {
		stock = new Stock();
		inventory = new ArrayList<List>();
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add("apple");
		temp.add("2");
		temp.add("3");
		temp.add("225");
		temp.add("300");
		temp.add("5");
		inventory.add(temp);
	}
	
	/**
	 * Create the inventory using an array
	 */
	@Test
	public void creatInventory() {
		stock.creatInventory(inventory);
	}

	/**
	 * Test for checking an item exist
	 */
	@Test
	public void itemExist() {
		assertEquals(true, stock.itemExists("apple"));
	}
	
	/**
	 * Test for checking if an item exists in the inventory
	 */
	@Test
	public void getItem() {
		assertEquals("apple", stock.getItem("apple").getName());
	}
	
}
