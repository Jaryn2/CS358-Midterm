import greenfoot.*;  // import greenfoot classes

public class Textbox extends Actor {
    int score;
    int size;
    String text;
    
    GreenfootImage img;
    public Textbox(String text, int size, boolean onOff) {
        this.size = size;
        this.text = text;

        int additionalLength = 0;
        
        if (onOff) 
        {
            additionalLength = 150; 
        }
        
        img = new GreenfootImage(text.length() * (size / 2) + size + additionalLength, size * 2);
        img.setFont(new Font("Serif Sans", true, false, size));
        
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
    
    public void setScore(int score)
    {
        this.score = score;
        img.clear();
        setImage(img);
        img.drawString(text + score, 5, size); 
        setImage(img);
    }
    
    public String getText()
    {
        return text;
    }
}
