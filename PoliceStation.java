import java.util.*;

public class PoliceStation
{
	public PoliceStation()
	{
		
	}
	public static void main(String[] args)
	{
		PoliceStation ps = new PoliceStation();
		ps.run(new Scanner(System.in), new GameVars());
	}
	public void run(Scanner inputReader, GameVars gameVars)
	{
		System.out.println("The police force has been riddled with worthless cases lately.\nPeople will call the police over such stupid things nowadays.");
		System.out.println("\""+gameVars.getCharacterName()+"! You need to get to the scene of the crime immediately!\"");
		ChoiceMenu first = new ChoiceMenu();
		first.addOption("inquire");
		first.addOption("leave");
		first.execute(inputReader);
		if (first.getChoice() == 1)
		{
			System.out.println("You leave the police station");
		}else
		{
			System.out.println("\"What's this mess about, Walt?\"");
		}
	}
}