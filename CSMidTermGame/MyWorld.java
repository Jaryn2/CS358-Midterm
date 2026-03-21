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

    public MyWorld(String selectedCharacter)
    {
        super(1550, 1080, 1);

        Player player = new Player(0.3, 4, 6.5);
        player.setCharacter(selectedCharacter);
        addObject(player, 100, 700);

        addObject(new Score("Score: 0", 75), 750, 150);

        loadPrefabs();
        PermanentLeft();
        PermanentRight();
        spawnRandomMiddle();
    }

    public void act()
    {
        updateBackground();
    }

    public void loadPrefabs()
    {
        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,0,0,1,1,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,1,1,0,0,1,1,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,0,1,1,1,0,1,1,1,1,0}
        });

        prefabs.add(new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
                }
            }
        }
    }

    public void clearMiddle()
    {
        removeObjects(getObjects(MiddleBlock.class));
    }

    public void updateBackground()
    {
        frameCounter++;
        if (frameCounter < 3) return;
        frameCounter = 0;

        int speed = 1;

        if (stage == 0)
        {
            g += speed;
            if (g >= 255)
            {
                g = 255;
                stage = 1;
            }
        }
        else if (stage == 1)
        {
            r -= speed;
            if (r <= 0)
            {
                r = 0;
                stage = 2;
            }
        }
        else if (stage == 2)
        {
            b += speed;
            if (b >= 255)
            {
                b = 255;
                stage = 3;
            }
        }
        else if (stage == 3)
        {
            g -= speed;
            if (g <= 0)
            {
                g = 0;
                stage = 4;
            }
        }
        else if (stage == 4)
        {
            r += speed;
            if (r >= 255)
            {
                r = 255;
                stage = 5;
            }
        }
        else if (stage == 5)
        {
            b -= speed;
            if (b <= 0)
            {
                b = 0;
                stage = 0;
            }
        }

        getBackground().setColor(new Color(r, g, b));
        getBackground().fill();
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
}