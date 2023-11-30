package com.nwtcstudent.java.textadventure;

public class Parser {
	
	// Input Parser - for now just returns input in lowercase format
	public static String GetInput() {
		boolean matchFound = false;
		
		//while (!matchFound) {
			String input = Controller.myScan.nextLine();
			
	    	if (input.toString().toLowerCase() == "exit") {
	    		Controller.endGame();
	    	}
	    	

		//}
    	return input.toString().toLowerCase();
	}

}
