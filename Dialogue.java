import java.util.*;

public class Dialogue
{
	String text;
	
	public Dialogue()
	{
		this(new String());
	}
	public Dialogue(String s)
	{
		text = s;
	}
	
	public void setText(String s)
	{
		text = s;
	}
}