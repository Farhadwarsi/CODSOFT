import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int NUMBER_RANGE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int bestScore = Integer.MAX_VALUE;

        while (playAgain) {
            int attempts = playRound(scanner, random);
            if (attempts!= -1 && attempts < bestScore) {
                bestScore = attempts;
                System.out.println("New best score! You guessed the number in " + attempts + " attempts.");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Your overall best score was " + bestScore + " attempts.");
        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();
    }

    private static int playRound(Scanner scanner, Random random) {
        int numberToGuess = random.nextInt(NUMBER_RANGE) + 1;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've chosen a number between 1 and " + NUMBER_RANGE + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the correct number.");

        while (attempts < MAX_ATTEMPTS &&!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Woohoo! You guessed the correct number.");
                    hasGuessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again. You're getting closer!");
                } else {
                    System.out.println("Too high! Try again. You're almost there!");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + NUMBER_RANGE + ".");
                scanner.next(); // discard invalid input
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
            return -1; // indicate that the user didn't guess correctly
        }

        return attempts;
    }
}
