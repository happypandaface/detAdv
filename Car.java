import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Car extends Location
{
	public static final long DRIVING_TO_MANSION = 1;
	public static final long DRIVING_TO_POLICE_STATION = 2;
	public long state;
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		if (state == DRIVING_TO_MANSION)
		{
			Scanner inputReader = saveAndGetScanner(gv);
			System.out.println("The car ride to the Mansion is long and arduous\nIt is in the hills and the road is unkept and riddled with pot holes.");
			DetUtil.doContinue(inputReader);
			System.out.println("But I make it. My firebird is old, but it can still tough\nthrough anything, just like me.");
			DetUtil.doContinue(inputReader);
			gv.setLocation(GameVars.MANSION);
			da.run(gv);
		}
	}
}