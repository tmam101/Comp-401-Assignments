package a8jedi;

import java.util.Observable;
import java.util.Observer;

import comp401.sushi.IngredientPortion;

public class SpoilageCollector implements Observer {
	private Belt b;
	private double costOfSpoiledSushi;
	private double amountOfSpoiledShellfish;
	private double amountOfSpoiledSeafood;
	private double amountOfSpoiledFood;

	public SpoilageCollector(Belt b) {
		this.b = b;
		// Throw an exception if the belt is null.
		if (b == (null)) {
			throw new IllegalArgumentException();
		} else {
			// This is an observer of the belt.
			b.addObserver(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		PlateEvent event = (PlateEvent) arg;
		// If a plate is spoiled
		if (event.getType() == PlateEvent.EventType.PLATE_SPOILED) {
			// Remove the plate
			b.removePlateAtPosition(event.getPosition());
			IngredientPortion[] ingredients;
			ingredients = event.getPlate().getContents().getIngredients();

			// For each ingredient of the sushi on the plate
			for (int i = 0; i < ingredients.length; i++) {
				// Get the cost of the ingredient
				costOfSpoiledSushi += ingredients[i].getCost();
				if (ingredients[i].getIsShellfish() == true) {
					// Get the amount of shelffish in the ingredient
					amountOfSpoiledShellfish += ingredients[i].getAmount();
				}
				if (ingredients[i].getName() == "crab" || ingredients[i].getName() == "shrimp"
						|| ingredients[i].getName() == "salmon" || ingredients[i].getName() == "tuna"
						|| ingredients[i].getName() == "eel") {
					// Get the amount of seafood in the ingredient
					amountOfSpoiledSeafood += ingredients[i].getAmount();
				}
				// Get the total amount of food in the ingredient
				amountOfSpoiledFood += ingredients[i].getAmount();
			}
		}
	}

	public double getTotalSpoiledCost() {
		return costOfSpoiledSushi;
	}

	public double getTotalSpoiledShellfish() {
		return amountOfSpoiledShellfish;
	}

	public double getTotalSpoiledSeafood() {
		return amountOfSpoiledSeafood;
	}

	public double getTotalSpoiledFood() {
		return amountOfSpoiledFood;
	}
}
