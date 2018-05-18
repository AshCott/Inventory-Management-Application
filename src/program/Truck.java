package program;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Truck {
	protected double capacity;
	protected double cost;
	protected Integer itemCount;
	private String truckType;
	protected HashMap<String, Integer> items;
	private double totalPrice;
	
	public Truck() {
		this.items = new HashMap<String, Integer>();
		this.itemCount = 0; // assume the truck starts empty
	}
	
	public Integer getItemCount() {
		return this.itemCount;
	}
	
	public double costCalculation(double tempQuant) {
		return cost;
	}
	public double cargoCaps() {
		return capacity;
	}
	
	public String getTruckType() {
		
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	
	public void addItem(String itemName, Integer quantity) {
		items.put(itemName, quantity);
		itemCount += quantity;
	}
	public void getItem(String itemName) {
		items.get(itemName);
	}
	public void setTotalPriceInTruck(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalPriceInTruck() {

		return totalPrice;
	}
	

}
