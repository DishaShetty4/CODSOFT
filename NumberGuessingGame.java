package project;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static int totalRounds = 0;
    private static int totalAttempts = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            totalRounds++;
            int numberToGuess = random.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I'm thinking of a number between " + MIN + " and " + MAX + ".");

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Enter your guess (" + (MAX_ATTEMPTS - attempts) + " attempts left): ");
                int userGuess = scanner.nextInt();
                attempts++;
                totalAttempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    guessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("Game over! Here are your stats:");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total attempts made: " + totalAttempts);
        scanner.close();
    }
}