package ch.bbw.zork;/* * author:  Michael Kolling, Version: 1.0, Date: July 1999 * refactoring: Rinaldo Lanza, September 2020 */import java.io.BufferedReader;import java.io.InputStream;import java.io.InputStreamReader;import java.util.StringTokenizer;public class Parser {	private CommandWords validCommandWords;	private InputStream inputStream;	public Parser(InputStream inputStream) {		this.inputStream = inputStream;		this.validCommandWords = new CommandWords();	}	public Command getCommand() {		String inputLine;		String word1;		String word2;		System.out.print("> ");		BufferedReader bufferedReader =				new BufferedReader(						new InputStreamReader(this.inputStream));		try {			inputLine = bufferedReader.readLine();			StringTokenizer tokenizer = new StringTokenizer(inputLine);			if (tokenizer.hasMoreTokens()) {				word1 = tokenizer.nextToken(); // get first word			} else {				word1 = null;			}			if (tokenizer.hasMoreTokens()) {				word2 = tokenizer.nextToken(); // get second word			} else {				word2 = null;			}			if (validCommandWords.isCommand(word1)) {				return new Command(word1, word2);			} else {				return new Command(null, word2);			}		} catch (java.io.IOException exc) {			System.out.println("There was an error during reading: " + exc.getMessage());		}		// TODO: handle error		return new Command(null);	}	public String showCommands() {		return validCommandWords.showAll();	}}