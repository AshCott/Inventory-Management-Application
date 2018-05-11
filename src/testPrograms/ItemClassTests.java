package testPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import program.*;

public class ItemClassTests {
	IOCSV importer;
	
	@Before
	public void constructor() {
		importer = new IOCSV();
	}
	
	@Test
	public void ReadItem_PropertiesFile() throws IOException {
		//ArrayList<List> b = importer.readCSVFile("item_properties.csv");
		//System.out.println(b);
	}
	
	@Test
	public void importItemProperties() throws IOException {
		Store a = new Store();
		a.creatInventory("item_properties.csv");
	}
	
	@Test
	public void importManifest() throws IOException {
		Store a = new Store();
		a.importManifest("manifest.csv");
	}
	
	@Test
	public void importSalesLog() throws IOException {
		Store a = new Store();
		a.importSalesLog("sales_log_0.csv");
	}
	
}
