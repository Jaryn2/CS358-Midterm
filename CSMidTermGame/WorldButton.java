import greenfoot.*;

/**
 * This class generates buttons for use on
 * the start screen and death screen.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class WorldButton extends Actor
{
    private StartScreen startScreen;
    private DeathScreen deathScreen;
    
    //Initalizes the buttons text, width, height, fontSize, startScreen, and deathScreen.
    public WorldButton(String text, int width, int height, int fontSize, StartScreen startScreen, DeathScreen deathScreen)
    {
        this.startScreen = startScreen;
        this.deathScreen = deathScreen;

        int arc = 20;
        GreenfootImage img = new GreenfootImage(width, height);

        img.setColor(new Color(150, 170, 205));

        img.fillRect(arc / 2, 0, width - arc, height);
        img.fillRect(0, arc / 2, width, height - arc);

        img.fillOval(0, 0, arc, arc);
        img.fillOval(width - arc, 0, arc, arc);
        img.fillOval(0, height - arc, arc, arc);
        img.fillOval(width - arc, height - arc, arc, arc);

        GreenfootImage textImage = new GreenfootImage(text, fontSize, Color.BLACK, null);
        int x = (width - textImage.getWidth()) / 2;
        int y = (height - textImage.getHeight()) / 2;
        img.drawImage(textImage, x, y);

        setImage(img);
    }

    public void act()
    {
        //Checks for a mouse click and determines which variable has been declared
        //and sets the new world based off of that.
        if (Greenfoot.mouseClicked(this))
        {
            if (startScreen != null)
            {
                Greenfoot.setWorld(new MainGame(startScreen.getSelectedCharacter()));
            }else if (deathScreen != null)
            {
                 Greenfoot.setWorld(new MainGame(deathScreen.getSelectedCharacter()));
            }
        }
    }
}
