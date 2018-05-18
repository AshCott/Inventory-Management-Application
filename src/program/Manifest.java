package program;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author 
 *
 */

public class Manifest {
	//collection of truck
	public ArrayList<RefrigratedTruck> refTruck = new ArrayList<>(); 
	public ArrayList<OrdinaryTruck> ornTruck = new ArrayList<>();
//	public ArrayList<Truck> truck = new ArrayList<>();
	public Manifest() {
		this.ornTruck = new ArrayList<OrdinaryTruck>();
		this.refTruck = new ArrayList<RefrigratedTruck>();
		
	}
	public void saveOrnTruck(OrdinaryTruck truck) {
		ornTruck.add(truck);
	}
	public void saveRefTruck(RefrigratedTruck truck) {
		refTruck.add(truck);	
	}
	
}
