package a4adept;

public abstract class IngredientImpl implements Ingredient {
	private String name;
	private double price;
	private int cal;
	private boolean is_veggie;
	private boolean is_rice;
	private boolean is_shell;

	protected IngredientImpl(String name, double price, int cal, boolean is_veggie, boolean is_rice, boolean is_shell) {
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.is_veggie = is_veggie;
		this.is_rice = is_rice;
		this.is_shell = is_shell;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public double getCaloriesPerDollar() {
		// TODO Auto-generated method stub
		return cal / price;
	}

	@Override
	public int getCaloriesPerOunce() {
		// TODO Auto-generated method stub
		return cal;
	}

	@Override
	public double getPricePerOunce() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public boolean equals(Ingredient other) {
		// TODO Auto-generated method stub
		// return this == other;
		if (other == null)
			return false; // return false or error

		// price is double
		return (name.equals(other.getName()) && cal == other.getCaloriesPerOunce() && is_rice == other.getIsRice()
				&& price == other.getPricePerOunce() && is_veggie == other.getIsVegetarian()
				&& is_shell == other.getIsShellfish());
	}

	@Override
	public boolean getIsVegetarian() {
		// TODO Auto-generated method stub
		return is_veggie;
	}

	@Override
	public boolean getIsRice() {
		// TODO Auto-generated method stub
		return is_rice;
	}

	@Override
	public boolean getIsShellfish() {
		// TODO Auto-generated method stub
		return is_shell;
	}

}
