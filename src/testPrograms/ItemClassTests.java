package testPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import program.IOCSV;

public class ItemClassTests {
	IOCSV importer;
	
	@Before
	public void constructor() {
		importer = new IOCSV();
	}

	@Test
	public void ReadItem_PropertiesFile() throws IOException {
		ArrayList<List> b = importer.readCSVFile("item_properties.csv");
		System.out.println(b);
	}
	
}
