package program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public class Stock {
	public static HashMap<String, Item> inventory = new HashMap<String, Item>();
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	public void creatInventory(ArrayList<List> inventorylist) {
		for(List i : inventorylist) {
			String name =  (String) i.get(0);
			double ManufactureCost = Double.parseDouble((String) i.get(1));
			double SellPrice = Double.parseDouble((String) i.get(2));
			int ReorderPoint = Integer.parseInt((String) i.get(3));
			int ReorderAmount = Integer.parseInt((String) i.get(4)); 
			if(i.size() == 6) {
				int Tempreture = Integer.parseInt((String) i.get(5));
				//System.out.println(name+", "+ManufactureCost+", "+SellPrice+", "+ReorderPoint+", "+ReorderAmount+", "+Tempreture);
				Item a = new Item(name,ManufactureCost,SellPrice,ReorderPoint,ReorderAmount,Tempreture);
				inventory.put(name, a);
			}else {
				//System.out.println(name+", "+ManufactureCost+", "+SellPrice+", "+ReorderPoint+", "+ReorderAmount);
				Item a = new Item(name,ManufactureCost,SellPrice,ReorderPoint,ReorderAmount);
				inventory.put(name, a);
			}
			
		}
	}
	
	public Item getItem(String name){
		return inventory.get(name);
	}
}
