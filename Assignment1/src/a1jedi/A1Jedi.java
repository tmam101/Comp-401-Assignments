package a1jedi;

import java.util.Scanner;

public class A1Jedi {

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

		// Grade Arrays for calculations
		double[] assignment4Array = new double[numberOfStudents];
		double[] recitation4Array = new double[numberOfStudents];
		int[] midterm1GradesArray = new int[numberOfStudents];
		int[] midterm2GradesArray = new int[numberOfStudents];
		int[] finalGradesArray = new int[numberOfStudents];

		for (int i = 0; i < numberOfStudents; i++) {
			// For each student
			double assignmentPointsSum = 0;
			String firstName = s.next();
			String lastName = s.next();
			int numberOfRecitations = s.nextInt();
			for (int a = 0; a < numberOfAssignments; a++) {
				double assignmentPoints = s.nextDouble();
				assignmentPointsSum += assignmentPoints;
			}
			int midterm1Grade = s.nextInt();
			int midterm2Grade = s.nextInt();
			int finalGrade = s.nextInt();

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
			} else if (recitationPercentage < 0.95) {
				recitation4 = 1.5 + ((recitationPercentage - .7) * 10);
			} else if (recitationPercentage >= 0.95) {
				recitation4 = 4.0;
			}
			System.out.println("recitation percentage is " + recitationPercentage);
			System.out.println("recitation 4 is " + recitation4);

			// Assigning Arrays
			midterm1GradesArray[i] = midterm1Grade;
			midterm2GradesArray[i] = midterm2Grade;
			finalGradesArray[i] = finalGrade;
			assignment4Array[i] = assignment4;
			recitation4Array[i] = recitation4;
		}

		// Midterm1
		// Find Standard Deviation
		double midterm1StandardDeviation = 0.0;
		// Find Mean
		int midterm1GradesSum = 0;
		int midterm1Mean = 0;
		for (int a = 0; a < midterm1GradesArray.length; a++) {
			midterm1GradesSum += midterm1GradesArray[a];
		}
		midterm1Mean = midterm1GradesSum / midterm1GradesArray.length;
		// Subtract the Mean and square the result
		double[] xi = new double[midterm1GradesArray.length];
		double gradeMinusMeanSquared = 0.0;
		for (int a = 0; a < midterm1GradesArray.length; a++) {
			gradeMinusMeanSquared = (midterm1GradesArray[a] - midterm1Mean) * (midterm1GradesArray[a] - midterm1Mean);
			xi[a] = gradeMinusMeanSquared;
		}
		// Mean of Squared Differences
		int midterm1xiSum = 0;
		double midterm1xiMean = 0.0;
		for (int a = 0; a < midterm1GradesArray.length; a++) {
			midterm1xiSum += xi[a];
		}
		midterm1xiMean = midterm1xiSum / midterm1GradesArray.length;
		midterm1StandardDeviation = Math.sqrt(midterm1xiMean);
		// Normalized
		double[] normalizedScore = new double[midterm1GradesArray.length];
		for (int a = 0; a < midterm1GradesArray.length; a++) {
			normalizedScore[a] = (midterm1GradesArray[a] - midterm1Mean) / midterm1StandardDeviation;
		}

		// Midterm2
		// Find Standard Deviation
		double midterm2StandardDeviation = 0.0;
		// Find Mean
		int midterm2GradesSum = 0;
		int midterm2Mean = 0;
		for (int a = 0; a < midterm2GradesArray.length; a++) {
			midterm2GradesSum += midterm2GradesArray[a];
		}
		midterm2Mean = midterm2GradesSum / midterm2GradesArray.length;
		// Subtract the Mean and square the result
		double[] xi2 = new double[midterm2GradesArray.length];
		double gradeMinusMeanSquared2 = 0.0;
		for (int a = 0; a < midterm2GradesArray.length; a++) {
			gradeMinusMeanSquared2 = (midterm2GradesArray[a] - midterm2Mean) * (midterm2GradesArray[a] - midterm2Mean);
			xi2[a] = gradeMinusMeanSquared2;
		}
		// Mean of Squared Differences
		int midterm2xiSum = 0;
		double midterm2xiMean = 0.0;
		for (int a = 0; a < midterm2GradesArray.length; a++) {
			midterm2xiSum += xi2[a];
		}
		midterm2xiMean = midterm2xiSum / midterm2GradesArray.length;
		midterm2StandardDeviation = Math.sqrt(midterm2xiMean);
		// Normalized
		double[] normalizedScore2 = new double[midterm2GradesArray.length];
		for (int a = 0; a < midterm2GradesArray.length; a++) {
			normalizedScore2[a] = (midterm2GradesArray[a] - midterm2Mean) / midterm2StandardDeviation;
		}

		// Final
		// Find Standard Deviation
		double finalStandardDeviation = 0.0;
		// Find Mean
		int finalGradesSum = 0;
		int finalMean = 0;
		for (int a = 0; a < finalGradesArray.length; a++) {
			finalGradesSum += finalGradesArray[a];
		}
		finalMean = finalGradesSum / finalGradesArray.length;
		// Subtract the Mean and square the result
		double[] xiF = new double[finalGradesArray.length];
		double gradeMinusMeanSquaredF = 0.0;
		for (int a = 0; a < finalGradesArray.length; a++) {
			gradeMinusMeanSquaredF = (finalGradesArray[a] - finalMean) * (finalGradesArray[a] - finalMean);
			xiF[a] = gradeMinusMeanSquaredF;
		}
		// Mean of Squared Differences
		int finalxiSum = 0;
		double finalxiMean = 0.0;
		for (int a = 0; a < finalGradesArray.length; a++) {
			finalxiSum += xi2[a];
		}
		finalxiMean = finalxiSum / finalGradesArray.length;
		finalStandardDeviation = Math.sqrt(finalxiMean);
		// Normalized
		double[] normalizedScoreF = new double[finalGradesArray.length];
		for (int a = 0; a < finalGradesArray.length; a++) {
			normalizedScoreF[a] = (finalGradesArray[a] - finalMean) / finalStandardDeviation;
		}

		// Normalized Score to 4.0
		// The calculations here might be wrong
		double[] normalizedScoreTo4 = new double[normalizedScore.length];
		for (int a = 0; a < normalizedScore.length; a++) {
			if (normalizedScore[a] <= -2.0) {
				normalizedScoreTo4[a] = 0.0;
			} else if (normalizedScore[a] <= .7) {
				normalizedScoreTo4[a] = ((normalizedScore[a] - .4) * 5);
			} else if (normalizedScore[a] >= .7) {
				normalizedScoreTo4[a] = 1.5 + ((normalizedScore[a] - .7) * 10);
			} else if (normalizedScore[a] >= 1.0) {
				normalizedScoreTo4[a] = 4.0;
			}
		}

		// Normalized Score 2 to 4.0
		// The calculations here might be wrong
		double[] normalizedScore2To4 = new double[normalizedScore2.length];
		for (int a = 0; a < normalizedScore2.length; a++) {
			if (normalizedScore2[a] <= -2.0) {
				normalizedScore2To4[a] = 0.0;
			} else if (normalizedScore2[a] <= .7) {
				normalizedScore2To4[a] = ((normalizedScore2[a] - .4) * 5);
			} else if (normalizedScore2[a] >= .7) {
				normalizedScore2To4[a] = 1.5 + ((normalizedScore2[a] - .7) * 10);
			} else if (normalizedScore2[a] >= 1.0) {
				normalizedScore2To4[a] = 4.0;
			}
		}

		// Normalized Score Final to 4.0
		// The calculations here might be wrong
		double[] normalizedScoreFTo4 = new double[normalizedScoreF.length];
		for (int a = 0; a < normalizedScoreF.length; a++) {
			if (normalizedScoreF[a] <= -2.0) {
				normalizedScoreFTo4[a] = 0.0;
			} else if (normalizedScoreF[a] <= .7) {
				normalizedScoreFTo4[a] = ((normalizedScoreF[a] - .4) * 5);
			} else if (normalizedScoreF[a] >= .7) {
				normalizedScoreFTo4[a] = 1.5 + ((normalizedScoreF[a] - .7) * 10);
			} else if (normalizedScoreF[a] >= 1.0) {
				normalizedScoreFTo4[a] = 4.0;
			}
		}
		double[] classGrade = new double[numberOfStudents];
		for (int i = 0; i < numberOfStudents; i++) {
			classGrade[i] = (assignment4Array[i] * .4) + (recitation4Array[i] * .1) + (midterm1GradesArray[i] * .15)
					+ (midterm2GradesArray[i] * .15) + (finalGradesArray[i] * .2);
		}
		// Assign to Letter
		String[] letterGrade = new String[numberOfStudents];
		for (int i = 0; i < numberOfStudents; i++) {
			if (classGrade[i] >= 3.85) {
				letterGrade[i] = "A";
			} else if (3.5 <= classGrade[i]) {
				letterGrade[i] = "A-";
			} else if (3.15 <= classGrade[i]) {
				letterGrade[i] = "B+";
			} else if (2.85 <= classGrade[i]) {
				letterGrade[i] = "B";
			} else if (2.5 <= classGrade[i]) {
				letterGrade[i] = "B-";
			} else if (2.15 <= classGrade[i]) {
				letterGrade[i] = "C+";
			} else if (1.85 <= classGrade[i]) {
				letterGrade[i] = "C";
			} else if (1.5 <= classGrade[i]) {
				letterGrade[i] = "C-";
			} else if (1.15 <= classGrade[i]) {
				letterGrade[i] = "D+";
			} else if (0.85 <= classGrade[i]) {
				letterGrade[i] = "D";
			} else {
				letterGrade[i] = "F";
			}
		}
		int countA = 0;
		int countAMinus = 0;
		int countBPlus = 0;
		int countB = 0;
		int countBMinus = 0;
		int countCPlus = 0;
		int countC = 0;
		int countCMinus = 0;
		int countDPlus = 0;
		int countD = 0;
		int countF = 0;
		for (int i = 0; i < letterGrade.length; i++) {
			if (letterGrade[i] == "A") {
				countA++;
			} else if (letterGrade[i] == "A-") {
				countAMinus++;
			} else if (letterGrade[i] == "B+") {
				countBPlus++;
			} else if (letterGrade[i] == "B") {
				countB++;
			} else if (letterGrade[i] == "B-") {
				countBMinus--;
			} else if (letterGrade[i] == "C+") {
				countCPlus++;
			} else if (letterGrade[i] == "C") {
				countC++;
			} else if (letterGrade[i] == "C-") {
				countCMinus++;
			} else if (letterGrade[i] == "D+") {
				countDPlus++;
			} else if (letterGrade[i] == "D") {
				countD++;
			} else if (letterGrade[i] == "F") {
				countF++;
			}
		}

		// Tests
		for (int i = 0; i < classGrade.length; i++) {
			System.out.println(classGrade[0]);
		}
		for (int i = 0; i < midterm1GradesArray.length; i++) {
			System.out.println("Midterm 1 grade " + i + " is: " + midterm1GradesArray[i]);
		}
		for (int i = 0; i < midterm2GradesArray.length; i++) {
			System.out.println("Midterm 2 grade " + i + " is: " + midterm2GradesArray[i]);
		}
		for (int i = 0; i < finalGradesArray.length; i++) {
			System.out.println("Final grade " + i + " is: " + finalGradesArray[i]);
		}
		for (int i = 0; i < recitation4Array.length; i++) {
			System.out.println("Recitation grade " + i + " is: " + recitation4Array[i]);
		}
		for (int i = 0; i < assignment4Array.length; i++) {
			System.out.println("Assignment grade " + i + " is: " + assignment4Array[i]);
		}
		// Printing
		System.out.println("A : " + countA);
		System.out.println("A- : " + countAMinus);
		System.out.println("B+: " + countBPlus);
		System.out.println("B : " + countB);
		System.out.println("B- : " + countBMinus);
		System.out.println("C+ : " + countCPlus);
		System.out.println("C : " + countC);
		System.out.println("C- : " + countCMinus);
		System.out.println("D+ : " + countDPlus);
		System.out.println("D : " + countD);
		System.out.println("F: " + countF);
	}
}