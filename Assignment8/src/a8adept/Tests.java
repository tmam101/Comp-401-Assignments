package a8adept;

import comp401.sushi.Nigiri;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;

public class Tests {

	public static void main(String[] args) throws PlatePriceException, BeltPlateException {
		// Belt belt = new Belt(6);
		// Nigiri nigiri = new Nigiri(Nigiri.NigiriType.CRAB);
		// BluePlate plate1 = new BluePlate(nigiri);
		// BluePlate plate2 = new BluePlate(nigiri);
		// RedPlate plate3 = new RedPlate(nigiri);
		// GreenPlate plate4 = new GreenPlate(nigiri);
		// PlateCounter plateCounter = new PlateCounter(belt);
		// ProfitCounter profitCounter = new ProfitCounter(belt);
		// belt.setPlateAtPosition(plate1, 1);
		// belt.setPlateAtPosition(plate2, 2);
		// belt.setPlateAtPosition(plate3, 3);
		// belt.setPlateAtPosition(plate4, 4);
		//
		// belt.clearPlateAtPosition(1);
		// System.out.println(plateCounter.getBluePlateCount());
		// System.out.println(plateCounter.getRedPlateCount());
		// System.out.println(plateCounter.getGreenPlateCount());
		Nigiri nigiri = new Nigiri(Nigiri.NigiriType.SALMON);
		RedPlate plate = new RedPlate(nigiri);
		System.out.println(plate.getProfit());

	}
}
