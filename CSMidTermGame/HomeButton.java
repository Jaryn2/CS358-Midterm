import greenfoot.*;

public class HomeButton extends Actor
{
    private StartScreen startScreen;
    private DeathScreen deathScreen;

    public HomeButton(int width, int height, int fontSize)
    {

        int arc = 20;
        GreenfootImage img = new GreenfootImage(width, height);

        img.setColor(new Color(150, 170, 205));

        img.fillRect(arc / 2, 0, width - arc, height);
        img.fillRect(0, arc / 2, width, height - arc);

        img.fillOval(0, 0, arc, arc);
        img.fillOval(width - arc, 0, arc, arc);
        img.fillOval(0, height - arc, arc, arc);
        img.fillOval(width - arc, height - arc, arc, arc);

        GreenfootImage textImage = new GreenfootImage("Home", fontSize, Color.BLACK, null);
        int x = (width - textImage.getWidth()) / 2;
        int y = (height - textImage.getHeight()) / 2;
        img.drawImage(textImage, x, y);

        setImage(img);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
                 Greenfoot.setWorld(new StartScreen());
        }
    }
}
