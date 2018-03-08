/* JUnit Test for a8adept
 * Written by: Ethan Koch
 * April 8, 2017
 */

package a8tests;

import a8adept.*;
import comp401.sushi.*;

import static org.junit.Assert.*;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class A8AdeptTest {

	private final double precision = 0.0001;

	private Belt workingBelt;
	private Belt nullBelt;

	// Because painters care about color
	private PlateCounter painter;
	private PlateCounter latePainter;

	// Because accountants care about profit
	private ProfitCounter accountant;
	private ProfitCounter lateAccountant;

	private Plate greenPlate;
	private Plate redPlate;
	private Plate bluePlate;
	private Plate goldPlate;

	private Sushi nigiri;
	private Sushi sashimi;
	private Sushi roll;

	private SalmonPortion salmon = new SalmonPortion(.75);
	private SeaweedPortion seaweed = new SeaweedPortion(.3);
	private TunaPortion tuna = new TunaPortion(1.2);
	private RicePortion rice = new RicePortion(.6);
	private IngredientPortion[] roll_ingredients;


	@Before
	public void setup() throws PlatePriceException {		
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		roll_ingredients = new IngredientPortion[]{salmon,seaweed,tuna,rice};

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll",roll_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);
	}

	// BEGINNING OF TESTS FROM A7ADEPT
	@Test
	public void testExtendsObservableObserver() {
		// Belt class should extend java.util.Observable
		assertTrue(workingBelt instanceof Observable);
		
		assertTrue(accountant instanceof Observer);
		assertTrue(painter instanceof Observer);
	}

	// Cannot observe an null belt
	@Test
	public void testBeltNullException() {
		boolean counterExceptionThrown;
		try{
			latePainter = new PlateCounter(nullBelt);
			counterExceptionThrown = false;
		} catch (IllegalArgumentException e) {
			counterExceptionThrown = true;
		}
		assertTrue(counterExceptionThrown);

		boolean profitExceptionThrown;
		try{
			lateAccountant = new ProfitCounter(nullBelt);
			profitExceptionThrown = false;
		} catch (IllegalArgumentException e) {
			profitExceptionThrown = true;
		}
		assertTrue(profitExceptionThrown);
	}

	@Test
	public void testPlateCounterNotifiedSetPlate() throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		roll_ingredients = new IngredientPortion[]{salmon,seaweed,tuna,rice};

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll",roll_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);

		assertEquals(painter.getBluePlateCount(), 0);
		assertEquals(painter.getGreenPlateCount(), 0);
		assertEquals(painter.getGoldPlateCount(), 0);
		assertEquals(painter.getRedPlateCount(), 0);

		workingBelt.setPlateAtPosition(bluePlate, 0);
		assertEquals(painter.getBluePlateCount(), 1);

		workingBelt.setPlateNearestToPosition(greenPlate, 1);
		assertEquals(painter.getGreenPlateCount(), 1);

		workingBelt.setPlateAtPosition(goldPlate, 2);
		assertEquals(painter.getGoldPlateCount(), 1);

		workingBelt.setPlateNearestToPosition(redPlate, 3);
		assertEquals(painter.getRedPlateCount(), 1);		

		// this guy is late to the observer party!
		latePainter = new PlateCounter(workingBelt);

		assertEquals(latePainter.getBluePlateCount(), 1);
		assertEquals(latePainter.getGreenPlateCount(), 1);
		assertEquals(latePainter.getGoldPlateCount(), 1);
		assertEquals(latePainter.getRedPlateCount(), 1);

		// reset variables so they don't affect other tests
		for (int i=0; i < workingBelt.getSize(); i++) {
			workingBelt.clearPlateAtPosition(i);
		}
		painter = new PlateCounter(workingBelt);
		latePainter = null;	
	}

	@Test
	public void testProfitCounterNotifiedSetPlate() throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		roll_ingredients = new IngredientPortion[]{salmon,seaweed,tuna,rice};

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll",roll_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);

		assertTrue(Math.abs(accountant.getTotalBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - 0.0) < precision);
		double runningProfit = 0.0;
		int numPlates = 0;

		workingBelt.setPlateAtPosition(bluePlate, 0);
		runningProfit += bluePlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.setPlateNearestToPosition(greenPlate, 1);
		runningProfit += greenPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.setPlateAtPosition(goldPlate, 2);
		runningProfit += goldPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.setPlateNearestToPosition(redPlate, 3);
		runningProfit += redPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		// this guy is late to the observer party!
		lateAccountant = new ProfitCounter(workingBelt);

		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		// reset variables so they don't affect other tests
		for (int i=0; i < workingBelt.getSize(); i++) {
			workingBelt.clearPlateAtPosition(i);
		}
		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;	
	}

	@Test
	public void testPlateCounterNotifiedRemoveClearPlate() throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		roll_ingredients = new IngredientPortion[]{salmon,seaweed,tuna,rice};

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll",roll_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);

		assertEquals(painter.getBluePlateCount(), 0);
		assertEquals(painter.getGreenPlateCount(), 0);
		assertEquals(painter.getGoldPlateCount(), 0);
		assertEquals(painter.getRedPlateCount(), 0);

		workingBelt.setPlateAtPosition(bluePlate, 0);
		workingBelt.setPlateNearestToPosition(greenPlate, 1);
		workingBelt.setPlateAtPosition(goldPlate, 2);
		workingBelt.setPlateNearestToPosition(redPlate, 3);

		assertEquals(painter.getBluePlateCount(), 1);
		assertEquals(painter.getGreenPlateCount(), 1);

		workingBelt.removePlateAtPosition(0);
		workingBelt.clearPlateAtPosition(1);
		assertEquals(painter.getBluePlateCount(), 0);
		assertEquals(painter.getGreenPlateCount(), 0);

		// this guy is late to the observer party!
		latePainter = new PlateCounter(workingBelt);


		assertEquals(painter.getGoldPlateCount(), 1);
		assertEquals(painter.getRedPlateCount(), 1);
		assertEquals(latePainter.getBluePlateCount(), 0);
		assertEquals(latePainter.getGreenPlateCount(), 0);
		assertEquals(latePainter.getGoldPlateCount(), 1);
		assertEquals(latePainter.getRedPlateCount(), 1);

		workingBelt.removePlateAtPosition(2);
		workingBelt.clearPlateAtPosition(3);
		assertEquals(painter.getGoldPlateCount(), 0);
		assertEquals(painter.getRedPlateCount(), 0);
		assertEquals(latePainter.getGoldPlateCount(), 0);
		assertEquals(latePainter.getRedPlateCount(), 0);

		// reset variables so they don't affect other tests
		for (int i=0; i < workingBelt.getSize(); i++) {
			workingBelt.clearPlateAtPosition(i);
		}
		// final checks to make sure everything is reset
		assertEquals(painter.getBluePlateCount(), 0);
		assertEquals(painter.getGreenPlateCount(), 0);
		assertEquals(painter.getGoldPlateCount(), 0);
		assertEquals(painter.getRedPlateCount(), 0);
		assertEquals(latePainter.getBluePlateCount(), 0);
		assertEquals(latePainter.getGreenPlateCount(), 0);
		assertEquals(latePainter.getGoldPlateCount(), 0);
		assertEquals(latePainter.getRedPlateCount(), 0);


		painter = new PlateCounter(workingBelt);
		latePainter = null;	
	}

	@Test
	public void testProfitCounterNotifiedRemoveClearPlate() throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		roll_ingredients = new IngredientPortion[]{salmon,seaweed,tuna,rice};

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll",roll_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);

		assertTrue(Math.abs(accountant.getTotalBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - 0.0) < precision);
		double runningProfit = 0.0;
		int numPlates = 0;

		workingBelt.setPlateAtPosition(bluePlate, 0);
		runningProfit += bluePlate.getProfit();
		numPlates += 1;
		workingBelt.setPlateNearestToPosition(greenPlate, 1);
		runningProfit += greenPlate.getProfit();
		numPlates += 1;
		workingBelt.setPlateAtPosition(goldPlate, 2);
		runningProfit += goldPlate.getProfit();
		numPlates += 1;
		workingBelt.setPlateNearestToPosition(redPlate, 3);
		runningProfit += redPlate.getProfit();
		numPlates += 1;

		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.removePlateAtPosition(0);
		runningProfit -= bluePlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.clearPlateAtPosition(1);
		runningProfit -= greenPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		// this guy is late to the observer party!
		lateAccountant = new ProfitCounter(workingBelt);

		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);

		workingBelt.removePlateAtPosition(2);
		runningProfit -= goldPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);
		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit/numPlates) < precision);


		workingBelt.removePlateAtPosition(3);
		runningProfit -= redPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - 0.0) < precision);


		// reset variables so they don't affect other tests
		for (int i=0; i < workingBelt.getSize(); i++) {
			workingBelt.clearPlateAtPosition(i);
		}
		// final checks to make sure everything is reset
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - 0.0) < precision);

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;	
	}
	// END OF TESTS FROM A7ADEPT
	
	//BEGINNING OF TESTS FOR A8ADEPT
	@Test
	public void testAgeOfPlateAtPosition() throws BeltPlateException {
		int bluePlateAge = 0;
		workingBelt.setPlateAtPosition(bluePlate,0);

		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(0));

		workingBelt.rotate();
		bluePlateAge++;

		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(2));

		int goldPlateAge = 0;
		workingBelt.setPlateAtPosition(goldPlate,0);

		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(0));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(1));

		workingBelt.rotate();
		goldPlateAge++;
		bluePlateAge++;

		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(2));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(3));

		int greenPlateAge = 0;
		workingBelt.setPlateAtPosition(greenPlate,0);

		assertEquals(greenPlateAge, workingBelt.getAgeOfPlateAtPosition(0));
		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(2));

		workingBelt.rotate();
		greenPlateAge++;
		goldPlateAge++;
		bluePlateAge++;

		assertEquals(greenPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(2));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(3));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(4));

		int redPlateAge = 0;
		workingBelt.setPlateAtPosition(redPlate,0);

		assertEquals(redPlateAge, workingBelt.getAgeOfPlateAtPosition(0));
		assertEquals(greenPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(2));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(3));

		workingBelt.rotate();
		redPlateAge++;
		greenPlateAge++;
		goldPlateAge++;
		bluePlateAge++;

		assertEquals(redPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(greenPlateAge, workingBelt.getAgeOfPlateAtPosition(2));
		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(3));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(4));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(5));


		for (int i=0; i < workingBelt.getSize(); i++) {
			// it still must return a plate object!
			if (i == 1) {
				assertTrue(workingBelt.removePlateAtPosition(i).getClass().equals(RedPlate.class));
			} else if (i == 2) {
				assertTrue(workingBelt.removePlateAtPosition(i).getClass().equals(GreenPlate.class));
			} else if (i == 3) {
				assertTrue(workingBelt.removePlateAtPosition(i).getClass().equals(GoldPlate.class));
			} else if (i == 4) {
				assertTrue(workingBelt.removePlateAtPosition(i).getClass().equals(BluePlate.class));
			} else {
				workingBelt.clearPlateAtPosition(i);
			}
			// just as well test this method! probably a bit of overkill here
			assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(i));
		}
	}
	//END OF TESTS FOR A8ADEPT
}