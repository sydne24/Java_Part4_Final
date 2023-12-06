package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

public class Parser {
	
	// Parser singleton and controller reference
	private static final Parser instance = new Parser();
	private Controller controller;
	
	// Declare libraries
	// NOTE: input will be split by space, so phrases like "pick up" will be split into "pick" and "up" in the input array
	private String useLib[] = new String[] {"use", "open", "eat", "wear", "pick", "drop", "destroy", "take"};
	private String lookLib[] = new String[] {"look", "check", "inspect", "describe", "what"};
	private String moveLib[] = new String[] {"open", "through", "move", "walk", "run", "skip", "jump", "dance", "crawl"};
	private String nounLib[] = new String[] {"north", "south", "east", "west", "bag", "inventory"};
	//declare noun library - values are added with initializeItemLibrary() in Controller during setup()
	private ArrayList<String> itemLib = new ArrayList<String>();
	
	// PARSER METHOD
	/**
	 * 
	 * @param input the input to be parsed.
	 * @return a String array with 2 elements. [0] verb, [1] noun.
	 */
	public Command parseInput(String input) {
		
		boolean matchFound = false;
		String noun = "";
		String verb = "";
		Command phrase = new Command();
		
		// TODO: 27 & 29 are only used in testing - remove before release
		
		input = input.toLowerCase().trim(); //converts input to lowercase and trims whitepace
		
		// Calls exit function if player enters 'exit'
	    if (input.equals("exit")) {
	    	
	    	Controller.endGame();
	    	return null;
	    }
	    
	    // Calls help function if player enters 'help'
	    if (input.equals("help")) {
	    	
	    	System.out.println(GameInfo.getAvailableCommands());
	    	return null;
	    }
	    
	    // Calls look around function if command includes phrase "look around"
	    if (input.contains("look around") ||
	    		input.contains("look room") ||
	    		input.contains("inspect room")) {
	    	controller.lookAround();
	    	return null;
	    }
	    
	    //searches for named items
		for (String checkWord : itemLib) {
			if (input.contains(checkWord)) {
				noun = checkWord;
				break;
			}
		}
	    
	    
		
		String cleanedInput[] = input.split("\\s+"); //creates array with parts of input split by space
		
		// 3.5 - Valid example of a foreach statement
		// Loop through split input array
		for (String word : cleanedInput) {
			//if you want to make it more complicated, you could restrict comparisons to word size instead of scanning the whole library
			
			//only searches and assigns verb or noun if one hasn't already been assigned
			if (verb.length() < 1) {
				verb = getVerb(word);
			}
			if (noun.length() < 1) {
				noun = getNoun(word);
			}
		}
		
		// Account for incomplete input (missing noun/verb)
		if (noun.length() == 0 || verb.length() == 0) {
			
			System.out.println("Unknown command.");
		}

		// If have noun and verb, set matchFound to true
		if (noun.length() > 0 && verb.length() > 0) {
			matchFound = true;
		}
		
	    // Set and return phrase
		phrase.verb = verb;
		phrase.noun = noun;
		
		return phrase;
	}
	
	private String getVerb(String input) {
		String getVerb = "";
		for (String checkWord : useLib) {
			if (input.equals(checkWord)) {
				getVerb = "use";
			}
		}
		for (String checkWord : lookLib) {
			if (input.equals(checkWord)) {
				getVerb = "look";
			}
		}
		for (String checkWord : moveLib) {
			if (input.equals(checkWord)) {
				getVerb = "move";
			}
		}
		return getVerb;
	}

	private String getNoun (String input) {
		String noun = "";	
		
		
		for (String checkWord : nounLib) {
			if (input.equals(checkWord)) {
				noun = input;
				break;
			}
		}
		return noun;
	}

	
	/**
	 * Nested class to return the parsed verb/noun command out for use
	 * @return String Command.verb & String Command.noun
	 */
	//1.7 - use of nested class
	static class Command {
		private String noun;
		private String verb;
		
		public String getNoun() {
			return noun;
		}
		public void setNoun(String noun) {
			this.noun = noun;
		}
		public String getVerb() {
			return verb;
		}
		public void setVerb(String verb) {
			this.verb = verb;
		}
		
	}
	
	/**
	 * @return the singleton instance of the Parser
	 */
	public static Parser getInstance() {
		
		return instance;
	}
	
	/**
	 * Set the reference to the controller
	 * @param c the controller object
	 */
	public void setController(Controller c) {
		
		controller = c;
	}
	
	public void addItemToLib(Item i) {
		
		itemLib.add(i.getName().toLowerCase());
	}
}



