

public class RaceTracks extends Location
{
	
	public static void main(String[] args)
	{
		GameVars gv = Saver.tryLoad();
		DetAdv da = new DetAdv();
		RaceTracks rt = new RaceTracks();
		rt.run(da, gv);
		System.exit(0);
	}
	
	@Override
	public void run(DetAdv da, GameVars gv)
	{
		Scanner inputReader = saveAndGetScanner(gameVars);
	}
}