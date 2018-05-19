package program;
import java.lang.Math.*;
import java.util.HashMap;
public class RefrigratedTruck extends Truck {
	private double temperature;
	private final double CARGO_CAPACITY= 800;
	private final double MAX_TEMPERATURE = 10;
	private final double MIN_TEMPERATURE = -20;
	private double quantity;
	
	public RefrigratedTruck () {
		this.items = new HashMap<String, Integer>();
		this.itemCount =0;
	}
	@Override
	public boolean isCargoFull() {
		return(this.CARGO_CAPACITY==this.quantity);
	}
	@Override
	public double costCalculation(double temperature) {
		this.temperature = temperature;
		cost = 900+(200*Math.pow(0.7, (temperature/5)));
		return cost;
	}
	@Override
	public double getQuantity() {
		return quantity;
	}
	@Override
	public void setQuantity(double quantity) {
		this.quantity=quantity;
		
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	@Override
	public void addItem(String itemName, Integer quantity) {
		items.put(itemName, quantity);
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


}
