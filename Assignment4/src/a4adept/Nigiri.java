package a4adept;

public class Nigiri implements Sushi {
	public enum NigiriType {
		TUNA, SALMON, EEL, CRAB, SHRIMP
	}

	NigiriType type;
	private IngredientPortion[] ingredients = new IngredientPortion[2];

	public Nigiri(NigiriType type) {
		this.type = type;
		switch (type) {
		case TUNA:
			ingredients[0] = new TunaPortion(0.75);
			ingredients[1] = new RicePortion(0.5);
			break;
		case SALMON:
			ingredients[0] = new SalmonPortion(0.75);
			ingredients[1] = new RicePortion(0.5);

			break;
		case EEL:
			ingredients[0] = new EelPortion(0.75);
			ingredients[1] = new RicePortion(0.5);

			break;
		case CRAB:
			ingredients[0] = new CrabPortion(0.75);
			ingredients[1] = new RicePortion(0.5);

			break;
		case SHRIMP:
			ingredients[0] = new ShrimpPortion(0.75);
			ingredients[1] = new RicePortion(0.5);

			break;
		default:
			throw new RuntimeException("Must be a valid type.");
		}
	}

	public String getName() {
		return type.toString().toLowerCase() + " nagiri";
	}

	public IngredientPortion[] getIngredients() {
		return ingredients.clone();
	}

	public int getCalories() {
		double calorieSum = 0.0;
		for (int i = 0; i < ingredients.length; i++) {
			calorieSum += ingredients[i].getCalories(); // This might be broken
		}
		return ((int) (calorieSum + 0.5));
	}

	public double getCost() {
		double costSum = 0.0;
		for (int i = 0; i < ingredients.length; i++) {
			costSum += ingredients[i].getCost();
		}
		return ((int) ((costSum * 100.0) + 0.5)) / 100.0;
		// needs to be rounded
	}

	public boolean getHasRice() {
		boolean hasRice = false;
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i].getIsRice() == true) {
				hasRice = true;
			}
		}
		return hasRice;
	}

	public boolean getHasShellfish() {
		boolean hasShellfish = false;
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i].getIsShellfish() == true) {
				hasShellfish = true;
			}
		}
		return hasShellfish;
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
