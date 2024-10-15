package game;
//Adam Alkins
import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

public class Guesser {
    // tracks letters guessed
    protected StringBuilder myLettersLeftToGuess;
    
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    
    
    // Process a guess by updating the necessary internal state. Returns index 
    // so that main program can check to see if letter was already guessed
    // against the secret word. 
    public int makeGuess (char guess) {
        // do not count repeated guess as a miss
        int index = myLettersLeftToGuess.indexOf("" + guess);
        if (index >= 0) {
            recordGuess(index);}
        return index;
    }
    
    //HOW YOU ENTER A GUESS
    public String guessEntry() {
    	String guess = ConsoleReader.promptString("Make a guess: ");
    	return guess;
    }
    
    // Record that a specific letter was guessed
    private void recordGuess (int index) {
        myLettersLeftToGuess.deleteCharAt(index);
 
    }
    
    //Set the letters left to guess.
    private void setMyLettersLeftToGuess(StringBuilder lettersLeft) {
    	myLettersLeftToGuess = lettersLeft;
    }
    // returns the letters left to guess
    public StringBuilder getLettersLeftToGuess() {
    	return myLettersLeftToGuess;
    }
    //Constructor, only used in game startup
    public Guesser() {
    	StringBuilder lettersLeft = new StringBuilder(ALPHABET);
    	setMyLettersLeftToGuess(lettersLeft);
    }
}