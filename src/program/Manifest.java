package program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This classes is manifest where it represent a collection of trucks it will be
 * used to generate manifest, and building CSV needed for importing it later on
 * 
 * @author Radhimas Djan
 * @author
 */

public class Manifest {

	public ArrayList<RefrigratedTruck> refTruck = new ArrayList<>();
	public ArrayList<OrdinaryTruck> ornTruck = new ArrayList<>();
	Store superMart = Store.getInstance();

	/**
	 * This is the constructor for the manifest where it makes a new
	 * RefrigratedTruck and a OrdinaryTruck in an ArrayList where it will contains
	 * an item
	 */
	public Manifest() {
		this.ornTruck = new ArrayList<OrdinaryTruck>();
		this.refTruck = new ArrayList<RefrigratedTruck>();
	}

	/**
	 * The saveOrnTruck is used when adding a new ordinary truck to the arraylist
	 * 
	 * @param the
	 *            ordinary truck that want to be saved
	 * 
	 */
	public void saveOrnTruck(OrdinaryTruck truck) {
		ornTruck.add(truck);
	}

	/**
	 * The saveRefTruck is used when adding a new ordinary truck to the arraylist
	 * 
	 * @param the
	 *            refrigerated truck that want to be saved
	 * 
	 */
	public void saveRefTruck(RefrigratedTruck truck) {
		refTruck.add(truck);
	}

	/**
	 * The generate manifest is a method that export the manifest to csv based on
	 * the amount of inventory the way the method work is that it will loop through
	 * an inventory and check if the item in the inventory is less than reorder
	 * point if yes then reorder if not continue to the next item
	 * 
	 * @return The total price of the exporting manifest item and the truck
	 * @throws IOException
	 *             throws an IO exception if there's something wrong in writing the
	 *             csv
	 * 
	 */
	public double generateManifest() throws IOException {
		double itemCost = 0;
		double deliveryTruckCost = 0;

		// this what will keep track of item
		int extraItem = -1;
		HashMap<String, Item> inventory = superMart.getInventory();
		ArrayList<Item> whatToBuy = new ArrayList<Item>();

		// loop through inventory to get the
		for (String key : inventory.keySet()) {
			if (inventory.get(key).getCurrentInventory() <= inventory.get(key).getReorderPoint()) {
				whatToBuy.add(inventory.get(key));
			} else {
				continue;
			}
		}

		// sort the arraylist
		whatToBuy.sort((Comparator.comparing(Item::getTemperature)));
		// make linked hashmap so that it is possible to put stuff based on the
		// insertion order
		// by using the arraylist from the whatToBuy it will be a sorted map\

		LinkedHashMap<String, Item> sortedInventory = new LinkedHashMap<String, Item>();

		for (Item each : whatToBuy) {
			sortedInventory.put(each.getName(), each);
		}

		// loop through the sorted inventory
		for (String key : sortedInventory.keySet()) {

			// made extra item = -1 just for a flag so that it will still get into the while
			// loop
			extraItem = -1;

			// check if the item is indeed less than the reorder point
			if (sortedInventory.get(key).getCurrentInventory() <= sortedInventory.get(key).getReorderPoint()) {
				// get the total item cost
				itemCost += sortedInventory.get(key).getManufactureCost() * sortedInventory.get(key).getReorderAmount();

				// the while loop will check if there's a leftover item that exceed from the
				// truck capacity
				// if there is check all the truck again
				while (extraItem != 0) {
					// does item have temperature
					if (sortedInventory.get(key).hasTempreture()) {
						// it is a temperature item
						if (this.refTruck.isEmpty()) {
							// check if refTruck is indeed empty
							// make new one
							this.refTruck.add(new RefrigratedTruck());
							// check if there's a leftover item or not
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(sortedInventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();

							} else {
								this.refTruck.get(this.refTruck.size() - 1)
										.addItemOptimizeManifest(sortedInventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();

							}
							continue;
						}
						// truck isn't full add items
						if (!this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
							// check if there's a leftover item or not
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(sortedInventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							} else {
								this.refTruck.get(this.refTruck.size() - 1)
										.addItemOptimizeManifest(sortedInventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							}
							continue;
						}

						else {
							// truck is indeed full. add new truck
							this.refTruck.add(new RefrigratedTruck());
							// check if there's a leftover item or not
							if (extraItem > 0) {
								this.refTruck.get(this.refTruck.size() - 1).addItemExtraItem(sortedInventory.get(key),
										extraItem);
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							} else {
								this.refTruck.get(this.refTruck.size() - 1)
										.addItemOptimizeManifest(sortedInventory.get(key));
								extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
							}
							continue;
						}

					}
					// so item is a dryGoods
					else {
						// check if refrigerated truck is empty
						if (this.refTruck.isEmpty()) {
							// no refrigerated truck truck in which to store dry goods
							// hence check the ordinary truck if exist or not
							if (this.ornTruck.isEmpty()) {
								// orn truck is indeed empty so make a new one
								this.ornTruck.add(new OrdinaryTruck());
								// check if there's a left over item
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemExtraItem(sortedInventory.get(key), extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(sortedInventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								}
								continue;
							} // ordinary truck is not full so add some item to it
							else if (!this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {

								// check if there's a left over item
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemExtraItem(sortedInventory.get(key), extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(sortedInventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								}
								continue;
							} else {
								// ordinary truck is indeed full so make a new truck
								this.ornTruck.add(new OrdinaryTruck());
								// check if there's a left over item
								if (extraItem > 0) {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemExtraItem(sortedInventory.get(key), extraItem);
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.ornTruck.get(this.ornTruck.size() - 1)
											.addItemOptimizeManifest(sortedInventory.get(key));
									extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
								}
								continue;
							}

						} else {
							// so refrigerated truck does exist, so check if its full or not
							if (this.refTruck.get(this.refTruck.size() - 1).isCargoFull()) {
								// refrigerated truck is full so check if ordinary truck exist or not
								if (this.ornTruck.isEmpty()) {
									// ordinarytruck is indeed empty hence made a new one
									this.ornTruck.add(new OrdinaryTruck());
									// check if there's a left over item
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemExtraItem(sortedInventory.get(key), extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(sortedInventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									}
									continue;
								} else if (this.ornTruck.get(this.ornTruck.size() - 1).isCargoFull()) {
									// ordinary truck exist but its full so make a new truck then add it
									this.ornTruck.add(new OrdinaryTruck());
									// check if there's a left over item
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemExtraItem(sortedInventory.get(key), extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(sortedInventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									}
									continue;
								}
								// there's an ordinary truck that is not full so put the item
								else {
									// check if there's a left over item
									if (extraItem > 0) {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemExtraItem(sortedInventory.get(key), extraItem);
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									} else {
										this.ornTruck.get(this.ornTruck.size() - 1)
												.addItemOptimizeManifest(sortedInventory.get(key));
										extraItem = this.ornTruck.get(this.ornTruck.size() - 1).getExtraItemInCargo();
									}
									continue;
								}
							} else {
								// refrigerated truck is not full some put some stuff into it

								// check if there is a left over item
								if (extraItem > 0) {
									this.refTruck.get(this.refTruck.size() - 1)
											.addItemExtraItem(sortedInventory.get(key), extraItem);
									extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
								} else {
									this.refTruck.get(this.refTruck.size() - 1)
											.addItemOptimizeManifest(sortedInventory.get(key));
									extraItem = this.refTruck.get(this.refTruck.size() - 1).getExtraItemInCargo();
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

		this.makeCSV("exportManifest");
		deliveryTruckCost = calculateTruckCost();
		return itemCost + deliveryTruckCost;
	}

	/**
	 * This method will make a new csv based on the inside of the ordinary and
	 * refrigerated truck it will loop through the truck and see what inside of the
	 * cargo then write it to csv
	 * 
	 * @param nameOfFile
	 * @throws IOException
	 */
	public void makeCSV(String nameOfFile) throws IOException {
		BufferedWriter write = new BufferedWriter(
				new FileWriter(System.getProperty("user.dir") + "/" + nameOfFile + ".csv"));
		for (int index = 0; index < this.ornTruck.size(); index++) {
			write.write(">Ordinary\n");
			for (String key : this.ornTruck.get(index).getCargo().keySet()) {
				String row = key + "," + this.ornTruck.get(index).getCargo().get(key) + "\n";
				write.write(row);
			}
		}
		for (int index = 0; index < this.refTruck.size(); index++) {
			write.write(">Refrigerated\n");
			for (String key : this.refTruck.get(index).getCargo().keySet()) {
				String row = key + "," + this.refTruck.get(index).getCargo().get(key) + "\n";
				write.write(row);
			}
		}

		write.close();

	}

	/**
	 * This method will return the truck calculation
	 * 
	 * @return
	 */
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
