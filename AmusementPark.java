import java.util.*;
import java.nio.file.*;
import java.io.*;

public class AmusementPark extends Location
{
	
	public static void main(String[] args)
	{
		GameVars gv = Saver.tryLoad();
		DetAdv da = new DetAdv();
		AmusementPark ap = new AmusementPark();
		ap.run(da, gv);
		System.exit(0);
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
	}
}