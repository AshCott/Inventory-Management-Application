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
	
	public Item(String name, double sellPrice, int reorderAmount, int reorderPoint, double temperature) {
		this.Name = name;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
		this.Temperature = temperature;
	}
	
	public Item(String name, double sellPrice, int reorderAmount, int reorderPoint) {
		this.Name = name;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
	}
	
	public double getTemperature() {
		return Temperature;
	}	

}
