package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * @author Ashley Cottrell
 *
 */
public class IOCSV {

	public IOCSV() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param Takes file location as a String
	 * @return a 2D array with the items from the CSV
	 * @throws IOException
	 */
	public ArrayList<List> readCSVFile(String file, String type) throws IOException, CSVFormatException {
		if (!file.endsWith(".csv")) {
			throw new CSVFormatException("Imported File is not CSV");
		}

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
				inventoryTests(list, type);
			} catch (CSVFormatException e) {
				throw e;
			}
			break;
		}
		return list;
	}
	
	private void sales_logTests(ArrayList<List> list, String type) throws CSVFormatException{
		for (List item : list) {
			if (item.size() == 2) {
			} else {
				throw new CSVFormatException("Item contains incorrect number of columns \n" + item);
			}
			
			try {
				int numbSold = Integer.parseInt((String) item.get(1));
			} catch (Exception e) {
				throw new CSVFormatException("The number sold is not a number \n" + item);
			}
			
		}
	}
	
	private void inventoryTests(ArrayList<List> list, String type) throws CSVFormatException {
		for (List item : list) {
			if (item.size() == 5 || item.size() == 6) {
			} else {
				throw new CSVFormatException("Item contains incorrect number of columns \n" + item);
			}

			try {
				double ManufactureCost = Double.parseDouble((String) item.get(1));
			} catch (Exception e) {
				throw new CSVFormatException("Manufacture cost is not a number \n" + item);
			}

			try {
				double SellPrice = Double.parseDouble((String) item.get(2));
			} catch (Exception e) {
				throw new CSVFormatException("Sell price is not a number \n" + item);
			}

			try {
				int ReorderPoint = Integer.parseInt((String) item.get(3));
			} catch (Exception e) {
				throw new CSVFormatException("Re-order point is not a number \n" + item);
			}

			try {
				int ReorderAmount = Integer.parseInt((String) item.get(4));
			} catch (Exception e) {
				throw new CSVFormatException("Re-order Amount is not a number \n" + item);
			}

			if (item.size() == 6) {
				int Tempreture;
				try {
					Tempreture = Integer.parseInt((String) item.get(5));
				} catch (Exception e) {
					throw new CSVFormatException("Tempreture is not a number \n" + item);
				}
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
