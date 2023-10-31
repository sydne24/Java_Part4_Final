package com.nwtcstudent.java.textadventure;

// Nathaniel was here
// Nicholas was here

/**
 * The Main class is the first to run on program startup, and performs any non-game-related tasks before creating the game controller
 */
public class Main {

	public static void main(String[] args) {
		
		try {
			
			Controller controller = new Controller();
		}
		catch (Exception e) {
			
			System.out.println(
					"An error has occured and the application had to exit!" +
					"\n\nCause of Error: " + e.getMessage() +
					"\n\nLocation:\n\t" + e.getStackTrace()[0] +
					"\n\nFull Stack Trace:"
					);
			for (StackTraceElement line : e.getStackTrace()) {
				
				System.out.println("\t" + line);
			}
		}
		finally {
			System.out.println(
					"Have a great day!"
					);
			System.exit(0);
		}
		
	}

}
