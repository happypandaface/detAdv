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
	public static final long RACE_TRACKS = 6;
	public static final long AMUSEMENT_PARK = 7;
	public static final long CLUB = 9; // returns poker room currently
	public static final long CONSTRUCTION_SITE = 10;
	
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
	
	public Inventory inventory;
	
	public PoliceStation policeStation;
	public Mansion mansion;
	public Car car;
	public PokerRoom pokerRoom;
	public Diner diner;
	public ConstructionSite constructionSite;
	public RaceTracks raceTracks;
	public AmusementPark amusementPark;
	
	public GameVars()
	{	
		// initialize all the locations when starting
		// the game
		diner = new Diner();
		policeStation = new PoliceStation();
		mansion = new Mansion();
		car = new Car();
		pokerRoom = new PokerRoom();
		inventory = new Inventory();
		constructionSite = new ConstructionSite();
		raceTracks = new RaceTracks();
		amusementPark = new AmusementPark();
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
		if (currentLocation == POKER_ROOM ||
			currentLocation == CLUB)
		{
			return pokerRoom;
		}else
		if (currentLocation == RACE_TRACKS)
		{
			return raceTracks;
		}else
		if (currentLocation == AMUSEMENT_PARK)
		{
			return amusementPark;
		}else
		if (currentLocation == CONSTRUCTION_SITE)
		{
			return constructionSite;
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