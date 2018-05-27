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
 * @author Radhimas Djan
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
		superMart.reset();
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
	 * @throws DeliveryException
	 */
	@Test
	public void ExportManifest() throws IOException, CSVFormatException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
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
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
	}

	/**
	 * Test if the import manifest for the first one work like pdf
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 * @throws StockException
	 */
	@Test
	public void ImportManifestInit() throws IOException, CSVFormatException, DeliveryException, StockException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());

	}

	/**
	 * Test if the import manifest for the first one till the end work like pdf
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 * @throws StockException
	 */
	@Test
	public void ImportManifestTillFinal() throws IOException, CSVFormatException, DeliveryException, StockException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_0.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$27,569.79", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_1.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,069.94", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_2.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$47,549.04", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_3.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$51,838.22", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_4.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$56,140.25", superMart.getStoreCapital());
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
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_0.csv");
		assertEquals("$72,047.88", superMart.getStoreCapital());
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
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_1.csv");
		assertEquals("$82,317.88", superMart.getStoreCapital());
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
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_2.csv");
		assertEquals("$82,042.88", superMart.getStoreCapital());
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
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_3.csv");
		assertEquals("$77,135.88", superMart.getStoreCapital());
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
		superMart.reset();
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		assertEquals("$42,717.88", superMart.getStoreCapital());
		superMart.importSalesLog("sales_log_4.csv");
		assertEquals("$84,731.88", superMart.getStoreCapital());
	}

	/**
	 * Test if exception is thrown when sales log item doesn't exists within the
	 * inventory
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 * @throws StockException
	 */
	@Test(expected = StockException.class)
	public void Sales_Log_itemDoesntExist() throws IOException, CSVFormatException, DeliveryException, StockException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.reset();
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("csv_failed_tests/saleslog_Item_Doesnt_Exist.csv");
	}

	/**
	 * Test if inventory has enough items to sell. i.e. inventory = 10 & SalesLog =
	 * 20 you can't sell 10 more than what you have
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 * @throws StockException
	 */
	@Test(expected = StockException.class)
	public void Sales_Log_Inventory_less_than_Log()
			throws IOException, CSVFormatException, DeliveryException, StockException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.reset();
		superMart.importSalesLog("csv_failed_tests/saleslog_Item_Doesnt_Exist.csv");
	}

	/**
	 * Test if exception is thrown when item in an imported manifest doesn't exist
	 * in the inventory
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException
	 */
	@Test(expected = DeliveryException.class)
	public void manifest_Item_Exists() throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.reset();
		superMart.importManifest("csv_failed_tests/manifest_Item_not_Exists.csv");
	}

	/**
	 * Test if exception is thrown when an item isn't needed to be reorderd
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws DeliveryException
	 */
	@Test(expected = DeliveryException.class)
	public void ExportManifest_No_Item_Need_Reorder()
			throws IOException, CSVFormatException, StockException, DeliveryException {
		Store superMart;
		superMart = Store.getInstance();
		superMart.creatInventory("item_properties.csv");
		superMart.reset();
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.exportingManifest();
	}
}
