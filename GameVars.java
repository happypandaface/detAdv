import org.json.simple.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class GameVars extends Savable
{
	private String characterName;
	public long policeStationState;
	
	public GameVars()
	{
	}
	public void setCharacterName(String s)
	{
		characterName = s;
	}
	public String getCharacterName()
	{
		return characterName;
	}
	
	@Override
	public String getSaveGameName()
	{
		return getCharacterName();
	}
	@Override
	public String toString()
	{
		JSONObject obj = new JSONObject();
		
		// here's where we save all the player's variables
		
		obj.put("name",characterName);
		obj.put("policeStationState",policeStationState);
		String jsonText = JSONValue.toJSONString(obj);
		return jsonText;
	}
	@Override
	public void fromString(String s)
	{
		Object obj=JSONValue.parse(s);
		JSONObject jsonObj=(JSONObject)obj;
		
		// here's where we load all the player's variables
		characterName = (String)jsonObj.get("name");
		policeStationState = (long)jsonObj.get("policeStationState");
	}
}