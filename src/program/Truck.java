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
	 * @return
	 */
	public abstract double costCalculation(double tempQuant);

	/**
	 * 
	 * @param itemName
	 * @param quantity
	 */
	public abstract void addItemImportManifest(String itemName, Integer quantity);

	/**
	 * 
	 * @param item
	 * @return
	 */
	public abstract int addItemOptimizeManifest(Item item);

	/**
	 * 
	 * @return
	 */
	public abstract boolean isCargoFull();

	/**
	 * 
	 * @param quantity
	 */
	public abstract void setQuantity(double quantity);

	/**
	 * 
	 * @return
	 */
	public abstract double getQuantity();

	/**
	 * 
	 * @param totalPrice
	 */
	public abstract void setTotalPriceInTruck(double totalPrice);

	/**
	 * 
	 * @return
	 */
	public abstract double getTotalPriceInTruck();

	/**
	 * 
	 * @return
	 */
	public abstract int getExtraItemInCargo();

	/**
	 * 
	 * @param extraItem
	 */
	public abstract void setExtraItemInCargo(int extraItem);

	/**
	 * 
	 * @param cargoItem
	 * @param extraCargo
	 * @return
	 */
	public abstract int addItemExtraItem(Item cargoItem, int extraCargo);

	/**
	 * 
	 * @return
	 */
	public abstract HashMap<String, Integer> getCargo();

}
