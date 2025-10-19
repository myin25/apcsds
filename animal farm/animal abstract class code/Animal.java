// This is an Animal abstract class that provides the framework for other animals
//@author Ainslie Chen and Melody Yin
//@version 10/11/21
public abstract class Animal implements Comparable
{
	String latinName; // instance variable that stores Latin name
	String commonName; // instance variable that stores the common name 

	//constructs Animal
 	//@param latin is a string that is the Latin name of the Animal
	//@param common is a string that is the common name of the ANimal
	//@precondition object is already instantiated
	//@postcondition no original values changed
	//@return latin name of animal
	public Animal (String latin, String common)
	{
		latinName = latin;
		commonName = common;
	}

		
	//returns Latin name of the object 
	//@precondition object is already instantiated
	//@postcondition no original values changed
	//@return Latin name of animal
	public String getLatinName()
	{
		return latinName;
	}


	//returns common name of the object 
	//@precondition object is already instantiated
	//@postcondition no original values changed
	//@return common name of animal
	public String getCommonName() 
	{
		return commonName;
	}

	//sets Latin name of the object 
	//@precondition object is already instantiated
	//@postcondition latinName is modified to specified user input
	//@return nothing
	public void setLatinName(String newLatinName) 
	{
		latinName = newLatinName;
	}

	//sets common name of the object 
	//@precondition object is already instantiated
	//@postcondition commonName is modified to specified user input
	//@return nothing
	public void setCommonName(String newCommonName) 
	{
		commonName = newCommonName;
	}


	public abstract String speak();

	public int compareTo (Object other)
	{
		if (!(other instanceof Animal))
		{
				throw new IllegalArgumentException ("inputted object is not an Animal");

		}
		else
		{
				return commonName.compareTo (((Animal) other).getCommonName());
		}
	}

}