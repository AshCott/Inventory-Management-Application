package program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author
 *
 */

public class Manifest {
	// collection of truck
	public ArrayList<RefrigratedTruck> refTruck = new ArrayList<>();
	public ArrayList<OrdinaryTruck> ornTruck = new ArrayList<>();
	Store superMart = Store.getInstance();

	// public ArrayList<Truck> truck = new ArrayList<>();
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

	public double generateManifest() throws IOException {
		double itemCost = 0;
		double trucksCost = 0;
		double truckCurrentCapacity = 0;
		HashMap<String, Item> inventory = superMart.getInventory();
		ArrayList<Item> whatToBuy = new ArrayList<Item>();
		boolean isBuySomething = false;
		for (String key : inventory.keySet()) {
			System.out.println(inventory.get(key).getName());
		}
		for (String key : inventory.keySet()) {
			System.out.println("This is the item" + inventory.get(key).getName());
			if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
				System.out.println("Quantity for each truck");
				System.out.println("reftruck" + refTruck.get(this.refTruck.size() - 1).getQuantity());
				System.out.println("ornTruck" + ornTruck.get(this.ornTruck.size() - 1).getQuantity());
				System.out.println("quantity for each item in the inventory");
				System.out.println(inventory.get(key).getName() + " " + inventory.get(key).getCurrentInventory());
				System.out.println("Size of each truck");
				System.out.println("reftruck" + refTruck.size());
				System.out.println("ornTruck" + ornTruck.size());
			}
			// get the reorder point add the inventory based on the reorder amount,
			// check if the current inventory is less than the reorder point
			if (inventory.get(key).getCurrentInventory() < inventory.get(key).getReorderPoint()) {
				itemCost += inventory.get(key).getManufactureCost() * inventory.get(key).getReorderAmount();
				// check if the item in inventory have temperature or not
				if (inventory.get(key).hasTempreture()) {
					// check if refTruck is empty then make a new one
					if (this.refTruck.isEmpty()) {
						this.refTruck.add(new RefrigratedTruck());
						inventory.get(key).setCurrentInventory(this.refTruck.get(this.refTruck.size() - 1)
								.addItemOptimizeManifest(inventory.get(key)));
						if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
							// System.out.println("Quantity for each truck");
							// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
							// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
							// System.out.println("quantity for each item in the inventory");
							// System.out.println(inventory.get(key).getName()+" "+
							// inventory.get(key).getCurrentInventory());
							// System.out.println("Size of each truck");
							// System.out.println("reftruck"+refTruck.size());
							// System.out.println("ornTruck"+ornTruck.size());

						}
						continue;

					}
					// if there is a reftruck and it isn't full then add some stuff
					else if (!this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
						inventory.get(key).setCurrentInventory(this.refTruck.get(this.refTruck.size() - 1)
								.addItemOptimizeManifest(inventory.get(key)));
						if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
							// System.out.println("Quantity for each truck");
							// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
							// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
							// System.out.println("quantity for each item in the inventory");
							// System.out.println(inventory.get(key).getName()+" "+
							// inventory.get(key).getCurrentInventory());
							// System.out.println("Size of each truck");
							// System.out.println("reftruck"+refTruck.size());
							// System.out.println("ornTruck"+ornTruck.size());

						}
						continue;
					} else {
						// truck is full add a new truck
						this.refTruck.add(new RefrigratedTruck());
						inventory.get(key).setCurrentInventory(this.refTruck.get(this.refTruck.size() - 1)
								.addItemOptimizeManifest(inventory.get(key)));
						if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
							// System.out.println("Quantity for each truck");
							// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
							// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
							// System.out.println("quantity for each item in the inventory");
							// System.out.println(inventory.get(key).getName()+" "+
							// inventory.get(key).getCurrentInventory());
							// System.out.println("Size of each truck");
							// System.out.println("reftruck"+refTruck.size());
							// System.out.println("ornTruck"+ornTruck.size());

						}
						continue;
					}

				}
				// if item does not have temperature (dry goods)
				else {
					// check if refrigrated truck is not empty there
					if (this.refTruck.isEmpty()) {

						// if it is empty then check if there is an ordinary truck

						if (this.ornTruck.isEmpty()) {
							// refigrated truck is indeed full and there is no ordinary truck so make new
							// one
							this.ornTruck.add(new OrdinaryTruck());
							inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
									.addItemOptimizeManifest(inventory.get(key)));
							if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
								// System.out.println("Quantity for each truck");
								// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
								// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
								// System.out.println("quantity for each item in the inventory");
								// System.out.println(inventory.get(key).getName()+" "+
								// inventory.get(key).getCurrentInventory());
								// System.out.println("Size of each truck");
								// System.out.println("reftruck"+refTruck.size());
								// System.out.println("ornTruck"+ornTruck.size());

							}
							continue;
						} else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
							// if the ordinary truck is not empty but is full then made a new one
							this.ornTruck.add(new OrdinaryTruck());
							inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
									.addItemOptimizeManifest(inventory.get(key)));
							if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
								// System.out.println("Quantity for each truck");
								// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
								// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
								// System.out.println("quantity for each item in the inventory");
								// System.out.println(inventory.get(key).getName()+" "+
								// inventory.get(key).getCurrentInventory());
								// System.out.println("Size of each truck");
								// System.out.println("reftruck"+refTruck.size());
								// System.out.println("ornTruck"+ornTruck.size());
							}
							continue;
						} else {
							// refrigerated truck is full, but there is an existing ordinary truck so add it
							// to that
							inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
									.addItemOptimizeManifest(inventory.get(key)));
							if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
								// System.out.println("Quantity for each truck");
								// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
								// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
								// System.out.println("quantity for each item in the inventory");
								// System.out.println(inventory.get(key).getName()+" "+
								// inventory.get(key).getCurrentInventory());
								// System.out.println("Size of each truck");
								// System.out.println("reftruck"+refTruck.size());
								// System.out.println("ornTruck"+ornTruck.size());

							}
							continue;
						}
					}

					else {

						// if there is a reftruck
						if (this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {

							// reftruck is full so make ordinary truck
							if (this.ornTruck.isEmpty()) {
								// refigrated truck is indeed full and there is no ordinary truck so make new
								// one
								this.ornTruck.add(new OrdinaryTruck());
								inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
										.addItemOptimizeManifest(inventory.get(key)));
								if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
									// System.out.println("Quantity for each truck");
									// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
									// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
									// System.out.println("quantity for each item in the inventory");
									// System.out.println(inventory.get(key).getName()+" "+
									// inventory.get(key).getCurrentInventory());
									// System.out.println("Size of each truck");
									// System.out.println("reftruck"+refTruck.size());
									// System.out.println("ornTruck"+ornTruck.size());

								}
								continue;
							} else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
								// if the ordinary truck is not empty but is full then made a new one
								this.ornTruck.add(new OrdinaryTruck());
								inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
										.addItemOptimizeManifest(inventory.get(key)));
								if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
									// System.out.println("Quantity for each truck");
									// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
									// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
									// System.out.println("quantity for each item in the inventory");
									// System.out.println(inventory.get(key).getName()+" "+
									// inventory.get(key).getCurrentInventory());
									// System.out.println("Size of each truck");
									// System.out.println("reftruck"+refTruck.size());
									// System.out.println("ornTruck"+ornTruck.size());
								}
								continue;
							} else {
								// refrigerated truck is full, but there is an existing ordinary truck so add it
								// to that
								inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size() - 1)
										.addItemOptimizeManifest(inventory.get(key)));
								if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
									// System.out.println("Quantity for each truck");
									// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
									// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
									// System.out.println("quantity for each item in the inventory");
									// System.out.println(inventory.get(key).getName()+" "+
									// inventory.get(key).getCurrentInventory());
									// System.out.println("Size of each truck");
									// System.out.println("reftruck"+refTruck.size());
									// System.out.println("ornTruck"+ornTruck.size());
								}
								continue;
							}
						} else {
							// if not then put the item in the refrigrated truck
							inventory.get(key).setCurrentInventory(this.refTruck.get(this.refTruck.size() - 1)
									.addItemOptimizeManifest(inventory.get(key)));
							if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
								// System.out.println("Quantity for each truck");
								// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
								// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
								// System.out.println("quantity for each item in the inventory");
								// System.out.println(inventory.get(key).getName()+" "+
								// inventory.get(key).getCurrentInventory());
								// System.out.println("Size of each truck");
								// System.out.println("reftruck"+refTruck.size());
								// System.out.println("ornTruck"+ornTruck.size());
							}
							continue;
						}
					}
				}
				// end first if for checking if more than current invent
			} else {

				// item in the inventory for that key isn't under reorder point, no need to
				// restock
				continue;
			} // end of restock logic

		}
		if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
			System.out.println("Size of each truck");
			System.out.println("reftruck" + refTruck.size());
			System.out.println("ornTruck" + ornTruck.size());
		}
		trucksCost = calculateTruckCost();
		System.out.println(itemCost + trucksCost);
		System.out.println("THIS IS THE INVENTORY");
		for (String key : inventory.keySet()) {
			System.out.println(inventory.get(key).getName() + " " + inventory.get(key).getCurrentInventory());
		}
		System.out.println("this is truck");
		for (int index = 0; index < this.refTruck.size(); index++) {
			System.out.println(refTruck.get(index).cargo);
		}

		for (int index = 0; index < this.ornTruck.size(); index++) {
			System.out.println(ornTruck.get(index).cargo);
		}

		return itemCost + trucksCost;
	}

	public double calculateTruckCost() {
		double cost = 0;
		for (int index = 0; index < this.refTruck.size(); index++) {
			cost += this.refTruck.get(index).costCalculation(this.refTruck.get(index).getTemperature());
		}

		for (int index = 0; index < this.ornTruck.size(); index++) {
			cost += this.ornTruck.get(index).costCalculation(this.ornTruck.get(index).getQuantity());
		}
		return cost;
	}
}
