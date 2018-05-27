package program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
/**
 * This classes is manifest where it represent a collection of trucks it will be
 * used to generate manifest, and building CSV needed for importing it later on
 * 
 * @author Radhimas Djan
 */

public class Manifest {

	public ArrayList<RefrigratedTruck> refTruck = new ArrayList<>();
	public ArrayList<OrdinaryTruck> ornTruck = new ArrayList<>();
	Store superMart = Store.getInstance();
	Stock inventory = new Stock();

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
	 * point if yes then reorder if not continue to the next item, then it will put
	 * it in to the truck based on the logic of if refrigrated truck or ordinary
	 * truck is empty make the truck, where if the item is dry goods make ordinary
	 * if not make refrigerated afterward check if there's leftover in either truck
	 * then it optimize in looking at truck that already exist and filling
	 * refrigerated truck take more priority than ordinary truck to fill in,
	 * afterward generate a csv to be used
	 * 
	 * 
	 * @throws IOException
	 *             throws an IO exception if there's something wrong in writing the
	 *             csv
	 * @throws DeliveryException
	 * 
	 */
	public void generateManifest() throws IOException, DeliveryException {

		// this what will keep track of item
		// initialize as a -1 so that it will get in the while loop which is crucial
		int extraItem = -1;
		// counter to keep track of the loop of checking if item is reordered
		int counter = 0;
		HashMap<String, Item> inventory = superMart.getInventory();
		ArrayList<Item> whatToBuy = new ArrayList<Item>();

		// loop through inventory to get the item that have less than reorderpoint
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

		for (String key : sortedInventory.keySet()) {
			if (sortedInventory.get(key).getCurrentInventory() <= sortedInventory.get(key).getReorderPoint()) {

				continue;
			} else {
				counter++;
				continue;
			}
		}
		if (counter == sortedInventory.size()) {
			throw new DeliveryException("No item need to be reordered");
		}
		// loop through the sorted inventory
		for (String key : sortedInventory.keySet()) {

			// made extra item = -1 just for a flag so that it will still get into the while
			// loop
			extraItem = -1;

			// check if the item is indeed less than the reorder point
			if (sortedInventory.get(key).getCurrentInventory() <= sortedInventory.get(key).getReorderPoint()) {

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
	}

	/**
	 * 
	 * The import manifest is a method that will import a csv manifest file that
	 * were made either by exporting manifest or by other method, the way the
	 * program work is that first it read the csv file then check the manifest if
	 * the item inside the manifest do exist in inventory then calculate the capital
	 * based on the amount of quantity of item,manufacture cost, truck and
	 * temperature
	 * 
	 * @param file
	 *            The file name of the manifest that going to be imported
	 * @throws IOException
	 * @throws CSVFormatException
	 */
	public void importingManifest(String file) throws IOException, CSVFormatException, DeliveryException {
		IOCSV importer = new IOCSV();
		String itemName;
		ArrayList<List> manifestFile = null;
		inventory.getInventory();
		// see if the manifest file is indeed in correct format if not throw exception
		try {
			manifestFile = importer.readCSVFile(file, "manifest");
		} catch (CSVFormatException e) {
			throw e;
		}
		Boolean isTruckType;
		Boolean isRefrigerated = false;
		Boolean isOrdinary = false;
		double totalProductBoughtOrdinary = 0.0;
		double totalProductBoughtRefrigrated = 0.0;
		double totalPriceInOrdinary = 0.0;
		double totalPriceInRefrigrated = 0.0;
		RefrigratedTruck refTruck = null;
		OrdinaryTruck ornTruck = null;
		Manifest manifest = new Manifest();
		double coldest = Double.MAX_VALUE;

		// check the manifest file if there is an item that doesn't exist in the
		// inventory first if yes throw a delivery exception
		for (List line : manifestFile) {
			String content = (String) line.get(0);
			isTruckType = content.charAt(0) == '>';
			if (isTruckType) {

			} else {
				itemName = (String) content;
				if (!inventory.itemExists(itemName)) {
					throw new DeliveryException("Item doesnt exist in inventory \n" + itemName);
				}
			}
		}

		// loop the manifestfile line by line
		for (List line : manifestFile) {
			String content = (String) line.get(0);
			// check if the line start with '>' if it does then it's the line for the truck
			// type
			isTruckType = content.charAt(0) == '>';

			if (isTruckType) {
				// check if the truck type is refrigerated or ordinary
				if (content.equals(">Refrigerated")) {
					// if refrigerated make a new refrigerated and add it to the truck
					refTruck = new RefrigratedTruck();
					manifest.saveRefTruck(refTruck);
					coldest = Double.MAX_VALUE;
					isRefrigerated = true;
					isOrdinary = false;
					totalPriceInRefrigrated = 0.0;
				} else if (content.equals(">Ordinary")) {
					// else if ordinary then make a new ordinary and add it to the truck
					ornTruck = new OrdinaryTruck();
					manifest.saveOrnTruck(ornTruck);
					isOrdinary = true;
					isRefrigerated = false;
					totalPriceInOrdinary = 0.0;
					totalProductBoughtOrdinary = 0.0;
				}
			} else {
				// if content is not about the truck type
				Integer numbBought = Integer.parseInt((String) line.get(1));
				// check if it's ordinary truck
				if (isOrdinary) {
					// if yes made a new one and increase the quantity of the truck
					ornTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					totalProductBoughtOrdinary += numbBought;
					ornTruck.setQuantity(totalProductBoughtOrdinary);
					// check if item does exist in the item properties
					if (temp != null) {
						// update the current inventory
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore += numbBought;
						temp.setCurrentInventory(currentInventoryInStore);
						// get the total prices of the product in the turck
						totalPriceInOrdinary += temp.getManufactureCost() * numbBought;
						ornTruck.setTotalPriceInTruck(totalPriceInOrdinary);
					} else {
						// so item is null throw exception
						throw new DeliveryException("Cannot deliver an item that is not in item properties");
					}
				} else {
					refTruck.addItemImportManifest(content, numbBought);
					itemName = (String) content;
					Item temp = inventory.getItem(itemName);
					// set the temperature of the to be as cold as the coldest item
					if (temp.hasTempreture()) {
						if (coldest > temp.getTemperature()) {
							coldest = temp.getTemperature();
							refTruck.setTemperature(coldest);
						}
					}

					// check if item does exist in the inventory
					if (temp != null) {
						int currentInventoryInStore = temp.getCurrentInventory();
						currentInventoryInStore += numbBought;
						// update the current inventory
						temp.setCurrentInventory(currentInventoryInStore);
						// get the total prices of the product in the turck
						totalPriceInRefrigrated += temp.getManufactureCost() * numbBought;
						refTruck.setTotalPriceInTruck(totalPriceInRefrigrated);
					}
				}
			}

		} // end loop
		for (OrdinaryTruck each : manifest.ornTruck) {
			// calculate each ordinary truck
			superMart.capital -= each.costCalculation(each.getQuantity());
			// calculate each item in ordinary truck
			superMart.capital -= each.getTotalPriceInTruck();
		}
		for (RefrigratedTruck each : manifest.refTruck) {
			// calculate each refrigerated truck
			superMart.capital -= each.costCalculation(each.getTemperature());
			// calculating each item
			superMart.capital -= each.getTotalPriceInTruck();
		}

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

}
