package program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
/**
 * 
 * @author Ashley Cottrell
 *
 */
public class Stock {
	
	public static HashMap<String, Item> inventory = new HashMap<String, Item>();
	public HashMap<String, Integer> itemList = new HashMap<String, Integer>();

	public Stock() {
	}

	/**
	 * Create items from a list and add them to the inventory
	 * 
	 * @param inventorylist
	 *            List of Items imported from the IOCSV
	 */
	public void creatInventory(ArrayList<List> inventorylist) {
		for (List item : inventorylist) {
			// Extract Item infomation from array
			String name = (String) item.get(0);
			double ManufactureCost = Double.parseDouble((String) item.get(1));
			double SellPrice = Double.parseDouble((String) item.get(2));
			int ReorderPoint = Integer.parseInt((String) item.get(3));
			int ReorderAmount = Integer.parseInt((String) item.get(4));

			// Check if temperature is required and create an item.
			if (item.size() == 6) {
				int Tempreture = Integer.parseInt((String) item.get(5));
				// Create item with temperature
				Item product = new Item(name, ManufactureCost, SellPrice, ReorderPoint, ReorderAmount, Tempreture);
				inventory.put(name, product);
			} else {
				// Create item without temperature
				Item product = new Item(name, ManufactureCost, SellPrice, ReorderPoint, ReorderAmount);
				inventory.put(name, product);
			}

		}
	}

	/**
	 * Return a requested Item
	 * 
	 * @param name
	 *            The Name of the Item
	 * @return Item
	 */
	public Item getItem(String name) {
		return inventory.get(name);
	}

	/**
	 * Return a copy of the inventory
	 * 
	 * @return HashMap<String, Item>
	 */
	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	/**
	 * Add an item to a stock
	 * 
	 * @param itemName
	 * @param quantity
	 */
	public void addItem(String itemName, int quantity) {
		itemList.put(itemName, quantity);
	}

	/**
	 * Return Item list
	 * 
	 * @return HashMap<String, Integer>
	 */
	public HashMap<String, Integer> getTruckItem() {
		return itemList;
	}

	/**
	 * Check if item exists within the inventory
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean itemExists(String name) {
		return inventory.containsKey(name);
	}
}
