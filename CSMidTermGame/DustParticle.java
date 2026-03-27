import greenfoot.*;

/**
 * This class adds particles which follow
 * the character sprite as it moves.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class DustParticle extends Actor
{
    private int life = 20;
    private int dx;
    private int dy;
    private GreenfootImage img;
    
    //Initializes the dust particle and sets a random start point.
    public DustParticle()
    {
        img = new GreenfootImage(12, 12);
        img.setColor(new Color(200, 200, 200, 180));
        img.fillOval(0, 0, 12, 12);
        setImage(img);

        dx = Greenfoot.getRandomNumber(5) - 2;
        dy = Greenfoot.getRandomNumber(3) - 1;
    }

    public void act()
    {
        //sets a random location and takes away from the life
        //fades the particle out and removes itself when life  is < 0
        setLocation(getX() + dx, getY() + dy);

        life--;

        int newSize = Math.max(1, life / 2);
        GreenfootImage faded = new GreenfootImage(newSize, newSize);
        faded.setColor(new Color(200, 200, 200, Math.max(0, life * 10)));
        faded.fillOval(0, 0, newSize, newSize);
        setImage(faded);

        if (life <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
