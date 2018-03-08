package a4novice;

public class AvocadoPortion extends IngredientPortionImpl {
	private static final Ingredient AVOCADO = new Avocado();

	public AvocadoPortion(double amount) {
		super(AVOCADO, amount);
		if (amount <= 0) {
			throw new RuntimeException("amount is not greater than zero.  ");
		}
	}

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		} else if (other.getName().equals(this.getName()) == false) {
			throw new RuntimeException("Other is a portion of a different ingredient. ");
		} else {
			return new AvocadoPortion((this.getAmount() + other.getAmount()));
		}
	}
}