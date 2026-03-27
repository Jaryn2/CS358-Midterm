import greenfoot.*;

/**
 * A class that is used as a shell for text boxes 
 * can be used for normal text and scores.
 * 
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class Textbox extends Actor {
    int score;
    int size;
    String text;
    
    GreenfootImage img;
    //Initializes the text boxes, text, size, and whether or not 
    //it will include the score.
    public Textbox(String text, int size, boolean onOff) {
        this.size = size;
        this.text = text;   
        //Used for ui centering
        int additionalLength = 0;
        
        if (onOff) 
        {
            additionalLength = 150; 
        }
        
        img = new GreenfootImage(text.length() * (size / 2) + size + additionalLength, size * 2);
        img.setFont(new Font("Serif Sans", true, false, size));
        
        //Checks to see if the score is needed or not and draws the ui image
        //based off that info
        if (onOff)
        {
            img.drawString(text + score, 5, size);
        }
        else
        {
            img.drawString(text, 5, size);
        }
        setImage(img);
    }
    
    //Sets the score
    public void setScore(int score)
    {
        this.score = score;
        img.clear();
        setImage(img);
        img.drawString(text + score, 5, size); 
        setImage(img);
    }
    
    //Returns the text
    public String getText()
    {
        return text;
    }
}
