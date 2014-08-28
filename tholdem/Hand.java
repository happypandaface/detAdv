
import java.util.*;

public class Hand
{
	int num_cards;
	List<Card> cards;
	static long maxCard = 13;
	static long handTypeRankDiff = maxCard*maxCard*maxCard*maxCard*maxCard;
	static long highCard = 0;
	static long onePair = handTypeRankDiff;
	static long twoPair = handTypeRankDiff*2;
	static long threeOfAKind = handTypeRankDiff*3;
	static long straight = handTypeRankDiff*4;
	static long flush = handTypeRankDiff*5;
	static long fullHouse = handTypeRankDiff*6;
	static long fourOfAKind = handTypeRankDiff*7;
	static long straightFlush = handTypeRankDiff*8;
	
	public Hand()
	{
		this(2); // default for texas holdem
	}
	public Hand(int size)
	{
		num_cards = size;
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card c)
	{// indiscriminate
		cards.add(c);
	}
	
	public boolean checkInHand(Card c)
	{
		for (int i = 0; i < cards.size(); ++i)
			if (cards.get(i).checkSame(c))
				return true;
		return false;
	}
	
	public Hand setRandom(List<Hand> hands)
	{
		for (int i = 0; i < num_cards; ++i)
		{
			while (true)
			{
				boolean works = true;
				Card c = Card.getRandom(hands);
				if (checkInHand(c))
					works = false;
				if (works)
				{
					cards.add(c);
					break;
				}
			}
		}
		return this;
	}
	
	public String getDescription()
	{
		String str = "";
		for (int i = 0; i < cards.size(); ++i)
		{
			if (i == cards.size()-1)
				str += "and ";
			str += "the " + cards.get(i).getName();
			if (i != cards.size()-1)
				str += ", ";
		}
		return str;
	}
	public long getVal()
	{
		long highestVal = 0;
		// every permuatation of hands
		for (int i = 0; i < cards.size(); ++i)
			for (int c = 0; c < cards.size(); ++c)
				for (int p = 0; p < cards.size(); ++p)
					for (int d = 0; d < cards.size(); ++d)
						for (int v = 0; v < cards.size(); ++v)
						{
							if (i != c &&
								i != p &&
								i != d &&
								i != v &&
								c != p &&
								c != d &&
								c != v &&
								p != d &&
								p != v &&
								d != v)
							{
								Card iCard = cards.get(i);
								Card cCard = cards.get(c);
								Card pCard = cards.get(p);
								Card dCard = cards.get(d);
								Card vCard = cards.get(v);
								int iNum = iCard.num;
								int cNum = cCard.num;
								int pNum = pCard.num;
								int dNum = dCard.num;
								int vNum = vCard.num;
								long baseVal = 0;
								baseVal += vNum;
								baseVal += dNum*maxCard;
								baseVal += pNum*maxCard*maxCard;
								baseVal += cNum*maxCard*maxCard*maxCard;
								baseVal += iNum*maxCard*maxCard*maxCard*maxCard;
								if (baseVal > highestVal)
									highestVal = baseVal;
								if (iNum == cNum)
								{
									if (pNum == dNum)
									{
										if (dNum == vNum)
										{
											long tryVal = fullHouse+baseVal;
											if (tryVal > highestVal)
												highestVal = tryVal;
										}else
										{
											long tryVal = twoPair+baseVal;
											if (tryVal > highestVal)
												highestVal = tryVal;
										}
									}else
									{
										long tryVal = onePair+baseVal;
										if (tryVal > highestVal)
											highestVal = tryVal;
									}
								}
								if (iNum == cNum &&
									iNum == pNum)
								{
									if (dNum == vNum)
									{
										long tryVal = fullHouse+baseVal;
										if (tryVal > highestVal)
											highestVal = tryVal;
									}else
									{
										long tryVal = threeOfAKind+baseVal;
										if (tryVal > highestVal)
											highestVal = tryVal;
									}
								}
								if (iNum == cNum &&
									iNum == pNum &&
									iNum == dNum)
								{
									long tryVal = fourOfAKind+baseVal;
									if (tryVal > highestVal)
										highestVal = tryVal;
								}
								if (iCard.suit == cCard.suit &&
									cCard.suit == pCard.suit &&
									pCard.suit == dCard.suit &&
									dCard.suit == vCard.suit)
								{
									long tryVal = flush+baseVal;
									if (tryVal > highestVal)
										highestVal = tryVal;
								}
								if (iNum == cNum+1 &&
									cNum == pNum+1 &&
									pNum == dNum+1 &&
									dNum == vNum+1)
								{
									long tryVal = straight+baseVal;
									if (tryVal > highestVal)
										highestVal = tryVal;
								}
								if (iCard.suit == cCard.suit &&
									cCard.suit == pCard.suit &&
									pCard.suit == dCard.suit &&
									dCard.suit == vCard.suit &&
									iNum == cNum+1 &&
									cNum == pNum+1 &&
									pNum == dNum+1 &&
									dNum == vNum+1)
								{
									long tryVal = straightFlush+baseVal;
									if (tryVal > highestVal)
										highestVal = tryVal;
								}
							}
						}
		return highestVal;
	}
}