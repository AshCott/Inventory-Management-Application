package program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
		double deliveryTruckCost = 0;

		// this what will keep track of item
		int extraItem = -1;
		Stock cargoItem = new Stock();
		// cargoItem = superMart.getInventory();
		ArrayList<Item> whatToBuy = new ArrayList<Item>();

		HashMap<String, Item> inventory = superMart.getInventory();
		for (String key : inventory.keySet()) {
			extraItem=-1;
			if (inventory.get(key).getCurrentInventory() <= inventory.get(key).getReorderPoint()) {
				// get the total item cost
				itemCost += inventory.get(key).getManufactureCost() * inventory.get(key).getReorderAmount();
				// do the truck calculation

				// while there is an extra item that come from the reorder amount which exceed
				// the truck capacity
				// check all the truck again
				while (extraItem != 0) {
					// does it have temperature
					if (inventory.get(key).hasTempreture()) {
						// it is a temperature item
						if (this.refTruck.isEmpty()) {
							// check if refTruck is indeed empty
							// make new one
							this.refTruck.add(new RefrigratedTruck());
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(inventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
								System.out.println(extraItem);
							} else {
								this.refTruck.get(this.refTruck.size() - 1).addItemOptimizeManifest(inventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size()-1).getExtraItemInCargo();
								System.out.println(extraItem);
							}
							continue;
						}
						if (!this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
							// truck isn't full add items
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(inventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							} else {
								this.refTruck.get(this.refTruck.size() - 1).addItemOptimizeManifest(inventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size()-1).getExtraItemInCargo();
							}
							continue;
						}

						else {
							// truck is indeed full. add new truck
							this.refTruck.add(new RefrigratedTruck());
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(inventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							} else {
								this.refTruck.get(this.refTruck.size() - 1).addItemOptimizeManifest(inventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size()-1).getExtraItemInCargo();
							}
							continue;
						}

					}
					// so item is a dryGoods
					else {
						// continue;
						if (this.refTruck.isEmpty()) {
							// no refrigerated truck in which to store dry goods
							// hence check the ordinary truck if exist or not
							if (this.ornTruck.isEmpty()) {
								this.ornTruck.add(new OrdinaryTruck());
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
											extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(inventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
								}
								continue;
							} else if (!this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
								// ordinary truck is not full so add some item to it
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
											extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(inventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
								}
								continue;
							} else {
								// ordinary truck is indeed full so make a new truck
								this.ornTruck.add(new OrdinaryTruck());
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
											extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(inventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
								}
								continue;
							}

						} else {
							// so refrigerated truck do exist, so check if its full or not
							if (this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
								// refrigerated truck is full so check if ordinary truck exist or not
								if (this.ornTruck.isEmpty()) {
									// ordinarytruck is indeed empty hence made a new one
									this.ornTruck.add(new OrdinaryTruck());
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
												extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(inventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
									}
									continue;
								} else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
									// ordinary truck exist but its full so make a new truck then add it
									this.ornTruck.add(new OrdinaryTruck());
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
												extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(inventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
									}
									continue;
								} else {
									// there's an ordinary truck that is not full so put the item
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1).addItemExtraItem(inventory.get(key),
												extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(inventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size()-1).getExtraItemInCargo();
									}
									continue;
								}
							} else {
								// refrigerated truck is not full some put some stuff into it
								if (extraItem > 0) {
									this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(inventory.get(key),
											extraItem);
									extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.refTruck.get(this.refTruck.size() - 1)
											.addItemOptimizeManifest(inventory.get(key));
									extraItem = this.refTruck.get(this.refTruck.size()-1).getExtraItemInCargo();
								}
								continue;
							}

						}

					}
				}

			} // end of restock first if
			else {
				// item is not understock no need to restock so continue to second item in the
				// list
				continue;
			}
		}
		System.out.println(this.refTruck.size());
		for(RefrigratedTruck something:refTruck) {
			System.out.println(something.getCargo());
		}
		for(OrdinaryTruck something: ornTruck) {
			System.out.println("this is orn truck");
			System.out.println(something.getCargo());
			System.out.println(something.getQuantity());
		}
		System.out.println(this.ornTruck.size());
		this.makeCSV("exportManifest");
		return itemCost + deliveryTruckCost;
	}
	
	public void makeCSV(String nameOfFile) throws IOException{
		BufferedWriter write = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/"+nameOfFile+".csv"));
		for(int index = 0;index<this.ornTruck.size();index++) {
			write.write(">Ordinary\n");
			for(String key: this.ornTruck.get(index).getCargo().keySet()) {
				String row = key+ "," + this.ornTruck.get(index).getCargo().get(key)+"\n";
				write.write(row);
			}
		}
		for(int index =0; index<this.refTruck.size();index++) {
			write.write(">Refrigerated\n");
			for(String key:this.refTruck.get(index).getCargo().keySet()) {
				String row = key+ "," + this.refTruck.get(index).getCargo().get(key)+"\n";
				write.write(row);
			}
		}
		
		write.close();
		
	}
	// public double generateManifest() throws IOException {
	// double itemCost = 0;
	// double trucksCost = 0;
	// double truckCurrentCapacity = 0;
	// HashMap<String, Item> inventory = superMart.getInventory();
	// boolean isBuySomething = false;
	//// for (String key : inventory.keySet()) {
	//// System.out.println(inventory.get(key).getName());
	//// }
	// for (String key : inventory.keySet()) {
	//// System.out.println("This is the item" + inventory.get(key).getName());
	// // get the reorder point add the inventory based on the reorder amount,
	// // check if the current inventory is less or equal with the reorder point
	// if (inventory.get(key).getCurrentInventory() <=
	// inventory.get(key).getReorderPoint()) {
	//
	// itemCost += inventory.get(key).getManufactureCost() *
	// inventory.get(key).getReorderAmount();
	// // check if the item in inventory have temperature or not
	// if (inventory.get(key).hasTempreture()) {
	// // check if refTruck is empty then make a new one
	// if (this.refTruck.isEmpty()) {
	// this.refTruck.add(new RefrigratedTruck());
	// //setting
	// refTruck.get(refTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//// inventory.get(key).setCurrentInventory(this.refTruck.get(this.refTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	// continue;
	//
	// }
	// // if there is a reftruck and it isn't full then add some stuff
	// else if (!this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
	// refTruck.get(refTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//
	// continue;
	// } else {
	// // truck is full add a new truck
	// this.refTruck.add(new RefrigratedTruck());
	// refTruck.get(refTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//
	// continue;
	// }
	//
	// }
	// // if item does not have temperature (dry goods)
	// else {
	// // check if refrigrated truck is not empty there
	// if (this.refTruck.isEmpty()) {
	//
	// // if it is empty then check if there is an ordinary truck
	//
	// if (this.ornTruck.isEmpty()) {
	// // refigrated truck is indeed empty and there is no ordinary truck so make
	// new
	// // one
	// this.ornTruck.add(new OrdinaryTruck());
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	//
	// continue;
	// } else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
	// // if the ordinary truck is not empty but is full then made a new one
	// this.ornTruck.add(new OrdinaryTruck());
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	//
	//
	// continue;
	// } else {
	// // refrigerated truck is full, but there is an existing ordinary truck so add
	// it
	// // to that
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	//
	// continue;
	// }
	// }
	//
	// else {
	//
	// // if there is a reftruck
	// if (this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
	//
	// // reftruck is full so make ordinary truck
	// if (this.ornTruck.isEmpty()) {
	// // refigrated truck is indeed full and there is no ordinary truck so make new
	// // one
	// this.ornTruck.add(new OrdinaryTruck());
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//
	// continue;
	// } else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
	// // if the ordinary truck is not empty but is full then made a new one
	// this.ornTruck.add(new OrdinaryTruck());
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//
	// continue;
	// } else {
	// // refrigerated truck is full, but there is an existing ordinary truck so add
	// it
	// // to that
	//// inventory.get(key).setCurrentInventory(this.ornTruck.get(this.ornTruck.size()
	// - 1)
	//// .addItemOptimizeManifest(inventory.get(key)));
	// ornTruck.get(ornTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	//
	// continue;
	// }
	// } else {
	// // if not then put the item in the refrigrated truck
	// refTruck.get(refTruck.size()-1).addItemOptimizeManifest(inventory.get(key));
	// if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
	// // System.out.println("Quantity for each truck");
	// //
	// System.out.println("reftruck"+refTruck.get(this.refTruck.size()-1).getQuantity());
	// //
	// System.out.println("ornTruck"+ornTruck.get(this.ornTruck.size()-1).getQuantity());
	// // System.out.println("quantity for each item in the inventory");
	// // System.out.println(inventory.get(key).getName()+" "+
	// // inventory.get(key).getCurrentInventory());
	// // System.out.println("Size of each truck");
	// // System.out.println("reftruck"+refTruck.size());
	// // System.out.println("ornTruck"+ornTruck.size());
	// }
	// continue;
	// }
	// }
	// }
	// // end first if for checking if more than current invent
	// } else {
	//
	// // item in the inventory for that key isn't under reorder point, no need to
	// // restock
	// continue;
	// } // end of restock logic
	// //$12,257.12
	// }
	//// if (!this.refTruck.isEmpty() && !this.ornTruck.isEmpty()) {
	//// System.out.println("Size of each truck");
	//// System.out.println("reftruck" + refTruck.size());
	//// System.out.println("ornTruck" + ornTruck.size());
	//// }
	//// trucksCost = calculateTruckCost();
	//// System.out.println(itemCost + trucksCost);
	//// System.out.println("THIS IS THE INVENTORY");
	//// for (String key : inventory.keySet()) {
	//// System.out.println(inventory.get(key).getName() + " " +
	// inventory.get(key).getCurrentInventory());
	//// }
	//// System.out.println("this is truck");
	//// System.out.println(refTruck);
	//// System.out.println(ornTruck);
	//// for (int index = 0; index < this.refTruck.size(); index++) {
	//// System.out.println(refTruck.get(index).getQuantity());
	//// }
	////
	//// for (int index = 0; index < this.ornTruck.size(); index++) {
	//// System.out.println(ornTruck.get(index).getQuantity());
	//// }
	//// System.out.println(itemCost);
	// return itemCost + trucksCost;
	// }

	// public double generateManifest() throws IOException {
	// double itemCost = 0;
	// double trucksCost = 0;
	// double truckCurrentCapacity = 0;
	// HashMap<String, Item> inventory = superMart.getInventory();
	// HashMap<Item,Integer> test= new HashMap<Item,Integer>();
	// ArrayList<Item> whatToBuy = new ArrayList<Item>();
	// boolean isBuySomething = false;
	// Stock a;
	// //pseudo]
	// ArrayList<List> list = new ArrayList<List>();
	// for(String key:inventory.keySet()) {
	// if(inventory.get(key).getCurrentInventory()<=inventory.get(key).getReorderPoint())
	// {
	// whatToBuy.add(inventory.get(key));
	// }else {
	// continue;
	// }
	// }
	//
	// whatToBuy.sort((Comparator.comparing(Item::getTemperature)));
	//// System.out.println(whatToBuy.get(0).getName());
	//// HashMap<Item,Integer> test2 = new HashMap<Item,Integer>();
	//// for(Item item : whatToBuy) {
	//// test2.put(item, item.getReorderAmount());
	//// }
	//// for(Entry<Item, Integer> test3:test2.entrySet()) {
	//// System.out.println(test3.getKey().getName());
	//// System.out.println(test3.getValue());
	//// System.out.println(test3.setValue(200));
	//// }
	//
	//
	//
	//
	//
	// //set the temp reorder amount
	// for (Item inCargo:whatToBuy) {
	// inCargo.setTempReorderAmount(inCargo.getReorderAmount());
	// }
	//
	//
	// int refTruckCounter =0;
	// int ornTruckCounter =0;
	// int refTruckMax = 800;
	//
	//
	//
	//
	// for(Item inCargo: whatToBuy) {
	// if(inCargo.hasTempreture()&&inCargo.getTempReorder()!=0) {
	// //check if reftruck empty
	//
	// refTruck.add(new RefrigratedTruck());
	// refTruckCounter++;
	// refTruck.get(refTruckCounter-1).addItem(inCargo.getName(),
	// inCargo.getTempReorder());
	//// if()
	// //inCargo.setTempReorderAmount(100);
	//
	// for(int i=0;i<whatToBuy.size();i++) {
	// if(refTruck.get(refTruckCounter-1).getQuantity() > 800) {
	// break;
	// }
	// refTruck.get(refTruckCounter-1).addItem(inCargo.getName(),
	// inCargo.getTempReorder());
	// }
	//
	// }
	//
	// }
	////
	// for (RefrigratedTruck inTruck:refTruck) {
	// HashMap<String,Integer> stockInTruck = inTruck.getCargo();
	// for(String item:stockInTruck.keySet()) {
	// System.out.println(item+" "+stockInTruck.get(item));
	// }
	// System.out.println(inTruck.getCargo());
	//// for()
	// }
	// return 0;
	// }

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
