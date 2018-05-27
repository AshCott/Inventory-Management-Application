package program;

/**
 * This is the item class where it represent the item properties in the
 * supermart store
 * 
 * @author Ashley Cottrell
 *
 */
public class Item {

	private String Name;
	private double ManufactureCost;
	private double SellPrice;
	private int ReorderPoint;
	private int ReorderAmount;

	// make the temperature to be nan so that if its a dry goods it doesn't' have a
	// temperature and make it possible to sort later on in manifest classes
	private double Temperature = Double.NaN;
	private int CurrentInventory;
	private boolean hasTemp;

	/**
	 * Create a new Refrigerated Item
	 * 
	 * @param name
	 *            The name of the Item
	 * @param ManufactureCost
	 *            The Manufacture cost of the item
	 * @param sellPrice
	 *            The Sell price of hte item
	 * @param reorderAmount
	 *            the Re-Order Amount of the item
	 * @param reorderPoint
	 *            The Re-Roder Amount of the item
	 * @param temperature
	 *            The Tempreture of an item
	 */
	public Item(String name, double ManufactureCost, double sellPrice, int reorderPoint, int reorderAmount,
			double temperature) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.Temperature = temperature;
		this.hasTemp = true;
		this.CurrentInventory = 0;
	}

	/**
	 * Create a new Un-refrigerated Item
	 * 
	 * @param name
	 *            The name of the Item
	 * @param ManufactureCost
	 *            The Manufacture cost of the item
	 * @param sellPrice
	 *            The Sell price of hte item
	 * @param reorderAmount
	 *            the Re-Order Amount of the item
	 * @param reorderPoint
	 *            The Re-Roder Amount of the item
	 */
	public Item(String name, double ManufactureCost, double sellPrice, int reorderPoint, int reorderAmount) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.CurrentInventory = 0;
		this.hasTemp = false;
	}

	/**
	 * Returns the Items Name
	 * 
	 * @return The name of the item as a String
	 */
	public String getName() {
		return this.Name;
	}

	/**
	 * Returns the items manufacture cost
	 * 
	 * @return The manufacture cost of the Item as a Double
	 */
	public double getManufactureCost() {
		return this.ManufactureCost;
	}

	/**
	 * Returns the sell price of an item
	 * 
	 * @return The sell price as a double
	 */
	public double getSellPrice() {
		return this.SellPrice;
	}

	/**
	 * Get the re-order amount
	 * 
	 * @return the re-order amount as a integer
	 */
	public int getReorderAmount() {
		return this.ReorderAmount;
	}

	/**
	 * gets the re-order point
	 * 
	 * @return the re-order point as a integer
	 */
	public int getReorderPoint() {
		return this.ReorderPoint;
	}

	/**
	 * Check if an item has a temperature
	 * 
	 * @return boolean | true - Item has temperature | False - Item doesn't have a
	 *         temperature
	 */
	public boolean hasTempreture() {
		if (hasTemp) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get the temperature of an item
	 * 
	 * @return the temperature as a double
	 */
	public double getTemperature() {
		return Temperature;
	}

	/**
	 * Get the current inventory of an item
	 * 
	 * @return the current inventory as a integer
	 */
	public int getCurrentInventory() {
		return CurrentInventory;
	}

	/**
	 * Set the current inventory of an item
	 * 
	 * @param number
	 *            value that the current inventory will be changed to
	 */
	public void setCurrentInventory(int number) {
		CurrentInventory = number;
	}

}
