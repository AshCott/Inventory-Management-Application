package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import program.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Item a = new Item();
		ArrayList<List> b = a.readCSVFile();
		System.out.println(b.get(0));
		System.out.println(b.get(1));
		
	}

}
