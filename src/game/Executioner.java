package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;
//Adam Alkins
public class Executioner {
    // word that is being guessed
    protected String mySecretWord;
    // Contains word list, we only have one right now. If you want to pick
    // new list, change dictionary
    protected HangmanDictionary dictionary = new HangmanDictionary("HangmanLabFiles/data/lowerwords.txt");
	
    // Returns a secret word.
    public String makeSecretWord (int wordLength) {
        return dictionary.getRandomWord(wordLength).toLowerCase();
    }
    
    // Used in hangman play code to find access the secret code
    public String getSecretWord() {
    	return mySecretWord;
    }
    
    public boolean letterInWord(char guess) {
    	if (mySecretWord.indexOf(guess) >= 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //Constructor, only used in game startup
    public Executioner(int wordLength) {
    	mySecretWord = makeSecretWord(wordLength);
    }
}