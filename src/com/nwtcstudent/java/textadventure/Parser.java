package com.nwtcstudent.java.textadventure;

import java.util.Scanner;

public class Parser {
	
	//Declare libraries
	//NOTE: input will be split by space, so phrases like "pick up" will be split into "pick" and "up" in the input array
	//NOTE: input will be converted to all lower case to be compared - DO NOT add capitals to new dictionary entries
	private static String useLib[] = new String[] {"open", "eat", "wear", "pick", "drop", "destroy"};
	private static String lookLib[] = new String[] {"look", "inspect", "describe", "what"};
	private static String moveLib[] = new String[] {"open", "through", "move", "walk", "run", "skip", "jump", "dance", "crawl"};
	private static String nounLib[] = new String[] {"north", "south", "east", "west"};
	
	// PARSER METHOD
	/**
	 * @return a String array with 2 elements. [0] verb, [1] noun.
	 */
	public static String[] GetInput() {
		boolean matchFound = false;
		String noun = "";
		String verb = "";
		String[] phrase = new String[2]; //creates array to store noun & verb in
		//TODO: consider returning embedded class with noun and verb properties instead of an array - get team feedback on preference
		
		//TODO: 27 & 29 are only used in testing - remove before release
		//Scanner myScan = new Scanner(System.in);
		String input = Controller.myScan.nextLine(); //get input
		//String input = myScan.nextLine();
		
		while (!matchFound) {
		
			//Prompts user for input if none is given
			if (input == "") {
				System.out.println("Please enter a command.");
				System.out.println(GameInfo.getHelpMessage());
				GetInput();
			}
			
			input.toString().toLowerCase(); //converts input to lowercase
			input.trim(); //remove leading and ending white space, if applicable
			
			//Calls exit function if player enters 'exit'
		    if (input == "exit") {
		    	Controller.endGame();
		    }
		    
		    //Calls help function if player enters 'help'
		    if (input == "help" ) {
		    	//TODO: write command list and implement in GameInfo
		    	System.out.println("TODO: created a help command list");
		    	GetInput();
		    }
			
			String cleanedInput[] = input.split("\\s+"); //creates array with parts of input split by space
				
			//TODO: account for multiple verb/noun inputs?
				//include prompt for which they'd like to use?
			
			//3.5 - Valid example of a foreach statement
			//loop through split input array
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
			
			//TODO: account for incomplete input (missing noun/verb)
			if (noun.length() == 0 || noun.length() == 0) {
				System.out.println("Unknown command.");
				GetInput();
			}
	
		//if have noun and verb, set matchFound to true
			if (noun.length() > 0 && verb.length() > 0) {
				matchFound = true;
			}
		}
		
	    //set and return phrase
	    phrase[0] = verb;
	    phrase[1] = noun;
		
		return phrase;
	}
	
	private static String getVerb(String input) {
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

	private static String getNoun (String input) {
		String noun = "";		
		for (String checkWord : nounLib) {
			if (input.equals(checkWord)) {
				noun = input;
				break;
			}
		}
		return noun;
	}
}
