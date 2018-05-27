package testPrograms;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import program.*;

/**
 * Testing the truck if it can handle all kind of items and getting left over
 * needed for generate manifest logic
 * 
 * @author Radhimas Djan
 * 
 */
public class TruckTest {
	Truck truck;
	OrdinaryTruck truckOrdinary;
	RefrigratedTruck truckRefrigrated;
	Item temp;

	@Before
	public void constructor() {
		truckOrdinary = new OrdinaryTruck();
		truckRefrigrated = new RefrigratedTruck();
		temp = new Item(null, 0, 0, 0, 0);

	}

	/**
	 * test for trying to get the cost of a maximum quantity in ordinary truck
	 */
	@Test
	public void GetCostCalculationOrdinaryMaxQuantity() {
		assertEquals(1000, truckOrdinary.costCalculation(1000), 0.02);
	}

	/**
	 * test for trying to get the cost of a minimum quantity in ordinary truck
	 */
	@Test
	public void GetCostCalculationOrdinaryMinQuantity() {
		assertEquals(750, truckOrdinary.costCalculation(0), 0.02);
	}

	/**
	 * test for trying to get the cost calculation for a lowest allowed temperature
	 * in a refrigrated truck
	 */
	@Test
	public void GetCostCalculationRefrigratorMinTemp() {
		assertEquals(1732.98625573, truckRefrigrated.costCalculation(-20), 0.02);
	}

	/**
	 * test for trying to get the cost calculation for a highest allowed temperature
	 * in a refrigrated truck
	 * 
	 */
	@Test
	public void GetCostCalculationRefrigratorMaxTemp() {
		assertEquals(998, truckRefrigrated.costCalculation(10), 0.02);
	}

	/**
	 * 
	 * test to get and set extra item in ordinary cargo
	 */
	@Test
	public void GetExtraItemOrdinary() {
		truckOrdinary.setExtraItemInCargo(100);
		assertEquals(100, truckOrdinary.getExtraItemInCargo());
	}

	/**
	 * 
	 * test to get and set extra item in refrigerated cargo
	 */
	@Test
	public void GetExtraItemRefrigerated() {
		truckRefrigrated.setExtraItemInCargo(200);
		assertEquals(200, truckRefrigrated.getExtraItemInCargo());
	}

	/**
	 * test too see if it add Quantity in refrigrated truck
	 */
	@Test
	public void SetItemRefrigerated() {
		temp = new Item("ice cream", 8, 14, 175, 250, -20);
		truckRefrigrated.addItemOptimizeManifest(temp);
		assertEquals(250, truckRefrigrated.getQuantity(), 0.02);
	}

	/**
	 * test to see if it add Quantity in ordinary truck
	 */
	@Test
	public void SetItemOrdinary() {
		temp = new Item("bread", 2, 3, 125, 200);
		truckOrdinary.addItemOptimizeManifest(temp);
		assertEquals(200, truckOrdinary.getQuantity(), 0.02);
	}

	/**
	 * test to see if ordinary truck is full will it say it full in isCargoFull
	 */
	@Test
	public void CargoFullOrdinary() {
		temp = new Item("bread", 2, 3, 125, 1000);
		truckOrdinary.addItemOptimizeManifest(temp);
		assertEquals(true, truckOrdinary.isCargoFull());
	}

	/**
	 * test to see if refrigerated truck is full will it say it full in isCargoFull
	 */
	@Test
	public void CargoFullRefrigerated() {
		temp = new Item("ice cream", 8, 14, 175, 800, -20);
		truckRefrigrated.addItemOptimizeManifest(temp);
		assertEquals(true, truckRefrigrated.isCargoFull());
	}

	/**
	 * 
	 * test to see if it can detect a left over from a ordinary truck if its more
	 * than its capacity
	 */
	@Test
	public void ordinaryExtraTest() {
		temp = new Item("bread", 2, 3, 125, 1500);
		truckOrdinary.addItemOptimizeManifest(temp);
		// because capacity is 1000 so left over should be 500
		assertEquals(500, truckOrdinary.getExtraItemInCargo());
	}

	/**
	 * 
	 * test to see if it can detect a left over from a refrigerated truck if its
	 * more than its capacity
	 */
	@Test
	public void refrigeratedExtraTest() {
		temp = new Item("ice cream", 8, 14, 175, 1200, -20);
		truckRefrigrated.addItemOptimizeManifest(temp);
		// because capacity is 800 so left over should be 400
		assertEquals(400, truckRefrigrated.getExtraItemInCargo());
	}

	/**
	 * 
	 * test to see if a truck that already filled with item then filled again with a
	 * leftover item from another truck that is bigger than the whole truck, can get
	 * the leftover correctly
	 */
	@Test
	public void refrigeratedExtraOtherTruckTest() {
		temp = new Item("ice cream", 8, 14, 175, 400, -20);
		truckRefrigrated.addItemOptimizeManifest(temp);
		Item temp2 = new Item("frozen vegetable mix", 5, 8, 225, 3000, -12);
		truckRefrigrated.addItemExtraItem(temp2, 2000);
		assertEquals(1600, truckRefrigrated.getExtraItemInCargo());
	}

	/**
	 * 
	 * test to see if a truck that already filled with item then filled again with a
	 * leftover item from another truck that is bigger than the whole truck, can get
	 * the leftover correctly
	 */
	@Test
	public void ordinaryExtraOtherTruckTest() {
		temp = new Item("bread", 2, 3, 125, 200);
		truckOrdinary.addItemOptimizeManifest(temp);
		Item temp2 = new Item("chips", 2, 4, 125, 5000);
		truckOrdinary.addItemExtraItem(temp2, 3000);
		assertEquals(2200, truckOrdinary.getExtraItemInCargo());
	}

}