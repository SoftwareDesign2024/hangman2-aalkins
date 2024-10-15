package game;

import java.util.ArrayList;

import util.HangmanDictionary;

public class ExecutionerCheater extends Executioner{
	//fields
	private HangmanGame gameController;
	
	private ArrayList<Character> lettersGuessed;
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	private int wordLength;
	
	private int guessesMade = 0;
	
	
	public ExecutionerCheater(int wordLength){
		super(wordLength);
		this.wordLength = wordLength;
		lettersGuessed = new ArrayList<>();
	}
	
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
	
	public String pickNewWord(HangmanDictionary dictionary) {
		String newWord = super.mySecretWord;
		boolean wordWorks = false;
		while ((newWord.equals(super.mySecretWord) || !wordWorks) && guessesMade < 6) {
			newWord = dictionary.getRandomWord(wordLength);
			wordWorks = checkWordWorks(newWord);
			System.out.println("New attempt: " + newWord);
		}
		return newWord;
	}
	
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
