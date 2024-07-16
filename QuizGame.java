package project;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {

    private static final int TIME_LIMIT = 10; // seconds per question
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int questionIndex = 0;

    public static void main(String[] args) {
        // Create questions
        createQuestions();
        // Start the quiz
        startQuiz();
        // Show results
        showResults();
    }

    // Create quiz questions and options
    private static void createQuestions() {
        questions.add(new Question("What is the capital of France?",
                new String[]{"A. Paris", "B. Berlin", "C. Madrid", "D. Rome"},
                'A'));

        questions.add(new Question("What is the largest planet in our solar system?",
                new String[]{"A. Earth", "B. Jupiter", "C. Saturn", "D. Neptune"},
                'B'));

        questions.add(new Question("Which element has the chemical symbol 'O'?",
                new String[]{"A. Gold", "B. Oxygen", "C. Osmium", "D. Silver"},
                'B'));

        // Add more questions as needed
    }

    // Start the quiz
    private static void startQuiz() {
        for (Question question : questions) {
            askQuestion(question);
            questionIndex++;
        }
    }

    // Ask a question and handle the timer
    private static void askQuestion(Question question) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime is up!");
                scanner.nextLine(); // Consume input to prevent blocking
            }
        };
        timer.schedule(task, TIME_LIMIT * 1000); // Schedule the timer

        System.out.println("\nQuestion " + (questionIndex + 1) + ": " + question.getQuestionText());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }

        System.out.print("Your answer (A, B, C, D): ");
        String userAnswer = scanner.nextLine().toUpperCase();

        // Cancel the timer after receiving input
        timer.cancel();

        if (userAnswer.length() == 1 && "ABCD".contains(userAnswer)) {
            if (userAnswer.charAt(0) == question.getCorrectAnswer()) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was " + question.getCorrectAnswer());
            }
        } else {
            System.out.println("Invalid input. Please enter A, B, C, or D.");
        }
    }

    // Show the results of the quiz
    private static void showResults() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.size());
        System.out.println("Thank you for participating!");
    }
}

// Question class to store the question text, options, and correct answer
class Question {
    private final String questionText;
    private final String[] options;
    private final char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}