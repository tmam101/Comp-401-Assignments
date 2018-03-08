package a2jedi;

import java.util.Scanner;

public class A2Jedi {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Your code goes here.

		// Ingredients
		int numberOfIngredients = s.nextInt();
		for (int i = 0; i < numberOfIngredients; i++) {
			// User input
			String ingredientName = s.next();
			double pricePerOunce = s.nextDouble();
			boolean isVegetarian = s.nextBoolean();
			int caloriesPerOunce = s.nextInt();
		}

		// Recipes
		// Variables
		int numberOfRecipes = s.nextInt();
		// I might need to double check the lengths on these arrays and maybe
		// make them jagged
		String[] recipeNameA = new String[numberOfRecipes];
		String[][] ingredientNeededA = new String[numberOfIngredients][numberOfIngredients];
		double[][] ouncesNeededA = new double[numberOfRecipes][numberOfIngredients];
		// Recipe Loop
		for (int i = 0; i < numberOfRecipes; i++) {
			String recipeName = s.next();
			int ingredientNumber = s.nextInt();
			recipeNameA[i] = recipeName;
			// Ingredient Loop
			for (int a = 0; a < ingredientNumber; a++) {
				String ingredientName = s.next();
				double ounces = s.nextDouble();
				ingredientNeededA[i][a] = ingredientName;
				ouncesNeededA[i][a] = ounces;
			}
		}

		double riceSum = 0.0;
		double seaweedSum = 0.0;
		double avocadoSum = 0.0;
		double salmonSum = 0.0;
		double yellowtailSum = 0.0;
		double eelSum = 0.0;

		for (int i = 0; i < ingredientNeededA.length; i++) {
			// System.out.println(recipeNameA[i]);
			for (int a = 0; a < ouncesNeededA.length; a++) {
				// if (ingredientNeededA[i][a] == null) {
				// return; // return to the previous for loop?
				// }
				// if (ouncesNeededA[i][a] == 0.0) {
				// return; // return to the previous for loop?
				// }
				System.out.println(ingredientNeededA[i][a]);
				System.out.println(ouncesNeededA[i][a]);
				if (ingredientNeededA[i][a].equals("Rice")) {
					riceSum += ouncesNeededA[i][a];
				} else if (ingredientNeededA[i][a].equals("Avocado")) {
					avocadoSum += ouncesNeededA[i][a];
				} else if (ingredientNeededA[i][a].equals("Seaweed")) {
					seaweedSum += ouncesNeededA[i][a];
				}
			}
		}

		// Order
		for (int i = 0; i < 100; i++) {
			String orderItem = s.next();
			// for (int a = 0; a < numberOfRecipes; a++) {
			//
			// }
			if (orderItem.equals("EndOrder")) {
				return;
			}
		}
		// idk where this should go yet
		System.out.println("This order will require:");
		System.out.println(riceSum + " ounces of Rice");
		System.out.println(seaweedSum + " ounces of Seaweed");
		System.out.println(avocadoSum + " ounces of Avocado");
		System.out.println(salmonSum + " ounces of Salmon");
		System.out.println(yellowtailSum + " ounces of Yellowtail");
		System.out.println(eelSum + " ounces of Eel");
	}
}