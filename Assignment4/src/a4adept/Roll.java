package a4adept;

public class Roll implements Sushi {
	public enum RollType {
		TUNA, SALMON, EEL, CRAB, SHRIMP
	}

	String name;
	private IngredientPortion[] roll_ingredients;

	public Roll(String name, IngredientPortion[] roll_ingredients) {
		this.name = name;
		this.roll_ingredients = roll_ingredients.clone(); // not sure about this
	}

	public String getName() {
		return name;
	}

	public IngredientPortion[] getIngredients() {
		return roll_ingredients.clone();
	}

	public int getCalories() {
		double calorieSum = 0.0;
		for (int i = 0; i < roll_ingredients.length; i++) {
			calorieSum += roll_ingredients[i].getCalories(); // This might be
																// broken
		}
		return ((int) (calorieSum + 0.5));
	}

	public double getCost() {
		double costSum = 0.0;
		for (int i = 0; i < roll_ingredients.length; i++) {
			costSum += roll_ingredients[i].getCost();
		}
		return ((int) ((costSum * 100.0) + 0.5)) / 100.0;
		// needs to be rounded
	}

	public boolean getHasRice() {
		boolean hasRice = false;
		for (int i = 0; i < roll_ingredients.length; i++) {
			if (roll_ingredients[i].getIsRice() == true) {
				hasRice = true;
			}
		}
		return hasRice;
	}

	public boolean getHasShellfish() {
		boolean hasShellfish = false;
		for (int i = 0; i < roll_ingredients.length; i++) {
			if (roll_ingredients[i].getIsShellfish() == true) {
				hasShellfish = true;
			}
		}
		return hasShellfish;
	}

	public boolean getIsVegetarian() {
		boolean isveg = true;
		for (int i = 0; i < roll_ingredients.length; i++) {
			if (roll_ingredients[i].getIsVegetarian() == false) {
				isveg = false;
			}
		}
		return isveg;
	}
}
