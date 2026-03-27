import greenfoot.*;

/**
 * This class adds particles which appear and
 * fade out when the player dies.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class DeathParticle extends Actor
{
    private int dx;
    private int dy;
    private int life;
    private GreenfootImage image;
    
    public DeathParticle()
    {
        //Sets the the duration of the particles and creates a random x and y
        //position sets the color and sets the image.
        life = 25;

        dx = Greenfoot.getRandomNumber(9) - 4;
        dy = Greenfoot.getRandomNumber(9) - 4;

        image = new GreenfootImage(8, 8);
        image.setColor(new Color(255, 71, 76));
        image.fillOval(0, 0, 8, 8);
        setImage(image);
    }

    public void act()
    {
        //Moves the particles, fades it out, and runs removes itself after
        //life is < 0.
        moveParticle();
        fadeOut();

        life--;
        if (life <= 0)
        {
            getWorld().removeObject(this);
        }
    }
    //Moves the particle in a random direction
    private void moveParticle()
    {
        setLocation(getX() + dx, getY() + dy);
        dy += Greenfoot.getRandomNumber(9) - 4;
    }

    private void fadeOut()
    {
        //Increases the alpha (transparency) of the object which slowly
        //fades the object off the screen
        int alpha = Math.max(0, (life * 255) / 25);

        GreenfootImage faded = new GreenfootImage(8, 8);
        faded.setColor(new Color(255, 71, 76, alpha));
        faded.fillOval(0, 0, 8, 8);
        setImage(faded);
    }
}
