import java.util.*;
import java.nio.file.*;
import java.io.*;

public class GameVars
{
	public static final long LOWEST_LOC = 5;
	public static final long POLICE_STATION = 1;
	public static final long MANSION = 2;
	public static final long CAR = 3;
	public static final long POKER_ROOM = 4;
	public static final long DINER = 5;
	
	public static final long NO_RAIN = 0;
	public static final long LIGHT_ = 1;
	public static final long RAIN = 2;
	public static final long HEAVY_RAIN = 3;
	
	public static final long NO_COFFEE = 0;
	public static final long COFFEE = 1;
	
	private String characterName;
	public long currentLocation = DINER;
	public long rain = NO_RAIN;
	public long coffee = NO_COFFEE;
	public PoliceStation policeStation;
	public Mansion mansion;
	public Car car;
	public PokerRoom pokerRoom;
	public Diner diner;
	public Inventory inventory;
	
	public GameVars()
	{
		diner = new Diner();
		policeStation = new PoliceStation();
		mansion = new Mansion();
		car = new Car();
		pokerRoom = new PokerRoom();
		inventory = new Inventory();
	}
	public Location getLocation()
	{
		if (currentLocation == DINER)
		{
			return diner;
		}else
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
		}else
		if (currentLocation == POKER_ROOM)
		{
			return pokerRoom;
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