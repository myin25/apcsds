// This is a Chicken class that extends the Animal class
//@author Ainslie Chen and Melody Yin
//@version 10/11/21
public class Chicken extends Animal
{
	//constructor that has no parameters and calls on constructor with 1 parameter
	//@postcondition creates new Chicken
	//@return nothing
	public Chicken()
	{
		this("chicken");
	}

	//constructor that has 1 parameter and calls on the superclass constructor
	//@postcondition creates new Chicken
	//@return nothing
	public Chicken (String chickenType)
	{
		super("Gallus Gallus Domesticus", chickenType);
}

public Chicken (String latinName, String chickenType)
{
	super (latinName, chickenType);
}

public String speak()
{
	return "bawk";
}
}
