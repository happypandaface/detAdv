import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Mansion extends Location
{
	long state = ARRIVE_FIRST_TIME;
	public static long ARRIVE_FIRST_TIME = 1;
	public static long AT_CRIME_SCENE = 2;
	public static long AT_POOL = 3;
	public static long MAIN = 4;
	
	long tempAction = INVESTIGATING;
	public static long INVESTIGATING = 1;
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		if (state == ARRIVE_FIRST_TIME)
		{
			System.out.println("I pull up to the Mansion");
			DetUtil.doContinue(inputReader);
			state = AT_CRIME_SCENE;
			run(da, gv);
		}else
		if (state == AT_CRIME_SCENE)
		{
			System.out.println("I go around to the back where the stables are. I guess rich people can just have stables wherever they want."); //alter this
			DetUtil.doContinue(inputReader);
			System.out.println("The scene isn't so gruesome. It looks like the horse's neck was slit. Probably while it was sleeping. Still a painful death probably."); //change this wording
			ChoiceMenu crimeFirst = new ChoiceMenu();
			if (gv.rain == GameVars.RAIN)
			{
				System.out.println("There's a small stream of water running through the crime scene.");
				crimeFirst.addOption("follow the stream");
			}else
			if (gv.rain == GameVars.NO_RAIN)
			{
				System.out.println("I find a note near the horse.");
				crimeFirst.addOption("read the note");
			}
			crimeFirst.addOption("leave");
			crimeFirst.execute(gv);
			if (crimeFirst.getChoice() == 1)
			{
				if (gv.rain == GameVars.RAIN)
				{
					state = AT_POOL;
					run(da, gv);
				}else
				if (gv.rain == GameVars.NO_RAIN)
				{
					System.out.println("The note is interesting and has very important information to the case.");
					DetUtil.doContinue(inputReader);
				}
			}else
			if (crimeFirst.getChoice() == 2)
			{
				System.out.println("I leave. There's nothing left to see here.");
				crimeFirst.addOption("read the note");
			}
		}else
		if (state == AT_POOL)
		{
			System.out.println("I follow the stream and find that it has pooled near the end of the stable.");
			DetUtil.doContinue(inputReader);
			System.out.println("I'm surprised to find a note in this small puddle!");
			DetUtil.doContinue(inputReader);
			gv.inventory.addItem(Mansion.getMurderNote());
			ChoiceMenu atPool = new ChoiceMenu();
			atPool.addOption("Back to crime scene");
			atPool.execute(gv);
		}else
		if (state == MAIN)
		{
		ChoiceMenu main = new ChoiceMenu();
		main.addOption("Examine the victim.");
		main.addOption("Press the caretaker.");
		main.addOption("Find the Jockey.");
		main.addOption("Look around the other horse pens.");
		main.execute(gv);
			if (main.getChoice() == 3)
			{
			System.out.println("Nothing to see here.");
			}else
			if (main.getChoice() == 2)
			{
			System.out.println("\"I don't have anything else to say to you.\"");
			DetUtil.doContinue(inputReader);
			System.out.println("\"I still have a lot of other horses to take care of.\"");
			DetUtil.doContinue(inputReader);
			System.out.println("\"Stubborn guy. He might be hiding something.\"");
			DetUtil.doContinue(inputReader);
			
			}else
			if (main.getChoice() == 3)
			{
			System.out.println("I manage to find the jockey after looking around a bit.");
			DetUtil.doContinue(inputReader);
			System.out.println("\"I can't believe he is really gone! \"");
			DetUtil.doContinue(inputReader);
			System.out.println("\"Good for nothing horse! We were supposed to make it big!\"");
			DetUtil.doContinue(inputReader);
			
			}else
			if (main.getChoice() == 1)
			{
			System.out.println("Really a hack job. This wasn't a clean way to go. The murderer didn't really know what he was doing.");
			DetUtil.doContinue(inputReader);
			System.out.println("It's clearly the work of an amateur. This guy has never killed anything before.");
			DetUtil.doContinue(inputReader);
			System.out.println("Damn. That poor bastard. It wasn't an easy death.");
			DetUtil.doContinue(inputReader);
			}
			
			
			
		}
	}
	public static Item getMurderNote()
	{
		Item i = new Item("murder note");
		i.setDescription("It says cool stuff");
		return i;
	}
}