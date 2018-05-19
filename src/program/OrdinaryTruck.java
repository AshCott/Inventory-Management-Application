package program;

import java.util.HashMap;

public class OrdinaryTruck extends Truck {
	private double quantity;
	private final double CARGO_CAPACITY= 1000;	
	public OrdinaryTruck() {
		this.items = new HashMap<String, Integer>();
		this.itemCount =0;
	}
	
	@Override
	public boolean isCargoFull() {
		return(this.CARGO_CAPACITY==this.quantity);
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
	public double getQuantity() {
		return quantity;
	}
	@Override
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public void setTotalPriceInTruck(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public double getTotalPriceInTruck() {

		return totalPrice;
	}
	@Override
	public void addItem(String itemName, Integer quantity) {
		items.put(itemName, quantity);
		itemCount += quantity;
 	}



}
