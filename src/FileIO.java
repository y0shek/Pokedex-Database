///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Pokedex.java
// File:             FileIO.java
// Semester:         CS302 Fall 2012
//
// Author:           Andrew caris@wisc.edu
// CS Login:         andrew
// Lecturer's Name:  Deb Deppeler
// Lab Section:      327
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Joshua Kellerman jkellerman@wisc.edu
// CS Login:         kellerma
// Lecturer's Name:  Deb Deppeler
// Lab Section:      327
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Character;

/**
 * Handles the input and output of text files of Pokemon and if the Pokemon
 * are valid then they are created into Pokemon objects and added into the
 * @Pokedex. 
 *
 * <p>Bugs: none known
 *
 * @author Andrew Caris
 * @author Joshua Kellerman
 */
public class FileIO {
	public static boolean found = false;
	public static int pokeTally;

	/**
	 * Reads in a file and checks to see if that file exists or not and then
	 * adds a Pokemon if it does not already exist. Does not overwrite already
	 * existing Pokemon.
	 * 
	 * @param filename (the name of the file with the list of Pokemon)
	 * @param p (an array list of existing Pokemon for the new Pokemon in the
	 * 			file to be added to)
	 * @return none
	 */
	public static void readFile(String filename, ArrayList<Pokemon> p){	
		Scanner in;
		try {
			pokeTally = 0;
			in = new Scanner(new File(filename));
			while(in.hasNextLine()){
				Pokemon newPoke = readPokemon(in);
				for (int i = 0; i < p.size(); i ++){
					if(p.get(i).getName().equals(newPoke.getName())){
						return;
					}
				}
				p.add(newPoke);
				pokeTally ++;
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		} catch (NullPointerException f){
			System.out.println(f.getMessage());
		}
	}

	/**
	 * Processes the data in a valid file to see if the Pokemon are valid
	 * to be passed through the @Pokemon constructor.
	 * 
	 * @param in (is a scanner object to read the data in the file)
	 * @return A Pokemon object that is used in {@link #readFile) to add it to the 
	 * 		   existing @Pokedex.
	 */
	public static Pokemon readPokemon(Scanner in) throws NullPointerException{

		try {
			String name = in.next();
			for (int i = 0; i < name.length(); i++){
				if (Character.isLowerCase(name.charAt(i))){
					throw new InputMismatchException("ERROR: Invalid Name! " +
							"Name must be in all-caps!");
				}
			}
			String type = in.next();

			for (int i = 0; i < type.length(); i++){
				if (Character.isLowerCase(type.charAt(i))){
					throw new InputMismatchException("ERROR: Invalid Type name! " +
							"Type must be in all-caps!");
				}
			}

			if(!(type.equals("FIRE") || type.equals("WATER") || type.equals("ELECTRIC") || 
					type.equals("GRASS") || type.equals("FLYING") || type.equals("PSYCHIC") ||
					type.equals("ROCK") || type.equals("GHOST") || type.equals("DRAGON"))){
				throw new InputMismatchException("ERROR: Invalid Type.");
			}

			/*
			 * WHY DOESNT PASSING IN AN ARRAY FROM POKEDEX WORK?
			 */
			//		for(int n=0; n < Pokedex.validType.length;n++){
			//			if(type.equals(Pokedex.validType[n])){
			//				found=true;
			//			}
			//			
			//			}
			//			if(!found){
			//				throw new InputMismatchException("ERROR: Invalid Type.");
			//			}



			int height = in.nextInt();
			if (height <= 0){
				throw new InputMismatchException("ERROR: Height must be a positive" +
						" integer.");
			}
			String descript = in.nextLine().trim();
			Pokemon newPoke = new Pokemon (name, type, height, descript);
			return newPoke;
		} 
		catch (InputMismatchException e){
			System.out.println(e.getMessage());
		} throw new NullPointerException("Pokemon after last properly " +
				"formatted Pokemon in file are not saved. Check input file for errors.");
	}

	/**
	 * Writes the all of the valid Pokemon in the existing @Pokedex to a filename
	 * specified by the user
	 * 
	 * @param filename (the name of the file the user want to save the Pokedex to.
	 * @param p (the current Pokedex of @Pokedex)
	 * @return none
	 */
	public static void write (String filename, ArrayList<Pokemon> p){

		PrintWriter out;
		try {
			out = new PrintWriter(filename);
			for (int i = 0; i < p.size(); i++){
				out.println(p.get(i).toString());
			}
			out.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not Found!!");

		}//end for
	}//end method
}//end class
