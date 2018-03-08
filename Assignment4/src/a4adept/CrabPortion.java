package a4adept;

public class CrabPortion extends IngredientPortionImpl {
	public static final Ingredient CRAB = new Crab();

	public CrabPortion(double amount) {
		super(CRAB, amount);
		if (amount <= 0) {
			throw new RuntimeException("amount is not greater than zero. ");
		}
	}

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		} else if (this.getName().equals(other.getName()) == false) {
			throw new RuntimeException("Other is a portion of a different ingredient");
		} else {
			return new CrabPortion(this.getAmount() + other.getAmount());
		}
	}
}
