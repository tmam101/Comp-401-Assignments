package a2novice;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Your code goes here.

		int numberOfVegIngredients = 0;
		double highestCaloriesForCost = 0.0;
		double lowestCaloriesForCost = 1000;
		String a[] = new String[2];

		int numberOfIngredients = s.nextInt();

		for (int i = 0; i < numberOfIngredients; i++) {

			String ingredientName = s.next();
			double pricePerOunce = s.nextDouble();
			boolean isVegetarian = s.nextBoolean();
			int caloriesPerOunce = s.nextInt();

			// Create counter for number of vegetarian ingredients
			if (isVegetarian) {
				numberOfVegIngredients = numberOfVegIngredients + 1;
			}
			// Test highest cal/cost
			if ((caloriesPerOunce / pricePerOunce) > highestCaloriesForCost) {
				highestCaloriesForCost = (caloriesPerOunce / pricePerOunce);
				a[0] = ingredientName;
			}
			if ((caloriesPerOunce / pricePerOunce) < lowestCaloriesForCost) {
				lowestCaloriesForCost = (caloriesPerOunce / pricePerOunce);
				a[1] = ingredientName;
			}
		}
		System.out.println("Number of vegetarian ingredients: " + numberOfVegIngredients);
		System.out.println("Highest cals/$: " + a[0]);
		System.out.println("Lowest cals/$: " + a[1]);
	}
}
