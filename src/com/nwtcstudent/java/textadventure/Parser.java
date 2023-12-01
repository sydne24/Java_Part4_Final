package com.nwtcstudent.java.textadventure;

public class Parser {
	
	// Input Parser - for now just returns input in lowercase format
	public static String[] GetInput() {
		boolean matchFound = false;
		String noun = "";
		String verb = "";
		String[] phrase = new String[2]; //creates array to store noun & verb in
		//TODO: consider returning embedded class with noun and verb properties instead of an array - get team feedback on preference
		
		//while (!matchFound) {
		String input = Controller.myScan.nextLine(); //get input
		input.toString().toLowerCase(); //converts input to lowercase
		input.trim(); //remove leading and ending white space, if applicable
		String cleanedInput[] = input.split("//s"); //creates array with parts of input split by space
			
		//TODO: need to account for multiple verb/noun inputs
			//include prompt for which they'd like to use?
		
		//loop through split input array
		for (int i = 0; i < cleanedInput.length; i++) {
			//TODO: scan cleanedInput array and assign to variables
		}
		
		//TODO: move or delete -- depending on where functionality will be written
	    if (input.toString().toLowerCase() == "exit") {
	    		Controller.endGame();
	    }
	    	

		//}
    	return phrase; //returns 2-item array with noun and verb
	}

}
