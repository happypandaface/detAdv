import java.util.*;

public class Inverntory
{
	public Array<Item> items;
	
	public Inventory()
	{
		items = new Array<Item>();
	}
	
	public void addItem(String s)
	{
		items.add(s);
	}
	
	public void printInventory()
	{
		System.out.println("You check your inventory.");
		System.out.println("You have:");
		for (int i = 0; i < items.size; ++i)
		{
			Item i = new Item();
			System.out.println(i.getInvName());
		}
	}
}