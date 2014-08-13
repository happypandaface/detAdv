import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Mansion extends Location
{
	long state = ARRIVE_FIRST_TIME;
	public static long ARRIVE_FIRST_TIME = 1;
	public static long AT_CRIME_SCENE = 2;
	public static long AT_POOL = 3;
	
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
			System.out.println("I go around to the back where the stables are. I guess rich people can just have stables wherever they want.");
			DetUtil.doContinue(inputReader);
			System.out.println("The scene isn't so gruesome. It looks like the horse's neck was slit. Probably while it was sleeping. Still a painful death probably.");
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
			atPool.addOption("back to crime scene");
			atPool.execute(gv);
		}
	}
	public static Item getMurderNote()
	{
		Item i = new Item("murder note");
		i.setDescription("It says cool stuff");
		return i;
	}
}