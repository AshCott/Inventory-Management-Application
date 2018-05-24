package program;

import java.lang.Math.*;
import java.util.HashMap;

public class RefrigratedTruck extends Truck {
	private double temperature;
	private final double CARGO_CAPACITY = 800;
	private final double MAX_TEMPERATURE = 10;
	private final double MIN_TEMPERATURE = -20;
	private double quantity;

	public RefrigratedTruck() {
		this.cargo = new HashMap<String, Integer>();
		this.itemCount = 0;
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
		int leftOverCapacity = (int) ((cargoItem.getReorderAmount()+this.quantity)-this.CARGO_CAPACITY);
		if(leftOverCapacity<=0) {
			//if left over capacity is negative
			//300, 200, 800
			this.quantity+=cargoItem.getReorderAmount();
			this.cargo.put(cargoItem.getName(), cargoItem.getReorderAmount());
			System.out.println("this is left over capacity"+leftOverCapacity);
			return leftOverCapacity*-1;
			
		}else if(leftOverCapacity == cargoItem.getReorderAmount()) {
			System.out.println("this is left over capacity"+leftOverCapacity);
			return leftOverCapacity;
		}else {
			
			cargoItem.setCurrentInventory(cargoItem.getReorderAmount()-leftOverCapacity);
			this.quantity+=cargoItem.getCurrentInventory();
			this.cargo.put(cargoItem.getName(), cargoItem.getCurrentInventory());
			System.out.println("this is left over capacity"+leftOverCapacity);
			return leftOverCapacity;
		}

	}

}
