import java.util.*;

public class ChoiceMenuModel extends Dialogue
{
	List<String> options;
	List<DialogueModel> choiceResultsDM;
	List<Integer> choiceResultsIndex;
	int lastChoice;
	String answerFailText = "I have to choose an option";
	boolean canAccessInventory = true;
	
	public ChoiceMenuModel()
	{
		super();
		choiceResultsDM = new ArrayList<DialogueModel>();
		choiceResultsIndex = new ArrayList<Integer>();
		options = new ArrayList<String>();
	}
	
	public void addOption(String s, DialogueModel dm, int idx)
	{
		choiceResultsDM.add(dm);
		choiceResultsIndex.add(idx);
		options.add(s);
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
	
	public int getChoice()
	{
		return lastChoice;
	}
	
	public DialogueModel getDialogueModelForChoice(int lastChoice)
	{
		return choiceResultsDM.get(lastChoice-1);
	}
	public int getDialogueNumberForChoice(int lastChoice)
	{
		return (int)choiceResultsIndex.get(lastChoice-1);
	}
}