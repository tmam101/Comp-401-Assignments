package a4novice;

public class TunaPortion extends IngredientPortionImpl {
	public static final Ingredient TUNA = new Tuna();

	public TunaPortion(double amount) {
		super(TUNA, amount);
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
			return new TunaPortion(this.getAmount() + other.getAmount());
		}
	}
}