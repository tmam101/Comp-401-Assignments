package a2adept;

import java.util.HashMap;
import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Your code goes here.

		// Maps for ingredients
		HashMap<String, Double> priceMap = new HashMap<String, Double>();
		HashMap<String, Boolean> vegMap = new HashMap<String, Boolean>();
		HashMap<String, Integer> calMap = new HashMap<String, Integer>();

		// Ingredients
		int numberOfIngredients = s.nextInt();
		for (int i = 0; i < numberOfIngredients; i++) {
			// User input
			String ingredientName = s.next();
			double pricePerOunce = s.nextDouble();
			boolean isVegetarian = s.nextBoolean();
			int caloriesPerOunce = s.nextInt();
			// Assign to maps
			priceMap.put(ingredientName, pricePerOunce);
			vegMap.put(ingredientName, isVegetarian);
			calMap.put(ingredientName, caloriesPerOunce);
		}
		// Recipes
		// Variables
		double calSum = 0;
		double cost = 0.0;
		boolean vegetarian = true;

		int numberOfRecipes = s.nextInt();
		// Recipe Loop
		for (int i = 0; i < numberOfRecipes; i++) {
			String recipeName = s.next();
			int ingredientNumber = s.nextInt();
			// Ingredient Loop
			for (int a = 0; a < ingredientNumber; a++) {
				String ingredientName = s.next(); // It gets executed until at
				// least here
				double ounces = s.nextDouble();
				// Get calories
				int b = calMap.get(ingredientName).intValue();
				calSum += (b * ounces);
				// Get cost
				double c = priceMap.get(ingredientName).doubleValue();
				cost += (c * ounces);
				// Get veg status
				boolean d = vegMap.get(ingredientName).booleanValue();
				if (d == false) {
					vegetarian = false;
				}
			}
			System.out.println(recipeName + ":");
			System.out.println(((int) (calSum + 0.5)) + " calories");
			System.out.println("$" + ((int) ((cost * 100.0) + 0.5)) / 100.0);
			if (vegetarian == true) {
				System.out.println("Vegetarian");
			} else {
				System.out.println("Non-Vegetarian");
			}
			calSum = 0;
			cost = 0.0;
		}
	}
}