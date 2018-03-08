package a1novice;

import java.util.Scanner;

public class A1Novice {

	// Do not change the main method.
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);
	}

	public static void process(Scanner s) {
		int numberOfStudents = s.nextInt();

		for (int i = 0; i <= numberOfStudents; i++) {

			String firstName = s.next();
			String lastName = s.next();
			double grade1 = s.nextDouble();
			double grade2 = s.nextDouble();
			double grade3 = s.nextDouble();
			double grade4 = s.nextDouble();
			double grade5 = s.nextDouble();

			String letterGrade = new String();

			// Determine the Weighted Average and assign it to a letter grade.
			double WA = ((grade1 * .4) + (grade2 * .1) + (grade3 * .15) + (grade4 * .15) + (grade5 * .2));
			if (WA >= 3.85) {
				letterGrade = "A";
			} else if (3.5 <= WA) {
				letterGrade = "A-";
			} else if (3.15 <= WA) {
				letterGrade = "B+";
			} else if (2.85 <= WA) {
				letterGrade = "B";
			} else if (2.5 <= WA) {
				letterGrade = "B-";
			} else if (2.15 <= WA) {
				letterGrade = "C+";
			} else if (1.85 <= WA) {
				letterGrade = "C";
			} else if (1.5 <= WA) {
				letterGrade = "C-";
			} else if (1.15 <= WA) {
				letterGrade = "D+";
			} else if (0.85 <= WA) {
				letterGrade = "D";
			} else {
				letterGrade = "F";
			}

			// Print out the results.
			System.out.println(firstName.charAt(0) + "." + " " + lastName + " " + letterGrade);

		}
	}

	// Put your code here.
}
