import greenfoot.*;

public class DeathScreen extends World
{
    private Player previewPlayer;
    private int selectedIndex = 0;
    private Textbox logoText = new Textbox("You Died", 100, false);
    
    private String[] spriteArray = {
        "clockplayer.png",
        "eraserplayer.png",
        "hatplayer.png",
        "sunglassesplayer.png"
    };

    public DeathScreen()
    {
        super(1550, 1080, 1);

        previewPlayer = new Player(0, 0, 0);
        addObject(previewPlayer, 775, 400);

        previewPlayer.setCharacter(spriteArray[selectedIndex]);
        addObject(logoText, 800, 200);
        addObject(new CharacterSwitchButton("<", 80, 40, false, null, this), 650, 400);
        addObject(new CharacterSwitchButton(">", 80, 40, true, null, this), 900, 400);
        addObject(new HomeButton(150, 50, 25), 775, 610);
        addObject(new WorldButton("Retry", 150, 50, 25, null, this), 775, 550);
        getBackground().setColor(new Color(255, 209, 220));
        getBackground().fill();
    }

    public void switchCharacter(boolean right)
    {
        if (right)
        {
            selectedIndex++;
            if (selectedIndex >= spriteArray.length)
            {
                selectedIndex = 0;
            }
        }
        else
        {
            selectedIndex--;
            if (selectedIndex < 0)
            {
                selectedIndex = spriteArray.length - 1;
            }
        }

        previewPlayer.setCharacter(spriteArray[selectedIndex]);
    }

    public String getSelectedCharacter()
    {
        return spriteArray[selectedIndex];
    }
}
