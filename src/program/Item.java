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
	
	public Item(String name, double ManufactureCost, double sellPrice, int reorderAmount, int reorderPoint, double temperature) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.Temperature = temperature;
	}
	
	public Item(String name, double ManufactureCost, double sellPrice, int reorderAmount, int reorderPoint) {
		this.Name = name;
		this.ManufactureCost = ManufactureCost;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
	}
	
	public String getName() {
		return this.Name;
	}	
	
	public double getManufactureCost() {
		return this.ManufactureCost;
	}	
	
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

}
