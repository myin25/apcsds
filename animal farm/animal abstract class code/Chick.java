// this is a Chick class that has methods to simulate a chick’s actions
//@author Ainslie Chen
//@author Melody Yin
//@version 10/14/21
public class Chick extends Chicken
{
	//default constructor, calls on superclass method in Chicken class
	public Chick() 
{
	super();
}

//single-parameter constructor, calls on superclass method with one parameter in //Chicken class
public Chick (String chick)
{
	super("chick");
}
	
	//prints “peep” when speak() method is called
	//simulates the “speaking” of a chick
	public String speak()
	{
		return "peep";
	}
}
