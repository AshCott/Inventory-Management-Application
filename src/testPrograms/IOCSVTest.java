package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import program.CSVFormatException;
import program.IOCSV;
@SuppressWarnings({"rawtypes","unused"})
public class IOCSVTest {
	IOCSV importer;

	@Before
	public void constructor() {
		importer = new IOCSV();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//Passing Tests
	////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void importItemProperties() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("item_properties.csv", "inventory");
		assertEquals("Importing Item Properties failed", "rice", b.get(0).get(0));
	}

	@Test
	public void importManifest() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("manifest.csv", "manifest");
		assertEquals("Importing Item Properties failed", ">Refrigerated", b.get(0).get(0));
	}

	
	@Test
	public void importSalesLog() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("sales_log_0.csv", "sales_log");
		assertEquals("Importing Item Properties failed", "rice", b.get(0).get(0));
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//Exception Tests
	////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////Import Item Properties Tests//////////////////////////////
	//Incorrect number of columns
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_SizeIncorrect() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_wrongColumns.csv", "inventory");
	}
	
	//Manufacture Cost is not a number
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_Manufactue_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_Manufacture_NaN.csv", "inventory");
	}
	
	//Sale price is not a number
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_Sale_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_SaleP_NaN.csv", "inventory");
	}
	
	//Re-Order Point is not a number
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_reOrderPoint_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_ReOrderPoint_NaN.csv", "inventory");
	}
	
	//Re-Order Amount is not a number
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_reOrderAmount_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_ReOrderAmount_NaN.csv", "inventory");
	}
	
	//Tempreture is not a number
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_Tempreture_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_Tempreture_NaN.csv", "inventory");
	}
	
	//Manufacture Price can't be less than or equal to zero
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_Manufacturing_numb_Less_Than_Or_Equal_To_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_NotZeroORBelow_Manufacturing.csv", "inventory");
	}
	
	//Sell Price can't be less than or equal to zero
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_Sell_numb_Less_Than_Or_Equal_To_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_NotZeroORBelow_Sell.csv", "inventory");
	}
	
	//Re-Order Point can't be less than or equal to zero
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_ReOrderPoint_numb_Less_Than_Or_Equal_To_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_NotZeroORBelow_ReOrderPoint.csv", "inventory");
	}
	
	//Re-Order Amount can't be less than or equal to zero
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_ReOrderAmount_numb_Less_Than_Or_Equal_To_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_NotZeroORBelow_ReOrderAmount.csv", "inventory");
	}
	
	//Tempreture Above 10 Degrees
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_temp_Above_10_Degrees() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_temp_Above_10_Degrees.csv", "inventory");
	}
	
	//Tempreture Below -20 Degrees
	@Test(expected = CSVFormatException.class)
	public void ItemProperties_temp_Below_20_Degrees() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/item_properties_temp_Below_Neg20_Degrees.csv", "inventory");
	}
	
	///////////////////////////////////Import Manifest Tests///////////////////////////////////
	
	//Manifest Size incorrect
	@Test(expected = CSVFormatException.class)
	public void Manifest_SizeIncorrect() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/manifest_SizeIncorrect.csv", "inventory");
	}
	
	//Manifest amount is a number
	@Test(expected = CSVFormatException.class)
	public void Manifest_Not_a_Number() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/manifest_NaN.csv", "inventory");
	}
	
	//Manifest Amount is not less than or equal to zero
	@Test(expected = CSVFormatException.class)
	public void Manifest_Less_Than_or_Equal_to_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/manifest_Less_Than_or_Equal_to_Zero.csv", "inventory");
	}
	
	//Manifest Truck is spelt correctly
	@Test(expected = CSVFormatException.class)
	public void Manifest_Truck_Type_Incorrect() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/manifest_Truck_Type_Incorrect.csv", "inventory");
	}
		
	//////////////////////////////////Import Sales Log Tests///////////////////////////////////
	
	//Sales Log incorrect Size
	@Test(expected = CSVFormatException.class)
	public void SalesLog_SizeIncorrect() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/saleslog_SizeIncorrect.csv", "inventory");
	}
	
	//Sales Log incorrect Size
	@Test(expected = CSVFormatException.class)
	public void SalesLog_NaN() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/saleslog_NaN.csv", "inventory");
	}
	
	//Sales Log incorrect Size
	@Test(expected = CSVFormatException.class)
	public void SalesLog_Not_Less_Than_Zero() throws IOException, CSVFormatException {
	ArrayList<List> b = importer.readCSVFile("csv_failed_tests/saleslog_Not_Less_Than_Zero.csv", "inventory");
	}
}
