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
 * @author
 * @author
 *
 */
public class Store {
	private String name;
	double capital;
	ArrayList<List> inventorylist;
	private static Store firstInstance = new Store();

	Stock inventory = new Stock();

	private Store() {
		this.name = "SuperMart";
		this.capital = 100000;
	}

	public static Store getInstance() {
		return firstInstance;
	}

	/**
	 * 
	 * @return The formatted store capital as currency i.e. $1,000,000
	 */
	public String getStoreCapital() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		return "$" + formatter.format(capital);
	}

	/**
	 * @return The Store Name as String
	 */
	public String getStoreName() {
		return this.name;
	}

	/**
	 * Import and process the inventory csv files
	 * 
	 * @param link
	 *            to the csv file
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
	 *            to the csv file
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
	 * 
	 * The import manifest is a method that will import a csv manifest file that
	 * were made either by exporting manifest or by other method, the way the
	 * program work is that first it read the csv file then check the manifest if
	 * the item inside the manifest do exist in inventory then calculate the capital based on the
	 * amount of quantity of item,manufacture cost, truck and temperature
	 * 
	 * @param file
	 *            The file name of the manifest that going to be imported
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	public void importManifest(String file) throws IOException, CSVFormatException, DeliveryException {
		IOCSV importer = new IOCSV();
		String itemName;
		ArrayList<List> manifestFile = null;
		// see if the manifest file is indeed in correct format if not throw exception
		try {
			manifestFile = importer.readCSVFile(file, "manifest");
		} catch (CSVFormatException e) {
			throw e;
		}
		Boolean isTruckType;
		Boolean isRefrigerated = false;
		Boolean isOrdinary = false;
		double totalProductBoughtOrdinary = 0.0;
		double totalProductBoughtRefrigrated = 0.0;
		double totalPriceInOrdinary = 0.0;
		double totalPriceInRefrigrated = 0.0;
		RefrigratedTruck refTruck = null;
		OrdinaryTruck ornTruck = null;
		Manifest manifest = new Manifest();
		double coldest = Double.MAX_VALUE;

		// check the manifest file if there is an item that doesn't exist in the
		// inventory first if yes throw a delivery exception
		for (List line : manifestFile) {
			String content = (String) line.get(0);
			isTruckType = content.charAt(0) == '>';
			if (isTruckType) {

			} else {
				itemName = (String) content;
				if (!inventory.itemExists(itemName)) {
					throw new DeliveryException("Item doesnt exist in inventory \n" + itemName);
				}
			}
		}

		// loop the manifestfile line by line
		for (List line : manifestFile) {
			String content = (String) line.get(0);
			// check if the line start with '>' if it does then it's the line for the truck
			// type
			isTruckType = content.charAt(0) == '>';

			if (isTruckType) {
				// check if the truck type is refrigerated or ordinary
				if (content.equals(">Refrigerated")) {
					// if refrigerated make a new refrigerated and add it to the truck
					refTruck = new RefrigratedTruck();
					manifest.saveRefTruck(refTruck);
					coldest = Double.MAX_VALUE;
					isRefrigerated = true;
					isOrdinary = false;
					totalPriceInRefrigrated = 0.0;
				} else if (content.equals(">Ordinary")) {
					// else if ordinary then make a new ordinary and add it to the truck
					ornTruck = new OrdinaryTruck();
					manifest.saveOrnTruck(ornTruck);
					isOrdinary = true;
					isRefrigerated = false;
					totalPriceInOrdinary = 0.0;
					totalProductBoughtOrdinary = 0.0;
				}
			} else {
				// if content is not about the truck type
				Integer numbBought = Integer.parseInt((String) line.get(1));
				// check if it's ordinary truck
				if (isOrdinary) {
					// if yes made a new one and increase the quantity of the truck
					ornTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					totalProductBoughtOrdinary += numbBought;
					ornTruck.setQuantity(totalProductBoughtOrdinary);
					// check if item does exist in the item properties
					if (temp != null) {
						// update the current inventory
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore += numbBought;
						temp.setCurrentInventory(currentInventoryInStore);
						// get the total prices of the product in the turck
						totalPriceInOrdinary += temp.getManufactureCost() * numbBought;
						ornTruck.setTotalPriceInTruck(totalPriceInOrdinary);
					} else {
						// so item is null throw exception
						throw new DeliveryException("Cannot deliver an item that is not in item properties");
					}
				} else {
					refTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					// set the temperature of the to be as cold as the coldest item
					if (temp.hasTempreture()) {
						if (coldest > temp.getTemperature()) {
							coldest = temp.getTemperature();
							refTruck.setTemperature(coldest);
						}
					}

					// check if item does exist in the inventory
					if (temp != null) {
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore += numbBought;
						// update the current inventory
						temp.setCurrentInventory(currentInventoryInStore);
						// get the total prices of the product in the turck
						totalPriceInRefrigrated += temp.getManufactureCost() * numbBought;
						refTruck.setTotalPriceInTruck(totalPriceInRefrigrated);
					}
				}
			}

		} // end loop
		for (OrdinaryTruck each : manifest.ornTruck) {
			// calculate each ordinary truck
			capital -= each.costCalculation(each.getQuantity());
			// calculate each item in ordinary truck
			capital -= each.getTotalPriceInTruck();
		}
		for (RefrigratedTruck each : manifest.refTruck) {
			// calculate each refrigerated truck
			capital -= each.costCalculation(each.getTemperature());
			// calculating each item
			capital -= each.getTotalPriceInTruck();
		}

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
