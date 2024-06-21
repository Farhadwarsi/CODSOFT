import java.util.Scanner;

public class Studentgradecalculator {
    private static final int MAX_MARKS = 100;
    private static final String[] GRADE_DESCRIPTIONS = {"Excellent", "Good", "Fair", "Pass", "Fail"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSubjects = getNumberOfSubjects(scanner);
        int[] marks = getMarksFromUser(scanner, numSubjects);

        int totalMarks = calculateTotalMarks(marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);
        char grade = determineGrade(averagePercentage);
        String gradeDescription = getGradeDescription(grade);

        displayResults(totalMarks, averagePercentage, grade, gradeDescription);

        scanner.close();
    }

    private static int getNumberOfSubjects(Scanner scanner) {
        System.out.print("Enter the number of subjects: ");
        return scanner.nextInt();
    }

    private static int[] getMarksFromUser(Scanner scanner, int numSubjects) {
        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of " + MAX_MARKS + "): ");
            marks[i] = scanner.nextInt();
            validateMark(marks[i]);
        }
        return marks;
    }

    private static void validateMark(int mark) {
        if (mark < 0 || mark > MAX_MARKS) {
            System.out.println("Invalid mark. Please enter a value between 0 and " + MAX_MARKS + ".");
            System.exit(1); // exit with error code
        }
    }

    private static int calculateTotalMarks(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return totalMarks;
    }

    private static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private static char determineGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static String getGradeDescription(char grade) {
        switch (grade) {
            case 'A':
                return GRADE_DESCRIPTIONS[0];
            case 'B':
                return GRADE_DESCRIPTIONS[1];
            case 'C':
                return GRADE_DESCRIPTIONS[2];
            case 'D':
                return GRADE_DESCRIPTIONS[3];
            default:
                return GRADE_DESCRIPTIONS[4];
        }
    }

    private static void displayResults(int totalMarks, double averagePercentage, char grade, String gradeDescription) {
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Grade: " + grade + " (" + gradeDescription + ")");
    }
}
