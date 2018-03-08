package a1adept;

import java.util.Scanner;

public class A1Adept {

	// Do not change the main method.
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);

	}

	public static void process(Scanner s) {
		// Put your code here.

		int numberOfAssignments = s.nextInt();
		int[] valueArray = new int[numberOfAssignments];
		int assignmentValueSum = 0;
		// Loop that creates assignments
		for (int i = 0; i < numberOfAssignments; i++) {
			int assignmentValue = s.nextInt();
			valueArray[i] = assignmentValue;
			assignmentValueSum += assignmentValue;
		}
		int numberOfStudents = s.nextInt();

		for (int i = 0; i < numberOfStudents; i++) {
			double assignmentPointsSum = 0;
			String firstName = s.next();
			String lastName = s.next();
			int numberOfRecitations = s.nextInt();
			for (int a = 0; a < numberOfAssignments; a++) {
				double assignmentPoints = s.nextDouble();
				assignmentPointsSum += assignmentPoints;
			}
			double midterm1Grade = s.nextDouble();
			double midterm2Grade = s.nextDouble();
			double finalGrade = s.nextDouble();

			double assignment4 = 0.0;
			double recitation4 = 0.0;
			double recitationPercentage = (numberOfRecitations / 15.0);
			double assignmentPercentage = assignmentPointsSum / assignmentValueSum;

			// Assignment to 4.0
			if (assignmentPercentage <= 0.4) {
				assignment4 = 0.0;
			} else if (assignmentPercentage <= .7) {
				assignment4 = ((assignmentPercentage - .4) * 5);
			} else if (assignmentPercentage >= .7) {
				assignment4 = 1.5 + ((assignmentPercentage - .7) * 10);
			} else if (assignmentPercentage >= 0.95) {
				assignment4 = 4.0;
			}

			// Recitation to 4.0
			if (recitationPercentage <= 0.4) {
				recitation4 = 0.0;
			} else if (recitationPercentage <= .7) {
				recitation4 = ((recitationPercentage - .4) * 5);
			} else if (recitationPercentage >= .7) {
				recitation4 = 1.5 + ((recitationPercentage - .7) * 10);
			} else if (recitationPercentage >= 0.95) {
				recitation4 = 4.0;
			}

			// Final Weighted Average
			double finalWeightedAverage = (assignment4 * .4) + (recitation4 * .1) + (midterm1Grade * .15)
					+ (midterm2Grade * .15) + (finalGrade * .2);

			// Assign to Letter
			String letterGrade;
			if (finalWeightedAverage >= 3.85) {
				letterGrade = "A";
			} else if (3.5 <= finalWeightedAverage) {
				letterGrade = "A-";
			} else if (3.15 <= finalWeightedAverage) {
				letterGrade = "B+";
			} else if (2.85 <= finalWeightedAverage) {
				letterGrade = "B";
			} else if (2.5 <= finalWeightedAverage) {
				letterGrade = "B-";
			} else if (2.15 <= finalWeightedAverage) {
				letterGrade = "C+";
			} else if (1.85 <= finalWeightedAverage) {
				letterGrade = "C";
			} else if (1.5 <= finalWeightedAverage) {
				letterGrade = "C-";
			} else if (1.15 <= finalWeightedAverage) {
				letterGrade = "D+";
			} else if (0.85 <= finalWeightedAverage) {
				letterGrade = "D";
			} else {
				letterGrade = "F";
			}

			System.out.println(firstName.charAt(0) + ". " + lastName + " " + letterGrade);

		}
	}
}
