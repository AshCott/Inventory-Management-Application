package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Imports and exports a CSV file
 * 
 * @author Ashley Cottrell
 */
public class IOCSV {

	public IOCSV() {
	}

	/**
	 * Imports a csv file and checks the integrity of the file
	 * 
	 * @param Takes
	 *            file location as a String
	 * @param Type
	 *            of CSV for checking integrity of the file
	 * @return a 2D array with the items from the CSV
	 * @throws IOException
	 */
	public ArrayList<List> readCSVFile(String file, String type) throws IOException, CSVFormatException {
		// Checks if file extension is a .csv
		if (!file.endsWith(".csv")) {
			throw new CSVFormatException("Imported File is not CSV");
		}

		// Import the csv into an Array List
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		ArrayList<List> list = new ArrayList<List>();
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			List<String> temp = new ArrayList<String>();
			for (String str : values) {
				temp.add(str);
			}
			list.add(temp);
		}
		br.close();

		// Run file verification for specified type
		switch (type) {
		case "inventory":
			try {
				inventoryTests(list, type);
			} catch (CSVFormatException e) {
				throw e;
			}
			break;

		case "sales_log":
			try {
				sales_logTests(list, type);
			} catch (CSVFormatException e) {
				throw e;
			}
			break;

		case "manifest":
			try {
				manifestTests(list, type);
			} catch (CSVFormatException e) {
				throw e;
			}
			break;
		}
		return list;
	}

	/**
	 * Verifies the manifest file data to prevent errors
	 * 
	 * @param list
	 *            CSV information from importer
	 * @param type
	 *            What type of csv is being imported
	 * @throws CSVFormatException
	 */
	private void manifestTests(ArrayList<List> list, String type) throws CSVFormatException {
		int numbSold;

		// loop throug all the items
		for (List item : list) {

			// Check that the size of items are as expected
			if (item.size() == 1 || item.size() == 2) {
			} else {
				throw new CSVFormatException("Item contains incorrect number of columns \n" + item);
			}

			// Check that truck is only >Refrigerated or >Ordinary
			if (item.size() == 1) {
				String truck = (String) item.get(0);
				if (truck.equals(">Refrigerated") || truck.equals(">Ordinary")) {

				} else {
					throw new CSVFormatException("Truck type can only be >Refrigerated or >Ordinary \n" + item);
				}
			}

			// Check that amount purchased is a number
			if (item.size() == 2) {
				try {
					numbSold = Integer.parseInt((String) item.get(1));
				} catch (Exception e) {
					throw new CSVFormatException("Amount Purchased is not a number \n" + item);
				}
				// Checks if number is not less than or equal to zero
				if (numbSold <= 0) {
					throw new CSVFormatException("Number can't be less than or equal to zero \n" + item);
				}
			}
		}
	}

	/**
	 * Verifies the Sales Log file data to prevent errors
	 * 
	 * @param list
	 *            CSV information from importer
	 * @param type
	 *            What type of csv is being imported
	 * @throws CSVFormatException
	 */
	private void sales_logTests(ArrayList<List> list, String type) throws CSVFormatException {
		int numbSold;

		// Loop through items
		for (List item : list) {

			// Checks if size of Item is correct
			if (item.size() == 2) {
			} else {
				throw new CSVFormatException("Item contains incorrect number of columns \n" + item);
			}

			// Checks if value is a number
			try {
				numbSold = Integer.parseInt((String) item.get(1));
			} catch (Exception e) {
				throw new CSVFormatException("The number sold is not a number \n" + item);
			}

			// Checks if value is not less than zero
			if (numbSold < 0) {
				throw new CSVFormatException("Number can't be less than or equal to zero \n" + item);
			}

		}
	}

	/**
	 * Verifies the Inventory csv file data to prevent errors
	 * 
	 * @param list
	 *            CSV information from importer
	 * @param type
	 *            What type of csv is being imported
	 * @throws CSVFormatException
	 */
	private void inventoryTests(ArrayList<List> list, String type) throws CSVFormatException {
		double ManufactureCost;
		double SellPrice;
		int ReorderPoint;
		int ReorderAmount;

		// Loop through items
		for (List item : list) {

			// Checks if size is as expected
			if (item.size() == 5 || item.size() == 6) {
			} else {
				throw new CSVFormatException("Item contains incorrect number of columns \n" + item);
			}

			// Checks if manufacture cost is a number
			try {
				ManufactureCost = Double.parseDouble((String) item.get(1));
			} catch (Exception e) {
				throw new CSVFormatException("Manufacture cost is not a number \n" + item);
			}

			// Checks if sell price is a number
			try {
				SellPrice = Double.parseDouble((String) item.get(2));
			} catch (Exception e) {
				throw new CSVFormatException("Sell price is not a number \n" + item);
			}

			// Checks if the Re-order point is a number
			try {
				ReorderPoint = Integer.parseInt((String) item.get(3));
			} catch (Exception e) {
				throw new CSVFormatException("Re-order point is not a number \n" + item);
			}

			// Checks if Re-Order amount is a number
			try {
				ReorderAmount = Integer.parseInt((String) item.get(4));
			} catch (Exception e) {
				throw new CSVFormatException("Re-order Amount is not a number \n" + item);
			}

			// Checks if values are not less than or equal to zero
			if (ManufactureCost <= 0 || SellPrice <= 0 || ReorderPoint <= 0 || ReorderAmount <= 0) {
				throw new CSVFormatException("Number can't be less than or equal to zero \n" + item);
			}

			// Checks if tempreture is a number
			if (item.size() == 6) {
				int Tempreture;
				try {
					Tempreture = Integer.parseInt((String) item.get(5));
				} catch (Exception e) {
					throw new CSVFormatException("Tempreture is not a number \n" + item);
				}

				// Checks if tempreture is within the allowed range
				if (Tempreture > 10) {
					throw new CSVFormatException("Tempreture is greater than 10 degrees \n" + item);
				}
				if (Tempreture < -20) {
					throw new CSVFormatException("Tempreture is less than -20 degrees \n" + item);
				}
			}
		}
	}
}
