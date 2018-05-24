package program;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
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
	 * @param link to the csv file
	 * @throws IOException
	 * @throws CSVFormatException 
	 */
	public void creatInventory(String file) throws IOException, CSVFormatException {
		IOCSV importer = new IOCSV();
		try {
			inventorylist = importer.readCSVFile(file, "inventory");
		} catch (CSVFormatException e) {
			// TODO Auto-generated catch block
			// System.out.println(e);
			throw e;
		}
		System.out.println("test");
		inventory.creatInventory(inventorylist);
		System.out.println("Import Inventory: Success");

	}
	
	/**
	 * Import and process the sales Log CSV files
	 * @param link to the csv file
	 * @throws IOException
	 * @throws CSVFormatException 
	 * @throws StockException 
	 */
	public void importSalesLog(String file) throws IOException, CSVFormatException, StockException {
		IOCSV importer = new IOCSV();
		double totalSales = 0.0;
		ArrayList<List> salesLog = null;
		
		//Import Sales Log
		try {
			salesLog = importer.readCSVFile(file, "sales_log");
		} catch (CSVFormatException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		//Check that item exists in inventory
		for(List item : salesLog) {
			if (inventory.itemExists((String )item.get(0)) == false) {
				throw new StockException("Item doesnt exist in inventory \n" + item);
			}
			
			//Checks if inventory stock has the amount that can be sold
			String itemName = (String) item.get(0);
			Item temp = inventory.getItem(itemName);
			int numbSold = Integer.parseInt((String) item.get(1));
			int currentInventory = temp.getCurrentInventory();
			currentInventory -= numbSold;
			if (currentInventory < 0) {
				throw new StockException("Cannot sell more items than are in the inventory \n" + item);
			}
		}
		
		//Loop though the sales log
		for(List i : salesLog) {
			
			//Get Variables
			String itemName = (String) i.get(0);
//			System.out.println(itemName);
			int numbSold = Integer.parseInt((String) i.get(1));
			Item temp = inventory.getItem(itemName);
			
			//Calculate the total sales revenue
			totalSales += temp.getSellPrice() * numbSold;
			
			//Change inventory ammount
			int currentInventory = temp.getCurrentInventory();
			currentInventory -= numbSold;
			temp.setCurrentInventory(currentInventory);
		}
		//Increase the capital
		capital += totalSales;
		System.out.println("Import Sales Log: Success");
	}
	
	
	public void importManifest(String file) throws IOException, CSVFormatException {
		IOCSV importer = new IOCSV();
		String itemName;
		ArrayList<List> manifestFile = null;
		try {
			manifestFile = importer.readCSVFile(file, "manifest");
		} catch (CSVFormatException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		Boolean isTruckType;
		Boolean isRefrigerated = false;
		Boolean isOrdinary = false;
		double totalProductBoughtOrdinary =0.0;
		double totalProductBoughtRefrigrated =0.0;
		double totalPriceInOrdinary = 0.0;
		double totalPriceInRefrigrated = 0.0;
		RefrigratedTruck refTruck = null;
		OrdinaryTruck ornTruck = null;
		Manifest manifest = new Manifest();
		double coldest = Double.MAX_VALUE;
//		System.out.println("INITIALISE A MANIFEST HERE OUTSIDE OF THE LOOOP");
		for (List line : manifestFile) {
			String content = (String)line.get(0);
			isTruckType = content.charAt(0) == '>';
			// reset variables for new truck
			if (isTruckType) {
//				System.out.println("MAKE A NEW TRUCK HERE. " + content);
				if(content.equals(">Refrigerated")) {
					refTruck = new RefrigratedTruck();
					manifest.saveRefTruck(refTruck);
					coldest = Double.MAX_VALUE;
					isRefrigerated = true;
					isOrdinary = false;
					totalPriceInRefrigrated = 0.0;
				}else if (content.equals(">Ordinary")) {

					ornTruck = new OrdinaryTruck();
					manifest.saveOrnTruck(ornTruck);
					isOrdinary = true;
					isRefrigerated = false;
					totalPriceInOrdinary = 0.0;
					totalProductBoughtOrdinary=0.0;
				}
			}
			else {
				Integer numbBought = Integer.parseInt((String)line.get(1));
				if(isOrdinary) {
					ornTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					totalProductBoughtOrdinary +=numbBought;
					System.out.println(totalProductBoughtOrdinary);
					ornTruck.setQuantity(totalProductBoughtOrdinary);
					if (temp!=null) {
						//update the current inventory
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore +=numbBought;
						temp.setCurrentInventory(currentInventoryInStore);
						//get the total prices of the product in the turck
						totalPriceInOrdinary +=temp.getManufactureCost()*numbBought;
						ornTruck.setTotalPriceInTruck(totalPriceInOrdinary);
						}
					}else {
					
					refTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					if(coldest>temp.getTemperature()) {
						coldest = temp.getTemperature();
						refTruck.setTemperature(coldest);
					}
					if (temp!=null) {
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore +=numbBought;
						temp.setCurrentInventory(currentInventoryInStore);
						totalPriceInRefrigrated +=temp.getManufactureCost()*numbBought;
						refTruck.setTotalPriceInTruck(totalPriceInRefrigrated);
					}
				}

//				System.out.println("SAVE THIS TO THE TRUCK THAT YOU MADE: " + content + ", " + count);
				
				
			}
			
		}//end loop
		for (OrdinaryTruck each: manifest.ornTruck) {
			capital-=each.costCalculation(each.getQuantity());
			System.out.println(each.costCalculation(each.getQuantity()));
			capital-=each.getTotalPriceInTruck();
			System.out.println(each.getTotalPriceInTruck());
		}
		for (RefrigratedTruck each : manifest.refTruck) {
			capital-=each.costCalculation(each.getTemperature());
			
			//calculating each item
			capital-=each.getTotalPriceInTruck();
		}
		System.out.println("Import Manifest: Success");
	}
	/**
	 * return all the items in the inventory
	 * @return HashMap of all items in inventory
	 */
	public HashMap<String, Item> getInventory() {
		HashMap<String, Item> temp = inventory.getInventory();
		return temp;
	}
	public void calculateExportManifest() throws IOException {
		Manifest manifest = new Manifest();
		double manifestCost = manifest.generateManifest();
	}
}
