package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import util.HangmanDictionary;

public class GuesserBot extends Guesser{
	
	private String dictionaryPath = "HangmanLabFiles/data/lowerwords.txt";
	
	private HangmanDictionary dictionary = new HangmanDictionary(dictionaryPath);
	
	private int wordLength;
	
	private ArrayList<String> possibleWords;
	
	private ArrayList<String> guessesMade = new ArrayList<String>();
	
	public GuesserBot(int wordLength) {
		super();
		this.wordLength = wordLength;
		
	}

	public String guessEntry(){
		possibleWords = getPossibleWords();
		String letterToGuess = findFrequentLetter();
		System.out.println(letterToGuess);
		return letterToGuess;
		
	}
	
	public ArrayList<String> getPossibleWords(){
		try {
			FileInputStream io = new FileInputStream(dictionaryPath);
			Scanner fileScanner = new Scanner(io);
			ArrayList<String> wordsPossible = new ArrayList<>();
			while (fileScanner.hasNextLine()){
				String word = fileScanner.nextLine();
				if (word.length() == wordLength) {
				wordsPossible.add(word);
				}
			}
			return wordsPossible;
		}
		catch (FileNotFoundException f) {
			System.out.println("file wrong :(");
		}
		return null;
	}
	
	public String findFrequentLetter() {
		HashMap<String, Integer> freqMap = findLetterFrequencies();
		freqMap = removeGuessesAlreadyMade(freqMap);
		int mostFrequency = 0;
		String mostFrequent = "";
		for (String letter : freqMap.keySet()) {
			if (freqMap.get(letter) > mostFrequency) {
				mostFrequency = freqMap.get(letter);
				mostFrequent = letter;
			}
		}
		guessesMade.add(mostFrequent);
		return mostFrequent;
	}

	public HashMap<String,Integer> removeGuessesAlreadyMade(HashMap<String, Integer> freqMap) {
		for (String guess : guessesMade) {
			freqMap.remove(guess);
		}
		return freqMap;
	}

	public HashMap<String, Integer> findLetterFrequencies() {
		HashMap<String, Integer> freqMap = new HashMap<>();
		for (String word : possibleWords) {
			String[] letters = word.split("");
			for (String letter : letters) {
				if (!freqMap.containsKey(letter)) {
					freqMap.put(letter, 1);
				}
				else {
					freqMap.replace(letter, freqMap.get(letter) + 1);
				}
			}
		}
		return freqMap;
	}
	
}
