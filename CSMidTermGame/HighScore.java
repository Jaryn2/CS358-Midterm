import greenfoot.*;  // import greenfoot classes

public class HighScore extends Actor {
    static int highScore;
    int size;
    String text;
    
    GreenfootImage img;
    
    public HighScore(String text, int size, boolean onOff) {
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
    
    public void refreshHighScore()
    {
        img.clear();
        setImage(img);
        img.drawString(text + highScore, 5, size); 
        setImage(img);
    }
    
    public static int getHighScore()
    {
        return highScore;   
    }
    
    public String getText()
    {
        return text;
    }
}
