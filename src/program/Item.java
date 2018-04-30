package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author 
 * @author
 *
 */
public class Item {

	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<List> readCSVFile() throws IOException {
		Scanner s = new Scanner(new File("item_properties.csv"));
		ArrayList<List> list = new ArrayList<List>();
		while (s.hasNext()){
			String data = s.next();
			List<String> item = Arrays.asList(data.split("\\s*,\\s*"));
		    list.add(item);
		}
		s.close();

		return list;
		
	}

}
