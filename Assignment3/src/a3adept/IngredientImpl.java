package a3adept;

public class IngredientImpl implements Ingredient {

	private String name;
	private double price;
	private int calories;
	private boolean is_vegetarian;

	public IngredientImpl(String name, double price, int calories, boolean is_vegetarian) {
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.is_vegetarian = is_vegetarian;

		// Test to see if arguments are illegal
		if (name == null) {
			throw new RuntimeException("Name is null.");
		}
		if (price < 0) {
			throw new RuntimeException("Price is negative.");
		}
		if (calories < 0) {
			throw new RuntimeException("Calories are negative.");
		}
	}

	public String getName() {
		return name;
	}

	public double getPricePerOunce() {
		return price;
	}

	public int getCaloriesPerOunce() {
		return calories;
	}

	public boolean getIsVegetarian() {
		return is_vegetarian;
	}

	public double getCaloriesPerDollar() {
		return calories / price;
	}

}
