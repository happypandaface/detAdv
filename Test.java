import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Test
{
	public static void main(String[] args)
	{
		Test t;
		t=new Test();
		t.run();
	}
	public Test(){
	}
	public void run()
	{
		Scanner inputReader = new Scanner(System.in);
		System.out.println("The newspaper is dated March 15th 1987. The headline is something about another Hollywood overdose. Filth. There is some dirt under my fingernails.");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("\"What can I get for you darling?\"");
		System.out.println("1: Croissant \n2: Coffee");
		String dinerchoice = inputReader.nextLine();
		if (dinerchoice.equals("1")){
			System.out.println("\"Gimme a Croissant.\"");
			System.out.println("Continue... (press enter)");
			inputReader.nextLine();
			System.out.println("\"Coming right up!\"");
		}
		else
		{
			System.out.println("Just a coffee, thanks.\n Croissants always upset my stomach. \nThe waitress gives me a look I know all too well and turns away without another word. ");
		}
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("The bells on the door jingle as the only other patron comes in.");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("Peering over my newspaper I notice the man is standing at the front counter.");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("There's some sort of commotion with the new patron.");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("\"Put the fucking money in the fucking bag!\"");
		System.out.println("Continue... (press enter)");
		inputReader.nextLine();
		System.out.println("This whole city has gone to shit.");
		System.out.println("1: confront \n2: Leave");
		String fightchoice = inputReader.nextLine();
		if (fightchoice.equals("1")){
			System.out.println("This guy chose a bad day for crime.");
			System.out.println("Continue... (press enter)");
			inputReader.nextLine();
			System.out.println("Hurry it up already!");
		}
		else
		{
			System.out.println("This lowlife isn't worth my effort.");
			System.out.println("Continue... (press enter)");
			inputReader.nextLine();
			System.out.println("I sneak out the back door");
			System.out.println("Continue... (press enter)");
			inputReader.nextLine();
		}
		
		
		
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