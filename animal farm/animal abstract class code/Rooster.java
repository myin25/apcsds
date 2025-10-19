// this is a Rooster class that has methods to simulate a rooster’s actions
//@author Melody Yin
//@author Ainslie Chen
//@version 10/14/21
public class Rooster extends Chicken
{
	// default constructor
	public Rooster()
	{
		super();
	}

	// constructor that calls on super if there’s one parameter
	public Rooster(String roosterType)
	{
		super(roosterType);
	}

	// simulates the ‘speaking’ of a rooster
	public String speak()
	{
		return "cock-a-doodle-do";
	}
}
