import java.util.*;

public class ChoiceMenu
{
	private List<String> options;
	private int lastChoice;
	private String answerFailText = "You have to choose an option";
	
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
	
	public void execute(Scanner inputReader)
	{
		lastChoice = -1;
		say();
		read(inputReader);
	}
	public void say()
	{
		for (int i = 0; i < options.size(); ++i)
		{
			
			System.out.println((i+1)+". "+options.get(i));
		}
	}
	public void read(Scanner inputReader)
	{
		while (true)
		{
			try
			{
				lastChoice = Integer.parseInt(inputReader.nextLine());
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