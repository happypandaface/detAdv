import java.util.*;

public class DetAdv
{
	public static void main(String[] args)
	{
		//GameVars gv = new GameVars();
		GameVars gv = Saver.tryLoad();
		DetAdv da = new DetAdv();
		da.run(gv);
		System.exit(0);
	}
	public void run(GameVars gv)
	{
		Location currLocation = gv.getLocation();
		currLocation.run(this, gv);
	}
}