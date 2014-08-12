import java.util.*;
import java.nio.file.*;
import java.io.*;

public abstract class Location
{
	public abstract void run(DetAdv da, GameVars gv);
	public Scanner saveAndGetScanner(GameVars gv)
	{
		Scanner inputReader = new Scanner(System.in);
		Saver.save(gv);
		return inputReader;
	}
	public String toString()
	{
		return "";
	}
	public void fromString(String s)
	{
	}
}