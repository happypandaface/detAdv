import java.util.*;

public class ChoiceMenu
{
	private List<String> options;
	private int lastChoice;
	private String answerFailText = "I have to choose an option";
	private boolean canAccessInventory = true;
	
	public ChoiceMenu()
	{
		options = new ArrayList<String>();
	}
	
	public void addOption(String s)
	{
		options.add(s);
	}
	public void setFailText(String s)
	{
		answerFailText = s;
	}
	public void setCanAccessInventory(boolean b)
	{
		canAccessInventory = b;
	}
	
	public void execute(Scanner inputReader)
	{
		lastChoice = -1;
		say();
		read(inputReader);
	}
	public void execute(GameVars gv)
	{
		lastChoice = -1;
		say();
		read(gv);
	}
	public void say()
	{
		for (int i = 0; i < options.size(); ++i)
		{
			
			System.out.println((i+1)+". "+options.get(i));
		}
	}
	public void read(GameVars gv)
	{
		read(gv, new Scanner(System.in));
	}
	public void read(Scanner inputReader)
	{
		read(new GameVars(), inputReader);
	}
	public void read(GameVars gv, Scanner inputReader)
	{
		while (true)
		{
			String str = inputReader.nextLine();
			// if they've pressed 'i' and we're given access to the inventory,
			// do the inventory
			// the only currently case where access is disallowed is when you're
			// doing a choice menu in the inventory
			if (canAccessInventory && str.equals("i"))
			{
				gv.inventory.access(gv);
			}
			try
			{
				lastChoice = Integer.parseInt(str);
			}catch(NumberFormatException e)
			{
				lastChoice = -1;
			}
			if (lastChoice > 0 && lastChoice <= options.size())
			{
				break;
			}
			answerFail();
			say();
		}
	}
	public void answerFail()
	{
		System.out.println(answerFailText);
	}
	
	public int getChoice()
	{
		return lastChoice;
	}
}