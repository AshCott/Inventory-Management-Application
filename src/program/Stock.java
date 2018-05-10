package program;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public class Stock {
	public static ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	public void creatInventory(ArrayList<List> inventorylist) {
		for(List i : inventorylist) {
			String name = (String) i.get(0);
			double ManufactureCost = Double.parseDouble((String) i.get(1));
			double SellPrice = Double.parseDouble((String) i.get(2));
			int ReorderPoint = Integer.parseInt((String) i.get(3));
			int ReorderAmount = Integer.parseInt((String) i.get(4)); 
			if(i.size() == 6) {
				int Tempreture = Integer.parseInt((String) i.get(5));
				//System.out.println(name+", "+ManufactureCost+", "+SellPrice+", "+ReorderPoint+", "+ReorderAmount+", "+Tempreture);
			}else {
				//System.out.println(name+", "+ManufactureCost+", "+SellPrice+", "+ReorderPoint+", "+ReorderAmount);
			}
			
		}
	}

}
