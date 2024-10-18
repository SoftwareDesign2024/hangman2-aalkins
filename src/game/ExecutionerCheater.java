package game;
//Adam Alkins

import java.util.ArrayList;

import util.HangmanDictionary;

public class ExecutionerCheater extends Executioner{
	//fields
	private HangmanGame gameController;
	
	private ArrayList<Character> lettersGuessed;
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	private int wordLength;
	
	private int guessesMade = 0;
	
	//CONSTRUCTOR
	public ExecutionerCheater(int wordLength){
		super(wordLength);
		this.wordLength = wordLength;
		lettersGuessed = new ArrayList<>();
	}

	//Takes the letters guessed by guesser and adds them to list know that it cannot pick words with those letters
	public boolean letterInWord(char guess) {
		guessesMade +=1;
		lettersGuessed.add(guess);
		if (super.mySecretWord.indexOf(guess) >= 0) {
			super.mySecretWord = pickNewWord(super.dictionary);
		}
		if (super.mySecretWord.indexOf(guess) >= 0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	//Picks new word after a player guesses
	public String pickNewWord(HangmanDictionary dictionary) {
		String newWord = super.mySecretWord;
		boolean wordWorks = false;
		//Makes sure the game is still possible and doesn't break the logic/rules
		while ((newWord.equals(super.mySecretWord) || !wordWorks) && guessesMade < 6) {
			newWord = dictionary.getRandomWord(wordLength);
			wordWorks = checkWordWorks(newWord);
			System.out.println("New attempt: " + newWord);
		}
		return newWord;
	}

	//Checks new words to see if it has any letters that have alredy been guessed
	public boolean checkWordWorks(String word) {
		boolean works = true;
		
		for (Character character : lettersGuessed) {
			if (word.contains(character.toString())) {
				return false;
			}
		}
		return works;
	}
}
