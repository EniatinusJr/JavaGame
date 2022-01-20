package ch.bbw.zork;import java.util.ArrayList;/** * Class Game - the main class of the "Zork" game. * * Author:  Michael Kolling, 1.1, March 2000 * refactoring: Rinaldo Lanza, September 2020 */public class Game {		private Parser parser;	private Room currentRoom;	private Room blackmarket, marketplace, home, dungeon, blacksmith, forest, graveyard, snowyhills;	private Item blackmarketItem1, marketplaceItem1, marketplaceItem2, dungeonItem1, blacksmithItem1, forestItem1, graveyardItem1, snowyhillsItem1, homeItem;	private ArrayList<String> playerPath = new ArrayList<String>();	private  boolean wentBackCheck = false;	public Game() {				parser = new Parser(System.in);		blackmarketItem1 = new Item("Dagger", "Poisoned Dagger", 2, Tag.Equipable);		marketplaceItem1 = new Item("Bread", "2 loafs of bread", 5, Tag.Consumable);		marketplaceItem2 = new Item("Apple", "one Apple", 2, Tag.Consumable);		dungeonItem1 = new Item("Sword", "Well crafted Sword", 9, Tag.Equipable);		blacksmithItem1 = new Item("Armor", "Steel Armor", 20, Tag.Equipable);		forestItem1 = new Item("Axe", "Old Axe", 11, Tag.Equipable);		graveyardItem1 = new Item("Bone", "Human Bone", 3, Tag.Craftable);		snowyhillsItem1 = new Item("Goat", "Frozen Goat", 40, Tag.Consumable);		homeItem = new Item("", "", 1, Tag.Consumable);		blackmarket = new Room("a sidestreet next to the market");		marketplace = new Room("a lively marketplace");		home = new Room("the entrance to the village");		dungeon = new Room("a dungeon deep in mountains");		blacksmith = new Room("a blacksmith near the marketplace");		forest = new Room("a dark forest");		graveyard = new Room("on a foggy graveyard");		snowyhills = new Room("on top of some snowy Hills");		blackmarket.setExits(null, null, marketplace, null);		marketplace.setExits(blackmarket, home, blacksmith, null);		home.setExits(dungeon, forest, null, marketplace);		dungeon.setExits(null, null, home, null);		blacksmith.setExits(marketplace, null, null, null);		forest.setExits(graveyard, snowyhills, null, home);		graveyard.setExits(null, null, forest, null);		snowyhills.setExits(null, null, null, forest);		blackmarket.addItems(blackmarketItem1);		marketplace.addItems(marketplaceItem1);		marketplace.addItems(marketplaceItem2);		dungeon.addItems(dungeonItem1);		blacksmith.addItems(blacksmithItem1);		forest.addItems(forestItem1);		graveyard.addItems(graveyardItem1);		snowyhills.addItems(snowyhillsItem1);		home.addItems(homeItem);		currentRoom = home; // start game outside	}	/**	 *  Main play routine.  Loops until end of play.	 */	public void play() {		printWelcome();		// Enter the main command loop.  Here we repeatedly read commands and		// execute them until the game is over.		boolean finished = false;		while (!finished) {			Command command = parser.getCommand();			finished = processCommand(command);		}		System.out.println("Thank you for playing.  Good bye.");	}	private void printWelcome() {		System.out.println();		System.out.println("Welcome to Zork!");		System.out.println("Zork is a simple adventure game.");		System.out.println("Type 'help' if you need help.\n");		System.out.println(currentRoom.longDescription());	}	private boolean processCommand(Command command) {		if (command.isUnknown()) {			System.out.println("I don't know what you mean...");			return false;		}		String commandWord = command.getCommandWord();		if (commandWord.equals("help")) {			printHelp();		} else if (commandWord.equals("go")) {			goRoom(command);		} else if (commandWord.equals("quit")) {			if (command.hasSecondWord()) {				System.out.println("Quit what?");			} else {				return true; // signal that we want to quit			}		}else if(commandWord.equals("back")){			this.back(command);		}		return false;	}	private void printHelp() {		System.out.println("You are lost. You are alone. You wander");		System.out.println("around at Monash Uni, Peninsula Campus.\n");		System.out.println("Your command words are:");		System.out.println(parser.showCommands());	}	private void goRoom(Command command) {		if (!command.hasSecondWord()) {			System.out.println("Go where?");		} else {			String direction = command.getSecondWord();				// Try to leave current room.			Room nextRoom = currentRoom.nextRoom(direction);				if (nextRoom == null)				System.out.println("There is no door!");			else {				currentRoom = nextRoom;				System.out.println(currentRoom.longDescription());				currentRoom.win();				if (!this.wentBackCheck){					this.playerPath.add(direction);				}			}		}	}	private void back(Command command){		if (this.playerPath.size() == 0){			System.out.println("Can't go any further back! ");		}else{			this.wentBackCheck = true;			String backDirection = "";			if (this.playerPath.get(this.playerPath.size() -1).equals("north")){				backDirection = "south";			}else if (this.playerPath.get(this.playerPath.size() -1).equals("south")){				backDirection = "north";			}else if (this.playerPath.get(this.playerPath.size()-1).equals("east")){				backDirection = "west";			} if (this.playerPath.get(this.playerPath.size()-1).equals("west")){				backDirection = "east";			}			command.setSecondWord(backDirection);			if (!backDirection.equals("")){				this.goRoom(command);				this.wentBackCheck=false;			}			this.playerPath.remove(this.playerPath.size()-1);		}	}}