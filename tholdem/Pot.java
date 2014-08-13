
import java.util.*;

public class Pot
{
	int totalMoney;
	int highestBet;
	int betOffset;
	List<Integer> players;
	Map<Integer, Integer> playerBet;
	List<Integer> foldedPlayers;
	
	public Pot()
	{
		players = new ArrayList<Integer>();
		foldedPlayers = new ArrayList<Integer>();
		playerBet = new HashMap<Integer, Integer>();
	}
	public int checkMoneyToGo(int player)
	{
		int inPot = 0;
		if (playerBet.get(player) != null)
			inPot = playerBet.get(player);
		return highestBet-inPot;
	}
	public void addPlayer(int player)
	{
		players.add(player);
	}
	public void removePlayer(int player)
	{
		foldedPlayers.add(player);
	}
	public void removePlayerNow(int player)
	{
		for (int i = players.size()-1; i >= 0; --i)
			if (players.get(i) == player)
				players.remove(i);
	}
	public void removeFoldedPlayers()
	{
		for (int i = foldedPlayers.size()-1; i >= 0; --i)
		{
			removePlayerNow(foldedPlayers.get(i));
			foldedPlayers.remove(i);
		}
	}
	public int getMakeup(int player)
	{
		int alreadyBet = 0;
		if (playerBet.get(player) != null)
			alreadyBet = playerBet.get(player);
		int makeUp = 0;
		if (alreadyBet < highestBet)
			makeUp += highestBet-alreadyBet;
		return makeUp;
	}
	public int raise(int player, int gold)
	{
		int alreadyBet = 0;
		if (playerBet.get(player) != null)
			alreadyBet = playerBet.get(player);
		
		int makeUp = getMakeup(player);
		int thisBet = makeUp+gold;
		playerBet.put(player, alreadyBet+thisBet);
		highestBet += gold;
		totalMoney += makeUp+gold;
		return thisBet;
	}
	public boolean lastPlayer()
	{
		return players.size() == 1;
	}
	public void doBetting(GameVars gv, CustomPoker cp)
	{
		boolean ripe = false;
		int idx = betOffset;
		int lastPlayerToRaise = -1;
		while (!ripe)
		{
			idx = idx%players.size();
			if (idx != -1 && idx == lastPlayerToRaise)
			{
				ripe = true;
				break;
			}
			if (lastPlayerToRaise == -1)
				lastPlayerToRaise = idx;
			int playerNum = players.get(idx);
			boolean playerFolded = false;
			for (int i = 0; i < foldedPlayers.size(); ++i)
				if (playerNum == foldedPlayers.get(i))
					playerFolded = true;
			if (!playerFolded)
			{
				int choice = cp.whatAction(gv, this, playerNum);
				if (choice == CustomPoker.RAISE)
					lastPlayerToRaise = idx;
			}
			++idx;
		}
		removeFoldedPlayers();
		System.out.println("Pot's ripe! "+totalMoney+" gold");
	}
	public void setBetLeaderOffset(int offset)
	{
		betOffset = offset;
	}
	public List<Integer> findWinners(GameVars gv, CustomPoker cp)
	{
		long bestPlayerHigh = 0;
		Hand bestOverallHand = null;
		List<Integer> winners = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); ++i)
		{
			int idx = i;//(i+betOffset)%players.size();
			int playerNum = players.get(idx);
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
									h.addCard(cp.getCardForPlayer(playerNum, c1));
									h.addCard(cp.getCardForPlayer(playerNum, c2));
									h.addCard(cp.getCardForPlayer(playerNum, c3));
									h.addCard(cp.getCardForPlayer(playerNum, c4));
									h.addCard(cp.getCardForPlayer(playerNum, c5));
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
				winners.add(playerNum);
			}else if (currPlayerVal == bestPlayerHigh)
			{
				winners.add(playerNum);
			}
		}
		String winString = "";
		if (winners.size() == 1)
		{
			winString += cp.getPlayerName(gv, winners.get(0));
		}else
		{
			winString += "players ";
			for (int i = 0; i < winners.size(); ++i)
			{
				winString += cp.getPlayerName(gv, (winners.get(i)));
				if (i == winners.size()-2)
					winString += " and ";
				else if (i != winners.size()-1)
					winString += ", ";
			}
		}
		System.out.println(winString+" won! best hand: "+bestOverallHand.getDescription());
		DetUtil.doContinue();
		return winners;
	}
}