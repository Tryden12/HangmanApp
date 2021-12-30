import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author tylerryden
 * 
 * 
 * 
 * This program is a Hangman game
 * It has the option for single-player or multi-player
 * For the single player, a random word will be generated
 * 
 * 
 */
public class Hangman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
            
        // Create a scanner for file input
        Scanner sc = new Scanner(new File("/Users/tylerryden/Documents/ProgrammingPractice/Java/words.txt"));
        Scanner keyboard = new Scanner(System.in);

        
        System.out.println("1 or 2 players?");
        String player = keyboard.nextLine();
        String word;
        
        
        if (player.equals("1")) {
           // Create an list/array of words
        List<String> words = new ArrayList<>();
        while(sc.hasNext()) {
            words.add(sc.nextLine());
        }
      
        // Create a random class to allow for random selection of a word 
        // for the single player option
        Random rand = new Random();
        
        // Below will be the random word 
        // This assigns a random word by picking a random index
        // from the entire list using size() method to include all words
        // from the file
        word = words.get(rand.nextInt(words.size()));
        // Print to screen to test:
        //System.out.println(word); 
        
        
        } else {
            System.out.println("Player 1, please enter your word:");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }

        // Create a list of letter guesses
        List<Character> playerGuesses = new ArrayList();
                
        int wrongCount = 0;
        while(true){
            
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }
            // Method for passing in:
            // the random, selected word
            // player letter guesses (stored in an ArrayList)
            printWordState(word, playerGuesses);
            
            // Scans for input from user
            if (!getPlayerGuess(keyboard, playerGuesses, word)) {
                wrongCount++;
            }  
            // Print game state again
            if (printWordState(word, playerGuesses)){
                System.out.println("You win!");  
                break;
            }
            System.out.println("Please enter your guess for the word:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");  
                break;
            } else {
                System.out.println("Nope! Try again.");
            }
        }
    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" ------");
        System.out.println(" |    |");
        if(wrongCount >= 1){
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3){
                System.out.println("/ ");
            }
            else {
                System.out.println("");
            }
        }
        if(wrongCount >= 4){
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6){
                System.out.print("\\");
            }
            else {
                System.out.println("");
            }
        }
        
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, List<Character> playerGuesses, String word) {
        // Get input from user (scanner keyboard)
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        System.out.print("The word is: ");
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
            
        } 
        System.out.println();
        
        return (word.length() == correctCount);
    }
        
    
}
