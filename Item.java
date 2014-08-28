

public class Item
{
	static int SADDLE_NOTE = 1;
	static int CARETAKER_PHOTOS = 2;
	static int MONEY = 3;
	static int PHONE_CALL_CASSETTE = 4;
	static int CARNIE_FLYER = 5;
	
	private int type;
	private int amount;
	private String name;
	public String description;
	
	public Item(int t)
	{
		type = t;
	}
	public Item(String s)
	{
		name = s;
	}
	
	public boolean checkType(int t)
	{
		return type == t;
	}
	
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int f)
	{
		amount = f;
	}
	public void addAmount(int a)
	{
		amount += a;
	}
	public void removeAmount(int a)
	{
		amount -= a;
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
		if (type == MONEY)
		{
			if (amount == 1)
				return "A Dollar";
			else
				return amount+" Dollars";
		}
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