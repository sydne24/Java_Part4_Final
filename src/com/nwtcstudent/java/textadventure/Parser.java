package com.nwtcstudent.java.textadventure;

public class Parser {
	
	//Declare libraries
	//NOTE: input will be split by space, so phrases like "pick up" will be split into "pick" and "up" in the input array
	//NOTE: input will be converted to all lower case to be compared - DO NOT add capitals to new dictionary entries
	private static String useLib[] = new String[] {"open", "eat", "wear", "pick", "drop", "destroy"};
	private static String lookLib[] = new String[] {"look", "inspect", "describe", "what"};
	private static String moveLib[] = new String[] {"open", "through", "move", "walk", "run", "skip", "jump", "dance", "crawl"};
	private static String directionLib[] = new String[] {"north", "south", "east", "west"};
	
	// PARSER METHOD
	public static String[] GetInput() {
		boolean matchFound = false;
		String noun = "";
		String verb = "";
		String[] phrase = new String[4]; //creates array to store noun & verb in
		//TODO: consider returning embedded class with noun and verb properties instead of an array - get team feedback on preference
		
		//while (!matchFound) {
		String input = Controller.myScan.nextLine(); //get input
		input.toString().toLowerCase(); //converts input to lowercase
		input.trim(); //remove leading and ending white space, if applicable
		String cleanedInput[] = input.split("//s"); //creates array with parts of input split by space
			
		//TODO: account for no input
		
		//TODO: need to account for multiple verb/noun inputs
			//include prompt for which they'd like to use?
		
		//loop through split input array
		for (int i = 0; i < cleanedInput.length; i++) {
				//if you want to make it more complicated, you could restrict comparisons to word size instead of scanning the whole library
			for (int z = 0; z < useLib.length; z++) {
				if (cleanedInput[i] == useLib[z]) {
					//TODO: if variable !empty, prompt which user would like to assign (would this interfere with 
					verb = "use";
				}
				if (cleanedInput[i] == lookLib[z]) {
					//TODO: if variable !empty, prompt which user would like to assign (would this interfere with 
					verb = "look";
				}
				if (cleanedInput[i] == moveLib[z]) {
					//TODO: if variable !empty, prompt which user would like to assign (would this interfere with 
					verb = "move";
				}
				if (cleanedInput[i] == directionLib[z]) {
					//TODO: if variable !empty, prompt which user would like to assign (would this interfere with 
					noun = cleanedInput[i].toString();
				}
			}
			
		//TODO: account for incomplete input (missing noun/verb)
			
		}
		
		//TODO: move or delete -- depending on where functionality will be written
	    if (input.toString().toLowerCase() == "exit") {
	    		Controller.endGame();
	    }
	    
	    //set and return phrase
	    phrase[0] = verb;
	    phrase[1] = noun;
    	return phrase;
	}

}
