import java.util.*;

public class Inventory
{
	public List<Item> items;
	
	public Inventory()
	{
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item s)
	{
		items.add(s);
	}
	
	public void printInventory()
	{
		System.out.println("You check your inventory.");
		System.out.println("You have:");
		for (int i = 0; i < items.size(); ++i)
		{
			Item it = items.get(i);
			System.out.println(it.getInvName());
		}
	}
}