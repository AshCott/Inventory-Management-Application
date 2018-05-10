package program;
import java.lang.Math.*;
public class RefrigratedTruck extends Truck {
	double temperature;
	
	public RefrigratedTruck () {

	}
	@Override
	public double costCalculation(double temperature) {
		this.temperature = temperature;
		cost = 900+(200*Math.pow(0.7, (temperature/5)));
		return cost;
		
	}
	@Override
	public double cargoCaps () {
		capacity = 800;
		return capacity;
	}
	
	public void getTemperature() {
		
	}

}
