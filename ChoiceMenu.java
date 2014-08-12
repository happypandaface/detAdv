import java.util.*;

public class ChoiceMenu
{
	private List<String> options;
	private String lastChoice = "";
	
	public ChoiceMenu()
	{
		options = new ArrayList<String>();
	}
	
	public void addOption(String s)
	{
		options.add(s);
	}
	
	public void execute(Scanner inputReader)
	{
		say();
		read(inputReader);
	}
	public void say()
	{
		for (int i = 0; i < options.size(); ++i)
		{
			
			System.out.println(i+". "+options.get(i));
		}
	}
	public void read(Scanner inputReader)
	{
		lastChoice = inputReader.nextLine();
	}
	
	public int getChoice()
	{
		return Integer.parseInt(lastChoice);
	}
}