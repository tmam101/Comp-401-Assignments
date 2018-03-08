package a4adept;

public class SalmonPortion extends IngredientPortionImpl {
	public static final Ingredient SALMON = new Salmon();

	public SalmonPortion(double amount) {
		super(SALMON, amount);
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
			return new SalmonPortion(this.getAmount() + other.getAmount());
		}
	}
}