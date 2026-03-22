import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
