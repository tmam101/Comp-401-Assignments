/* JUnit Test for a8jedi
 * Written by: Ethan Koch
 * April 8, 2017
 */

package a8tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import a8jedi.Belt;
import a8jedi.BeltFullException;
import a8jedi.BeltPlateException;
import a8jedi.PlateCounter;
import a8jedi.ProfitCounter;
import a8jedi.SpoilageCollector;
import comp401.sushi.BluePlate;
import comp401.sushi.CrabPortion;
import comp401.sushi.EelPortion;
import comp401.sushi.GoldPlate;
import comp401.sushi.GreenPlate;
import comp401.sushi.IngredientPortion;
import comp401.sushi.Nigiri;
import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.RicePortion;
import comp401.sushi.Roll;
import comp401.sushi.SalmonPortion;
import comp401.sushi.Sashimi;
import comp401.sushi.SeaweedPortion;
import comp401.sushi.ShrimpPortion;
import comp401.sushi.Sushi;
import comp401.sushi.TunaPortion;

public class A8JediTest {

	private final double precision = 0.0001;

	private Belt workingBelt;
	private Belt nullBelt;
	private Belt smallBelt;

	// Because painters care about color
	private PlateCounter painter;
	private PlateCounter latePainter;

	// Because accountants care about profit
	private ProfitCounter accountant;
	private ProfitCounter lateAccountant;

	private SpoilageCollector trashDude;

	private Plate greenPlate;
	private Plate redPlate;
	private Plate bluePlate;
	private Plate goldPlate;
	private Plate noShellNoVeggiePlate;
	private Plate isShellPlate;
	private Plate noShellIsVeggiePlate;

	private Sushi nigiri;
	private Sushi sashimi;
	private Sushi roll;
	private Sushi veggie_roll;
	private Sushi shell_roll;

	private CrabPortion crab = new CrabPortion(0.5);
	private ShrimpPortion shrimp = new ShrimpPortion(0.5);
	private SalmonPortion salmon = new SalmonPortion(0.5);
	private SeaweedPortion seaweed = new SeaweedPortion(0.5);
	private TunaPortion tuna = new TunaPortion(0.5);
	private RicePortion rice = new RicePortion(0.5);
	private EelPortion eel = new EelPortion(0.5);
	private IngredientPortion[] roll_ingredients;
	private IngredientPortion[] veggie_ingredients;
	private IngredientPortion[] shell_ingredients;

	@Before
	public void setup() throws PlatePriceException {
		workingBelt = new Belt(20);
		smallBelt = new Belt(3);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		trashDude = new SpoilageCollector(smallBelt);

		roll_ingredients = new IngredientPortion[] { salmon, eel, tuna };
		veggie_ingredients = new IngredientPortion[] { seaweed, rice };
		shell_ingredients = new IngredientPortion[] { crab, shrimp };

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll", roll_ingredients);
		veggie_roll = new Roll("VeggieRoll", veggie_ingredients);
		shell_roll = new Roll("ShellRoll", shell_ingredients);

		greenPlate = new GreenPlate(sashimi);
		redPlate = new RedPlate(nigiri);
		bluePlate = new BluePlate(roll);
		goldPlate = new GoldPlate(roll, 6.0);

		noShellNoVeggiePlate = new BluePlate(roll);
		isShellPlate = new RedPlate(shell_roll);
		noShellIsVeggiePlate = new GoldPlate(veggie_roll, 6.0);
	}

	// BEGINNING OF TESTS FROM A7ADEPT
	@Test
	public void testExtendsObservableObservable() {
		// Belt class should extend java.util.Observable
		assertTrue(workingBelt instanceof Observable);

		assertTrue(accountant instanceof Observer);
		assertTrue(painter instanceof Observer);
	}

	// Cannot observe an null belt
	@Test
	public void testBeltNullException() {
		boolean counterExceptionThrown;
		try {
			latePainter = new PlateCounter(nullBelt);
			counterExceptionThrown = false;
		} catch (IllegalArgumentException e) {
			counterExceptionThrown = true;
		}
		assertTrue(counterExceptionThrown);

		boolean profitExceptionThrown;
		try {
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

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll", roll_ingredients);

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
		for (int i = 0; i < workingBelt.getSize(); i++) {
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

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll", roll_ingredients);

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
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.setPlateNearestToPosition(greenPlate, 1);
		runningProfit += greenPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.setPlateAtPosition(goldPlate, 2);
		runningProfit += goldPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.setPlateNearestToPosition(redPlate, 3);
		runningProfit += redPlate.getProfit();
		numPlates += 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		// this guy is late to the observer party!
		lateAccountant = new ProfitCounter(workingBelt);

		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		// reset variables so they don't affect other tests
		for (int i = 0; i < workingBelt.getSize(); i++) {
			workingBelt.clearPlateAtPosition(i);
		}
		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;
	}

	@Test
	public void testPlateCounterNotifiedRemoveClearPlate()
			throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll", roll_ingredients);

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
		for (int i = 0; i < workingBelt.getSize(); i++) {
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
	public void testProfitCounterNotifiedRemoveClearPlate()
			throws BeltPlateException, BeltFullException, PlatePriceException {
		workingBelt = new Belt(20);

		painter = new PlateCounter(workingBelt);
		latePainter = null;

		accountant = new ProfitCounter(workingBelt);
		lateAccountant = null;

		nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		sashimi = new Sashimi(Sashimi.SashimiType.EEL);
		roll = new Roll("CoolRoll", roll_ingredients);

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
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.removePlateAtPosition(0);
		runningProfit -= bluePlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.clearPlateAtPosition(1);
		runningProfit -= greenPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		// this guy is late to the observer party!
		lateAccountant = new ProfitCounter(workingBelt);

		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.removePlateAtPosition(2);
		runningProfit -= goldPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);
		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - runningProfit / numPlates) < precision);

		workingBelt.removePlateAtPosition(3);
		runningProfit -= redPlate.getProfit();
		numPlates -= 1;
		assertTrue(Math.abs(accountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(accountant.getAverageBeltProfit() - 0.0) < precision);
		assertTrue(Math.abs(lateAccountant.getTotalBeltProfit() - runningProfit) < precision);
		assertTrue(Math.abs(lateAccountant.getAverageBeltProfit() - 0.0) < precision);

		// reset variables so they don't affect other tests
		for (int i = 0; i < workingBelt.getSize(); i++) {
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

	// BEGINNING OF TESTS FOR A8ADEPT
	@Test
	public void testAgeOfPlateAtPosition() throws BeltPlateException {

		int bluePlateAge = 0;
		workingBelt.setPlateAtPosition(bluePlate, 0);

		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(0));

		workingBelt.rotate();
		bluePlateAge++;

		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(2));

		int goldPlateAge = 0;
		workingBelt.setPlateAtPosition(goldPlate, 0);

		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(0));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(1));

		workingBelt.rotate();
		goldPlateAge++;
		bluePlateAge++;

		assertEquals(goldPlateAge, workingBelt.getAgeOfPlateAtPosition(1));
		assertEquals(bluePlateAge, workingBelt.getAgeOfPlateAtPosition(2));
		assertEquals(-1, workingBelt.getAgeOfPlateAtPosition(3));

		int greenPlateAge = 0;
		workingBelt.setPlateAtPosition(greenPlate, 0);

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
		workingBelt.setPlateAtPosition(redPlate, 0);

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

		for (int i = 0; i < workingBelt.getSize(); i++) {
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
	// END OF TESTS FOR A8ADEPT

	// BEGINNING OF TESTS FOR A8JEDI
	@Test
	public void testThrowOutSpoiled() throws BeltPlateException {
		smallBelt.setPlateAtPosition(isShellPlate, 0);
		smallBelt.setPlateAtPosition(noShellNoVeggiePlate, 1);
		smallBelt.setPlateAtPosition(noShellIsVeggiePlate, 2);

		for (int i = 0; i < smallBelt.getSize(); i++) {
			smallBelt.rotate();
		}
		double lostCost = crab.getCost() + shrimp.getCost();
		double lostShellfish = crab.getAmount() + shrimp.getAmount();
		double lostSeafood = crab.getAmount() + shrimp.getAmount();
		double lostFood = crab.getAmount() + shrimp.getAmount();

		assertTrue(Math.abs(lostCost - trashDude.getTotalSpoiledCost()) < precision);
		assertTrue(Math.abs(lostShellfish - trashDude.getTotalSpoiledShellfish()) < precision);
		assertTrue(Math.abs(lostSeafood - trashDude.getTotalSpoiledSeafood()) < precision);
		assertTrue(Math.abs(lostFood - trashDude.getTotalSpoiledFood()) < precision);

		for (int i = 0; i < smallBelt.getSize(); i++) {
			smallBelt.rotate();
		}
		lostCost += salmon.getCost() + eel.getCost() + tuna.getCost();
		lostSeafood += salmon.getAmount() + eel.getAmount() + tuna.getAmount();
		lostFood += salmon.getAmount() + eel.getAmount() + tuna.getAmount();

		assertTrue(Math.abs(lostCost - trashDude.getTotalSpoiledCost()) < precision);
		assertTrue(Math.abs(lostShellfish - trashDude.getTotalSpoiledShellfish()) < precision);
		assertTrue(Math.abs(lostSeafood - trashDude.getTotalSpoiledSeafood()) < precision);
		assertTrue(Math.abs(lostFood - trashDude.getTotalSpoiledFood()) < precision);

		for (int i = 0; i < smallBelt.getSize(); i++) {
			smallBelt.rotate();
		}

		lostCost += rice.getCost() + seaweed.getCost();
		lostFood += rice.getAmount() + seaweed.getAmount();

		assertTrue(Math.abs(lostCost - trashDude.getTotalSpoiledCost()) < precision);
		assertTrue(Math.abs(lostShellfish - trashDude.getTotalSpoiledShellfish()) < precision);
		assertTrue(Math.abs(lostSeafood - trashDude.getTotalSpoiledSeafood()) < precision);
		assertTrue(Math.abs(lostFood - trashDude.getTotalSpoiledFood()) < precision);

		for (int i = 0; i < smallBelt.getSize(); i++) {
			assertEquals(null, smallBelt.getPlateAtPosition(i));
		}
	}

	@Test
	public void testExtendsObserver() {
		assertTrue(trashDude instanceof Observer);
	}
	// END OF TESTS FOR A8JEDI
}