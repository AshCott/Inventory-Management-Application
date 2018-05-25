package program;

import java.util.HashMap;

public class OrdinaryTruck extends Truck {
	private double quantity;
	private int extraItem;
	private HashMap <String,Integer> cargo;
	private final double CARGO_CAPACITY= 1000;	
	public OrdinaryTruck() {
		this.cargo = new HashMap<String, Integer>();
		this.itemCount =0;
		this.insideTruck = 0;
		this.extraItem = 0;
	}
//	public String getItemNameInCargo() {
//		
//	}
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
//		System.out.println("THIS IS QUANTITY"+this.quantity);
		int leftOverCapacity;
		int itemGetToCargo;
		leftOverCapacity = (int) ((cargoItem.getReorderAmount()+this.quantity)-this.CARGO_CAPACITY);
		
		if(leftOverCapacity<=0) {
			//logic in here is correct
			this.quantity+=cargoItem.getReorderAmount();
			this.cargo.put(cargoItem.getName(), cargoItem.getReorderAmount());
			return cargoItem.getReorderAmount();
		}else {
			//if there is a left over set it to extra item
			this.setExtraItemInCargo(leftOverCapacity);
			itemGetToCargo = (int) (this.CARGO_CAPACITY-this.quantity);
			this.cargo.put(cargoItem.getName(), itemGetToCargo);
			this.quantity+=itemGetToCargo;
			return itemGetToCargo;
		}
	}
	public int addItemExtraItem(Item cargoItem,int extraCargo) {
//		System.out.println("THIS IS QUANTITY"+this.quantity);
		int leftOverCapacity;
		int itemGetToCargo;
		leftOverCapacity = (int) ((extraCargo+this.quantity)-this.CARGO_CAPACITY);
		if(leftOverCapacity<=0) {
			//logic in here is correct
			this.quantity+=extraCargo;
			this.cargo.put(cargoItem.getName(), extraCargo);
			return extraCargo;
		}else {
			//if there is a left over set it to extra item
			this.setExtraItemInCargo(leftOverCapacity);
			itemGetToCargo = (int) (this.CARGO_CAPACITY-this.quantity);
			this.cargo.put(cargoItem.getName(), itemGetToCargo);
			this.quantity+=itemGetToCargo;
			return itemGetToCargo;
		}
	}
	public HashMap<String, Integer> getCargo() {
		return this.cargo;
	}
	@Override
	public int getTruckNo() {
		return truckNum;
	}

	@Override
	public void setTruckNo(int truckNumber) {
		this.truckNum=truckNumber;
	}
	public int getExtraItemInCargo() {
		return extraItem;
	}
	public void setExtraItemInCargo(int extraItem) {
		this.extraItem= extraItem;
	}
	


}
