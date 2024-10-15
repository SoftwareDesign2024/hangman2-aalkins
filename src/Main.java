import game.Executioner;
import game.ExecutionerCheater;
import game.Guesser;
import game.GuesserBot;
import game.HangmanGame;
import util.HangmanDictionary;

//Adam Alkins
/**
 * This class launches the Hangman game and plays once.
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class Main {
    public static final String DICTIONARY = "HangmanLabFiles/data/lowerwords.txt";
    public static final int NUM_LETTERS = 6;
    public static final int NUM_MISSES = 8;
    //To utilize regular guesser, construct the guesser with "new Guesser()"
    //To utilize the guesser bot, construct the guesser with "new GuesserBot(NUM_LETTERS)"
    public static Guesser guesser = new Guesser();
    //To utilize regular executioner, construct the guesser with "new Executioner(NUM_LETTERS)"
    //To utilize the cheating executioner, construct the guesser with "new ExecutionerCheater(NUM_LETTERS)"
    public static Executioner executioner = new Executioner(NUM_LETTERS);


    public static void main (String[] args) {
    	new HangmanGame(new HangmanDictionary(DICTIONARY), NUM_LETTERS, NUM_MISSES, guesser, executioner).play();
    }
}
