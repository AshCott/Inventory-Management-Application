package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.*;

import program.CSVFormatException;
import program.StockException;
import program.Store;

/**
 * Tests for store class
 * 
 * @author Ashley Cottrell
 *
 */
public class StoreClassTests {
	Store superMart;

	/**
	 * constructor for store
	 */
	@Before
	public void constructor() {
		superMart = Store.getInstance();
	}

	/**
	 * Test for getting the store capital
	 */
	@Test
	public void GetStoreCapital() {
		assertEquals("$100,000.00", superMart.getStoreCapital());
	}

	/**
	 * Test for getting store name
	 */
	@Test
	public void GetStoreName() {
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
		superMart.creatInventory("item_properties.csv");
	}

	/**
	 * Test for importing a sales log from CSV file
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 */
	@Test
	public void importSalesLog() throws IOException, CSVFormatException, StockException {
		superMart.creatInventory("item_properties.csv");
		superMart.importSalesLog("sales_log_0.csv");
	}

	/**
	 * Test for importing a manifest from a CSV file
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 */
	@Test
	public void importManifest() throws IOException, CSVFormatException, StockException {
		superMart.creatInventory("item_properties.csv");
		superMart.importManifest("manifest.csv");
	}

}
