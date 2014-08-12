import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Mansion extends Location
{
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		System.out.println("I pull up to the Mansion");
		DetUtil.doContinue(inputReader);
	}
}