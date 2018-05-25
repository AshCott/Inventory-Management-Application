package program;

import java.lang.Math.*;
import java.util.HashMap;

public class RefrigratedTruck extends Truck {
	private double temperature;
	public final double CARGO_CAPACITY = 800;
	private final double MAX_TEMPERATURE = 10;
	private final double MIN_TEMPERATURE = -20;
	private int extraItem;
	private double quantity;
	
	
	
	
	private HashMap <String,Integer> cargo;
	private Stock cargoItems = new Stock();
	
	public RefrigratedTruck() {
		this.cargo = new HashMap<String, Integer>();
		this.itemCount = 0;
	}

	public HashMap<String, Integer> getCargo() {
		return this.cargo;
	}
	@Override
	public boolean isCargoFull() {
		return (this.CARGO_CAPACITY == getQuantity());
		
	}
	@Override
	public double costCalculation(double temperature) {
		this.temperature = temperature;
		cost = 900 + (200 * Math.pow(0.7, (temperature / 5)));
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

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public void addItemImportManifest(String itemName, Integer quantity) {
		cargo.put(itemName, quantity);
		itemCount += quantity;
	}
	public void addItem(String itemName, Integer quantity) {
		cargoItems.addItem(itemName, quantity);
		itemCount += quantity;
		this.quantity+=quantity;
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
	public int getTruckNo() {
		return truckNum;
	}

	@Override
	public void setTruckNo(int truckNumber) {
		this.truckNum=truckNumber;
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
	public int getExtraItemInCargo() {
		return extraItem;
	}
	public void setExtraItemInCargo(int extraItem) {
		this.extraItem= extraItem;
	}

}
