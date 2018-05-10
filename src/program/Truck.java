package program;

import java.util.ArrayList;

public abstract class Truck {
	protected double capacity;
	protected double cost;
	private ArrayList<Item> cargo;
	
	public double costCalculation(double tempQuant) {
		return cost;
	}
	public double cargoCaps() {
		return capacity;
	}
	public Truck() {
		
	}

}
