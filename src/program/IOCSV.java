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
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line = null;
	    ArrayList<List> list = new ArrayList<List>();
	    while ((line = br.readLine()) != null) {
	      String[] values = line.split(",");
	      List<String> temp = new ArrayList<String>();
	      for (String str : values) {
	    	  temp.add(str);
	      }
	      list.add(temp);
	    }
	    br.close();
		return list;
	}
}
