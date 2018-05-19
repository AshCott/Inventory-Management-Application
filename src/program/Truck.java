package program;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Truck {
	protected double capacity;
	protected double cost;
	protected Integer itemCount;
	protected HashMap<String, Integer> items;
	protected double totalPrice;
	public abstract double costCalculation(double tempQuant);
	public abstract void addItem(String itemName, Integer quantity);
	public abstract boolean isCargoFull();
	public abstract void setQuantity(double quantity);
	public abstract double getQuantity();
	public abstract void setTotalPriceInTruck(double totalPrice);
	public abstract double getTotalPriceInTruck();
}
