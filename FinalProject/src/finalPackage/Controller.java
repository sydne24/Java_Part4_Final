package finalPackage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller
{
    public static void main(final String[] args) {
        String input = "";
        Scanner myScan = new Scanner(System.in);
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
                    Inventory.Item numNote = inv.new Item("Small Piece of Paper", 1, "A weathered piece of paper, you can barely read the numbers '2936' scrawled across it");
                    
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
                        		System.out.println(bed.getDescription());
                        		options.add("Look Under Pillow");
                        		options.add("Look Under Bed");
                        		options.remove("Inspect Bed");
                        		break;
                            case "look under bed":
                                System.out.println("You peer under the bed and something catches your eye.\nYou reach under the bed and pull out a small rusty key.");
                                options.remove("Look Under Bed");
                                key.addItem();
                                break;
                            case "open chest":
                                if (inv.containsItem(key)) {
                                    System.out.println("You use the key you found on the chest, and it pops open with a click. \nInside you find a crowbar and a small piece of paper with the numbers '2936' scrawled on it.");
                                    options.remove("Open Chest");
                                    chest.setState("Empty");
                                    crowbar.addItem();
                                    numNote.addItem();
                                    break;
                                }
                                else {
                                	System.out.println("You pound on the chest with your fists, but it is sturdier than it appears.\nIt seems like you'll need to find another way to open it.");
                                }
                                break;
                            case "inspect chest":
                                System.out.println(chest.getDescription());
                                options.add("Open Chest");
                                options.remove("Inspect Chest");
                                break;
                            case "check inventory":
                                inv.getItems();
                                break;
                            case "inspect door":
                                System.out.println(door.getDescription());
                                options.add("Open Door");
                                options.remove("Inspect Door");
                                break;
                            case "exit":
                                EndGame();
                                break;
                            case "look under pillow":
                                System.out.println("You start rummaging through the sheets on the bed, looking for a tool to help you.\nYou look under the pillow, but there's nothing there except dust.");
                                options.remove("Look Under Pillow");
                                break;
                            case "open door":
                                if (inv.containsItem(crowbar)) {
                                    System.out.println("You use the crowbar to pry open the door\nLight streams through the opening, and once your eyes adjust to the light you find yourself\nstanding in front of a small staircase leading to the surface.");
                                    options.remove("Open Door");
                                    Ending();
                                }
                                if (door.getState().equals("Broke"))
                                    System.out.println("You already pulled the door handle off, and pushing on the door appears to have no effect.\nYou'll need to find another way to open the door.");

                                else {
                                	System.out.println("You grab the door handle and pull with all your might. The handle pops off the door.\nYou'll need to find another way to open the door.");
                                	door.setState("Broke");
                                }
                                break;
                        }
                        System.out.println(String.join(", ", options));
                        input = myScan.nextLine().toString().toLowerCase();
                    }
                }
            }
        	EndGame();
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