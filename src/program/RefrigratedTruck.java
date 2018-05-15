package program;
import java.lang.Math.*;
public class RefrigratedTruck extends Truck {
	double temperature;
	
	public RefrigratedTruck () {
		super();
	}
	@Override
	public double costCalculation(double temperature) {
		this.temperature = temperature;
		cost = Math.round(900+(200*Math.pow(0.7, (temperature/5))));
		return cost;
	}
	@Override
	public double cargoCaps () {
		capacity = 800;
		return capacity;
	}
	
	public double getTemperature() {
		return temperature;
	}
	@Override
	public void addItem(String itemName, Integer quantity) {
		items.put(itemName, quantity);
		itemCount += quantity;
 	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	

}
