
import java.util.*;

public class Card
{
	int suit;
	int num;
	static int HEARTS = 1;
	static int DIAMONDS = 2;
	static int SPADES = 3;
	static int CLUBS = 4;
	
	public Card(int n, int s)
	{
		suit = s;
		num = n;
	}
	public Card()
	{
		suit = 0;// no card
		num = 0;// no card
	}
	
	public String getName()
	{
		String str = "";
		if (num < 11)
			str += num + " of ";
		else if (num == 11)
			str += "jack of ";
		else if (num == 12)
			str += "queen of ";
		else if (num == 13)
			str += "king of ";
		else if (num == 14)
			str += "ace of ";
		if (suit == HEARTS)
			str += "hearts";
		else if (suit == DIAMONDS)
			str += "diamonds";
		else if (suit == SPADES)
			str += "spades";
		else if (suit == CLUBS)
			str += "clubs";
		return str;
	}
	
	public boolean checkSame(Card c)
	{
		if (c.num == num &&
			c.suit == suit)
			return true;
		return false;
	}
	
	public static Card getRandom()
	{
		Card c = new Card();
		c.num = (int)Math.floor(Math.random()*13)+2;
		c.suit = (int)Math.floor(Math.random()*4)+1;
		return c;
	}
	public static Card getRandom(List<Hand> hands)
	{
		while (true)
		{
			boolean works = true;
			Card c = Card.getRandom();
			for (int i = 0; i < hands.size(); ++i)
				if (hands.get(i).checkInHand(c))
					works = false;
			if (works)
				return c;
		}
	}
}