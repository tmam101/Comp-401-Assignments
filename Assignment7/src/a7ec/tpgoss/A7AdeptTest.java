package a7ec.tpgoss;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import a7adept.Belt;
import a7adept.BeltPlateException;
import a7adept.PlateCounter;
import a7adept.ProfitCounter;
import comp401.sushi.BluePlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.Nigiri;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;

public class A7AdeptTest {

	private static final double DELTA = 0;

	public static String[] getTestNames() {
		// Be sure to change the size of this
		// array to match the number of tests you
		// turning in for extra credit.

		String[] test_names = new String[6];

		// Each entry in the array should be the
		// test method name as a String. Be sure it matches
		// exactly. Remember to replace the first trivial
		// example test.

		test_names[0] = "testGetSize";
		test_names[1] = "testGetPlateAtPosition";
		test_names[2] = "testSetPlateAtPosition";
		test_names[3] = "testClearPlateAtPosition";
		test_names[4] = "testRemovePlateAtPosition";
		test_names[5] = "testSetPlateNearestToPosition";
		test_names[6] = "testGetRedPlateCount";

		return test_names;
	}

	// Each test method must be preceded by the @Test
	// compiler directive. This is necessary for the JUnit
	// framework to recognize the method as a JUnit test.
	//
	// Test methods must be void methods (i.e. doesn't produce
	// a return value) and do not take any parameter.
	//
	// Be sure to use the various forms of assertEquals
	// as necessary to check for expected values. The
	// first parameter is the value you are expecting.
	// The second parameter is the expression you are checking.

	// Belt Tests
	@Test
	public void testGetSize() {
		Belt b = new Belt(5);
		assertEquals(5, b.getSize());
		Belt c = new Belt(18);
		assertEquals(18, c.getSize());
	}

	@Test
	public void testGetPlateAtPosition() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		BluePlate plate1 = new BluePlate(nigiri);
		b.setPlateAtPosition(plate1, 2);
		b.setPlateAtPosition(plate1, 3);
		assertEquals(plate1, b.getPlateAtPosition(2));
		assertEquals(plate1, b.getPlateAtPosition(3));
		b.removePlateAtPosition(2);
		assertEquals(null, b.getPlateAtPosition(2));
	}

	@Test
	public void testSetPlateAtPosition() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		BluePlate plate1 = new BluePlate(nigiri);
		b.setPlateAtPosition(plate1, 4);
		assertEquals(plate1, b.getPlateAtPosition(4));
	}

	@Test
	public void testClearPlateAtPosition() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		BluePlate plate1 = new BluePlate(nigiri);
		b.setPlateAtPosition(plate1, 2);
		b.clearPlateAtPosition(2);
		assertEquals(null, b.getPlateAtPosition(2));
	}

	@Test
	public void testRemovePlateAtPosition() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		BluePlate plate1 = new BluePlate(nigiri);
		b.setPlateAtPosition(plate1, 4);
		assertEquals(plate1, b.removePlateAtPosition(4));
	}

	@Test
	public void testSetPlateNearestToPosition() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		BluePlate plate1 = new BluePlate(nigiri);
		BluePlate plate2 = new BluePlate(nigiri);
		b.setPlateAtPosition(plate1, 6);
		b.setPlateAtPosition(plate2, 8);
		assertEquals(plate1, b.getPlateAtPosition(1));
		assertEquals(plate2, b.getPlateAtPosition(3));
	}

	@Test
	public void testPlateCounter() throws BeltPlateException, PlatePriceException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		PlateCounter counter = new PlateCounter(b);
		RedPlate redPlate1 = new RedPlate(nigiri);
		RedPlate redPlate2 = new RedPlate(nigiri);
		RedPlate redPlate3 = new RedPlate(nigiri);
		RedPlate redPlate4 = new RedPlate(nigiri);
		BluePlate bluePlate = new BluePlate(nigiri);
		GreenPlate greenPlate = new GreenPlate(nigiri);
		// b.setPlateAtPosition(redPlate1, 1);
		// b.setPlateAtPosition(redPlate2, 2);
		// b.setPlateAtPosition(redPlate3, 3);
		// b.setPlateAtPosition(redPlate4, 4);

		for (int i = 0; i < 3; i++) {
			RedPlate redPlate = new RedPlate(nigiri);
			// Plate red = new RedPlate();
			b.setPlateAtPosition(redPlate, i);
		}
		assertEquals(3, counter.getRedPlateCount());
		// add some plates with different color
		b.rotate();
		assertEquals(3, counter.getRedPlateCount());
		b.removePlateAtPosition(1);
		assertEquals(2, counter.getRedPlateCount());
		b.setPlateAtPosition(bluePlate, 1);
		assertEquals(1, counter.getBluePlateCount());
		b.clearPlateAtPosition(1);
		b.setPlateAtPosition(greenPlate, 1);
		assertEquals(1, counter.getGreenPlateCount());
		b.rotate();
		assertEquals(1, counter.getGreenPlateCount());
	}

	@Test
	public void testProfitCounter() throws PlatePriceException, BeltPlateException {
		Belt b = new Belt(5);
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		ProfitCounter counter = new ProfitCounter(b);
		RedPlate redPlate1 = new RedPlate(nigiri);
		RedPlate redPlate2 = new RedPlate(nigiri);
		RedPlate redPlate3 = new RedPlate(nigiri);
		RedPlate redPlate4 = new RedPlate(nigiri);
		BluePlate bluePlate = new BluePlate(nigiri);
		GreenPlate greenPlate = new GreenPlate(nigiri);
		assertEquals(0.0, counter.getTotalBeltProfit(), DELTA);
		b.setPlateAtPosition(redPlate1, 1);
		assertEquals(0.4, counter.getTotalBeltProfit(), DELTA);
		assertEquals(0.4, counter.getAverageBeltProfit(), DELTA);
		b.setPlateAtPosition(redPlate3, 3);
		assertEquals(0.8, counter.getTotalBeltProfit(), DELTA);
		assertEquals(0.4, counter.getAverageBeltProfit(), DELTA);
	}

	// @Test
	// public void testGetBelt() {
	//
	// }
}
