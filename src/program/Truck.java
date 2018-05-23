package program;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Truck {
	protected double insideTruck;
	protected double cost;
	protected Integer itemCount;
	protected HashMap<String, Integer> cargo;
	protected double totalPrice;
	public abstract double costCalculation(double tempQuant);
	public abstract void addItemImportManifest(String itemName, Integer quantity);
	public abstract double addItemOptimizeManifest(Item item);
	public abstract boolean isCargoFull();
	public abstract void setQuantity(double quantity);
	public abstract double getQuantity();
	public abstract void setTotalPriceInTruck(double totalPrice);
	public abstract double getTotalPriceInTruck();
	
}
