import java.util.*;

public class ChoiceMenu
{
	private Array<String> options;
	private String lastChoice = "";
	
	public ChoiceMenu()
	{
		options = new Array<String>();
	}
	
	public void addOption(String s)
	{
		options.add(s);
	}
	
	public void execute(Scanner s)
	{
		say();
		read(s);
	}
	public void say()
	{
		for (int i = 0; i < options.size; ++i)
		{
			System.out.println(i+". "+s);
		}
	}
	public void read(Scanner s)
	{
		lastChoice = inputReader.nextLine();
	}
	
	public int getChoice()
	{
		return Integer.parseInt(lastChoice);
	}
}