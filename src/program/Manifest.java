package program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author 
 *
 */

public class Manifest {
	//collection of truck
	public ArrayList<RefrigratedTruck> refTruck = new ArrayList<>(); 
	public ArrayList<OrdinaryTruck> ornTruck = new ArrayList<>();
	Store superMart = Store.getInstance();

//	public ArrayList<Truck> truck = new ArrayList<>();
	public Manifest() {
		this.ornTruck = new ArrayList<OrdinaryTruck>();
		this.refTruck = new ArrayList<RefrigratedTruck>();
		
	}
	public void saveOrnTruck(OrdinaryTruck truck) {
		ornTruck.add(truck);
	}
	public void saveRefTruck(RefrigratedTruck truck) {
		refTruck.add(truck);	
	}
	public double generateManifest() throws IOException {
		//for test later on shouldn't create here
		double cost = 0;
		HashMap<String, Item> tempinventory = superMart.getInventory();
		TreeMap<String, Item> inventory = new TreeMap<String, Item>();
		inventory.putAll(tempinventory);
		
		ArrayList<Item> whatToBuy = new ArrayList<Item>();
		for(String key:inventory.keySet()) {
			//get the reorder point add the inventory based on the reorder amount,
			//sort the item based on the temperature, 
			if(inventory.get(key).getCurrentInventory()<inventory.get(key).getReorderPoint()) {
				whatToBuy.add(inventory.get(key));
				//get the cost for each of the item
				cost += inventory.get(key).getManufactureCost()*inventory.get(key).getReorderAmount();
			}else {
				continue;
			}
		}
//		for(Item test:whatToBuy) {
//		System.out.println(test.getName()+test.getTemperature());
//		}
		
		//sorting based on the temperature from negative to positive
		whatToBuy.sort(Comparator.comparing(Item::getTemperature));
		//after sorting look at the list of item then make the truck until it completely filled
		
//		for(Item test:whatToBuy) {
//			System.out.println(test.getName()+test.getTemperature());
//		}
		
		return 0;
		//test csv by looping through the csv then throw an exception 

	}
	
	
}
