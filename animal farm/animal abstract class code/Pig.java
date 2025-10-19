// this is a Pig class that has methods to simulate a pig’s actions
//@author Melody Yin
//@author Ainslie Chen
public class Pig extends Animal
{
	//constructor that has no parameters and calls on constructor with 1 parameter
	//@postcondition creates new Pig
	//@return nothing
	public Pig()
	{
		this("pig");
	}

	//constructor that has 1 parameter and calls on the superclass constructor
	//@postcondition creates new Pig
	//@return nothing
	public Pig (String commonName)
	{
		super("Sus Domesticus", commonName);
}


//constructor that has 2 parameters and calls on the superclass constructor
//@postcondition creates new Pig
public Pig(String latinName, String commonName)
{
	super(latinName, commonName);
}

//simulates the sounds that a pig makes
public String speak()
{
	return "oink";
}
}

