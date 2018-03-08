package a3adept;

public class IngredientPortionImpl implements IngredientPortion {
	private Ingredient ing;
	private double amount;

	public IngredientPortionImpl(Ingredient ing, double amount) {
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

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		} else if (other.getName() != this.getName()) {
			throw new RuntimeException("Other is a portion of a different ingredient. ");
		} else {
			return new IngredientPortionImpl(this.getIngredient(), (this.getAmount() + other.getAmount()));
		}
	}
}
