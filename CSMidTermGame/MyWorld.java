import greenfoot.*;
import java.util.ArrayList;

public class MyWorld extends World
{
    ArrayList<int[][]> prefabs = new ArrayList<int[][]>();

    int r = 255;
    int g = 0;
    int b = 0;

    int stage = 0;
    int frameCounter = 0;
    int lastPrefab = -1;
    int score;
    Player player = new Player(0.5, 4, 10);
    Textbox scoreUI = new Textbox("Score: ", 75, true);
    
    public MyWorld(String selectedCharacter)
    {
        super(1550, 1080, 1);
        background();
        player.setCharacter(selectedCharacter);
        addObject(player, 100, 700);
        addObject(scoreUI, 750, 200);
        loadPrefabs();
        PermanentLeft();
        PermanentRight();
        spawnRandomMiddle();
    }

    public void act()
    {
        this.score = player.getScore();
        scoreUI.setScore(score);
    }

    public void loadPrefabs()
    {
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,2,2,1,1,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,1,2,2,1,1,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,2,0,0},
            {0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,0,1,1,1,0,1,1,1,1,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,2,0,0,0,0,0,0,0},
            {0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });
    }
    
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

    public void spawnPrefab(int[][] layout, int startX, int startY)
    {
        int tileSize = 50;

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

    public void clearMiddle()
    {
        removeObjects(getObjects(MiddleBlock.class));
        removeObjects(getObjects(Spike.class));
    }

    public void spawnRandomMiddle()
    {
        clearMiddle();

        int randomIndex = Greenfoot.getRandomNumber(prefabs.size());

        while (randomIndex == lastPrefab && prefabs.size() > 1)
        {
            randomIndex = Greenfoot.getRandomNumber(prefabs.size());
        }

        lastPrefab = randomIndex;

        int[][] chosenPrefab = prefabs.get(randomIndex);
        spawnPrefab(chosenPrefab, 425, 650);
    }
    public void background()
    {
        setBackground("background.png");
        GreenfootImage bg = getBackground();
        bg.scale(1565, 1080);
        setBackground(bg);
    }
}