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
	private String useLib[] = new String[] {"use", "open", "eat", "wear",  "drop", "destroy", "interact"};
	private String takeLib[] = new String[] {"pick", "take"};
	private String lookLib[] = new String[] {"look", "check", "inspect", "describe", "what", "investigate"};
	private String moveLib[] = new String[] {"open", "through", "move", "walk", "run", "skip", "jump", "dance", "crawl", "go"};
	private String nounLib[] = new String[] {"north", "south", "east", "west", "bag", "inventory", "around", "room", "dagger", "amulet", "lantern", "pizza", "note", "key", "wig", "door", "gate", "portal", "archway"};
	
	//declare noun library - values are added with initializeItemLibrary() in Controller during setup()
	// 3.2 Use of an array list
	private ArrayList<String> itemLib = new ArrayList<>();
	
	// PARSER METHOD
	/**
	 * 
	 * @param input the input to be parsed.
	 * @return a Command object with properties noun and verb
	 */
	public void parseInput(String input) {
		
		String noun = "";
		String verb = "";

		//converts input to lowercase and trims white space
		input = input.toLowerCase().trim(); 
		
		// Calls exit function if player enters 'exit', 'end', or 'close', or common variants
	    if (input.equals("exit") ||
	    		input.equals("end") ||
	    		input.equals("end game") ||
	    		input.equals("close") ||
	    		input.equals("close game") ) {
	    	Controller.endGame();
	    	return;
	    }
	    
	    // Calls help function if player enters 'help'
	    if (input.equals("help") ||
	    		input.equals("help please") ||
	    		input.equals("please help") ) {
	    	System.out.println(GameInfo.getAvailableCommands());
	    	return;
	    }
	    
	    // Displays inventory if only 'inventory' is entered
	    if (input.equals("inventory")) {
	    	checkInventory();
	    	return;
	    }
	    
	    // Searches for named items as phrase before splitting input string
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
			
			// Only searches and assigns verb or noun if one hasn't already been assigned
			if (verb.length() < 1) {
				verb = getVerb(word);
			}
			if (noun.length() < 1) {
				noun = getNoun(word);
			}
		}
		
		//**BEGIN COMMAND CALLS WITH PARSED INPUT**
		
		//LOOK commands
		if (verb.equals("look") && noun.equals("")) {
			controller.inspect();
		}
		if (verb.equals("look") && noun.equals("room") ||
			verb.equals("look") && noun.equals("around")) {
			controller.lookAround();
		}
		else if (verb.equals("look") && noun.equals("bag") ||
				 verb.equals("look") && noun.equals("inventory")) {
			checkInventory();
		}
		//handles directionals - move/inspection direction are handled the same on the backend
		else if (verb.equals("look") && noun.equals("north") ||
			verb.equals("look") && noun.equals("east") ||
			verb.equals("look") && noun.equals("south") ||
			verb.equals("look") && noun.equals("west") ) {
			controller.move(noun);
		}

		//handles msc noun inspections
		else if (verb.equals("look") && noun.length() > 0){
			controller.inspect(noun);
		}
		
		//TAKE commands
		if (verb.equals("take") && noun.equals("")) {
			controller.take();
		}
		else if (verb.equals("take") && noun.length() > 0) {
			controller.take(noun);
		}
		
		//USE commands
		if (verb.equals("use")) {
			IFocusable obj = null;
			if (noun.equals("")) {
				//if no noun specified, get recent focusable object or kick out for player feedback
				obj = controller.getFocusable(); 
				if (obj != null) {
					//interact with focusable object
					obj.interact();
				}
			}
			else if (noun.length() > 0) {
				obj = controller.getFocusable(noun);
				if (obj != null) {
					obj.interact();
				}
			}
		}
		
		//MOVE commands
		if (verb.equals("move") && noun.equals("")) {
			controller.move();
		}
		else if (verb.equals("move") && noun.length() > 0) {
			IFocusable obj = null;
			//if MOVE DIRECTION
			if ( (noun.equals("north")) ||
				 (noun.equals("east")) ||
				 (noun.equals("south")) ||
				 (noun.equals("west")) ) {
				controller.move(noun);
			}
			//if MOVE OBJECT
			else {
				obj = controller.getFocusable(noun);
				if (obj != null) {
					controller.move(obj);
				}
			}
		}
		
		//INVALID INPUT
		if (verb.equals("")) {
			System.out.println("I'm sorry, I don't understand. What would you like to do?");
		}
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
		for (String checkWord : takeLib) {
			if (input.equals(checkWord)) {
				getVerb = "take";
			}
		}
		return getVerb;
	}

	private String getNoun (String input) {
		String noun = "";	
		
		for (String checkWord : nounLib) {
			if (input.equals(checkWord)) {
				noun = input;
				
			    // Searches for named items
				for (String namedItem : itemLib) {
					if (namedItem.contains(noun)) {
						noun = namedItem;
						break;
					}
				}
				break;
			}
		}
		return noun;
	}
	
	private void checkInventory() {
		//TODO: call check inventory
		Player.getInstance().getInventory().checkInventory();
		//System.out.println("DEVS: Please locate and enter an inventory output method here - Parser.java line 202");
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
	public void addItemToLib(IFocusable i) {
		
		itemLib.add(i.getName().toLowerCase());
	}
}



