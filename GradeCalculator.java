package project;

import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        
        // Validate number of subjects
        if (numberOfSubjects <= 0) {
            System.out.println("The number of subjects must be greater than 0.");
            return;
        }

        // Initialize array to store marks
        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        // Input: Marks for each subject
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            int mark = scanner.nextInt();

            // Validate the marks
            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                i--; // Retry input for this subject
            } else {
                marks[i] = mark;
                totalMarks += mark;
            }
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        // Determine Grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B+";
        } else if (averagePercentage >= 60) {
            grade = "B";
        } else if (averagePercentage >= 50) {
            grade = "C";
        } else {
            grade = "D";
        }

        // Display Results
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}