import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizItem {
    private String prompt;
    private String[] choices;
    private int correctChoiceIndex;

    public QuizItem(String prompt, String[] choices, int correctChoiceIndex) {
        this.prompt = prompt;
        this.choices = choices;
        this.correctChoiceIndex = correctChoiceIndex;
    }

    public String getPrompt() {
        return prompt;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectChoiceIndex() {
        return correctChoiceIndex;
    }
}

class Assessment {
    private QuizItem[] quizItems;
    private int currentItemIndex;
    private int score;
    private int[] userResponses;
    private static final int TIME_LIMIT = 10; // seconds per question
    private Timer timer;
    private boolean timeExpired;

    public Assessment(QuizItem[] quizItems) {
        this.quizItems = quizItems;
        this.currentItemIndex = 0;
        this.score = 0;
        this.userResponses = new int[quizItems.length];
    }

    public void administerQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (currentItemIndex = 0; currentItemIndex < quizItems.length; currentItemIndex++) {
            timeExpired = false;
            displayQuizItem();

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeExpired = true;
                    System.out.println("\nTime's up!");
                    userResponses[currentItemIndex] = -1; // mark as unanswered
                    timer.cancel();
                }
            }, TIME_LIMIT * 1000);

            int response = -1;
            if (!timeExpired) {
                System.out.print("Your response: ");
                response = scanner.nextInt();
                timer.cancel();
            }

            userResponses[currentItemIndex] = response;
            if (response == quizItems[currentItemIndex].getCorrectChoiceIndex() + 1) {
                score++;
            }
        }

        scanner.close();
        displayResults();
    }

    private void displayQuizItem() {
        QuizItem quizItem = quizItems[currentItemIndex];
        System.out.println("Question " + (currentItemIndex + 1) + ": " + quizItem.getPrompt());
        for (int i = 0; i < quizItem.getChoices().length; i++) {
            System.out.println((i + 1) + ". " + quizItem.getChoices()[i]);
        }
    }

    private void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + quizItems.length);
        System.out.println("Summary:");
        for (int i = 0; i < quizItems.length; i++) {
            QuizItem quizItem = quizItems[i];
            System.out.println("Question " + (i + 1) + ": " + quizItem.getPrompt());
            System.out.println("Correct Answer: " + quizItem.getChoices()[quizItem.getCorrectChoiceIndex()]);
            if (userResponses[i] == -1) {
                System.out.println("Your Response: Time's up!");
            } else {
                System.out.println("Your Response: " + quizItem.getChoices()[userResponses[i] - 1]);
            }
            System.out.println();
        }
    }
}

public class QuizApplicationWithTimer {
    public static void main(String[] args) {
        QuizItem[] quizItems = new QuizItem[] {
            new QuizItem("What is the capital of France?", new String[] {"Berlin", "Madrid", "Paris", "Rome"}, 2),
            new QuizItem("Which planet is known as the Red Planet?", new String[] {"Earth", "Mars", "Jupiter", "Saturn"}, 1),
            new QuizItem("What is the largest ocean on Earth?", new String[] {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3),
            // Add more quiz items as needed
        };

        Assessment assessment = new Assessment(quizItems);
        assessment.administerQuiz();
    }
}
