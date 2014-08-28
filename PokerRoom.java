import java.util.*;

public class PokerRoom extends Location implements CustomPoker
{
	int state = AT_TABLE;
	int gameState = NOT_STARTED;
	int totalGamesPlayed = 0;
	int royalSuit = 1;
	
	int beatSimon = 0;
	
	static int AT_TABLE = 0;
	static int IN_GAME = 1;
	
	static int NOT_STARTED = 0;
	static int FLOPPED = 1;
	static int TURNED = 2;
	static int RIVERED = 3;
	
	static int num_hands = 5;
	List<Hand> hands;
	
	public static void main(String[] args)
	{
		GameVars gv = Saver.tryLoad();
		DetAdv da = new DetAdv();
		Item money = new Item(Item.MONEY);
		money.setAmount(300);
		gv.inventory.addItem(money);
		PokerRoom pr = new PokerRoom();
		pr.run(da, gv);
		System.exit(0);
	}
	
	public PokerRoom()
	{
		hands = new ArrayList<Hand>();
	}
	
	@Override
	public String getPlayerName(GameVars gv, int num)
	{
		if (num == 0)
			return "I";
		if (num == 1)
			return "Bert";
		if (num == 2)
			return "Simon Blunt";
		if (num == 3)
			return "Patrick Stewart";
		if (num == 4)
			return "Martha Stewart";
		return "A Ghost";
	}
	@Override
	public int playerMoneyLeft(GameVars gv, int playerNum)
	{
		if (playerNum == 0)
		{
			return gv.inventory.getAmount(Item.MONEY);
		}
		return 0;
	}
	@Override
	public String getActionWord(GameVars gv, int playerNum, int action)
	{
		if (action == CustomPoker.RAISE)
		{
			if (playerNum == 0)
				return "raise";
			else
				return "raises";
		}else
		if (action == CustomPoker.CALL)
		{
			if (playerNum == 0)
				return "call";
			else
				return "calls";
		}else
		if (action == CustomPoker.FOLD)
		{
			if (playerNum == 0)
				return "fold";
			else
				return "folds";
		}
		return "does";
	}
	@Override
	public String boardState(GameVars gv)
	{
		String str = "I have "+hands.get(0).getDescription()+" in my hand";
		if (gameState >= FLOPPED)
			str += "\nThe flop is "+getFlop().getDescription();
		if (gameState >= TURNED)
			str += "\nThe turn is "+getTurn().getName();
		if (gameState >= RIVERED)
			str += "\nThe river is "+getRiver().getName();
		return str;
	}
	@Override
	public int whatAction(GameVars gv, Pot pot, int playerNum)
	{
		int choice = 0;
		int betMoney = 0;
		if (playerNum == 0)
		{
			while(true)
			{
				System.out.println("Bet's to me.");
				System.out.println(boardState(gv));
				System.out.println("The pot is at "+pot.totalMoney+" and I need "+pot.getMakeup(playerNum)+" more to stay in");
				System.out.println("What do I do?");
				ChoiceMenu handAction = new ChoiceMenu();
				handAction.addOption("raise");
				handAction.addOption("call");
				handAction.addOption("fold");
				handAction.execute(gv);
				choice = handAction.getChoice();
				if (choice == 1)
				{
					System.out.print("How much do I wanna raise? (enter 0 to go back)");
					boolean raising = true;
					while(true)
					{
						betMoney = DetUtil.getUInt();
						int moneyLeft = playerMoneyLeft(gv, playerNum);
						if (betMoney == 0)
							raising = false;
						if (betMoney <= moneyLeft)
							break;
						System.out.print("You don't have that much! (enter 0 to go back)");
					}
					if (raising)
					{
						// take away the money
						gv.inventory.removeItems(Item.MONEY, betMoney);
						break;
					}
					System.out.println("");
				}else
				if (choice == 2)
				{
					int callMoney = pot.getMakeup(0);
					gv.inventory.removeItems(Item.MONEY, callMoney);
					break;
				}else
				{
					break;
				}
			}
		}else
		{
			if (isRiggedGame())
			{
				if (playerNum == 2)
				{
					if (gameState == RIVERED)
					{
						choice = CustomPoker.RAISE;
						betMoney = gv.inventory.getAmount(Item.MONEY);
					}else
					{
						choice = CustomPoker.CALL;
					}
				}else
				{
					choice = CustomPoker.FOLD;
				}
			}else
			{
				int moneyToGo = pot.checkMoneyToGo(playerNum);
				float chance = (float)Math.random();
				if (chance < .6f)
				{
					choice = CustomPoker.CALL;
				}else if (chance < .8f)
				{
					// if the player has close to no money left :'( don't raise
					if (pot.totalMoney >= playerMoneyLeft(gv, 0)-10f)
					{
						choice = CustomPoker.CALL;
					}else
					{
						choice = CustomPoker.RAISE;
						do
						{
							betMoney = (int)Math.floor(Math.random()*(pot.totalMoney*.3f+10))+5;
						}while(pot.checkMoneyToGo(0)+betMoney > playerMoneyLeft(gv, 0));
					}
				}else
				{
					if (moneyToGo < 5 ||
						moneyToGo/pot.totalMoney < .08f)
						choice = CustomPoker.CALL;
					else
						choice = CustomPoker.FOLD;
				}
			}
		}
		if (choice == 1)
		{
			// check player's moneys to make more pots if necessary
			pot.raise(playerNum, betMoney);
			System.out.println(getPlayerName(gv, playerNum)+" "+getActionWord(gv, playerNum, CustomPoker.RAISE)+" "+betMoney + (betMoney==1?" dollar":" dollars"));
		}else 
		if (choice == 2)
		{
			pot.raise(playerNum, 0);
			System.out.println(getPlayerName(gv, playerNum)+" "+getActionWord(gv, playerNum, CustomPoker.CALL));
		}else
		if (choice == 3)
		{
			pot.removePlayer(playerNum);
			System.out.println(getPlayerName(gv, playerNum)+" "+getActionWord(gv, playerNum, CustomPoker.FOLD));
		}
		DetUtil.doContinue();
		return choice;
	}
	
	public boolean isRiggedGame()
	{
		if (totalGamesPlayed == 2)
			return true;
		return false;
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gv);
		if (state == AT_TABLE)
		{
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
			Pot pot = new Pot();
			System.out.println("The cards are dealt...");
			++totalGamesPlayed;
			DetUtil.doContinue();
			for (int i = 0; i < num_hands; ++i)
			{
				pot.addPlayer(i);
				Hand h = new Hand().setRandom(hands);
				// do the rigged game:
				if (isRiggedGame())
				{
					if (i == 0)
					{
						h = new Hand();
						h.addCard(new Card(11, royalSuit));
						h.addCard(new Card(13, royalSuit));
					}else
					{
						h = new Hand();
						h.addCard(new Card(2, (royalSuit+2)%4+1));
						h.addCard(new Card(7, (royalSuit+3)%4+1));
					}
				}
				hands.add(h);
				if (i == 0)
				{
					System.out.println(getPlayerName(gv, i)+" get "+h.getDescription());
					DetUtil.doContinue();
				}
			}
			do// this do loop is just so you can break out whenever
			{
				gameState = NOT_STARTED;
				if (isRiggedGame())
				{
					Hand flop = new Hand();
					flop.addCard(new Card(10, royalSuit));
					flop.addCard(new Card(6, (royalSuit+3)%4+1));
					flop.addCard(new Card(11, (royalSuit+2)%4+1));
					hands.add(flop); // rigged flop
					Hand turn = new Hand();
					turn.addCard(new Card(14, royalSuit));
					hands.add(turn); // rigged turn
					Hand river = new Hand();
					river.addCard(new Card(12, royalSuit));
					hands.add(river); // rigged river
				}else
				{
					hands.add(new Hand(3).setRandom(hands)); // flop
					hands.add(new Hand(1).setRandom(hands)); // turn
					hands.add(new Hand(1).setRandom(hands)); // river
				}
				pot.setBetLeaderOffset(3);
				pot.doBetting(gv, this);
				DetUtil.doContinue();
				if (pot.lastPlayer())
					break;
				System.out.println("the flop is "+getFlop().getDescription());
				gameState = FLOPPED;
				DetUtil.doContinue();
				pot.doBetting(gv, this);
				DetUtil.doContinue();
				if (pot.lastPlayer())
					break;
				System.out.println("the turn is the "+getTurn().getName());
				gameState = TURNED;
				DetUtil.doContinue();
				pot.doBetting(gv, this);
				DetUtil.doContinue();
				if (pot.lastPlayer())
					break;
				System.out.println("and the river is the "+getRiver().getName());
				gameState = RIVERED;
				DetUtil.doContinue();
				pot.doBetting(gv, this);
				if (pot.lastPlayer())
					break;
				DetUtil.doContinue();
			}while(false);
			
			List<Integer> winners = pot.findWinners(gv, this);
			
			for (int i = 0; i < winners.size(); ++i)
			{
				if (winners.get(0) == 0)
				{
					int winnings = (int)Math.floor(pot.totalMoney/winners.size());
					gv.inventory.addItems(Item.MONEY, winnings);
					System.out.println("You won "+winnings+" dollars!");
					DetUtil.doContinue();
				}
			}
			if (isRiggedGame() && winners.size() == 1 && winners.get(0) == 0)
			{
				beatSimon = 1;
			}else
			{
				state = AT_TABLE;
				run(da, gv);
			}
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