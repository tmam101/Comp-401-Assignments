package a3adept;

public interface IngredientPortion {
	public Ingredient getIngredient();

	public double getAmount();

	public String getName();

	public boolean getIsVegetarian();

	public double getCalories();

	public double getCost();

	public IngredientPortion combine(IngredientPortion other);
}
