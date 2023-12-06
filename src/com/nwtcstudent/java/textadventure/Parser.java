package com.nwtcstudent.java.textadventure;

import java.util.ArrayList;

public final class Parser {
	
	// 1.5 Proper use of the Static keyword
	// 1.6 Proper use of the final keyword
	// 2.3 Proper use of the Singleton pattern
	private static final Parser instance = new Parser();
	private Controller controller;
	
	// Declare libraries
	// NOTE: input will be split by space, so phrases like "pick up" will be split into "pick" and "up" in the input array
	private String useLib[] = new String[] {"use", "open", "eat", "wear", "pick", "drop", "destroy", "take"};
	private String lookLib[] = new String[] {"look", "check", "inspect", "describe", "what"};
	private String moveLib[] = new String[] {"open", "through", "move", "walk", "run", "skip", "jump", "dance", "crawl"};
	private String nounLib[] = new String[] {"north", "south", "east", "west", "bag", "inventory", "around", "dagger", "amulet", "lantern", "pizza", "note", "key", "wig"};
	
	//declare noun library - values are added with initializeItemLibrary() in Controller during setup()
	// 3.2 Use of an array list
	private ArrayList<String> itemLib = new ArrayList<>();
	
	// PARSER METHOD
	/**
	 * 
	 * @param input the input to be parsed.
	 * @return a Command object with properties noun and verb
	 */
	//TODO: this no longer needs to return anything - all action is called from other classes and handled outside this class
	public Command parseInput(String input) {
		
		String noun = "";
		String verb = "";
		Command phrase = new Command();
		//converts input to lowercase and trims white space
		input = input.toLowerCase().trim(); 
		
		// Calls exit function if player enters 'exit', 'end', or 'close', or common variants
	    if (input.equals("exit") ||
	    		input.equals("end") ||
	    		input.equals("end game") ||
	    		input.equals("close") ||
	    		input.equals("close game") ) {
	    	Controller.endGame();
	    	return null;
	    }
	    
	    // Calls help function if player enters 'help'
	    if (input.equals("help") ||
	    		input.equals("help please") ||
	    		input.equals("please help") ) {
	    	System.out.println(GameInfo.getAvailableCommands());
	    	return null;
	    }
	    
	    // Calls look around function if command includes phrase "look around"
	    //TODO: consider moving this into the noun/verb parse logic - all of these words have been added to the action/item dictionaries
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
		
	    // Set and return phrase
		phrase.verb = verb;
		phrase.noun = noun;
		//TODO: this no longer needs to return any information - instead it will call action methods
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
	//TODO: this class may no longer be utilized - double check and remove if this is the case
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
	
	/**
	 * Adds items to the library of available item names
	 * @param i the item to be added
	 */
	public void addItemToLib(Item i) {
		
		itemLib.add(i.getName().toLowerCase());
	}
}



