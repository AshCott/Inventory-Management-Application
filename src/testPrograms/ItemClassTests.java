package testPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import program.Item;
import program.IOCSV;

public class ItemClassTests {

	@Test
	public void ReadItem_PropertiesFile() throws IOException {
		IOCSV importer = new IOCSV();
		ArrayList<List> b = importer.readCSVFile("item_properties.csv");
		System.out.println(b);
	}
	
}
