package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.*;

import program.CSVFormatException;
import program.DeliveryException;
import program.StockException;
import program.Store;

/**
 * Tests for store class
 * 
 * @author Ashley Cottrell
 *
 */
public class StoreClassTests {

	/**
	 * constructor for store
	 */
	@Before
	public void constructor() {

	}

	/**
	 * Test for getting the store capital
	 */
	@Test
	public void GetStoreCapital() {
		Store superMart;
		superMart = Store.getInstance();
		assertEquals("$100,000.00", superMart.getStoreCapital());
	}

	/**
	 * Test for getting store name
	 */
	@Test
	public void GetStoreName() {
		Store superMart;
		superMart = Store.getInstance();
		assertEquals("SuperMart", superMart.getStoreName());
	}

	/**
	 * Test for creating the inventory from a CSV file
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	@Test
	public void createInventory() throws IOException, CSVFormatException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
	}

	/**
	 * Test for exporting Manifest
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	@Test
	public void ExportManifest() throws IOException, CSVFormatException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
	}

	/**
	 * Import the Manifest File
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException 
	 */
	@Test
	public void ImportManifest() throws IOException, CSVFormatException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
	}

	/**
	 * Test for importing a sales log 0
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	@Test
	public void importSalesLog_0() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_0.csv");
	}

	/**
	 * Test for importing a sales log 1
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	@Test
	public void importSalesLog_1() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_1.csv");
	}

	/**
	 * Test for importing a sales log 2
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	@Test
	public void importSalesLog_2() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_2.csv");
	}

	/**
	 * Test for importing a sales log 3
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	@Test
	public void importSalesLog_3() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_3.csv");
	}

	/**
	 * Test for importing a sales log 4
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException 
	 */
	@Test
	public void importSalesLog_4() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.calculateExportManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_4.csv");
	}

}
