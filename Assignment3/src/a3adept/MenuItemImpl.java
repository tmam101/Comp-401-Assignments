package a3adept;

public class MenuItemImpl implements MenuItem {
	private String name;
	private IngredientPortion[] ingredients;

	public MenuItemImpl(String name, IngredientPortion[] ingredients) {
		this.name = name;
		this.ingredients = ingredients.clone();

		// Test for illegal arguments
		if (name == null) {
			throw new RuntimeException("name is null.");
		}
		if (ingredients == null) {
			throw new RuntimeException("ingredients is null");
		}
		if (ingredients.length <= 0) {
			throw new RuntimeException("ingredients length is not greater than 0.");
		}
	}

	public String getName() {
		return name;
	}

	public IngredientPortion[] getIngredients() {
		return ingredients.clone();

	}

	public int getCalories() {
		double calorieSum = 0.0;
		for (int i = 0; i < ingredients.length; i++) {
			calorieSum += ingredients[i].getCalories();
		}
		return ((int) (calorieSum + 0.5));
	}

	public double getCost() {
		double costSum = 0.0;
		for (int i = 0; i < ingredients.length; i++) {
			costSum += ingredients[i].getCost();
		}
		return ((int) ((costSum * 100.0) + 0.5)) / 100.0; // needs to be rounded
	}

	public boolean getIsVegetarian() {
		boolean isveg = true;
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i].getIsVegetarian() == false) {
				isveg = false;
			}
		}
		return isveg;
	}
}
