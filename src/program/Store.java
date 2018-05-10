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
	String name;
	double capital;
	Stock inventory = new Stock();
	
	public Store() {
		// TODO Auto-generated constructor stub
		this.name = "SuperMart";
		this.capital = 100000;
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
	 * 
	 * @return The Store Name as String
	 */
	public String getStoreName() {
		return this.name;
	}

	public void creatInventory(String file) throws IOException {
		IOCSV importer = new IOCSV();
		ArrayList<List> inventorylist = importer.readCSVFile(file);
		inventory.creatInventory(inventorylist);		
	}
	
	public void importSalesLog(String file) throws IOException {
		IOCSV importer = new IOCSV();
		ArrayList<List> salesLog = importer.readCSVFile(file);
		System.out.println(salesLog);
	}
	
	public void importManifest(String file) throws IOException {
		IOCSV importer = new IOCSV();
		ArrayList<List> manifest = importer.readCSVFile(file);
		System.out.println(manifest);
	}
}
