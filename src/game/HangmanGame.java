package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;
//Adam Alkins

/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class HangmanGame {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // how many guesses are remaining
    private int myNumGuessesLeft;
    // what is shown to the user
    private DisplayWord myDisplayWord;
    //Executioner
    private Executioner executioner;
    //Guesser
    private Guesser guesser;
    


    /**
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGame (HangmanDictionary dictionary, int wordLength, int numGuesses, Guesser guesser, Executioner executioner) {
        this.executioner = executioner;
        myNumGuessesLeft = numGuesses;
        this.guesser = guesser;
        myDisplayWord = new DisplayWord(executioner.getSecretWord());
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            gameOver = singleGuessMade(gameOver);
        }
        System.out.println("The secret word was " + executioner.getSecretWord());
    }

	public boolean singleGuessMade(boolean gameOver) {
		printStatus();

		String guess = guesser.guessEntry();
		if (guess.length() == 1 && Character.isAlphabetic(guess.charAt(0))) {
		    int indexOfGuess = guesser.makeGuess(guess.toLowerCase().charAt(0));
		    //DEBUG
		    
		    System.out.println("Current Word: " + executioner.getSecretWord());
		    
		    
		    if (! checkGuessInSecret(guess.charAt(0)) && indexOfGuess >= 0) {
		           myNumGuessesLeft -= 1;
		        }
		    
		    if (isGameLost()) {
		        System.out.println("YOU ARE HUNG!!!");
		        gameOver = true;
		    }
		    else if (isGameWon()) {
		        System.out.println("YOU WIN!!!");
		        gameOver = true;
		    }
		}
		else {
		    System.out.println("Please enter a single letter ...");
		}
		return gameOver;
	}


    // Process a guess by updating the necessary internal state.
//    private void makeGuess (char guess) {
//        // do not count repeated guess as a miss
//        int index = myLettersLeftToGuess.indexOf("" + guess);
//        if (index >= 0) {
//            recordGuess(index);
//            if (! checkGuessInSecret(guess)) {
//                myNumGuessesLeft -= 1;
//            }
//        }
//    }


    // Returns true only if given guess is in the secret word.
    private boolean checkGuessInSecret (char guess) {
        if (executioner.letterInWord(guess)) {
            myDisplayWord.update(guess, executioner.getSecretWord());
            return true;
        }
        return false;
    }


    // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon () {
        return myDisplayWord.equals(executioner.getSecretWord());
    }

    // Returns true only if the guesser has used up all their chances to guess.
    private boolean isGameLost () {
        return myNumGuessesLeft == 0;
    }

    // Print game stats
    private void printStatus () {
        System.out.println(myDisplayWord);
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + guesser.getLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        //System.out.println("*** " + mySecretWord);
        System.out.println();
    }
    
    //Used for extended functionalities
    
    public Guesser getGuesser() {
    	return guesser;
    }
}
