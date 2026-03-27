import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class adds the sprite for the ground blocks.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */
public class Block extends Actor
{   
    public Block()
    {
        //Sets the sprite of each block and the size they need to be
        setImage("Ground.png");
        GreenfootImage image = getImage();
        image.scale(50, 50);
        setImage(image);
    }
}
