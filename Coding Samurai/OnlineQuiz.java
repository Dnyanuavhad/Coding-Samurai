import java.util.ArrayList;
import java.util.Scanner;

class Question {
    private String prompt;
    private String[] options;
    private int correctOptionIndex;

    public Question(String prompt, String[] options, int correctOptionIndex) {
        this.prompt = prompt;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getPrompt() { return prompt; }
    public String[] getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }

    public String getCorrectAnswerText() {
        return options[correctOptionIndex];
    }
}

public class OnlineQuiz {
    private static ArrayList<Question> quizBank = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupQuestions();
        runQuiz();
    }

    private static void setupQuestions() {
        quizBank.add(new Question(
            "What is the capital of France?",
            new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
            2
        ));

        quizBank.add(new Question(
            "Which programming language is known as the 'mother of all languages'?",
            new String[]{"1. Java", "2. C", "3. Python", "4. COBOL"},
            1
        ));

        quizBank.add(new Question(
            "What is 5 + 7?",
            new String[]{"1. 10", "2. 11", "3. 12", "4. 13"},
            2
        ));
    }

    private static void runQuiz() {
        int score = 0;
        ArrayList<String> summary = new ArrayList<>();

        System.out.println("=== WELCOME TO THE ONLINE QUIZ ===");
        System.out.println("Please enter the number of your choice.\n");

        for (int i = 0; i < quizBank.size(); i++) {
            Question q = quizBank.get(i);
            System.out.println("Question " + (i + 1) + ": " + q.getPrompt());
            
            for (String option : q.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Your Answer: ");
            int userChoice = scanner.nextInt() - 1;

            if (userChoice == q.getCorrectOptionIndex()) {
                score++;
                summary.add("Q" + (i + 1) + ": Correct");
            } else {
                summary.add("Q" + (i + 1) + ": Incorrect (Correct Answer: " + q.getCorrectAnswerText() + ")");
            }
            System.out.println();
        }

        System.out.println("--- QUIZ OVER ---");
        System.out.println("Your Final Score: " + score + "/" + quizBank.size());
        
        System.out.println("\nReview:");
        for (String line : summary) {
            System.out.println(line);
        }
    }
}