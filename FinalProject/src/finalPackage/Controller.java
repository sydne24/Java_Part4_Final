package finalPackage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller
{
	static Scanner myScan = new Scanner(System.in);
	
    public static void main(final String[] args) {
        String input = "";
        System.out.println("[Enter game name here] is a text-based puzzle/adventure game made to test your wits and challenge your mind. \n"
        		+ "Please note that the game can be closed at any time by typing 'exit'.");
        
        while (!input.equals("exit")) {
            while (!input.equals("start") && !input.equals("exit")) {
                System.out.println("Whenever you're ready, type 'Start' to begin your adventure.");
                input = myScan.nextLine().toString().toLowerCase();
                if (input.equals("exit")) {
                    EndGame();
                }
            }
            
            Location ruin = new Location("There is a small ray of sunlight penetrating through a crack in the ceiling above you that "
            		+ "allows you to barely see your surroundings.\n"
            		+ "There is a chest against the wall to your left and a large wooden door set into the wall directly in front of you.\n"
            		+ "Behind you is a makeshift bed that looks like it hasn't seen life in many years.");
            Inventory inv = new Inventory();
            
            System.out.println("You start coming to your senses, although groggy and with a pounding headache.\n"
            		+ "Options: Check Inventory, Look around, Go back to sleep");
            
            input = myScan.nextLine().toString().toLowerCase();
            
            while (!input.equals("look around") && !input.equals("go back to sleep") && !input.equals("exit")) {
                if (input.equals("exit")) {
                    EndGame();
                }
                else if (input.equals("check inventory")) {
                    inv.getItems();
                }
                
                System.out.println("Options: Check Inventory, Look around, Go back to sleep");
                input = myScan.nextLine().toString().toLowerCase();
            }
            
            switch (input) {
                case "go back to sleep":
                    System.out.println("You decide to go back to sleep and end up dying of starvation\nGAME OVER");
                    EndGame();
                    break;
                case "look around":
                    System.out.println(ruin.getDescription());
                    
                    Location.LocationObject chest = ruin.new LocationObject("A wooden chest, its iron lock guarding secrets within, beckons adventurers to unlock its hidden treasures.");
                    Location.LocationObject bed = ruin.new LocationObject("A weathered metal bed, its frame creaking with age.");
                    Location.LocationObject door = ruin.new LocationObject("A timeworn wooden door, its hinges seized by rust, stands as an impassable obstacle, \nhinting at the mysteries that lie beyond its weathered frame.");
                    chest.setState("Locked");
                    door.setState("Closed");
                    
                    Inventory.Item key = inv.new Item("Rusty Key", 1, "A worn metal key, its surface bearing the marks of ages, promises to unlock long-forgotten secrets.");
                    Inventory.Item crowbar = inv.new Item("Crowbar", 1, "A sturdy crowbar, its weathered surface bearing the scars of countless trials, offers a versatile tool for prying open secrets and overcoming obstacles.");
                    Inventory.Item numNote = inv.new Item("Small Piece of Paper that reads '2936'", 1, "A weathered piece of paper, you can barely read the numbers '2936' scrawled across it");
                    Inventory.Item handle = inv.new Item("Door Handle", 1, "A tarnished iron door handle, torn from its rightful place.");
                    
                    System.out.println("Options: Check Inventory, Inspect Chest, Inspect Bed, Inspect Door");
                    input = myScan.nextLine().toString().toLowerCase(); 
                    
                    while (!input.equals("inspect chest") && !input.equals("inspect bed") && !input.equals("inspect door") && !input.equals("exit")) {
                        if (input.equals("exit")) {
                            EndGame();
                        }
                        else if (input.equals("check inventory")) {
                            inv.getItems();
                        }
                        
                        System.out.println("Options: Check Inventory, Inspect Chest, Inspect Bed, Inspect Door");
                        input = myScan.nextLine().toString().toLowerCase(); 
                    }
                    
                    List<String> options = new ArrayList<String>();
                    options.add("Check Inventory");
                    options.add("Inspect Chest");
                    options.add("Inspect Bed");
                    options.add("Inspect Door");
                    
                    while (door.getState().equals("Closed") || door.getState().equals("Broke")) {
                        switch (input) {
                        	case "inspect bed":
                        		if (options.contains("Inspect Bed"))
                        		{
                        			System.out.println(bed.getDescription());
                            		options.add("Look Under Pillow");
                            		options.add("Look Under Bed");
                            		options.remove("Inspect Bed");
                        		}
                        		break;
                            case "look under bed":
                            	if (options.contains("Look Under Bed"))
                            	{
                            		System.out.println("You peer under the bed and something catches your eye.\nYou reach under the bed and pull out a small rusty key.");
                                    options.remove("Look Under Bed");
                                    key.addItem();
                            	}
                                break;
                            case "open chest":
                            	if (options.contains("Open Chest"))
                            	{
                            		if (inv.containsItem(key)) {
                                        System.out.println("You use the key you found on the chest, and it pops open with a click. \nInside you find a crowbar and a small piece of paper with the numbers '2936' scrawled on it.");
                                        options.remove("Open Chest");
                                        chest.setState("Empty");
                                        crowbar.addItem();
                                        numNote.addItem();
                                        key.removeItem();
                                        break;
                                    }
                                    else {
                                    	System.out.println("You pound on the chest with your fists, but it is sturdier than it appears.\nIt seems like you'll need to find another way to open it.");
                                    }
                            	}
                                break;
                            case "inspect chest":
                            	if (options.contains("Inspect Chest"))
                            	{
                            		System.out.println(chest.getDescription());
                                    options.add("Open Chest");
                                    options.remove("Inspect Chest");
                            	}
                                break;
                            case "check inventory":
                                inv.getItems();
                                break;
                            case "inspect door":
                            	if (options.contains("Inspect Door"))
                            	{
                            		System.out.println(door.getDescription());
                                    options.add("Open Door");
                                    options.remove("Inspect Door");
                            	}
                                break;
                            case "exit":
                                EndGame();
                                break;
                            case "look under pillow":
                            	if (options.contains("Look Under Pillow"))
                            	{
                            		System.out.println("You start rummaging through the sheets on the bed, looking for a tool to help you.\nYou look under the pillow, but there's nothing there except dust.");
                                    options.remove("Look Under Pillow");
                            	}
                                break;
                            case "open door":
                            	if (options.contains("Open Door"))
                            	{
                            		if (inv.containsItem(crowbar)) {
                                        System.out.println("You use the crowbar to pry open the door\nLight streams through the opening, and once your eyes adjust to the light you find yourself\nstanding in front of a small staircase leading to the surface.");
                                        options.remove("Open Door");
                                        crowbar.removeItem();
                                        door.setState("Open");
                                        break;
                                    }
                                    if (door.getState().equals("Broke"))
                                        System.out.println("You already pulled the door handle off, and pushing on the door appears to have no effect.\nYou'll need to find another way to open the door.");

                                    else {
                                    	System.out.println("You grab the door handle and pull with all your might. The handle pops off the door.\nYou'll need to find another way to open the door.");
                                    	handle.addItem();
                                    	door.setState("Broke");
                                    }
                            	}
                                break;
                        }
                        if (door.state.equals("Open"))
                        	break;
                        System.out.println("Options: " + String.join(", ", options));
                        input = myScan.nextLine().toString().toLowerCase();
                    }
                    
                    System.out.println("You ascend the staircase and notice that you're in\n"
                    		+ "a large, grassy clearing");
                    options.clear();
                    options.add("Check Inventory");
                    options.add("Look Around");
                    System.out.println("Options: " + String.join(", ", options));
                    input = myScan.nextLine().toString().toLowerCase();
                    
                    while (!input.equals("look around")) {
                        if (input.equals("exit")) {
                            EndGame();
                        }
                        else if (input.equals("check inventory")) {
                            inv.getItems();
                        }
                        
                        System.out.println("Options: " + String.join(", ", options));
                        input = myScan.nextLine().toString().toLowerCase();
                    }
                    
                    Location cabin = new Location("This cabin looks newly built, however it appears unoccupied. Around the back side\n"
                    		+ "of the cabin there is a cellar door, leading to unsolved mysteries. The inside of the cabin is clean,\n"
                    		+ "there is a stone fireplace on one wall and a large painting hanging from the wall on the other\n"
                    		+ "side of the room. Next to the fireplace is a set of wooden cabinets. On the far side of the room\n"
                    		+ "are four colored panels set into the wall.");
                    
                    System.out.println("You see a small wooden cabin in the distance.");
                    options.add("Go to Cabin");
                    options.remove("Look Around");
                    System.out.println("Options: " + String.join(", ", options));
                    input = myScan.nextLine().toString().toLowerCase();
                    
                    while (!input.equals("go to cabin")) {
                        if (input.equals("exit")) {
                            EndGame();
                        }
                        else if (input.equals("check inventory")) {
                            inv.getItems();
                        }
                        
                        System.out.println("Options: " + String.join(", ", options));
                        input = myScan.nextLine().toString().toLowerCase();
                    }
                    
                    System.out.println(cabin.getDescription());
                    
                    Location.LocationObject fireplace = cabin.new LocationObject("A cold hearth, a reminder of warmth lost, "
                    		+ "waiting for the spark that will rekindle its flame.");
                    Location.LocationObject painting = cabin.new LocationObject("A canvas captured in time, a window to a world "
                    		+ "that once was. Something seems off about it; it looks out-of-place.");
                    Location.LocationObject cabinets = cabin.new LocationObject("Within these wooden havens, stories are kept "
                    		+ "alive, preserved in the form of trinkets and treasures. One of the cabinets is missing a handle.");
                    Location.LocationObject panels = cabin.new LocationObject("Four colored panels set into the wall. From left-to-right "
                    		+ "they are colored red, yellow, purple, blue. \nAs you take a closer look you realize they aren't panels, "
                    		+ "but buttons.");
                    Location.LocationObject safe = cabin.new LocationObject("The painting swings open to reveal a safe locked behind"
                    		+ "a four-digit numeric code.");
                    Location.LocationObject cellarDoor = cabin.new LocationObject("A heavy wooden door with a tarnished brass lock, "
                    		+ "barring access to the dark depths below.");
                    painting.setState("Closed");
                    cabinets.setState("Broken");
                    safe.setState("Locked");
                    cellarDoor.setState("Locked");
                    
                    Inventory.Item cellarKey = inv.new Item("A Heavy Metal Key", 1, "A heavy, ornate key that promises to unlock adventure.");
                    Inventory.Item matchbox = inv.new Item("A matchbox", 1, "A well-worn matchbox with a few matches remaining.");
                    
                    options.add("Inspect Fireplace");
                    options.add("Inspect Cabinets");
                    options.add("Inspect Painting");
                    options.add("Inspect Panels");
                    options.add("Inspect Cellar Door");
                    options.remove("Go to Cabin");
                    System.out.println("Options: " + String.join(", ", options));
                    input = myScan.nextLine().toString().toLowerCase();
                    
                    while (cellarDoor.getState().equals("Locked"))
                    {
                    	switch (input)
                    	{
                    		case "check inventory":
                    			inv.getItems();
                    			break;
                    		case "exit":
                    			EndGame();
                    			break;
                    		case "inspect fireplace":
                    			if (options.contains("Inspect Fireplace"))
                    			{
                        			System.out.println(fireplace.getDescription());
                        			options.add("Light Fireplace");
                        			options.remove("Inspect Fireplace");
                    			}
                    			break;
                    		case "inspect cabinets":
                    			if (options.contains("Inspect Cabinets"))
                    			{
                    				System.out.println(cabinets.getDescription());
                        			options.add("Look in Cabinets");
                        			options.add("Fix Cabinet");
                        			options.remove("Inspect Cabinets");
                    			}
                    			break;
                    		case "inspect painting":
                    			if (options.contains("Inspect Painting"))
                    			{
                    				if (painting.getState().equals("Closed"))
                    				{
                    					System.out.println(painting.getDescription());
                            			options.remove("Inspect Painting");
                    				}
                    				else
                    				{
                    					System.out.println("A canvas captured in time, a window to a world that once was.\n"
                    							+ "It has swung open to reveal a safe.");
                            			options.remove("Inspect Painting");
                    				}
                    			}
                    			break;
                    		case "inspect panels":
                    			if (options.contains("Inspect Panels"))
                    			{
                    				System.out.println(panels.getDescription());
                        			options.add("Enter Panel Code");
                        			options.remove("Inspect Panels");
                    			}
                    			break;
                    		case "inspect cellar door":
                    			if (options.contains("Inspect Cellar Door"))
                    			{
                    				System.out.println(cellarDoor.getDescription());
                        			options.add("Open Cellar Door");
                        			options.remove("Inspect Cellar Door");
                    			}
                    			break;
                    		case "light fireplace":
                    			if (options.contains("Light Fireplace"))
                    			{
                    				if (inv.containsItem(matchbox))
                        			{
                        				System.out.println("You use a match from the matchbox you found to light the fireplace.\n"
                        						+ "The fire roars to life, and a leather-wrapped book falls from the ceiling.");
                        				options.add("Read Book");
                        				options.remove("Light Fireplace");
                        				matchbox.removeItem();
                        			}
                        			else
                        			{
                        				System.out.println("You don't have anything you can use to light the kindling in the fireplace.\n"
                        						+ "There must be something in this cabin you can use to light a fire.");
                        			}
                    			}
                    			break;
                    		case "look in cabinets":
                    			if (options.contains("Look in Cabinets"))
                    			{
                    				if (cabinets.getState().equals("Broken"))
                        			{
                        				System.out.println("You look through the cabinets one-by-one with no luck. You skip over the\n"
                        						+ "cabinet with no handle.");
                        				options.remove("Look in Cabinets");
                        			}
                        			else
                        			{
                        				System.out.println("You look through the rest of the cabinets, but it seems the matchbox was\n"
                        						+ "the only thing to be found.");
                        				options.remove("Look in Cabinets");
                        			}
                    			}
                    			break;
                    		case "fix cabinet":
                    			if (options.contains("Fix Cabinet"))
                    			{
                    				if (inv.containsItem(handle))
                        			{
                        				System.out.println("You affix the handle you 'found' to the broken cabinet.\n"
                        						+ "Inside the cabinet you find a matchbox with a few matches left.");
                        				cabinets.setState("Fixed");
                        				matchbox.addItem();
                        				handle.removeItem();
                        				options.remove("Fix Cabinet");
                        			}
                        			else
                        			{
                        				System.out.println("You don't have anything you can use to fix the cabinet.");
                        				EndGame();
                        			}
                    			}
                    			break;
                    		case "enter panel code":
                    			if (options.contains("Enter Panel Code"))
                    			{
                    				if (getPanelCode().equals("yellow blue red purple"))
                        			{
                        				System.out.println("You hear a soft click, and when you turn around you realize the painting has "
                        						+ "swung open, \nrevealing a safe locked behind a four-digit numeric code.");
                        				painting.setState("Open");
                        				options.add("Open Safe");
                        				options.remove("Read Book");
                        				options.remove("Enter Panel Code");
                        			}
                        			else
                        			{
                        				System.out.println("Nothing happens; it appears you have entered the wrong code. "
                        						+ "There must be a clue somewhere around here.");
                        			}
                    			}
                    			break;
                    		case "read book":
                    			if (options.contains("Read Book"))
                    			{
                    				System.out.println("The yellow sun sits high in the blue sky.\n"
                        					+ "As it sets, it bathes the land in a red hue.\n"
                        					+ "While the moon rises, the purple chrysanthemum blooms.");
                    			}
                    			break;
                    		case "open safe":
                    			if (options.contains("Open Safe"))
                    			{
                    				System.out.println("You enter the code you found on the scrap of paper in the ruin. The lock\n"
                        					+ "opens with a satisfying click. Inside you find a large metal key.");
                        			cellarKey.addItem();
                        			numNote.removeItem();
                        			options.remove("Open Safe");
                    			}
                    			break;
                    		case "open cellar door":
                    			if (options.contains("Open Cellar Door"))
                    			{
                    				if (inv.containsItem(cellarKey))
                        			{
                        				System.out.println("You use the large metal key to open the cellar door");
                        				cellarDoor.setState("Unlocked");
                        				cellarKey.removeItem();
                        				options.remove("Open Cellar Door");
                        				Ending();
                        			}
                        			else
                        			{
                        				System.out.println("You don't have anything you can use to open the cellar door\n"
                        						+ "and brute force definitely won't have any effect. There must be a key\n"
                        						+ "around here somewhere.");
                        			}
                    			}
                    			break;
                    	}
                    	
                    	System.out.println("Options: " + String.join(", ", options));
                        input = myScan.nextLine().toString().toLowerCase();
                    }
                }
            }
        
        	EndGame();
        }
    
    public static String getPanelCode()
    {
    	String panelCode = "";
    	
    	String panelInput1 = "";
        String panelInput2 = "";
        String panelInput3 = "";
        String panelInput4 = "";
        
        System.out.println("First Color: ");
        panelInput1 = myScan.nextLine().toString().toLowerCase();
        System.out.println("Second Color: ");
        panelInput2 = myScan.nextLine().toString().toLowerCase();
        System.out.println("Third Color: ");
        panelInput3 = myScan.nextLine().toString().toLowerCase();
        System.out.println("Fourth Color: ");
        panelInput4 = myScan.nextLine().toString().toLowerCase();
        
        panelCode = panelInput1 + " " + panelInput2 + " " + panelInput3 + " " + panelInput4;
        
        return panelCode;
    }
    
    public static void EndGame() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
    
    public static void Ending() {
        System.out.println("Congratulations, you have escaped!");
        System.out.println("Thanks for playing the demo!");
        System.exit(0);
    }
}