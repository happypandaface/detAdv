

public class Item
{
	private String name;
	public String description;
	
	public Item(String s)
	{
		name = s;
	}
	
	public void setDescription(String d)
	{
		description = d;
	}
	public String getName()
	{
		return "a " + name;
	}
	
	public String getInvName()
	{
		if (name.charAt(0) == 'a' ||
			name.charAt(0) == 'e' || 
			name.charAt(0) == 'i' || 
			name.charAt(0) == 'o' || 
			name.charAt(0) == 'u')
		{
			return "an " + name;
		}else
		{
			return "a " + name;
		}
	}
	
	public void inspect()
	{
		System.out.println(description);
		DetUtil.doContinue();
	}
}