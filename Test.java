import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Test
{
	public static void main(String[] args)
	{
		System.out.println("Enter your name:");
		Scanner inputReader = new Scanner(System.in);
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
		
		
		
		System.out.println("hit enter!");
		inputReader.nextLine();
		System.exit(0);
	}
}