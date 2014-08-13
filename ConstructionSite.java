import java.util.*;
import java.nio.file.*;
import java.io.*;

public class ConstructionSite extends Location
{
	static int JUST_ARRIVED = 1;
	
	int state = JUST_ARRIVED;
	
	public static void main(String[] args)
	{
		GameVars gv = Saver.tryLoad();
		gv.inventory.addItem(Mansion.getMurderNote());
		DetAdv da = new DetAdv();
		ConstructionSite cs = new ConstructionSite();
		cs.run(da, gv);
		System.exit(0);
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		if (state == JUST_ARRIVED)
		{
			System.out.println("The construction site is quite busy. There's a large crane moving bundles of wood around the site.");
			DetUtil.doContinue(gv);
			System.out.println("I search around and eventually find the guy I'm looking for");
			DetUtil.doContinue(gv);
			System.out.println("Bert Leonard: Waddya want? I ain't got time for yous guys.");
			DetUtil.doContinue(gv);
			DetUtil.popupImage("bertLeonard.png");
			DetUtil.doContinue(gv);
			System.out.println("Remeber! You can always type in 'i' to look at your inventory!");
			System.out.println("You can show people things in your inventory!");
			ChoiceMenu askConst = new ChoiceMenu();
			askConst.addOption("show note");
			askConst.execute(gv);
		}
	}
}