import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Car extends Location
{
	public static final long DRIVING_TO_MANSION = 1;
	public static final long DRIVING_TO_POLICE_STATION = 2;
	public static final long OUTSIDE_DINER = 3;
	public static final long DINER_TO_POLICE_STATION = 4;
	public long state;

	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		if (gv.mansion.visited == 0)
		{
			if (state == OUTSIDE_DINER)
			{
			System.out.println("The engine turns over a few times before coming to life.");
			DetUtil.doContinue(inputReader);
			System.out.println("I let her idle for several minutes before turning on the radio.");
			DetUtil.doContinue(inputReader);
			System.out.println("Dispatch has something about a death over in Arlington heights.");
			System.out.println("\"A body... discovered late... 2200 W Euclid Ave... \"");
			DetUtil.doContinue(inputReader);
			System.out.println("Static.");
			DetUtil.doContinue(inputReader);
			ChoiceMenu first = new ChoiceMenu();
				first.addOption("Head straight there.");
				first.addOption("Stop by the department first.");
				first.execute(gv);
				if (first.getChoice() == 2)
				{
					state = DINER_TO_POLICE_STATION;
					System.out.println("Better get some more info first.");
					DetUtil.doContinue(inputReader);
					run(da, gv);
				}else
				if (first.getChoice() == 1)
				{
					state = DRIVING_TO_MANSION;
					System.out.println("Let's check it out.");
					DetUtil.doContinue(inputReader);
					run(da, gv);
				}
			
			
			}
		


		
			else if (state == DINER_TO_POLICE_STATION)
			{
				if (gv.coffee == GameVars.COFFEE)
				{
					System.out.println("This coffee is still too hot to drink.");
					DetUtil.doContinue(inputReader);
				}
				System.out.println("The station is only a few blocks from here.");
				DetUtil.doContinue(inputReader);
				System.out.println("I still manage to hit most of the red lights on the way.");
				DetUtil.doContinue(inputReader);
				System.out.println("I pull up just as some repeat offenders are walking out after a night in the tank.");
				DetUtil.doContinue(inputReader);
				gv.setLocation(GameVars.POLICE_STATION);
				da.run(gv);
			}
		
		
		

		
			else if (state == DRIVING_TO_MANSION)
			{
				System.out.println("The car ride to the Mansion is long and arduous\nIt is in the hills and the road is unkept and riddled with pot holes.");
				DetUtil.doContinue(inputReader);
				if (gv.rain == GameVars.RAIN)
				{
					System.out.println("The rain isn't helping either. On one especially deep hole and my wheel spins, getting no traction.");
					DetUtil.doContinue(inputReader);
				}
				System.out.println("But I make it eventually. My firebird is old, but it can still tough\nthrough anything, just like me.");
				DetUtil.doContinue(inputReader);
				gv.setLocation(GameVars.MANSION);
				da.run(gv);
			}
		}
		else if (gv.mansion.visited == 1)
		{
		DetUtil.doContinue(inputReader);
		ChoiceMenu main = new ChoiceMenu();
			main.addOption("Back to the department.");
			main.addOption("Racetracks.");
			main.addOption("Stables.");
			main.addOption("Construction site.");
			main.addOption("Amusement park.");
			main.addOption("Club.");
			main.execute(gv);
			if (main.getChoice() == 1)
			{
				gv.setLocation(GameVars.POLICE_STATION);
				da.run(gv);
			}else
			if (main.getChoice() == 2)
			{
				gv.setLocation(GameVars.RACE_TRACKS);
				da.run(gv);
			}else
			if (main.getChoice() == 3)
			{
				gv.setLocation(GameVars.MANSION);
				da.run(gv);
			}else
			if (main.getChoice() == 4)
			{
				gv.setLocation(GameVars.CONSTRUCTION_SITE);
				da.run(gv);
			}else
			if (main.getChoice() == 5)
			{
				gv.setLocation(GameVars.AMUSEMENT_PARK);
				da.run(gv);
			}else
			if (main.getChoice() == 6)
			{
				gv.setLocation(GameVars.CLUB);
				da.run(gv);
			}
		}
		
		
		/*else if (gv.mansion.state == LEAVING) this is the template for thoughts that only happen after leaving a certain area
		{
			System.out.println("test");
			DetUtil.doContinue(inputReader);
			gv.mansion.state = main;
			
			if (first.getChoice() == 1)
				{
				gv.setLocation(GameVars.POLICE_STATION);
				da.run(gv);
				}
		}
		*/
		
		
		//else if (state == RaceTracks_TO_POLICE_STATION)
			//cut brakes?
	}
}