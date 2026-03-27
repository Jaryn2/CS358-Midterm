import greenfoot.*;

/**
 * This class is for creating the high score
 * object displayed on the main menu.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class HighScore extends Actor {
    static int highScore;
    int size;
    String text;
    
    GreenfootImage img;
    
    public HighScore(String text, int size, boolean onOff) {
        //Initializes the highscore textbox ui
        this.size = size;
        this.text = text;
        
        img = new GreenfootImage(text.length() * (size / 2) + size + 15, size * 2);
        img.setFont(new Font("Serif Sans", true, false, size));
        
        if (onOff)
        {
            img.drawString(text + highScore, 5, size);
        } else img.drawString(text, 5, size);
        
        setImage(img);
    }
    
    //Refreshes the highscore ui
    public void refreshHighScore()
    {
        img.clear();
        setImage(img);
        img.drawString(text + highScore, 5, size); 
        setImage(img);
    }
    
    //Returns highscore
    public static int getHighScore()
    {
        return highScore;   
    }
    //Returns text
    public String getText()
    {
        return text;
    }
}
