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
}