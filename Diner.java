//import java.io.*;
import java.util.*;
//import java.awt.*;
//import javax.swing.*;

public class Diner extends Location
{
	//public static void main(String[] args)
	//{
	//	Diner t;
	//	t=new Diner();
	//	t.run();
	//}
	public Diner()
	{
	
	}
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = new Scanner(System.in);
		System.out.println("Chapter One: Good Neighbors");
		inputReader.nextLine();
		System.out.println("The newspaper is dated March 15th 1987. The headline is something about another Hollywood overdose. Filth. There is some dirt under my fingernails.");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("\"What can I get for you darling?\"");
		while (true){
			System.out.println("1: Croissant \n2: Coffee");
			String dinerchoice = inputReader.nextLine();
			if (dinerchoice.equals("1")){
				System.out.println("\"Gimme a Croissant.\"");
				DetUtil.doContinue(inputReader);
				System.out.println("\"Coming right up!\"");
				break;
			}
			else if(dinerchoice.equals("2"))
			{
				System.out.println("Just a coffee, thanks.\nCroissants always upset my stomach. \nThe waitress gives me a look I know all too well, fills up my cup, and turns away without another word. ");
				gv.coffee = GameVars.COFFEE;
				da.run(gv);
				break;
			}
			System.out.println("I don't have all day.");
		}
		DetUtil.doContinue(inputReader);
		System.out.println("The bells on the door jingle as the only other patron comes in.");
		DetUtil.doContinue(inputReader);
		System.out.println("Peering over my newspaper I notice the man is standing at the front counter.");
		DetUtil.doContinue(inputReader);
		System.out.println("There's some sort of commotion with the new patron.");
		DetUtil.doContinue(inputReader);
		System.out.println("\"Put the fucking money in the fucking bag!\"");
		DetUtil.doContinue(inputReader);
		System.out.println("This whole city has gone to shit.");
		while (true){
			System.out.println("1: Confront \n2: Leave");
			String fightchoice = inputReader.nextLine();
			if (fightchoice.equals("1")){
				System.out.println("This guy chose a bad day for crime.");
				DetUtil.doContinue(inputReader);
				System.out.println("\"Hurry it up already!\"");
				DetUtil.doContinue(inputReader);
				System.out.println("I approach the counter.");
				DetUtil.doContinue(inputReader);
				System.out.println("\"Calm down buddy, nobody has to get hurt here.\"");
				DetUtil.doContinue(inputReader);
				
				//if coffee should throw in his face but need to have gotten the coffee
				if (gv.coffee == GameVars.COFFEE)
				{
					System.out.println("I throw my coffee in his face as he whirls around to face me.");
					DetUtil.doContinue(inputReader);
				}
				else
				{
					System.out.println("The surprise of my voice startles the would be thief. A moments hesitation is all I need. In an instant the assailant is on the ground with a bloody nose.");
					DetUtil.doContinue(inputReader);
				}
				System.out.println("\"Keep the change.\"");
				DetUtil.doContinue(inputReader);
				System.out.println("I exit out the front door.");
				
				break;
			}
			else if(fightchoice.equals("2"))
			{
				System.out.println("This lowlife isn't worth my effort.");
				DetUtil.doContinue(inputReader);
				System.out.println("I sneak out the back door.");
				DetUtil.doContinue(inputReader);
				break;
			}
			System.out.println("I don't have all day.");
		}
		System.out.println("The sun has started coming out but it looks like rain. I'd say it's about 6.");
		DetUtil.doContinue(inputReader);
		System.out.println("I make my way to the car.");
		DetUtil.doContinue(inputReader);
		gv.setLocation(GameVars.CAR);
		gv.car.state = Car.OUTSIDE_DINER;
		da.run(gv);
		

		
		
		
		/*Scanner inputReader = new Scanner(System.in);
		System.out.println("Enter your name:");
		String name = inputReader.nextLine();
		System.out.println("Welcome, "+name+", to the world of pokemon (press enter)!");
		
		{
			JFrame frame = new JFrame();
			frame.setSize ( 100, 100 );
			frame.setTitle( "image of game" );
			frame.setVisible( true );
			Rectangle r = new Rectangle(100, 100, 200, 200);
			frame.setBounds(r);
			JLabel label = new JLabel(new ImageIcon("game.png"));
			frame.getContentPane().add(label);
		}
		{
			JFrame frame = new JFrame();
			frame.setSize ( 100, 100 );
			frame.setTitle( "image of game" );
			frame.setVisible( true );
			Rectangle r = new Rectangle(200, 100, 200, 200);
			frame.setBounds(r);
			JLabel label = new JLabel(new ImageIcon("game.png"));
			frame.getContentPane().add(label);
		}
		*/
		
		
		System.out.println("hit enter!");
		inputReader.nextLine();
		System.exit(0);
	}
}