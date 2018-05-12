package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import program.*;

public class ItemClassTests {
	IOCSV importer;
	Store superMart;
	@Before
	public void constructor() {
		importer = new IOCSV();
		superMart = Store.getInstance();
	}
	@Test
	public void ReadItem_PropertiesFile() throws IOException {
		//ArrayList<List> b = importer.readCSVFile("item_properties.csv");
		//System.out.println(b);
	}
	
	@Test
	public void importItemProperties() throws IOException {
		superMart.creatInventory("item_properties.csv");
	}
	
	@Test
	public void importManifest() throws IOException {
		superMart.creatInventory("item_properties.csv");
		superMart.importManifest("manifest.csv");
	}
//	
	@Test
	public void importSalesLog() throws IOException {
		superMart.creatInventory("item_properties.csv");
		superMart.importSalesLog("sales_log_0.csv");
	}
	
}
