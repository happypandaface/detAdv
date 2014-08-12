import org.json.simple.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public abstract class Savable
{
	public static String file_encoding = "UTF-8";
	public static String save_folder = "saves";
	
	public void tryLoad()
	{
		Scanner s = new Scanner(System.in);
		File saveFolder = new File(save_folder);
		if (!saveFolder.exists() || !saveFolder.isDirectory())
		{
			try{
				saveFolder.mkdir();
				System.out.println("Created save folder");  
			} catch(Exception e){
				System.out.println("Couldn't create save folder");
				DetUtil.doContinue(s); 
				e.printStackTrace();
			}
		}
		File[] saveFiles = saveFolder.listFiles();
		ChoiceMenu startOpts = new ChoiceMenu();
		startOpts.addOption("Start a new game");
		if (saveFiles.length > 0)
		{
			for (int i = 0; i < saveFiles.length; i++)
			{
				startOpts.addOption("load "+saveFiles[i].getName()+"'s game");
			}
		}
		System.out.println("Choose an option:");
		startOpts.execute(s);
		if (startOpts.getChoice() > 1)
		{
			try
			{
				String saveString = new String(Files.readAllBytes(Paths.get(saveFiles[startOpts.getChoice()-2].getAbsolutePath())), file_encoding);
				fromString(saveString);
			}catch (Exception e)
			{
				System.out.println("Couldn't load save file");
				DetUtil.doContinue(s);
				e.printStackTrace();
			}
		}
	}
	public abstract String toString();
	public abstract void fromString(String s);
	public abstract String getSaveGameName();
	public void save()
	{
		String jsonString = toString();
		try
		{
			PrintWriter writer = new PrintWriter(save_folder+"/"+getSaveGameName(), file_encoding);
			writer.println(jsonString);
			writer.close();
		}catch(Exception e)
		{
			System.out.println("Couldn't write file");
			DetUtil.doContinue(new Scanner(System.in));
			e.printStackTrace();
		}
	}
}