import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordCount {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String text = "";

        System.out.println("Word Count Program");
        System.out.println("1. Enter Text");
        System.out.println("2. Provide a File");

        int choice = -1;  

        while (choice != 1 && choice != 2) {
            try {
                System.out.print("Enter your choice (1 or 2): ");
                choice = inputScanner.nextInt();
                inputScanner.nextLine(); 
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                inputScanner.nextLine();
            }
        }

        if (choice == 1) {
            System.out.println("Enter the text:");
            text = inputScanner.nextLine();
        } else if (choice == 2) {
            System.out.print("Enter the path to the file: ");
            String filePath = inputScanner.nextLine();

            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + " ";
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found. Exiting.");
                System.exit(1);
            }
        }

        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;

    
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase()); 
        }

        System.out.println("Total Word Count: " + wordCount);
        System.out.println("Unique Word Count: " + uniqueWords.size());

        inputScanner.close();
    }
}
