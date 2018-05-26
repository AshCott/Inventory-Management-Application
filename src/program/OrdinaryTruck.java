package program;

import java.util.HashMap;

/**
 * 
 * @author Radhimas Djan
 * @author 
 */
public class OrdinaryTruck extends Truck {
	private double quantity;
	private int extraItem;
	private HashMap<String, Integer> cargo;
	private final double CARGO_CAPACITY = 1000;

	public OrdinaryTruck() {
		this.cargo = new HashMap<String, Integer>();
		this.itemCount = 0;
		this.extraItem = 0;
	}

	@Override
	public boolean isCargoFull() {
		return (this.CARGO_CAPACITY == getQuantity());
	}

	@Override
	public double costCalculation(double quantity) {
		this.quantity = quantity;
		cost = 750 + (0.25 * quantity);
		return cost;
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setTotalPriceInTruck(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public double getTotalPriceInTruck() {

		return totalPrice;
	}

	@Override
	public void addItemImportManifest(String itemName, Integer quantity) {
		cargo.put(itemName, quantity);
		itemCount += quantity;
	}

	@Override
	public int addItemOptimizeManifest(Item cargoItem) {
		int leftOverCapacity;
		int itemGetToCargo;
		leftOverCapacity = (int) ((cargoItem.getReorderAmount() + this.quantity) - this.CARGO_CAPACITY);
		
		// if calculation of leftovercapacity is less than 0 it indicate that the
		// whole reorder amount can be put to the desired truck
		
		if (leftOverCapacity <= 0) {
			this.quantity += cargoItem.getReorderAmount();
			this.cargo.put(cargoItem.getName(), cargoItem.getReorderAmount());
			return cargoItem.getReorderAmount();
		}   // if its more than 0 it means that the truck doesn't have enough space for
			// reorder amount so set a new extra item which indicate there's a left over
			// from reorder amount that hasn't been put to the cargo in cargo which are
			// going to be used in the next loop
		else {
			this.setExtraItemInCargo(leftOverCapacity);
			itemGetToCargo = (int) (this.CARGO_CAPACITY - this.quantity);
			this.cargo.put(cargoItem.getName(), itemGetToCargo);
			this.quantity += itemGetToCargo;
			return itemGetToCargo;
		}
	}

	@Override
	public int addItemExtraItem(Item cargoItem, int extraCargo) {

		int leftOverCapacity;
		int itemGetToCargo;

		leftOverCapacity = (int) ((extraCargo + this.quantity) - this.CARGO_CAPACITY);
		// if calculation of leftovercapacity is less than 0 it indicate that the extra
		// cargo can be put to the desired truck

		if (leftOverCapacity <= 0) {
			this.quantity += extraCargo;
			this.cargo.put(cargoItem.getName(), extraCargo);
			return extraCargo;
		}
		// if its more than 0 it means that the truck doesn't have enough space to set
		// the extra item so set a new extra item which indicate there's a left over
		// from reorder amount that hasn't been put to the cargo in cargo which are
		// going to be used in the next loop
		else {
			this.setExtraItemInCargo(leftOverCapacity);
			itemGetToCargo = (int) (this.CARGO_CAPACITY - this.quantity);
			this.cargo.put(cargoItem.getName(), itemGetToCargo);
			this.quantity += itemGetToCargo;
			return itemGetToCargo;
		}
	}

	@Override
	public HashMap<String, Integer> getCargo() {
		return this.cargo;
	}

	@Override
	public int getExtraItemInCargo() {
		return extraItem;
	}

	@Override
	public void setExtraItemInCargo(int extraItem) {
		this.extraItem = extraItem;
	}

}
