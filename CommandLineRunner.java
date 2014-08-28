
import java.util.*;

public class CommandLineRunner
{
	DialogueModel dm;
	int currDialogue;
	public static void main(String[] args)
	{
		CommandLineRunner clr = new CommandLineRunner();
		DialogueModel dm = new DialogueModel();
		dm.addDialogue("test 1");
		dm.addDialogue("test 2");
		ChoiceMenuModel cmm = new ChoiceMenuModel();
		cmm.setText("choose.");
		cmm.addOption("choice 1", dm, 3);
		cmm.addOption("choice 3", dm, 4);
		dm.addDialogue(cmm);
		dm.addDialogue("choice 1 result");
		dm.addDialogue("choice 2 result");
		clr.setDialogueModel(dm);
		clr.run();
	}
	public CommandLineRunner()
	{
		currDialogue = 0;
	}
	public void setDialogueModel(DialogueModel dm)
	{
		this.dm = dm;
	}
	public void run()
	{
		Scanner inputReader = new Scanner(System.in);
		while (currDialogue < dm.tot())
		{
			Dialogue d = dm.get(currDialogue);
			String text = d.text;
			System.out.println(text);
			if (d instanceof ChoiceMenuModel)
			{
				ChoiceMenuModel cmm = (ChoiceMenuModel)d;
				int lastChoice = -1;
				while (true)
				{
					for (int i = 0; i < cmm.options.size(); ++i)
					{
						System.out.println((i+1)+". "+cmm.options.get(i));
					}
					String str = inputReader.nextLine();
					try
					{
						lastChoice = Integer.parseInt(str);
					}catch(NumberFormatException e)
					{
						lastChoice = -1;
					}
					if (lastChoice > 0 && lastChoice <= cmm.options.size())
					{
						break;
					}
					System.out.println(cmm.answerFailText);
					System.out.println(text);
				}
				dm = cmm.getDialogueModelForChoice(lastChoice);
				currDialogue = cmm.getDialogueNumberForChoice(lastChoice);
			}else
			{
				DetUtil.doContinue(inputReader);
				currDialogue++;
			}
		}
	}
}