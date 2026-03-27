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
        setImage("Ground.png");
        GreenfootImage image = getImage();
        image.scale(50, 50);
        setImage(image);
    }
}
