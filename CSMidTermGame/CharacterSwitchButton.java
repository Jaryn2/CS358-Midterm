import greenfoot.*;

public class CharacterSwitchButton extends Actor
{
    private boolean side;
    private StartScreen startScreen;

    public CharacterSwitchButton(String text, int width, int height, boolean side, StartScreen startScreen)
    {
        this.side = side;
        this.startScreen = startScreen;

        int arc = 20;
        GreenfootImage img = new GreenfootImage(width, height);

        img.setColor(new Color(150, 170, 205));

        img.fillRect(arc / 2, 0, width - arc, height);
        img.fillRect(0, arc / 2, width, height - arc);

        img.fillOval(0, 0, arc, arc);
        img.fillOval(width - arc, 0, arc, arc);
        img.fillOval(0, height - arc, arc, arc);
        img.fillOval(width - arc, height - arc, arc, arc);

        GreenfootImage textImage = new GreenfootImage(text, 20, Color.BLACK, null);
        int x = (width - textImage.getWidth()) / 2;
        int y = (height - textImage.getHeight()) / 2;
        img.drawImage(textImage, x, y);

        setImage(img);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            startScreen.switchCharacter(side);
        }
    }
}