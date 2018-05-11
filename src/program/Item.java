package program;

/**
 * 
 * @author 
 * @author
 *
 */
public class Item {
	
	private String Name;
	private double ManufactureCost;
	private double SellPrice;
	private int ReorderPoint;
	private int ReorderAmount;
	private double Temperature;
	private int CurrentInventory;
	
	/**
	 * Create a new Refreigerated Item
	 * @param name
	 * @param ManufactureCost
	 * @param sellPrice
	 * @param reorderAmount
	 * @param reorderPoint
	 * @param temperature
	 */
	public Item(String name, double ManufactureCost, double sellPrice, int reorderAmount, int reorderPoint, double temperature) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.Temperature = temperature;
		this.CurrentInventory = 0;
	}
	
	/**
	 * Create a new Unrefregerated Item 
	 * @param name
	 * @param ManufactureCost
	 * @param sellPrice
	 * @param reorderAmount
	 * @param reorderPoint
	 */
	public Item(String name, double ManufactureCost, double sellPrice, int reorderAmount, int reorderPoint) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.CurrentInventory = 0;
	}
	
	/**
	 * 
	 * @return The name of the item as a String
	 */
	public String getName() {
		return this.Name;
	}	
	
	/**
	 * 
	 * @return The manufacture cost of the Item as a Double
	 */
	public double getManufactureCost() {
		return this.ManufactureCost;
	}	
	
	/**
	 * 
	 * @return
	 */
	public double getSellPrice() {
		return this.SellPrice;
	}	
	
	public int getReorderAmount() {
		return this.ReorderAmount;
	}	
	
	public int getReorderPoint() {
		return this.ReorderPoint;
	}	
	
	public double getTemperature() {
		return Temperature;
	}	
	
	public int getCurrentInventory() {
		return CurrentInventory;
	}
	
	public void setCurrentInventory(int number) {
		CurrentInventory = number;
	}
}
