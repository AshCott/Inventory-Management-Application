package program;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class provides a skeletal implementation for the truck to minimize
 * effort require to implement it in the refrigerated truck and ordinary truck
 * 
 * @author Radhimas Djan
 * @author
 *
 */
public abstract class Truck {

	protected double cost;
	protected Integer itemCount;
	protected HashMap<String, Integer> cargo;
	protected double totalPrice;
	protected int truckNum;

	/**
	 * 
	 * @param tempQuant
	 * @return it will return the cost calculation of one truck either refrigerated
	 *         or ordinary truck
	 */
	public abstract double costCalculation(double tempQuant);

	/**
	 * This method will be used when adding an item in a import manifest the way it
	 * work, it will scan the csv file then based on that it will check if its
	 * ordinary or refrigrated then add a new truck based on what's written in the
	 * quantity,more detail are inside of the method where
	 * 
	 * @param itemName
	 * @param quantity
	 *            the quantity here is the item
	 */
	public abstract void addItemImportManifest(String itemName, Integer quantity);

	/**
	 * This method will be used when adding an item to a truck in generate manifest
	 * where it will add item to a truck based on which truck is empty, and it
	 * prioritize a refrigrated truck if there's a leftover item. More detail are
	 * inside of the method
	 * 
	 * @param item
	 *            the item that going to be filled to the cargo
	 * @return
	 */
	public abstract int addItemOptimizeManifest(Item item);

	/**
	 * This method will be used if there is an item that doesn't fit the cargo (ergo
	 * why it named extra item), it have the sameway with addItemOptimizeManifest
	 * but rather than using a reorder amount to put it to a truck it uses the extra
	 * item because it is the left over from the reorder amount. More detail are
	 * inside of the method
	 * 
	 * @param cargoItem
	 *            the item that going to be filled to the cargo
	 * @param extraCargo
	 *            indicate how many item is more than the capacity of a truck
	 * @return
	 */
	public abstract int addItemExtraItem(Item cargoItem, int extraCargo);

	/**
	 * check if a cargo is full by checking if it reached their maximum capacity
	 * where in refrigrated truck is 800 while in the ordinary truck is 1000
	 * 
	 * @return true if, full false if not
	 */
	public abstract boolean isCargoFull();

	/**
	 * set quantity for a truck
	 * 
	 * @param quantity
	 */
	public abstract void setQuantity(double quantity);

	/**
	 * get the quantity of the truck
	 * 
	 * @return
	 */
	public abstract double getQuantity();

	/**
	 * set the total price of a truck which is used when importing manifest
	 * 
	 * @param totalPrice
	 */
	public abstract void setTotalPriceInTruck(double totalPrice);

	/**
	 * get the total price of a truck
	 * 
	 * @return
	 */
	public abstract double getTotalPriceInTruck();

	/**
	 * get extra item in the truck
	 * 
	 * @return
	 */
	public abstract int getExtraItemInCargo();

	/**
	 * set the extra item where extra items are an item that get leftover when
	 * adding an item in optimize manifest because its more than the size of a truck
	 * 
	 * @param extraItem
	 */
	public abstract void setExtraItemInCargo(int extraItem);

	/**
	 * get the cargo inside of the truck
	 * 
	 * @return
	 */
	public abstract HashMap<String, Integer> getCargo();

}
