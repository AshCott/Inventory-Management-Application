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
		return(this.CARGO_CAPACITY==getQuantity());
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
	public int addItemOptimizeManifest(Item cargoItem) {
		System.out.println("THIS IS QUANTITY"+this.quantity);
		int leftOverCapacity = (int) ((cargoItem.getReorderAmount()+this.quantity)-this.CARGO_CAPACITY);
		if(leftOverCapacity<=0) {
			this.quantity+=cargoItem.getReorderAmount();
			this.cargo.put(cargoItem.getName(), cargoItem.getReorderAmount());
			System.out.println("THIS IS QUANTITY"+this.quantity);
			System.out.println("this is left over capacity"+leftOverCapacity);
			
			return leftOverCapacity*-1;
		}else if(leftOverCapacity == cargoItem.getReorderAmount()) {
			System.out.println("THIS IS QUANTITY"+this.quantity);
			System.out.println("this is left over capacity"+leftOverCapacity);
			return leftOverCapacity;
		}else {
			cargoItem.setCurrentInventory(cargoItem.getReorderAmount()-leftOverCapacity);
			this.quantity+=cargoItem.getCurrentInventory();
			this.cargo.put(cargoItem.getName(), cargoItem.getCurrentInventory());
			System.out.println("THIS IS QUANTITY"+this.quantity);
			System.out.println("this is left over capacity"+leftOverCapacity);
			return leftOverCapacity;
		}

	}

	@Override
	public int getTruckNo() {
		return truckNum;
	}

	@Override
	public void setTruckNo(int truckNumber) {
		this.truckNum=truckNumber;
	}



}
