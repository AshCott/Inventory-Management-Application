package program;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
	 */
	public void creatInventory(String file) throws IOException {
		IOCSV importer = new IOCSV();
		inventorylist = importer.readCSVFile(file);
		inventory.creatInventory(inventorylist);
	}
	
	/**
	 * Import and process the sales Log CSV files
	 * @param link to the csv file
	 * @throws IOException
	 */
	public void importSalesLog(String file) throws IOException {
		IOCSV importer = new IOCSV();
		double totalSales = 0.0;
		ArrayList<List> salesLog = importer.readCSVFile(file);
		
		//Loop though the sales log
		for(List i : salesLog) {
			
			//Get Variables
			String itemName = (String) i.get(0);
			int numbSold = Integer.parseInt((String) i.get(1));
			Item temp = inventory.getItem(itemName);
			
			//Calculate the total sales revenue
			totalSales += temp.getSellPrice() * numbSold;
			
			//Change inventory ammount
			int currentInventory = temp.getCurrentInventory();
			currentInventory -= numbSold;
			temp.setCurrentInventory(currentInventory);
			System.out.println(numbSold+" "+temp.getCurrentInventory());	
		}
		//Increase the capital
		capital += totalSales;
	}
	
	public void importManifest(String file) throws IOException {
		IOCSV importer = new IOCSV();
		ArrayList<List> manifest = importer.readCSVFile(file);
	}
}
