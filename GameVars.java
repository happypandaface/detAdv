import java.util.*;
import java.nio.file.*;
import java.io.*;

public class GameVars
{
	public static final long LOWEST_LOC = 1;
	public static final long POLICE_STATION = 1;
	public static final long MANSION = 2;
	public static final long CAR = 3;
	
	private String characterName;
	public long currentLocation = POLICE_STATION;
	public PoliceStation policeStation;
	public Mansion mansion;
	public Car car;
	
	public GameVars()
	{
		policeStation = new PoliceStation();
		mansion = new Mansion();
		car = new Car();
	}
	public Location getLocation()
	{
		if (currentLocation == POLICE_STATION)
		{
			return policeStation;
		}else
		if (currentLocation == MANSION)
		{
			return mansion;
		}else
		if (currentLocation == CAR)
		{
			return car;
		}
		return null;
	}
	public void setCharacterName(String s)
	{
		characterName = s;
	}
	public String getCharacterName()
	{
		return characterName;
	}
	public boolean checkLocation(long loc)
	{
		return currentLocation == loc;
	}
	public void setLocation(long s)
	{
		currentLocation = s;
	}
	public String getSaveGameName()
	{
		return getCharacterName();
	}
}