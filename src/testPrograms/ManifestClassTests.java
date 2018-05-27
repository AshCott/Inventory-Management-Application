package testPrograms;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import program.CSVFormatException;
import program.DeliveryException;
import program.Item;
import program.Manifest;
import program.OrdinaryTruck;
import program.RefrigratedTruck;
import program.StockException;
import program.Store;
import program.Truck;

/**
 * 
 * @author Radhimas Djan
 *
 */
public class ManifestClassTests {

	Store superMart;
	Manifest manifest;
	Item temp;
	Truck truck;
	OrdinaryTruck truckOrdinary;
	RefrigratedTruck truckRefrigrated;

	/**
	 * constructor for store
	 */
	@Before
	public void constructor() {
		manifest = new Manifest();
		manifest.ornTruck = new ArrayList<OrdinaryTruck>();
		manifest.refTruck = new ArrayList<RefrigratedTruck>();
		temp = new Item(null, 0, 0, 0, 0);
		superMart = Store.getInstance();
	}

	/**
	 * test adding more ordinary truck
	 */
	@Test
	public void addingMoreOrdinaryTruck() {
		truckOrdinary = new OrdinaryTruck();
		manifest.saveOrnTruck(truckOrdinary);
		assertEquals(1, manifest.ornTruck.size());
	}

	/**
	 * test adding more refrigrated truck
	 */
	@Test
	public void addingMoreRefrigeratedTruck() {
		truckRefrigrated = new RefrigratedTruck();
		manifest.saveRefTruck(truckRefrigrated);
		assertEquals(1, manifest.refTruck.size());
	}

	/**
	 * test if the first initiation of generate manifest result return an inventory
	 * where it current inventory for each item is the reorder amount of the
	 * item(because current inventory is equal to 0)
	 * 
	 * @throws DeliveryException
	 * @throws CSVFormatException
	 * @throws IOException
	 * @throws StockException
	 */
	@Test
	public void initManifest() throws IOException, CSVFormatException, DeliveryException {

		superMart.creatInventory("item_properties.csv");
		superMart.reset();
		manifest.generateManifest();
		superMart.importManifest("exportManifest.csv");

		// check if the inventory is now equal to the reorder amount
		assertEquals(superMart.getInventory().get("rice").getReorderAmount(),
				superMart.getInventory().get("rice").getCurrentInventory());
		assertEquals(superMart.getInventory().get("beans").getReorderAmount(),
				superMart.getInventory().get("beans").getCurrentInventory());
		assertEquals(superMart.getInventory().get("pasta").getReorderAmount(),
				superMart.getInventory().get("pasta").getCurrentInventory());
		assertEquals(superMart.getInventory().get("biscuits").getReorderAmount(),
				superMart.getInventory().get("biscuits").getCurrentInventory());
		assertEquals(superMart.getInventory().get("nuts").getReorderAmount(),
				superMart.getInventory().get("nuts").getCurrentInventory());
		assertEquals(superMart.getInventory().get("chips").getReorderAmount(),
				superMart.getInventory().get("chips").getCurrentInventory());
		assertEquals(superMart.getInventory().get("chocolate").getReorderAmount(),
				superMart.getInventory().get("chocolate").getCurrentInventory());
		assertEquals(superMart.getInventory().get("bread").getReorderAmount(),
				superMart.getInventory().get("bread").getCurrentInventory());
		assertEquals(superMart.getInventory().get("mushrooms").getReorderAmount(),
				superMart.getInventory().get("mushrooms").getCurrentInventory());
		assertEquals(superMart.getInventory().get("tomatoes").getReorderAmount(),
				superMart.getInventory().get("tomatoes").getCurrentInventory());
		assertEquals(superMart.getInventory().get("lettuce").getReorderAmount(),
				superMart.getInventory().get("lettuce").getCurrentInventory());
		assertEquals(superMart.getInventory().get("grapes").getReorderAmount(),
				superMart.getInventory().get("grapes").getCurrentInventory());
		assertEquals(superMart.getInventory().get("asparagus").getReorderAmount(),
				superMart.getInventory().get("asparagus").getCurrentInventory());
		assertEquals(superMart.getInventory().get("celery").getReorderAmount(),
				superMart.getInventory().get("celery").getCurrentInventory());
		assertEquals(superMart.getInventory().get("chicken").getReorderAmount(),
				superMart.getInventory().get("chicken").getCurrentInventory());
		assertEquals(superMart.getInventory().get("beef").getReorderAmount(),
				superMart.getInventory().get("beef").getCurrentInventory());
		assertEquals(superMart.getInventory().get("fish").getReorderAmount(),
				superMart.getInventory().get("fish").getCurrentInventory());
		assertEquals(superMart.getInventory().get("yoghurt").getReorderAmount(),
				superMart.getInventory().get("yoghurt").getCurrentInventory());
		assertEquals(superMart.getInventory().get("milk").getReorderAmount(),
				superMart.getInventory().get("milk").getCurrentInventory());
		assertEquals(superMart.getInventory().get("cheese").getReorderAmount(),
				superMart.getInventory().get("cheese").getCurrentInventory());
		assertEquals(superMart.getInventory().get("ice cream").getReorderAmount(),
				superMart.getInventory().get("ice cream").getCurrentInventory());
		assertEquals(superMart.getInventory().get("ice").getReorderAmount(),
				superMart.getInventory().get("ice").getCurrentInventory());
		assertEquals(superMart.getInventory().get("frozen meat").getReorderAmount(),
				superMart.getInventory().get("frozen meat").getCurrentInventory());
		assertEquals(superMart.getInventory().get("frozen vegetable mix").getReorderAmount(),
				superMart.getInventory().get("frozen vegetable mix").getCurrentInventory());
	}

	/**
	 * test to see if inventory is already stocked no need to do anything in
	 * generate manifest and throw an exception
	 * 
	 * @throws DeliveryException
	 * @throws IOException
	 */
	@Test(expected = DeliveryException.class)
	public void generateManifestThrowException() throws IOException, DeliveryException {
		manifest.generateManifest();
	}

	/**
	 * Test to see if the last inventory is the same with the one written on the pdf
	 * 
	 * @throws IOException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 * @throws StockException
	 */
	@Test
	public void finalManifestReadMe() throws IOException, CSVFormatException, DeliveryException, StockException {

		Store superMart;
		superMart = Store.getInstance();
		superMart.reset();
		superMart.creatInventory("item_properties.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_0.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_1.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_2.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_3.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");
		superMart.importSalesLog("sales_log_4.csv");
		superMart.exportingManifest();
		superMart.importManifest("exportManifest.csv");

		// check if the assert of each item is correct
		assertEquals(386, superMart.getInventory().get("rice").getCurrentInventory());
		assertEquals(805, superMart.getInventory().get("beans").getCurrentInventory());
		assertEquals(343, superMart.getInventory().get("pasta").getCurrentInventory());
		assertEquals(853, superMart.getInventory().get("biscuits").getCurrentInventory());
		assertEquals(357, superMart.getInventory().get("nuts").getCurrentInventory());
		assertEquals(145, superMart.getInventory().get("chips").getCurrentInventory());
		assertEquals(540, superMart.getInventory().get("chocolate").getCurrentInventory());
		assertEquals(182, superMart.getInventory().get("bread").getCurrentInventory());
		assertEquals(225, superMart.getInventory().get("mushrooms").getCurrentInventory());
		assertEquals(574, superMart.getInventory().get("tomatoes").getCurrentInventory());
		assertEquals(430, superMart.getInventory().get("lettuce").getCurrentInventory());
		assertEquals(198, superMart.getInventory().get("grapes").getCurrentInventory());
		assertEquals(253, superMart.getInventory().get("asparagus").getCurrentInventory());
		assertEquals(263, superMart.getInventory().get("celery").getCurrentInventory());
		assertEquals(571, superMart.getInventory().get("chicken").getCurrentInventory());
		assertEquals(701, superMart.getInventory().get("beef").getCurrentInventory());
		assertEquals(734, superMart.getInventory().get("fish").getCurrentInventory());
		assertEquals(338, superMart.getInventory().get("yoghurt").getCurrentInventory());
		assertEquals(549, superMart.getInventory().get("milk").getCurrentInventory());
		assertEquals(419, superMart.getInventory().get("cheese").getCurrentInventory());
		assertEquals(329, superMart.getInventory().get("ice cream").getCurrentInventory());
		assertEquals(279, superMart.getInventory().get("ice").getCurrentInventory());
		assertEquals(637, superMart.getInventory().get("frozen meat").getCurrentInventory());
		assertEquals(509, superMart.getInventory().get("frozen vegetable mix").getCurrentInventory());

	}

}
