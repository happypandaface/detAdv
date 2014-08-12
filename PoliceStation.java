import java.util.*;

public class PoliceStation extends Location
{
	private static int JUST_ENTERED = 0;
	private static int LEAVING = 1;
	private static int INQUIRE = 2;
	
	public long policeStationState = JUST_ENTERED; // set to starting state
	
	public PoliceStation()
	{
		
	}
	@Override
	public void run(DetAdv da, GameVars gameVars)
	{
		Scanner inputReader = saveAndGetScanner(gameVars);
		if (policeStationState == JUST_ENTERED)
		{
			System.out.println("The police force has been riddled with worthless cases lately.\nPeople will call the police over such stupid things nowadays.");
			DetUtil.doContinue(inputReader);
			System.out.println("\""+gameVars.getCharacterName()+"! You need to get to the scene of the crime immediately!\"");
			ChoiceMenu first = new ChoiceMenu();
			first.addOption("inquire");
			first.addOption("leave");
			first.execute(inputReader);
			if (first.getChoice() == 2)
			{
				policeStationState = LEAVING;
				run(da, gameVars);
			}else
			if (first.getChoice() == 1)
			{
				policeStationState = INQUIRE;
				run(da, gameVars);
			}
		}else
		if (policeStationState == LEAVING)
		{
			System.out.println("I left the police station. I figure this'll be just a cuff and book case...");
			DetUtil.doContinue(inputReader);
			System.out.println("Oh how wrong I was...");
			DetUtil.doContinue(inputReader);
			gameVars.setLocation(GameVars.CAR);
			gameVars.car.state = Car.DRIVING_TO_MANSION;
			da.run(gameVars);
		}else
		if (policeStationState == INQUIRE)
		{
			System.out.println(gameVars.getCharacterName()+": \"What's this mess about, Walt?\"");
			DetUtil.doContinue(inputReader);
			String explainStrange = "Walt: \"This is a strange one, "+gameVars.getCharacterName()+", but important none the less!\"";
			System.out.println(explainStrange);
			ChoiceMenu strange = new ChoiceMenu();
			strange.setFailText(explainStrange+"\nYou have to choose an option:");
			strange.addOption("comment on this");
			strange.addOption("continue");
			strange.execute(inputReader);
			if (strange.getChoice() == 1)
			{
				System.out.println(gameVars.getCharacterName()+": \"Walt... after 25 years on the jobs, nothing surprises me anymore...\"");
				DetUtil.doContinue(inputReader);
				System.out.println("I said that thinking this would be another cuff and book case");
				DetUtil.doContinue(inputReader);
				System.out.println("Oh how wrong I was...");
				DetUtil.doContinue(inputReader);
			}
			String fakeKidText = "Walt: \"The victim is a 8 year old male...\"";
			System.out.println(fakeKidText);
			ChoiceMenu kidChoice = new ChoiceMenu();
			kidChoice.setFailText(fakeKidText+"\nYou have to choose an option:");
			kidChoice.addOption("comment on this");
			kidChoice.addOption("continue");
			kidChoice.execute(inputReader);
			if (kidChoice.getChoice() == 1)
			{
				System.out.println(gameVars.getCharacterName()+": \"Jesus... Just a kid...\"");
				DetUtil.doContinue(inputReader);
				System.out.println("Walt: \"Damnit, "+gameVars.getCharacterName()+", it's a horse! Not a child! Will you stop interrupting!");
				DetUtil.doContinue(inputReader);
					DetUtil.popupImage("angryFace.png");
				DetUtil.doContinue(inputReader);
				System.out.println("Walt: \"Now I need you to get out there ASAP, you hear me!?\"");
				DetUtil.doContinue(inputReader);
			}else
			if (kidChoice.getChoice() == 2)
			{
				System.out.println("Walt: \"...horse. It died late last night around 11pm. An important figure wants this wrapped up quickly so get on it!");
				DetUtil.doContinue(inputReader);
			}
			System.out.println("I head out of the Police Station, it's raining now. I hope that doesn't affect the crime scene...");
			DetUtil.doContinue(inputReader);
		}
	}
}