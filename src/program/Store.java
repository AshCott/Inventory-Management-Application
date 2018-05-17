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
	 */
	public void creatInventory(String file) throws IOException {
		IOCSV importer = new IOCSV();
		inventorylist = importer.readCSVFile(file);
		inventory.creatInventory(inventorylist);
		System.out.println("Success :DDDDDDD");
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
	}
	
//	public void importManifest(String file) throws IOException {
//		String itemName ="";
//		int numbBought = 0;
//		double totalProductBought = 0.0;
//		double totalProductOrdin = 0.0;
//		double coolestItem = Double.MAX_VALUE;
//		String truckType = "";
//		Boolean flagOrdin = false;
//		Boolean flagRefrig = false;
//		Boolean flagEndOfTruck = false;
//		IOCSV importer = new IOCSV();
//		ArrayList<List> manifest = importer.readCSVFile(file);
//		for (List each: manifest ) {
//			System.out.println(each);
//			//problem will occurs if there's multiple refrigrated/ordinary
//			//so if the thing is refrigrated->ordinary then its fine but if its refrigrated->refrigrated then chaos
//			//because it will read the first refrigrated data as well it doesn't skip the first,second,etc in the list
//			//need a method to contain from ">" to the ">" until the end in each loop not checking it each
//			if(each.get(0).toString().startsWith(">")) {			
//				truckType = (String) each.get(0);
//				if (truckType.toString().equals(">Refrigerated")) {
//					RefrigratedTruck refTruck = new RefrigratedTruck();
//					
////					for (List test : manifest) {
////						System.out.println(test.get(0));
////						if(test.get(0).toString().startsWith(">")){
////							flagRefrig = true;
////						}
////						
////						if(!test.get(0).toString().startsWith(">")) {
////							itemName = (String) test.get(0);
////							Item temp = inventory.getItem(itemName);
////							System.out.println(temp.getTemperature());
////							if(coolest>temp.getTemperature()) {
////								coolest = temp.getTemperature();
////							}
////						}
////						flagRefrig = false;
////					}
//				}else if(truckType.toString().equals(">Ordinary")) {
////					for (List test : manifest) {
////						//checking if its the end of the truck manifest
////						if(test.get(0).toString().startsWith(">")) {
////							flagOrdin = true;
////						}
////						//if not put it in
////						if(!test.get(0).toString().startsWith(">")&&!flagOrdin) {
////							totalProductOrdin+= Integer.parseInt((String) test.get(1));
////						}
////						OrdinaryTruck ordTruck = new OrdinaryTruck();
////						flagOrdin = false;
////						}
//				}else {
//					itemName = (String) each.get(0);
//					numbBought = Integer.parseInt((String) each.get(1));
//				}
//				//making sure that the itemName is not null 
//				if(inventory.getItem(itemName)==null) {
//					
//				}
//				//checking if the .get(0) is an item not a truck type, if not make calculation to capital
//				else if (!each.get(0).toString().equals(">Refrigerated")&&!each.get(0).toString().equals(">Ordinary")){
//					Item temp = inventory.getItem(itemName);
//					int currentInventory = temp.getCurrentInventory();
//					currentInventory += numbBought;
//					temp.setCurrentInventory(currentInventory);
//					totalProductBought += temp.getManufactureCost() * numbBought;	
//					//System.out.println(temp.getSellPrice() +" "+ numbBought + " "+ totalProductBought);
//				}
//			}
//			capital -= totalProductBought;
//			flagOrdin = false;
//			flagRefrig = false;
//			}
//	}
	
	public void importManifest(String file) throws IOException {
		IOCSV importer = new IOCSV();
		String itemName;
		ArrayList<List> manifestFile = importer.readCSVFile(file);
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
				}

//				if (refTruck != null) {
//					System.out.println("double test");
//					manifest.saveRefTruck(refTruck);
////					System.out.println("SAVE THE TRUCK TO THE MANIFEST DEFINED ABOVE OR SOMETHING");
//				}
//				if(ornTruck!=null) {
//					manifest.saveOrnTruck(ornTruck);
//				}

			}
			else {
				Integer numbBought = Integer.parseInt((String)line.get(1));
				if(isOrdinary) {
					ornTruck.addItem(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					totalProductBoughtOrdinary +=numbBought;
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
					
					refTruck.addItem(content, numbBought);
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
		capital = 100000.0;
		System.out.println(capital);
		for (OrdinaryTruck each: manifest.ornTruck) {
			capital-=each.costCalculation(each.getQuantity());
			capital-=each.getTotalPriceInTruck();
		}
		System.out.println(capital);
		for (RefrigratedTruck each : manifest.refTruck) {
			capital-=each.costCalculation(each.getTemperature());
			//calculating each item
			capital-=each.getTotalPriceInTruck();
		}


		System.out.println(capital);
	}
	
	public HashMap<String, Item> getInventory() {
		HashMap<String, Item> temp = inventory.getInventory();
		return temp;
	}
}
