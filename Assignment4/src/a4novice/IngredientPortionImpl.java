package a4novice;

public abstract class IngredientPortionImpl implements IngredientPortion {
	private Ingredient ing;
	private double amount;

	protected IngredientPortionImpl(Ingredient ing, double amount) {
		this.ing = ing;
		this.amount = amount;

		// Test for illegal arguments
		if (ing == null) {
			throw new RuntimeException("ing is null.");
		}
		if (amount < 0) {
			throw new RuntimeException("amount is negative.");
		}
	}

	public Ingredient getIngredient() {
		return ing;
	}

	public double getAmount() {
		return amount;
	}

	public String getName() {
		return ing.getName();
	}

	public boolean getIsVegetarian() {
		return ing.getIsVegetarian();
	}

	public double getCalories() {
		return ing.getCaloriesPerOunce() * this.amount;
	}

	public double getCost() {
		return ing.getPricePerOunce() * this.amount;
	}

	public boolean getIsRice() {
		return ing.getIsRice();
	}

	public boolean getIsShellfish() {
		return ing.getIsShellfish();
	}

	// look at this
	public abstract IngredientPortion combine(IngredientPortion other);
}
