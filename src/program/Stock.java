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
	public HashMap<String, Integer> itemList = new HashMap<String, Integer>();
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	public void creatInventory(ArrayList<List> inventorylist) {
		for(List item : inventorylist) {
			
			//Extract Item infomation from array
			String name =  (String) item.get(0);
			double ManufactureCost = Double.parseDouble((String) item.get(1));
			double SellPrice = Double.parseDouble((String) item.get(2));
			int ReorderPoint = Integer.parseInt((String) item.get(3));
			int ReorderAmount = Integer.parseInt((String) item.get(4)); 
			
			//Check if tempreture is required and creat an item.
			if(item.size() == 6) {
				int Tempreture = Integer.parseInt((String) item.get(5));
				//Create item with temprature
				Item product = new Item(name,ManufactureCost,SellPrice,ReorderPoint,ReorderAmount,Tempreture);
				inventory.put(name, product);
			}else {
				//Create item without temprature
				Item product = new Item(name,ManufactureCost,SellPrice,ReorderPoint,ReorderAmount);
				inventory.put(name, product);
			}
			
		}
	}
	
	public Item getItem(String name){
		return inventory.get(name);
	}
	
	public void increaseItemAmount(String name) {
		int a = inventory.get(name).getCurrentInventory();
		inventory.get(name).setCurrentInventory(5557);
	}
}
