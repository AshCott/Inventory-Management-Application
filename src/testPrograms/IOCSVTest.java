package testPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import program.CSVFormatException;
import program.IOCSV;

public class IOCSVTest {
	IOCSV importer;
	
	@Before
	public void constructor() {
		importer = new IOCSV();
	}

	@Test
	public void importItem_Properties() throws IOException {

	}
	
	@Test
	public void importItemProperties() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("item_properties.csv", "inventory");
		System.out.println(b);
	}
	
	@Test
	public void importManifest() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("manifest.csv", "inventory");
		System.out.println(b);
	}

	@Test
	public void importSalesLog() throws IOException, CSVFormatException {
		ArrayList<List> b = importer.readCSVFile("sales_log_0.csv", "inventory");
		System.out.println(b);
	}
}
