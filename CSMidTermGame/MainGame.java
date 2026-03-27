import greenfoot.*;
import java.util.ArrayList;

/**
 * This is the main class for the game.
 * Prefabs are used to generate the levels.
 * 
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class MainGame extends World
{
    //Initialize variables needed for the class
    ArrayList<int[][]> prefabs = new ArrayList<int[][]>();
    
    int frameCounter = 0;
    int lastPrefab = -1;
    int score;
    int counter;
    
    Player player = new Player(0.5, 4, 10);
    Textbox infoText = new Textbox("Press Space to Jump", 30, false);
    Textbox scoreUIText = new Textbox("Score: ", 75, false);
    Textbox scoreUIScore = new Textbox("", 75, true);
    HighScore highScoreUI = new HighScore("High Score: ", 40, true);
    
    public MainGame(String selectedCharacter)
    {
        //Initializes objects and variables on world start
        super(1550, 1080, 1);
        background();
        player.setCharacter(selectedCharacter);
        addObject(player, 100, 700);
        addObject(scoreUIScore, 975, 200);
        addObject(scoreUIText, 775, 200);
        addObject(highScoreUI, 775, 275);
        addObject(infoText, 775, 600);
        loadPrefabs();
        PermanentLeft();
        PermanentRight();
        spawnRandomMiddle();
        counter = 0;
    }

    public void act()
    {
        //Gets the score thats stored in player and updates the ui. Also holds
        //the counter for the info text box
        this.score = player.getScore();
        scoreUIScore.setScore(score);
        counter++;
        if(counter == 240)
        {
            removeObject(infoText);   
        }
        
    }

    public void loadPrefabs()
    {
        // 0: empty block, 1: ground block, 2: spike
        // The third row is where the starting blocks
        // are. The player sprite initializes on the second row.
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,1,1,0,1,1,1,0,1,1,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,0,0,1,1,1,0,0,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
            
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,0,0,1,1,0,0,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,2,2,1,1,1,2,2,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,2,2,0,0,0,2,2,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,2,1,1,0,1,1,0,1,1,2,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,0,0,0,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,0,2,1,1,1,1,1,2,0,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,2,0,0,1,1,1,0,0,2,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,0,2,1,1,1,2,0,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,1,1,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,0,2,2,1,1,1,2,2,0,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,2,0,1,1,1,0,2,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,0,0,1,1,1,1,1,1,0,0,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
        
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,2,1,0,0,0,1,2,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,2,2,1,1,0,0,0,0,0},
            {0,1,1,0,0,0,1,1,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,1,2,2,2,1,1,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,2,0,0,0,0,0,0,0,2,1,1,1},
            {0,0,0,1,1,1,1,2,1,1,1,1,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,2,0,0,0,0,2,2,0,0,0,0,2,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
            
        prefabs.add(new int[][]{
            {0,0,0,0,1,1,1,0,1,1,1,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
            
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,1,1,0,1,1,1,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0}
            });
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,2,2,2,1,1,1,0,0,0},
            {1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });        
    
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {2,0,0,0,1,1,1,2,1,1,1,0,0,0,2},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            });
    }
    
    
    //PermanentLeft and Permanent right initialize the blocks that don't change.
    public void PermanentLeft()
    {
        for (int i = 0; i < 8; i++)
        {
            addObject(new Block(), 25 + (i * 50), 750);
        }
    }

    public void PermanentRight()
    {
        for (int i = 0; i < 8; i++)
        {
            addObject(new Block(), 1525 - (i * 50), 750);
        }
    }
    
    
    //spawns the prefab when called and given the layout and the position it
    //should be spawned at.
    public void spawnPrefab(int[][] layout, int startX, int startY)
    {
        int tileSize = 50;
        //For loop runs through each prefab layout and places a block, spike
        //or nothing based on the #'s in the array.
        for (int row = 0; row < layout.length; row++)
        {
            for (int col = 0; col < layout[row].length; col++)
            {
                if (layout[row][col] == 1)
                {
                    int x = startX + (col * tileSize);
                    int y = startY + (row * tileSize);
                    addObject(new MiddleBlock(), x, y);
                } else if (layout[row][col] == 2)
                {
                    int x = startX + (col * tileSize);
                    int y = startY + (row * tileSize);
                    addObject(new Spike(), x, y);
                }
            }
        }
    }
    //Removes the prefabs that way new ones can spawn without overlapping
    public void clearMiddle()
    {
        removeObjects(getObjects(MiddleBlock.class));
        removeObjects(getObjects(Spike.class));
        highScoreUI.refreshHighScore();
    }
    
    //Spawns the layout prefabs randomly
    public void spawnRandomMiddle()
    {
        //Clears middle so newly spawned prefabs dont overlap
        clearMiddle();

        int randomIndex = Greenfoot.getRandomNumber(prefabs.size());
        //While loop checks to make sure that the same prefab doesn't spawn
        //twice in a row.
        while (randomIndex == lastPrefab && prefabs.size() > 1)
        {
            randomIndex = Greenfoot.getRandomNumber(prefabs.size());
        }
        //Sets last prefab and then calls spawnPrefab with newly generated one.
        lastPrefab = randomIndex;
        
        int[][] chosenPrefab = prefabs.get(randomIndex);
        spawnPrefab(chosenPrefab, 425, 650);
    }
    //Sets worlds background
    public void background()
    {
        setBackground("background1.png");
        GreenfootImage bg = getBackground();
        bg.scale(1565, 1080);
        setBackground(bg);
    }
}
