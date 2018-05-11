package program;

public class OrdinaryTruck extends Truck {
	double quantity = 1000;
	
	public OrdinaryTruck() {
		
	}
	@Override
	public double costCalculation(double quantity) {
		this.quantity = quantity;
		cost = 750+ (0.25*quantity);
		
		return cost;
	}
	@Override
	public double cargoCaps () {
		return 1000;
	}
}
