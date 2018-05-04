package program;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * @author Ashley Cottrell
 *
 */
public class IOCSV {

	public IOCSV() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param Takes file location as a String
	 * @return a 2D array with the items from the CSV
	 * @throws IOException
	 */
	public ArrayList<List> readCSVFile(String file) throws IOException {
		Scanner s = new Scanner(new File(file));
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
