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
	private double ReorderPoint;
	private double ReorderAmount;
	private double Temperature;
	
	public Item(String name, double sellPrice, double reorderAmount, double reorderPoint, double temperature) {
		this.Name = name;
		this.SellPrice = sellPrice;
		this.ReorderAmount = reorderAmount;
		this.ReorderPoint = reorderPoint;
	}
	

}
