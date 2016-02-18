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
/**
 * Defines how a Pokemon is created and what parameters are valid and how
 * parameters should be entered to return a valid Pokemon
 *
 * <p>Bugs: none known
 *
 * @author Andrew Caris
 * @author Joshua Kellerman
 */
public class Pokemon implements Comparable{

	private String name;
	private String type;
	private int height;
	private String descript;

	public Pokemon (String name, String type, int height, String descript){
		this.name = name;
		this.type = type;
		this.height = height;
		this.descript = descript;	
	}
	/**
	 * An accessor to get the name of the Pokemon
	 * 
	 * @param none
	 * @return returns the {@link #name} of the Pokemon object
	 */
	public String getName(){
		return name;
	}

	/**
	 * A mutator to set the name for the Pokemon
	 * 
	 * @param inName (passed in by the user when creating an new pokemon)
	 * @return none
	 */
	public void setName(String inName){
		name = inName;
	}

	/**
	 * An accessor to get the type of the Pokemon
	 * 
	 * @param none
	 * @return returns the {@link #type} of the Pokemon object
	 */
	public String getType(){
		return type;
	}

	/**
	 * A mutator to set the type for the Pokemon
	 * 
	 * @param inType (passed in by the user when creating an new pokemon)
	 * @return none
	 */
	public void setType(String inType){
		type = inType;
	}

	/**
	 * An accessor to get the height of the Pokemon
	 * 
	 * @param none
	 * @return returns the {@link #height} of the Pokemon object
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * A mutator to set the height for the Pokemon
	 * 
	 * @param inHeight (passed in by the user when creating an new pokemon)
	 * @return none
	 */
	public void setHeight(int inHeight){
		height = inHeight;
	}

	/**
	 * An accessor to get the description of the Pokemon
	 * 
	 * @param none
	 * @return returns the {@link #descript} of the Pokemon object
	 */
	public String getDescript(){
		return descript;
	}

	/**
	 * A mutator to set the description for the Pokemon
	 * 
	 * @param inDescript (passed in by the user when creating an new pokemon)
	 * @return none
	 */
	public void setDescript(String inDescript){
		descript = inDescript;
	}

	/**
	 * Modifies the toString() method of @String calls to print out in the order
	 * of name, type, height, descript each preceded by a space except for the
	 * name data
	 * 
	 * @param none
	 * @return returns a properly formatted Pokemon string to be saved in a file
	 */
	public String toString (){
		return name +" " + type + " " + height + " " + descript;
	}

	/**
	 * Modifies the compareTo() method of @Comparable class to check if two
	 * pokemon equal depending on their name
	 * 
	 * @param arg0 is an object that is casted to a Pokemon object
	 * @return returns an int if the names equal of both Pokemon objects
	 * 			if the pokemon equal returns a 0
	 * 			if the pokemon are not it either returns a -1 or 1
	 */
	public int compareTo(Object arg0) {
		Pokemon p = (Pokemon)arg0;
		return this.getName().compareTo(p.getName());
	}

}//end of Pokemon Class
