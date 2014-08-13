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
	public void addItems(int type, int amount)
	{
		for (int i = 0; i < items.size(); ++i)
		{
			Item it = items.get(i);
			if (it.checkType(type))
				it.addAmount(amount);
		}
	}
	public void removeItems(int type, int amount)
	{
		for (int i = 0; i < items.size(); ++i)
		{
			Item it = items.get(i);
			if (it.checkType(type))
				it.removeAmount(amount);
		}
	}
	public int getAmount(int type)
	{
		for (int i = 0; i < items.size(); ++i)
		{
			Item it = items.get(i);
			if (it.checkType(type))
				return it.getAmount();
		}
		return 0;
	}
	public void access(GameVars gv)
	{
		System.out.println("I look in my bag...");
		if (items.size() > 0)
		{
			System.out.println("You check your inventory.");
			System.out.println("You have:");
			ChoiceMenu inv = new ChoiceMenu();
			inv.setCanAccessInventory(false);
			for (int i = 0; i < items.size(); ++i)
			{
				Item it = items.get(i);
				inv.addOption(it.getInvName());
			}
			inv.addOption("back");
			inv.execute(gv);
			if (inv.getChoice() != items.size()+1)
				// activates as long as the user didn't
				// select "back"
			{
				items.get(inv.getChoice()-1).inspect();
				access(gv);
			}
		}else
		{
			System.out.println("Looks like I don't have anything");
			DetUtil.doContinue();
		}
		
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