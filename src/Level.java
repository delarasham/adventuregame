import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Level {
	
	// level number of object
	private int levelNumber = 0;
	// array lists for the levels props and puzzles, and story text (can be changed, just testing things)
	private ArrayList<Prop> props = new ArrayList<Prop>();
	private ArrayList<Prop> walls = new ArrayList<Prop>(); // walls for level design
	private ArrayList<Prop> puzzles = new ArrayList<Prop>();
	private ArrayList<Prop> triggers = new ArrayList<Prop>(); 
	private ArrayList<String> storyText = new ArrayList<String>();
	private ArrayList<String> description = new ArrayList<String>();
	private Map map;
	private LevelGenerator levelGenerator;

	
	
	
	public Level(int level)
	{
		levelNumber = 1;
		levelGenerator = new LevelGenerator(level);
		map = levelGenerator.getMap();
		props = levelGenerator.getProps();
		
	}
	// instantiates level without puzzles, can be used for making scenes with story elements
	// no map
	public Level(int lNum,  String[] sText, String[] dText) {
		
			levelNumber = lNum;
			// iterates over String array to add story, level description text elements to level
			// can be used for specific level children where they have level specific methods
			for (int num = 0; num < sText.length; num++)
			{
				storyText.add(sText[num]);
			}
			for (int num = 0; num < dText.length; num++)
			{
				description.add(dText[num]);
			}
	}
	
	// constructor to make a scene with a generic map, a few props
	// can be used if you want to make a room with story elements and props and no puzzles
	// possible could take a Prop array as parameter and then generate story props using index
	public Level(int numProps, int lNum,  String[] sText, String[] dText) {
		
		
		levelNumber = lNum;
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}
		for (int num = 0; num < sText.length; num++)
		{
			storyText.add(sText[num]);
		}
		for (int num = 0; num < dText.length; num++)
		{
			description.add(dText[num]);
		}
}
	
	// constructor to make a scene with a generic map, a few props
	// can be used if you want to make a room with story elements and props and no puzzles
	// includes x,y values to make a different sized room for more props
	public Level(int numProps, int lNum,  String[] sText, String[] dText, int x, int y) {
		
		levelNumber = lNum;
		map = new Map(x, y);						
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}
		for (int num = 0; num < sText.length; num++)
		{
			storyText.add(sText[num]);
		}
		for (int num = 0; num < dText.length; num++)
		{
			description.add(dText[num]);
		}
}
	
	// constructor to make level with props and puzzles using arrays for story text and level description text, etc
	public Level(int numProps, int numPuzzles, int lNum, String[] sText, String[] dText, int x, int y) {
		
		levelNumber = lNum;
		map = new Map(x, y);
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}
		
		for (int num = 0; num < numPuzzles; num++)
		{
			puzzles.add(generatePuzzle());
			map.addProp(puzzles.get(num));
		}
		for (int num = 0; num < sText.length; num++)
		{
			storyText.add(sText[num]);
		}
		for (int num = 0; num < dText.length; num++)
		{
			description.add(dText[num]);
		}
	}
	
	
	// constructor to make level with props and puzzles using arrays for story text and level description text, etc
	public Level(int numProps, int numPuzzles, int numWalls, int lNum, String[] sText, String[] dText, int x, int y) {
		
		levelNumber = lNum;
		map = new Map(x, y);
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}		
		for (int num = 0; num < numPuzzles; num++)
		{
			puzzles.add(generatePuzzle());
			map.addProp(puzzles.get(num));
		}
		for (int num = 0; num < numWalls; num++)
		{
			walls.add(generateWall());
			map.addProp(walls.get(num));
		}
		for (int num = 0; num < sText.length; num++)
		{
			storyText.add(sText[num]);
		}
		for (int num = 0; num < dText.length; num++)
		{
			description.add(dText[num]);
		}
	}
	

	// constructor to make level with props and puzzles using just one string for story text and level description text, etc
	// can use \n to format for new lines for story.
	public Level(int numProps, int numPuzzles, int lNum, String sText, String dText, int x, int y) {
		
		levelNumber = lNum;
		map = new Map(x, y);
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}
		
		for (int num = 0; num < numPuzzles; num++)
		{
			puzzles.add(generatePuzzle());
			map.addProp(puzzles.get(num));
		}
		storyText.add(sText);
		description.add(dText);
		
	}
	
	
	// constructor to make level with props and puzzles using just one string for story text and level description text, etc
	// can use \n to format for new lines for story.
	public Level(int numProps, int numPuzzles, int numWalls, int numTriggers, int lNum, String sText, String dText, int x, int y) {
		
		levelNumber = lNum;
		map = new Map(x, y);
		for (int num = 0; num < numProps; num++)
		{
			props.add(generateProp());
			map.addProp(props.get(num));
		}
		
		for (int num = 0; num < numPuzzles; num++)
		{
			puzzles.add(generatePuzzle());
			map.addProp(puzzles.get(num));
		}
		for (int num = 0; num < numWalls; num++)
		{
			walls.add(generateWall());
			map.addProp(walls.get(num));
		}
		for (int num = 0; num < numTriggers; num++)
		{
			triggers.add(generateTrigger());
			map.addProp(triggers.get(num));
		}
		storyText.add(sText);
		description.add(dText);
		
	}
	
	// can use a method to generate prop/puzzle description, prop/puzzle questions, and puzzle answers based
	// on random number generated and using matching indexes in either a large String array, or
	// from lines using a delimiter of our choice. 
	// plan on moving the generate methods to a LevelGenerator class.
	private Prop generateProp() {
		Prop prop = new Prop("Put description in here.", 2, 1, false);
		return prop;
	}
	
	// simple puzzle generator
	private Prop generatePuzzle() {
		Prop puzzle = new Prop("Put description in here.", "What is 1 + 1", "1", 3, 4);
		return puzzle;
	}
	
	private Prop generateWall() {
		Prop decoration = new Prop(3, 1);
		return decoration;
	}
	
	// test class to try to generate maps from text files to allow us some cool scalability and easier
	// level creation
	private Prop generateWallFromFile() {
		Scanner inputStream = null;
		String filename = "level" + Integer.toString(levelNumber) + ".lvl";
		try
		{
			inputStream = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error generating walls. Exiting game.");
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			String line = inputStream.nextLine();
		}
		Prop decoration = new Prop(3, 1);
		return decoration;
	}
	
	private Prop generateTrigger() {
		Prop trigger = new Prop("Trigger activated", 1,4, true);
		return trigger;
	}
	
	
	// get level number for map view
	public int getLevelNum() {
		return levelNumber;
	}
	
	
	// method can generate story from story.txt file, we can use a delimiter to 
	// keep count of levels and get the story for the level number
	private String[] generateStory() {
		String[] foo = {"bar", "bar"};
		return foo;
	}
	
	
	// method can generate level description (flavor text when entering room/inspecting room, or sumthing) from ldesc.txt file, we can use a delimiter to 
	// keep count of levels and get the story for the level number
	private String[] generateDesc() {
		String[] foo = {"bar", "bar"};
		return foo;
	}
	
	
	// displays level without spaces between the map String "tiles"
	public void displayLevel(int playerX, int playerY) {
		map.printMap(playerX, playerY);
	}
	
	// diplays level with spaces between the map String "tiles"
	public void displayLevelSpaces(int playerX, int playerY) {
		map.printMapSpaces(playerX, playerY);
	}
	
	// has map object print the map to screen
	public void displayLevel(int playerX, int playerY, boolean hitObject) {
		map.printMap(playerX, playerY, hitObject);
	}
	
	// has map object print the map with spaces to the screen
	public void displayLevelSpaces(int playerX, int playerY, boolean hitObject) {
		map.printMapSpaces(playerX, playerY, hitObject);
	}
	
	
	// checks the map to see if the player can move
	public boolean checkMove(int move, int playerX, int playerY) {
		return map.canMove(move, playerX, playerY);
	}
	
	// checks the map to see if the player has stepped on a trigger
	public boolean checkTrigger(int playerX, int playerY)
	{
		boolean isTrigger = false;
		for (int num = 0; num < triggers.size(); num++)
		{
			if (playerX == triggers.get(num).getX() 
					&& playerY == triggers.get(num).getY() 
						&& triggers.get(num).isTrigger())
			{
				triggers.get(num).printTriggerText();
				isTrigger = true;
				triggers.get(num).triggerOff();
			}
		}		
		return isTrigger;
	}
	
	// simple getter for Map X length if needed
	public int mapX() {
		return map.getXLen();
	}
	
	// simple getter for Map Y lengthS if needed
	public int mapY() {
		return map.getYLen();
	}
	
}
