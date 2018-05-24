package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.*;

import program.CSVFormatException;
import program.StockException;
import program.Store;

public class StoreClassTests {
	Store superMart;
	
	@Before
	public void constructor() {
		superMart = Store.getInstance();
	}
	
	@Test
	public void GetStoreCapital() {
		assertEquals("$100,000.00", superMart.getStoreCapital());
	}
	
	@Test
	public void GetStoreName() {
		assertEquals("SuperMart", superMart.getStoreName());
	}
	
	@Test
	public void createInventory() throws IOException, CSVFormatException {
		superMart.creatInventory("item_properties.csv");
	}
	
	@Test
	public void importSalesLog() throws IOException, CSVFormatException, StockException {
		superMart.importSalesLog("sales_log_0.csv");
	}
	
	@Test
	public void importManifest() throws IOException, CSVFormatException, StockException {
		superMart.importSalesLog("manifest.csv");
	}

}
