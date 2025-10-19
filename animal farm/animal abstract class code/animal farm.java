import java.util.ArrayList;

// tester method for Animal and its subclasses
public class OldMacDonaldsFarm
{
	String farmerName; // name of the farmer
	ArrayList<Animal> farmAnimals; // list of animals on the farm
	private String phrase1 = farmerName + " had a farm," ; //first phrase in song
	private static String ei = " E-I-E-I-O"; //second phrase in song

	// default constructor
	public OldMacDonaldsFarm()
	{
	farmerName = "Old Macdonald";
	farmAnimals = new ArrayList<Animal>();
	}

	public void singVerse()
	{
		System.out.print(phrase1 + ei);
	System.out.print("and on his farm he had some " + farmAnimals.get (farmAnimals.size() - 1) + "s, ");
	System.out.print(ei);
		for (int i = farmAnimals.size() - 1; i >= 0; i--)
		{
			Animal tempanimal = farmAnimals.get(i);
			System.out.println("With a  " + tempanimal.speak() + "-" + tempanimal.speak() + " here, and a  " + tempanimal.speak() + "-" + tempanimal.speak() + " there,");
			System.out.println("Here a " + tempanimal.speak() +  ", there a," + tempanimal.speak() + ", every where a " + tempanimal.speak() + "-" + tempanimal.speak() + ",");
		}
		System.out.print (phrase1 + ei);
	}

	public static void main (String [ ] args)
	{
	OldMacDonaldsFarm singer = new OldMacDonaldsFarm( );
	singer.farmAnimals.add(new Chicken( ));
	singer.singVerse( );
	singer.farmAnimals.add(new Chick());
	singer.singVerse( );
	singer.farmAnimals.add(new Rooster( ));
	singer.singVerse( );
	singer.farmAnimals.add(new Pig( ));
	singer.singVerse( );
	}
}
