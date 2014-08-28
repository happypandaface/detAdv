
public interface CustomPoker
{
	static int RAISE = 1;
	static int CALL = 2;
	static int FOLD = 3;
	
	public String getPlayerName(GameVars gv, int num);
	public int playerMoneyLeft(GameVars gv, int playerNum);
	public Card getCardForPlayer(int i, int c);
	public String getActionWord(GameVars gv, int playerNum, int action);
	public String boardState(GameVars gv);
	public int whatAction(GameVars gv, Pot pot, int playerNum);
}