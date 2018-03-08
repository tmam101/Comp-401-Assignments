package a4novice;

public class SeaweedPortion extends IngredientPortionImpl {
	public static final Ingredient SEAWEED = new Seaweed();

	public SeaweedPortion(double amount) {
		super(SEAWEED, amount);
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
			return new SeaweedPortion(this.getAmount() + other.getAmount());
		}
	}
}