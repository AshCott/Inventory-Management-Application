package program;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Store is the class that represent the SuperMart store where every method that
 * related to changing inventory, capital of the store is in here
 * 
 * @author Ashley Cottrell
 * @author Radhimas Djan
 *
 */
public class Store {
	private String name;
	double capital;

	ArrayList<List> inventorylist;
	private static Store firstInstance = new Store();

	Stock inventory = new Stock();

	/**
	 * Construct a store
	 */
	private Store() {
		this.name = "SuperMart";
		this.capital = 100000;
	}

	/**
	 * Return the first instance of the store for use in singlton pattern
	 * 
	 * @return Return the frist instance of the store
	 */
	public static Store getInstance() {
		return firstInstance;
	}

	/**
	 * Get the Store Capital
	 * 
	 * @return The formatted store capital as currency i.e. $1,000,000
	 */
	public String getStoreCapital() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		return "$" + formatter.format(capital);
	}

	/**
	 * Get the store name
	 * 
	 * @return The Store Name as String
	 */
	public String getStoreName() {
		return this.name;
	}

	/**
	 * Import and process the inventory csv files
	 * 
	 * @param link
	 *            file location of the csv file
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	public void creatInventory(String file) throws IOException, CSVFormatException {
		IOCSV importer = new IOCSV();
		try {
			inventorylist = importer.readCSVFile(file, "inventory");
		} catch (CSVFormatException e) {
			throw e;
		}
		inventory.creatInventory(inventorylist);
		System.out.println("Import Inventory: Success");

	}

	/**
	 * Import and process the sales Log CSV files
	 * 
	 * @param link
	 *            file location of the csv file
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 */
	public void importSalesLog(String file) throws IOException, CSVFormatException, StockException {
		IOCSV importer = new IOCSV();
		double totalSales = 0.0;
		ArrayList<List> salesLog = null;

		// Import Sales Log
		try {
			salesLog = importer.readCSVFile(file, "sales_log");
		} catch (CSVFormatException e) {
			throw e;
		}

		// Check that item exists in inventory
		for (List item : salesLog) {
			if (inventory.itemExists((String) item.get(0)) == false) {
				throw new StockException("Item doesnt exist in inventory \n" + item);
			}

			// Checks if inventory stock has the amount that can be sold
			String itemName = (String) item.get(0);
			Item temp = inventory.getItem(itemName);
			int numbSold = Integer.parseInt((String) item.get(1));
			int currentInventory = temp.getCurrentInventory();
			currentInventory -= numbSold;
			if (currentInventory < 0) {
				throw new StockException("Cannot sell more items than are in the inventory \n" + item);
			}
		}

		// Loop though the sales log
		for (List i : salesLog) {

			// Get Variables
			String itemName = (String) i.get(0);
			int numbSold = Integer.parseInt((String) i.get(1));
			Item temp = inventory.getItem(itemName);

			// Calculate the total sales revenue
			totalSales += temp.getSellPrice() * numbSold;

			// Change inventory ammount
			int currentInventory = temp.getCurrentInventory();
			currentInventory -= numbSold;
			temp.setCurrentInventory(currentInventory);
		}
		// Increase the capital
		capital += totalSales;
		System.out.println("Import Sales Log: Success");
	}

	/**
	 * importing the manifest based on importing manifest method on the manifest
	 * classes
	 * 
	 * @param file
	 *            The file name of the manifest that going to be imported
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	public void importManifest(String file) throws IOException, CSVFormatException, DeliveryException {
		Manifest manifest = new Manifest();
		manifest.importingManifest(file);
		System.out.println("Import Manifest: Success");
	}

	/**
	 * return all the items in the inventory
	 * 
	 * @return HashMap of all items in inventory
	 */
	public HashMap<String, Item> getInventory() {
		HashMap<String, Item> temp = inventory.getInventory();
		return temp;
	}

	/**
	 * set capital to the desired number
	 * 
	 * @param capital
	 */
	public void setCapital(double capital) {
		this.capital = capital;
	}

	/**
	 * Method to reset the store capital and inventory to the start 100000 for
	 * capital and 0 for inventory
	 */
	public void reset() {
		setCapital(100000);
		for (String key : inventory.getInventory().keySet()) {
			inventory.getInventory().get(key).setCurrentInventory(0);
		}
	}

	/**
	 * exporting the manifest based on generate manifest method on the manifest
	 * classes
	 * 
	 * @throws IOException
	 * @throws DeliveryException
	 */
	public void exportingManifest() throws IOException, DeliveryException {
		Manifest manifest = new Manifest();
		manifest.generateManifest();
	}
}
