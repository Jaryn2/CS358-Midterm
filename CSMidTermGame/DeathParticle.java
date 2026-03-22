import greenfoot.*;

public class DeathParticle extends Actor
{
    private int dx;
    private int dy;
    private int life;
    private GreenfootImage image;
    
    public DeathParticle()
    {
        life = 25;

        dx = Greenfoot.getRandomNumber(9) - 4;
        dy = Greenfoot.getRandomNumber(9) - 4;

        image = new GreenfootImage(8, 8);
        image.setColor(new Color(255, 255, 255, 255));
        image.fillOval(0, 0, 8, 8);
        setImage(image);
    }

    public void act()
    {
        moveParticle();
        fadeOut();

        life--;
        if (life <= 0)
        {
            getWorld().removeObject(this);
        }
    }

    private void moveParticle()
    {
        setLocation(getX() + dx, getY() + dy);
        dy += 1;
    }

    private void fadeOut()
    {
        int alpha = Math.max(0, (life * 255) / 25);

        GreenfootImage faded = new GreenfootImage(8, 8);
        faded.setColor(new Color(255, 255, 255, alpha));
        faded.fillOval(0, 0, 8, 8);
        setImage(faded);
    }
}