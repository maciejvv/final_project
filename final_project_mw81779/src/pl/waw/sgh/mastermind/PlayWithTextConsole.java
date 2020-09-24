package pl.waw.sgh.mastermind;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PlayWithTextConsole {

    public static void main(String[] args) {

        while (true) {
            Scanner scan = new Scanner(System.in);
            int tries = 15;

            // main menu
            System.out.println("Welcome to Mastermind");
            System.out.println("Type d to adjust difficulty level");
            System.out.println("Type e to exit");
            System.out.println("Type anything else to play");

            String starter = scan.nextLine();
            if (starter.equals("d") || starter.equals("d ")) {
                tries = difficulty();
            }

            if (starter.equals("e") || starter.equals("e ")) {
                System.out.println("Goodbye");
                break;
            }

            // generating new code
            int[] code = new int[4];
            for (int i = 0; i < code.length; i++) {
                int r = new Random().nextInt(9);
                code[i] = r;
            }

            int score = 0;

            while (tries >= 1) {

                // collecting and processing player's guesses
                System.out.println(" ");
                System.out.println("Type your guess, separate the numbers using spaces");
                System.out.println("Tries left: " + tries);

                int[] guess = new int[4];

                try {
                String initialInput = scan.nextLine();
                String[] input = initialInput.split(" ");

                if (input[0].equals("e")) {
                    System.out.println("Exiting to main menu");
                    System.out.println(" ");
                    break;
                }

                if (input.length != 4) {
                        throw new IncorrectLengthException("Incorrect length, type 4 numbers");
                    }

                    for (int j = 0; j < code.length; j++) {
                        guess[j] = Integer.parseInt(input[j]);
                    }

                    if (guess[0] < 0 || guess[1] < 0 || guess[2] < 0 || guess[3] < 0) {
                        throw new IncorrectNumberException("Incorrect number, select a number from 0 to 9");
                    }

                    if (guess[0] > 9 || guess[1] > 9 || guess[2] > 9 || guess[3] > 9) {
                        throw new IncorrectNumberException("Incorrect number, select a number from 0 to 9");
                    }

                    // checking the amount of numbers in correct places
                    int correctPlace = 0;
                    for (int k = 0; k < code.length; k++) {
                        if (guess[k] == code[k]) {
                            correctPlace++;
                        }
                    }

                    System.out.println("Correct numbers in correct places: " + correctPlace);

                    // checking the amount of correct numbers, even incorrectly placed
                    int correctNum = 0;
                    int[] helperCode = new int[4];
                    System.arraycopy(code, 0, helperCode, 0, code.length);
                    int[] helperGuess = new int[4];
                    System.arraycopy(guess, 0, helperGuess, 0, guess.length);

                    for (int l = 0; l < code.length; l++) {
                        for (int m = 0; m < code.length; m++) {
                            if (helperGuess[l] == helperCode[m]) {
                                correctNum++;
                                helperCode[m] = 1000;
                                helperGuess[l] = 2000;
                            }

                        }
                    }
                    System.out.println("Correct numbers: " + correctNum);

                    score++;

                    // securing victory conditions
                    if (correctPlace == 4) {
                        System.out.println("You won in " + score + " tries");
                        break;
                    }

                    // decreasing the number of tries
                    tries--;

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IncorrectNumberException | IncorrectLengthException exception) {
                    System.out.println("Incorrect format, try again");
                }
            }

            if (tries == 0) {
                System.out.println("Game over");
            }

            // printing correct code
            System.out.println("Correct code: " + Arrays.toString(code));
            System.out.println(" ");
        }
    }

    // selecting the level of difficulty
    public static int difficulty() {

        System.out.println("Choose difficulty level, type: ");
        System.out.println("l for low difficulty");
        System.out.println("h for high difficulty");
        System.out.println("anything else for normal difficulty");

        Scanner s = new Scanner(System.in);
        String difficultyLevel = s.nextLine();

        if (difficultyLevel.equals("l") || difficultyLevel.equals("l ")) {
            return 20;
        }

        if (difficultyLevel.equals("h") || difficultyLevel.equals("h ")) {
            return 10;
        }

        else {
            return 15;
        }
    }
}