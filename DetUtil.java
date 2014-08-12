import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class DetUtil
{
	public static boolean firstContinue = true;
	
	public static void doContinue()
	{
		doContinue(new Scanner(System.in));
	}
	public static void doContinue(Scanner inputReader)
	{
		if (firstContinue)
		{
			System.out.println("Continue... (press enter)");
		}else
		{
			System.out.println("...");
		}
		firstContinue = false;
		inputReader.nextLine();
	}
	public static int getGTZeroInt()
	{
		Scanner inputReader = new Scanner(System.in);
		while (true)
		{
			try
			{
				int rtn = Integer.parseInt(inputReader.nextLine());
				if (rtn > 0)
					return rtn;
			}catch (Exception e)
			{
				
			}
			System.out.println("Input a positive number: ");
		}
	}
	public static void popupImage(String img)
	{
		JFrame frame = new JFrame();
		frame.setTitle(img);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Rectangle r = new Rectangle(100, 100, 200, 200);
		frame.setBounds(r);
		JLabel label = new JLabel(new ImageIcon(img));
		frame.getContentPane().add(label);
		frame.pack();
	}
}