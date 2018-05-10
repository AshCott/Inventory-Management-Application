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
		this.setName(name);
		this.setSellPrice(sellPrice);
		this.setReorderAmount(reorderAmount);
		this.setReorderPoint(reorderPoint);
		this.Temperature = temperature;
	}
	
	public Item(String name, double sellPrice, int reorderAmount, int reorderPoint) {
		this.setName(name);
		this.setSellPrice(sellPrice);
		this.setReorderAmount(reorderAmount);
		this.setReorderPoint(reorderPoint);
	}
	
	//make get and set
	public double getTemperature() {
		return Temperature;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getManufactureCost() {
		return ManufactureCost;
	}

	public void setManufactureCost(double manufactureCost) {
		ManufactureCost = manufactureCost;
	}

	public int getReorderPoint() {
		return ReorderPoint;
	}

	public void setReorderPoint(int reorderPoint) {
		ReorderPoint = reorderPoint;
	}

	public int getReorderAmount() {
		return ReorderAmount;
	}

	public void setReorderAmount(int reorderAmount) {
		ReorderAmount = reorderAmount;
	}

	public double getSellPrice() {
		return SellPrice;
	}

	public void setSellPrice(double sellPrice) {
		SellPrice = sellPrice;
	}
	

}
