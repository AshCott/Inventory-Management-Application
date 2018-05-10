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
	@Before
	public void constructor() {
		truckOrdinary = new OrdinaryTruck();
		truckRefrigrated = new RefrigratedTruck();
	}
	@Test
	public void GetCargoCapacityOrdinary() {
		assertEquals(1000, truckOrdinary.cargoCaps(),0.002);
	}

	@Test
	public void GetCostCalculationOrdinary() {
		assertEquals(1100, truckRefrigrated.costCalculation(0),0.0002);
	}	
	@Test
	public void GetCargoCapacityRefrigrated() {
		assertEquals(800, truckRefrigrated.cargoCaps(),0.002);
	}
	@Test
	public void GetCostCalculationRefrigrated() {
		assertEquals(1100, truckRefrigrated.costCalculation(0),0.0002);

	}
}