import java.util.*;
import java.nio.file.*;
import java.io.*;

public class RaceTracks extends Location
{
	long state = MAIN;
	public static long BOOKIE = 1;
	public static long STANDS = 2;
	public static long BOX = 3;
	public static long LEAVING = 4;
	public static long MAIN = 5;
	
	int JUST_ARRIVED = 1;
	int first_time = 1;
	
	public static void main(String[] args)
	{
		GameVars gv = Saver.tryLoad();
		DetAdv da = new DetAdv();
		RaceTracks rt = new RaceTracks();
		rt.run(da, gv);
		System.exit(0);
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		if (state == MAIN)
		{
		ChoiceMenu main = new ChoiceMenu();
		main.addOption("Ask the bookie a few questions");
		main.addOption("Ask around the stands.");
		main.addOption("Leave.");
		main.execute(gv);
			
			if (main.getChoice() == 1)
			{	
				if (JUST_ARRIVED == 1)
					{
						System.out.println("\"Place your bets.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Actually I'm here for something else.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("I flash my badge at the homely looking bookie.");
						DetUtil.doContinue(inputReader);
						System.out.println("She frowns.");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Well, what can I do for you, officer?\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"I'm investigating the murder of NNNNNAME OF HORSE HERE\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Terrible shame what happened to that horse.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Have you seen anything unusual in the past week?\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Let's see...\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Well with the first big race of the year coming up next week, I would expect to see more of my best customer but he hasn't been around in five or six days.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"What can you tell me about him?\""); //bulk up this dialogue why would he be of interest?
						DetUtil.doContinue(inputReader);
						System.out.println("\"Real odd fellow this guy, spent all his free time here at the tracks. Obsessive sort. Drove all the way accross town to come to Arlington heights when he lived right by the Hawthorne race tracks.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Claims the luck there was against him and the dirt there is too red. Like I said a real loon.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"I think I get the picture.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Know how to find him?\"");
						DetUtil.doContinue(inputReader);
						System.out.println("\"Sure, he's a construction worker. Recently he was complaining about a job they had in the lower west side. A new high rise.\"");
						DetUtil.doContinue(inputReader);
						System.out.println("Obsessive compulsive type, might want to look into him.");
						DetUtil.doContinue(inputReader);
						JUST_ARRIVED = 0;
					}
				System.out.println("Maybe the bookie has a bit more information");
				DetUtil.doContinue(inputReader);
				state = BOOKIE;
				run(da, gv);
		
			}else
			if (main.getChoice() == 2)
			{
				System.out.println("\"Popcorn! Get your popcorn!\""); //flesh this out (replace it)
				DetUtil.doContinue(inputReader);
				run(da, gv);
			}else
			if (main.getChoice() == 3)
			{
				state = LEAVING;
				run(da, gv);
			}
		}else
		if (state == LEAVING)
		{
			if (first_time == 1)
			{
				System.out.println("\"Pssst. Over here. Take this.\"");
				DetUtil.doContinue(inputReader);
				first_time = 0;
			}
			gv.setLocation(GameVars.CAR);
			state = MAIN; //may want to make a main state so just arrived only happens once
			da.run(gv);
				//undercover agent guy gives you photos of caretaker meeting with mafia
		}else
		if (state == BOOKIE)
		{
			ChoiceMenu chat = new ChoiceMenu();
			chat.addOption("Press for more on Keith Douglas.");
			chat.addOption("Ask about other gamblers.");
			chat.addOption("Head back outside.");
			chat.execute(gv);
				if (chat.getChoice() == 1)
				{
					System.out.println("\"I got nothing\""); //add here
					DetUtil.doContinue(inputReader);
					run(da, gv);
				}else
				if (chat.getChoice() == 2)
				{
					System.out.println("\"Any idea why someone would want that horse dead?\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\"I guess some of the other horse breeders?\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\"Alfonso Cheadle is a big name around here. He has permanent box seats. Don't think he would be too happy if you barged in there asking questions though.\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\"Anybody else you can tell me about?\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\"Well I guess it is odd that the other regular at my window hasn't been around yet today.\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\" I would think he came here just to hit on me if it weren't for the amount of money he blows. He never got a clue. Always inviting me to some club\""); //this is for the actual suspect
					DetUtil.doContinue(inputReader);
					System.out.println("\"You remember the name?\"");
					DetUtil.doContinue(inputReader);
					System.out.println("\"Hold on, I'm sure I have one of these stupid business cards he was always giving me.\"");
					DetUtil.doContinue(inputReader);
					//this is where you get a business card for the club
					run(da, gv);
				}else
				if (chat.getChoice() == 3)
				{
					state = MAIN;
					run(da, gv);
				}
		}
	}
}