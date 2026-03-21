import greenfoot.*;  // import greenfoot classes

public class Textbox extends Actor {
    public Textbox(String text, int size) {
        GreenfootImage img = new GreenfootImage(text.length() * (size / 2) + 10, size + 10); // Adjust size as needed
        img.setFont(new Font("Serif Sans", true, false, size)); // Example: Arial, bold, size
        img.drawString(text, 5, size); // Draw text with an offset
        setImage(img);
    }
}