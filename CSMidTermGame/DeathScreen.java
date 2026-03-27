import greenfoot.*;

/**
 * This world loads upon player death.
 * It also allows the player to select the
 * character sprite.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class DeathScreen extends World
{
    //Initialize variables for the class
    private Player previewPlayer;
    private int selectedIndex = 0;
    private Textbox logoText = new Textbox("You Died", 100, false);
    HighScore highScoreUI = new HighScore("High Score: ", 60, true);
    
    //Array for the character selection
    private String[] spriteArray = {
        "clockplayer.png",
        "eraserplayer.png",
        "hatplayer.png",
        "sunglassesplayer.png"
    };
    
    
    public DeathScreen()
    {
        //Initialize objects in the deathscreen on creation of class
        super(1550, 1080, 1);

        previewPlayer = new Player(0, 0, 0);
        addObject(previewPlayer, 775, 400);

        previewPlayer.setCharacter(spriteArray[selectedIndex]);
        addObject(logoText, 775, 200);
        addObject(new CharacterSwitchButton("<", 80, 40, false, null, this), 650, 400);
        addObject(new CharacterSwitchButton(">", 80, 40, true, null, this), 900, 400);
        addObject(new HomeButton(150, 50, 25), 775, 610);
        addObject(new WorldButton("Retry", 150, 50, 25, null, this), 775, 550);
        addObject(highScoreUI, 775, 275);
        highScoreUI.refreshHighScore();
        getBackground().setColor(new Color(255, 209, 220));
        getBackground().fill();
    }

    public void switchCharacter(boolean right)
    {
        /*Takes input from the character switch buttons. If the right button
         * is clicked right = true and if the left button is clicked 
         * right = false
         * If statement cycles through the spriteArray based on the state of
         * variable right
         * Finally at the end sets the character for the next world.
         */
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
