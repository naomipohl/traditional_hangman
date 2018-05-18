package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HangmanGame {
	
	public static void main(String[] args) throws IOException {
		
	DictionaryReader dict = new DictionaryReader();
	
	ComputerPlay comp = new ComputerPlay();
	
	// first check and clean the words file
	File file = new File("/Users/naomi/Downloads/words.txt");
	dict.checkDictionary(file);
	
	// computer picks a random word from the dictionary
	String word = dict.pickRandomWord(file);
	
	// represent the word by a row of underscores
	String underscores = comp.showUnderscores(word);
	
	// print the underscores with spaces between them, for readability
	System.out.println("Here is the random word as a string of underscores: " 
			+ comp.printSpacesBetweenLetters(underscores));
	
	// initialize incorrect guesses list
	List<String> incorrect = new ArrayList<String>();
	
	// initialize scanner
	Scanner scanner = new Scanner(System.in);
	
	// iterate until the game is over
	while (!comp.isGameOver(word, underscores, incorrect)) {
		// user guesses a letter
		System.out.println("Guess a letter");
		String guess = scanner.nextLine();
		// convert the letter to lower case
		guess = guess.toLowerCase();
		
		// check for invalid input (user can only guess one letter at a time)
		while (guess.length() > 1) {
			System.out.println("You can only guess one letter at a time");
			System.out.println("Guess a letter");
			guess = scanner.nextLine();
		}
		
		// get the index of the guessed letter
		int guessIndex = word.indexOf(guess);
		
		// initialize list of indices of the guessed letter
		List<Integer> guessIndices = new ArrayList<Integer>();
		
		// get all indices of the guessed letter
		while (guessIndex >= 0) {
			guessIndices.add(guessIndex);
		    guessIndex = word.indexOf(guess, guessIndex + 1);
		}
		
		// turn the underscores string into an arraylist
		List<String> underscoresList = new ArrayList<String>(Arrays.asList(underscores.split("")));
				
		if (guessIndices.isEmpty()) {
			// if the guess isn't already in the incorrect guesses list, add it
			if (comp.isInList(guess, incorrect) == false) {
				// tell the user the guess isn't in the word
				System.out.println("Your guess isn't in this word.");
				incorrect.add(guess);
			}
			else {
				// tell the user they've already guessed that letter
				System.out.println("You already guessed that letter!");
			}
		}
		else {
			// if the guess is in the word, find all instances of the letter in the word
			// and replace the underscores with that letter
			for (int i = 0; i < guessIndices.size(); i++) {
				underscoresList.set(guessIndices.get(i), guess);
				
			}
			System.out.println("Good guess!");
			// convert underscoresList back to a string and print it
			String joinedUnderscores = String.join(",", underscoresList);
			String finalString = joinedUnderscores.replace(",","");
			// set underscores equal to finalString
			underscores = finalString;
			// print out the board for the user with spaces between the underscores
			System.out.println("Here is the hangman game now: " + 
			comp.printSpacesBetweenLetters(underscores));
		}
		
		// print incorrect guesses list
		System.out.println("Incorrect Guesses: " + incorrect);
	
	}
	// close scanner
	scanner.close();
	
	}

}
