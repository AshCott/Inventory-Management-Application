package program;

import java.util.ArrayList;

public abstract class Truck {
	protected double capacity;
	protected double cost;
	private String truckType;
	private ArrayList<Item> cargo;
	
	public double costCalculation(double tempQuant) {
		return cost;
	}
	public double cargoCaps() {
		return capacity;
	}
	public Truck() {
		
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

}
