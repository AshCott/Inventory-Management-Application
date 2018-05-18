package program;

import java.util.HashMap;

public class OrdinaryTruck extends Truck {
	private double quantity;
	public OrdinaryTruck() {
		super();
	}
	@Override
	public double costCalculation(double quantity) {
		this.quantity = quantity;
		System.out.println("Quantity: "+quantity);
		cost = 750+ (0.25*quantity);
		System.out.println("Cost: "+cost);
		return cost;
	}
	
	@Override
	public double cargoCaps () {
		capacity = 1000;
		return capacity;
	}
	
	@Override
	public void addItem(String itemName, Integer quantity) {
		items.put(itemName, quantity);
		itemCount += quantity;
 	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public double getQuantity() {
		return quantity;
	}

}
