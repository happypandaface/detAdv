import java.util.*;

class Card
{
	int suit;
	int num;
	static int HEARTS = 1;
	static int DIAMONDS = 2;
	static int SPADES = 3;
	static int CLUBS = 4;
	
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
class Hand
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
										long tryVal = twoPair+baseVal;
										if (tryVal > highestVal)
											highestVal = tryVal;
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
									long tryVal = threeOfAKind+baseVal;
									if (tryVal > highestVal)
										highestVal = tryVal;
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

public class PokerRoom extends Location
{
	int state = AT_TABLE;
	static int AT_TABLE = 0;
	static int IN_GAME = 1;
	static int num_hands = 5;
	List<Hand> hands;
	
	public PokerRoom()
	{
		hands = new ArrayList<Hand>();
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		if (state == AT_TABLE)
		{
			Scanner inputReader = saveAndGetScanner(gv);
			System.out.println("You're in the poker room.");
			ChoiceMenu pokerDraw = new ChoiceMenu();
			pokerDraw.addOption("deal 'em");
			pokerDraw.addOption("leave");
			pokerDraw.execute(gv);
			if (pokerDraw.getChoice() == 1)
			{
				state = IN_GAME;
				run(da, gv);
			}
		}else
		if (state == IN_GAME)
		{
			// clear old data
			hands = new ArrayList<Hand>();
			System.out.println("dealing...");
			DetUtil.doContinue();
			for (int i = 0; i < num_hands; ++i)
			{
				Hand h = new Hand().setRandom(hands);
				hands.add(h);
				System.out.println("player "+i+" has "+h.getDescription());
				DetUtil.doContinue();
			}
			hands.add(new Hand(3).setRandom(hands)); // flop
			hands.add(new Hand(1).setRandom(hands)); // turn
			hands.add(new Hand(1).setRandom(hands)); // river
			System.out.println("the flop is "+getFlop().getDescription());
			System.out.println("the turn is the "+getTurn().getName());
			System.out.println("and the river is the "+getRiver().getName());
			DetUtil.doContinue();
			
			long bestPlayerHigh = 0;
			Hand bestOverallHand = null;
			List<Integer> winners = new ArrayList<Integer>();
			for (int i = 0; i < num_hands; ++i)
			{
				long currPlayerVal = 0;
				Hand bestHand = null;
				// every combination of hands
				for (int c1 = 0; c1 < 7; ++c1)
					for (int c2 = 0; c2 < 7; ++c2)
						for (int c3 = 0; c3 < 7; ++c3)
							for (int c4 = 0; c4 < 7; ++c4)
								for (int c5 = 0; c5 < 7; ++c5)
								{
									if (c1 != c2 &&
										c1 != c3 &&
										c1 != c4 &&
										c1 != c5 &&
										c2 != c3 &&
										c2 != c4 &&
										c2 != c5 &&
										c3 != c4 &&
										c3 != c5 &&
										c4 != c5)
									{
										Hand h = new Hand();
										h.addCard(getCardForPlayer(i, c1));
										h.addCard(getCardForPlayer(i, c2));
										h.addCard(getCardForPlayer(i, c3));
										h.addCard(getCardForPlayer(i, c4));
										h.addCard(getCardForPlayer(i, c5));
										long currVal = h.getVal();
										if (currVal > currPlayerVal)
										{
											bestHand = h;
											currPlayerVal = currVal;
										}
									}
								}
				if (currPlayerVal > bestPlayerHigh)
				{
					winners.clear();
					bestPlayerHigh = currPlayerVal;
					bestOverallHand = bestHand;
					winners.add(i);
				}else if (currPlayerVal == bestPlayerHigh)
				{
					winners.add(i);
				}
			}
			String winString = "";
			if (winners.size() == 1)
			{
				winString += "player "+winners.get(0);
			}else
			{
				winString += "players ";
				for (int i = 0; i < winners.size(); ++i)
				{
					winString += (winners.get(i)+1);
					if (i == winners.size()-2)
						winString += " and ";
					else if (i != winners.size()-1)
						winString += ", ";
				}
			}
			System.out.println(winString+" wins, best hand: "+bestOverallHand.getDescription());
			DetUtil.doContinue();
			
			run(da, gv);
		}
	}
	public Hand getFlop()
	{
		return hands.get(num_hands);
	}
	public Card getTurn()
	{
		return hands.get(num_hands+1).cards.get(0);
	}
	public Card getRiver()
	{
		return hands.get(num_hands+2).cards.get(0);
	}
	public Card getCardForPlayer(int player, int num)
	{
		if (num > 4)// it's a player card
			return hands.get(player).cards.get(num-5);
		if (num == 4)
			return getRiver();
		if (num == 3)
			return getTurn();
		if (num < 3)// it's part of the flop
			return getFlop().cards.get(num);
		return null;
	}
}