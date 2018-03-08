package a4adept;

public class ShrimpPortion extends IngredientPortionImpl {
	public static final Ingredient SHRIMP = new Shrimp();

	public ShrimpPortion(double amount) {
		super(SHRIMP, amount);
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
			return new ShrimpPortion(this.getAmount() + other.getAmount());
		}
	}
}