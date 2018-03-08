package a6adept;

import comp401.sushi.BluePlate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.RedPlate;
import comp401.sushi.Sashimi;
import comp401.sushi.Sashimi.SashimiType;

public class Tests {

	public static void main(String[] args) throws PlatePriceException, BeltPlateException {
		Belt belt = new Belt(4);
		System.out.println(1 % 4);
		Sashimi sashimi = new Sashimi(SashimiType.SALMON);
		BluePlate plate = new BluePlate(sashimi);
		RedPlate plate2 = new RedPlate(sashimi);

		belt.setPlateAtPosition(plate, 2);
		belt.setPlateAtPosition(plate2, 5);
		// belt.setPlateAtPosition(plate, 3);

		System.out.println(belt.getPlateAtPosition(1));
	}
}
