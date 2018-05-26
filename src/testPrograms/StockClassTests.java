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
 * 
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
	 * Test for getting an item inventory
	 */
	@Test
	public void getInventoryItem() {
		assertEquals("apple", stock.getItem("apple").getName());
	}

	/**
	 * Test adding an item to stock
	 */
	@Test
	public void AddItem() {
		stock.addItem("apple", 5);
	}

	/**
	 * Test Getting an Item from the stock class
	 */
	@Test
	public void getStockItem() {
		assertEquals("apple", stock.getItem("apple").getName());
	}

	/**
	 * Test Getting the stocks contents
	 */
	@Test
	public void getTruckItem() {
		stock.addItem("apple", 500);
		assertEquals("item is incorrect", 500, stock.itemList.get("apple"), 0);
	}

}
