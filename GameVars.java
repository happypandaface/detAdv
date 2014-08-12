import org.json.simple.*;

public class GameVars
{
	private String characterName;
	
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
	public String toString()
	{
		JSONObject obj = new JSONObject();
		obj.put("name",characterName);
		String jsonText = JSONValue.toJSONString(obj);
		return jsonText;
	}
	public void fromString(String s)
	{
		
	}
	public void save()
	{
		System.out.println(toString());
	}
}