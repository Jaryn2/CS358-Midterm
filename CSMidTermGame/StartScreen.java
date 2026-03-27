import greenfoot.*;

/**
 * The start screen allows the player to
 * switch character sprites and start the game.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class StartScreen extends World
{
    

    private Player previewPlayer;
    private int selectedIndex = 0;
    private Textbox logoText = new Textbox("Jump Man", 100, false);
    HighScore highScoreUI = new HighScore("High Score: ", 60, true);

    //Array for the character selection

    private String[] spriteArray = {
        "clockplayer.png",
        "eraserplayer.png",
        "hatplayer.png",
        "sunglassesplayer.png"
    };
    
    public StartScreen()
    {
        //Initialize objects in the deathscreen on creation of class
        super(1550, 1080, 1);

        previewPlayer = new Player(0, 0, 0);
        addObject(previewPlayer, 775, 400);

        previewPlayer.setCharacter(spriteArray[selectedIndex]);
        addObject(logoText, 775, 200);
        addObject(new CharacterSwitchButton("<", 80, 40, false, this, null), 650, 400);
        addObject(new CharacterSwitchButton(">", 80, 40, true, this, null), 900, 400);
        addObject(highScoreUI, 800, 275);
        highScoreUI.refreshHighScore();
        addObject(new WorldButton("Play", 150, 50, 25, this, null), 775, 550);
        getBackground().setColor(new Color(255, 209, 220));
        getBackground().fill();
    }
    /*Takes input from the character switch buttons. If the right button
     * is clicked right = true and if the left button is clicked 
     * right = false
     * If statement cycles through the spriteArray based on the state of
     * variable right
     * Finally at the end sets the character for the next world.
     */
    public void switchCharacter(boolean right)
    {
        if (right)
        {
            selectedIndex++;
            if (selectedIndex >= spriteArray.length)
            {
                selectedIndex = 0;
            }
        }
        else
        {
            selectedIndex--;
            if (selectedIndex < 0)
            {
                selectedIndex = spriteArray.length - 1;
            }
        }

        previewPlayer.setCharacter(spriteArray[selectedIndex]);
    }
    //Returns the selected character.

    public String getSelectedCharacter()
    {
        return spriteArray[selectedIndex];
    }
}
