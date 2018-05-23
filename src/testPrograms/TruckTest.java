package testPrograms;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import program.*;

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
//	@Test
//	public void GetCargoCapacityOrdinary() {
//		assertEquals(1000, truckOrdinary.getQuantity(),0.002);
//	}

	@Test
	public void GetCostCalculationOrdinary() {
		assertEquals(1100, truckRefrigrated.costCalculation(0),0.0002);
	}	
//	@Test
//	public void GetCargoCapacityRefrigrated() {
//		assertEquals(800, truckRefrigrated.getQuantity(),0.002);
//	}
	@Test
	public void GetCostCalculationRefrigrated() {
		assertEquals(1100, truckRefrigrated.costCalculation(0),0.0002);

	}
	@Test
	public void GetTruck() {
//		rice,2,3,225,300
		temp = new Item("rice",2,3,225,300);
//		Item temp = new ;
		double z =0;
		z = truckOrdinary.addItemOptimizeManifest(temp);
		System.out.println(z);
	}
}