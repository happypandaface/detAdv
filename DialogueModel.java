import java.util.*;

public class DialogueModel extends Dialogue
{
	List<Dialogue> dialogues;
	
	public DialogueModel()
	{
		super();
		dialogues = new ArrayList<Dialogue>();
	}
	
	public Dialogue addDialogue(String dialogue)
	{
		Dialogue rtn = new Dialogue(dialogue);
		dialogues.add(rtn);
		return rtn;
	}
	
	public Dialogue addDialogue(Dialogue rtn)
	{
		dialogues.add(rtn);
		return rtn;
	}
	
	public Dialogue get(int i)
	{
		return dialogues.get(i);
	}
	
	public int tot()
	{
		return dialogues.size();
	}
}