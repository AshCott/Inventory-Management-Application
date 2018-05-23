package program;

import java.util.HashMap;

public class OrdinaryTruck extends Truck {
	private double quantity;
	private final double CARGO_CAPACITY= 1000;	
	public OrdinaryTruck() {
		this.cargo = new HashMap<String, Integer>();
		this.itemCount =0;
		this.insideTruck = 0;
	}
	
	@Override
	public boolean isCargoFull() {
		return(this.CARGO_CAPACITY==this.quantity);
	}
	@Override
	public double costCalculation(double quantity) {
		this.quantity = quantity;
		cost = 750+ (0.25*quantity);
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
	public void addItemImportManifest(String itemName, Integer quantity) {
		cargo.put(itemName, quantity);
		itemCount += quantity;
 	}

	@Override
	public double addItemOptimizeManifest(Item cargoItem) {
//		double leftOverCapacity = this.CARGO_CAPACITY-(cargoItem.getCurrentInventory()+this.insideTruck);
//		System.out.println(this.insideTruck);
//		System.out.println(leftOverCapacity);
//		//if truck is not full add the thing and return the value of leftover
//		//
//		if(leftOverCapacity>0) {
////			this.insideTruck+=cargoItem.reo
//			return leftOverCapacity;
//		}else {
//			return 0;
//		}
//		//else (truck is full) then don't add the thing and return value of leftover as 0 since it is possible to become negative
//		//if truck is full
//		else if(leftOverCapacity<=0) {
//			
//			return 0;
//		}else {
//			
//		}
//		//if truck is full
//		else if(leftOverCapacity==cargoItem.getCurrentInventory()) {
//			return leftOverCapacity;
//		}else {
//			cargoItem.setCurrentInventory((int) (cargoItem.getCurrentInventory()-leftOverCapacity));
//			cargo.put(cargoItem.getName(), cargoItem.getCurrentInventory());
//			return leftOverCapacity;
//		}
		return 0;
	}



}
