///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Pokedex.java
// File:             FileIO.java
// Semester:         CS302 Fall 2012
//
// Author:           Joshua Kellerman kellerman.joshua.m@Gmail.com
// CS Login:         kellerma
// Lecturer's Name:  Deb Deppeler
// Lab Section:      327
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Andrew Caris caris@wisc.edu
// CS Login:         andrew
// Lecturer's Name:  Deb Deppeler
// Lab Section:      327
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Creates a @Pokedex accepting valid Pokemon specified by the @Pokemon class.
 * The @Pokedex checks to see if the paramaters passed into the 
 * @Pokemon constructor are valid in the context of {@link #inName}, 
 * {@link #inType}, {@link #inHeight}, {@link #inDescript}. Also, the @Pokedex 
 * implements a language where the user can only use the commands provided 
 * in the {@link #help} method.
 *
 * <p>Bugs: none known
 *
 * @author Andrew Caris
 * @author Joshua Kellerman
 */
public class Pokedex {
	private  String inName = "";
	private  String testName="";
	private  String inType = "";
	private  String testType="";
	private  Scanner in = new Scanner(System.in);
	private static  String inputType="";
	private  int inHeight = 0;
	private  int testHeight = 0;
	private  String inDescript = "";
	private  int sameType = 0;
	private	 int compare = 0;
	private	 int caps = 0;
	private  ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();
	private  Pokemon newPoke;
	private  boolean quit = false;
	private  String input;
	static final String[] validType = {"FIRE","WATER", "ELECTRIC", "GRASS",
		"FLYING","PSYCHIC", "ROCK", "GHOST", "DRAGON"};
	private	 int removeIndex;


	/**
	 * Creates a new @Pokedex and is used as the main driver to start the program.
	 *
	 * @param args (command-line arguments)
	 * @return none
	 */
	public static void main(String [] args){
		//Implement while loop terminating on quit
		Pokedex pokeDex = new Pokedex();
		pokeDex.launch();

	}

	/**
	 * Allows the user to input commands and then those commands are parsed
	 * by @String methods and handled by various methods to provide a fully
	 * functioning implementation of valid Pokemon in the Pokedex.
	 *
	 * @param none
	 * @return none
	 */
	public void launch(){

		System.out.println("Welcome to the Pokedex!");
		help();
		while(!quit){

			System.out.println("Enter a command: ");

			input = in.next();

			if(input.equals("quit")){
				quit();
				break;
			}

			else if(input.equals("help")){
				help();
			}

			else if(input.equals("remove")){
				String removeName = in.next();
				remove(removeName, getPokeList());

			}
			else if(input.equals("add")){
				//use the first part of the user command
				/**Pokemon only added when the name is upper case and the
					type is correct
				 */
				String pokeName = in.next();
				String pokeType = in.next();
				int pokeHeight = in.nextInt();
				String pokeDescription = in.nextLine().trim();
				newPokemon(pokeName, pokeType, pokeHeight, pokeDescription);

			}//end of add command

			else if(input.equals("info")){
				String infoName = in.next();
				if (infoPoke(infoName) == null){
					System.out.println("Error! No Pokemon with that name.");
				} else
					System.out.println(infoPoke(infoName));
			}

			else if(input.equals("all")){
				System.out.println("Total known Pokemon: " + 
						pokeList.size());
				Collections.sort(pokeList);
				for (int i = 0; i< pokeList.size(); i++){
					System.out.println(pokeList.get(i));
				}

			}
			else if(input.equals("clear")){
				pokeList.clear();
				System.out.println("Cleared all Pokemon from Pokedex!");
			}

			else if(input.equals("save")){
				Collections.sort(pokeList);
				String fileName = in.next();
				FileIO.write(fileName, pokeList);
				System.out.println("Saved " + pokeList.size()+ 
						" Pokemon to "+ fileName);
			}

			else if (input.equals("load")){
				String fileName = in.next();
				FileIO.readFile(fileName, pokeList);
				Collections.sort(pokeList);
				System.out.println("Successfully loaded " + FileIO.pokeTally
						+ " pokemon into Pokedex.");			
			} else {
				System.out.println("ERROR: Unrecognized Command!");
				in.nextLine();
			}
		} // end of main while loop
		System.out.println("Good-Bye");
	}//end of launch method

	/**
	 * Used for error checking to see if a new Pokemon should be created
	 * by the @Pokemon constructor. If every parameter passed into the 
	 * {@link #newPokemon} method then the Pokemon is created.
	 *
	 *
	 * @param name (checks to see if the name is uppercase)
	 * @param type (checks to see if the type is a valid type and is uppercase)
	 * @param height (checks to see if the type is not negative or a string)
	 * @param description (gets the values after the height parameter and is
	 *  					appended at the end of the Pokemon object)
	 * @return none
	 */
	public void newPokemon(String name, String type, 
			int height, String description){
		testName = name;
		if(testName.toUpperCase().equals(testName)){
			inName = testName;
		}else{System.out.println("Error: Invalid Name! " +
				"Name must be in all-caps!");
		}

		inputType = type;
		for(int n=0;n<validType.length;n++){
			testType = validType[n];
			if(inputType.equals(testType) && inputType.toUpperCase().
					equals(inputType)){
				inType = inputType;
				sameType = 1;
				break;
			}
		}//end of loop testing types

		if(!checkType(inputType)){
			compare = 1;
		}

		if(checkType(inputType)){

			if(checkTypeCaps() == false){
				//need to check for something else
				caps = 1;
			}
		}

		if(caps == 1){
			System.out.println("ERROR: Type must be in all-caps");
			resetTypeCheck();
		}
		else if (compare == 1){
			System.out.println("ERROR: Invalid type!");
			resetTypeCheck();
		}

		//end of type parse
		testHeight = height;
		if(testHeight >= 0){
			inHeight = testHeight;
		}else{
			System.out.println("ERROR: Height must be a positive " +
					"integer!");

		}//end of height parse

		inDescript = description;

		boolean exists = false;

		if (pokeList.size() > 0){
			for (int i = 0; i < pokeList.size(); i++){
				if(inName.equals(pokeList.get(i).getName())){
					removeIndex = i;
					exists = true;
				}
			}//end of loop for searching similar pokemon names
		}//end of if starting loop

		if(testName.equals(testName.toUpperCase()) && sameType == 1 &&
				testHeight >= 0){

			if(exists){
				System.out.print("There already exists a Pokemon with that name." +
						"\n Would you like to overwrite (y/n) ? ");
				String ynChoice;
				boolean done = false;
				while(!done){
					ynChoice = in.nextLine();
					if (ynChoice.equals("n")){
						done = true;
					} else if (ynChoice.equals("y")){
						newPoke = new Pokemon(inName, inType, inHeight,
								inDescript);
						pokeList.set(removeIndex, newPoke);
						System.out.println(pokeList.get(removeIndex).getName()
								+" replaced.");
						done = true;
					} else {
						System.out.print("Please enter (y/n): ");
					}
				}//end while for y or n
			} else {
				newPoke = new Pokemon(inName, inType, inHeight, inDescript);
				pokeList.add(newPoke);
				System.out.println(pokeList.get(pokeList.
						lastIndexOf(newPoke)).getName() +" added.");
			}

			sameType=0;
			caps=0;
			compare=0;

		}//end of test

	}//end of newPokemon

	/**
	 * Iterates through the {@link #pokeList} array to see if that 
	 * Pokemon exists and if the Pokemon exists then it echos that Pokemon 
	 * to the console. If the Pokemon is not found then an appropriate error
	 * message is printed but that is not handled in this method.
	 *
	 * @param pokeName (checks to see if the pokemon is in the Pokedex when
	 * 					invoked by the info command)
	 * @return Pokemon (returns a pokemon object at a specific 
	 * 					index in {@link #pokeList})
	 */
	public Pokemon infoPoke(String pokeName) {
		for(int i=0; i < pokeList.size(); i++){
			if(pokeList.get(i).getName().equals(pokeName)){
				return pokeList.get(i);
			}

		}return null;

	}

	/**
	 * Quits the program when the user enters "quit"
	 *
	 * @param none
	 * @return none
	 */
	public void quit(){
		quit=true;
		//Put in other things to print when you quit the program
	}

	/**
	 * Prints out the Pokedex Command List when the user enters "help"
	 *
	 * @param none
	 * @return none
	 */
	public void help(){
		System.out.println("POKEDEX COMMAND LIST");
		System.out.println("add    - add a Pokemon");
		System.out.println("remove - remove a Pokemon");
		System.out.println("info   - display info on a Pokemon");
		System.out.println("all    - display all Pokemon");
		System.out.println("clear  - remove all Pokemon");
		System.out.println("load   - load all from a file");
		System.out.println("save   - save all to file");
		System.out.println("help   - display this command list");
		System.out.println("quit   - quit Pokedex program");
	}

	/**
	 * Checks to see if the type entered during the "add" command is a 
	 * valid type in the {@link #validType} array which contains all of the
	 * valid types of Pokemon.
	 *
	 * @param checkType (the type the user entered is passed in)
	 * @return true if it is a valid type or false if not
	 */
	public boolean checkType(String checkType){

		checkType = inputType.toUpperCase();
		for(int i=0; i< validType.length;i++){
			if(checkType.equals(validType[i])){
				return true;
			}

		}
		return false;

	}

	/**
	 * Checks to see if the type entered during the "add" command is 
	 * upperCase
	 * 
	 * @param none
	 * @return true if type is already upperCase and false if not
	 */
	public boolean checkTypeCaps(){
		for(int i=0; i < validType.length;i++){
			if(inputType.equals(validType[i])){
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the {@link #caps} and (@link #compare} values used to keep track
	 * if the type is correct for both being upperCase and a valid type after
	 * a pokemon is added or not.
	 * 
	 * @param none
	 * @return none
	 */
	public void resetTypeCheck(){
		caps = 0;
		compare = 0;
	}

	/**
	 * Is an accessor for the pokeList
	 * 
	 * @param none
	 * @return An array list of Pokemon objects
	 */
	public ArrayList<Pokemon> getPokeList(){
		return pokeList;
	}

	/**
	 * If the user enters the "remove" command then that Pokemon specified
	 * by its name is removed from the Pokedex.
	 * 
	 * @param name (the name of the Pokemon to be searched for)
	 * @param l (an array list of Pokemon objects so far in the Pokedex)
	 * @return none
	 */
	public void remove(String name, ArrayList<Pokemon> l){
		boolean found = false;
		for (int i = 0; i < l.size(); i++){
			if (name.equals(l.get(i).getName())){
				l.remove(i);
				found = true;
			} 

		}
		if (found){
			System.out.println("Removed Pokemon "+name);

		} else { System.out.println("Error! No Pokemon with that name!"); }
	}

}//end Pokedex class