import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        String[] list = wordList("listOfWords.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number between 1 and 400 inclusive");
        int input = scanner.nextInt();


        String a = list[input + 5];
        char[] display = new char[a.length()];
        Arrays.fill(display, '_');


        int guesses = 7;
        while (guesses > 0) {
            System.out.println(display);
            char[] previousDisplay = Arrays.copyOf(display, display.length);
            guessProcess(a, display);


            if (Arrays.equals(previousDisplay, display)) {
                System.out.println("Incorrect.");
                guesses--;
            } else {
                System.out.println("Correct.");
            }


            System.out.println(guesses + " guesses remaining");


            String disp = new String(display);
            if (!disp.contains("_")) {
                System.out.println("You win.");
                System.out.println("Correct answer: " + a);
                break;
            }
        }


        if (guesses == 0) {
            System.out.println("You lost.");
            System.out.println("Correct answer: " + a);
        }




        System.out.println("Play again? 1) Yes, 2) No");
        int again = scanner.nextInt();
        if (again==1) main(args);
        else{
            System.out.println("Thanks for playing.");
        }
    }


    public static char[] guessProcess(String word, char[] display) {
        System.out.println("Enter your character, Capital letters only:");
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);


        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == input && display[i] == '_') {
                display[i] = input;
                correctGuess = true;
            }
        }


        if (!correctGuess) {
            System.out.println("Incorrect guess.");
        }


        return display;
    }


    // Reads words file into an array of words
    public static String[] wordList(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);


        int numLines = countLinesInFile(filename);
        String[] wList = new String[numLines];
        for (int i = 0; i < numLines; i++) {
            if (scanner.hasNextLine()) {
                wList[i] = scanner.nextLine();
            }
        }


        scanner.close();
        return wList;
    }




    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    public static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }


}
