import greenfoot.*;

/**
 * This is the class for the player character.
 * It updates the position, adds particles, 
 * checks for collision, and handles score updating.
 * 
 * @author Team Kappa
 * @version 2026-03-27
 */

public class Player extends Actor
{
    //Initialize variables used in the class.
    double gravity;
    int playerSpeed;
    double playerJumpForce;
    double yVelocity;
    boolean onGround;
    int startX = 100;
    int startY = 700;
    int score = 0;

    private String currentCharacter;
    private int currentFrame = 0;
    private int totalFrames = 2;
    private int animationDelay = 10;
    private int animationCounter = 0;
    private int particleTimer = 0;
    private boolean waiting = false;
    private int waitTimer = 0;

    private int coyoteTime = 0;
    private int coyoteFrames = 6;
    
    //Initializes the player with its gravity, speed, and jumpForce.
    public Player(double gravity, int speed, double jumpForce)
    {
        this.gravity = gravity;
        this.playerSpeed = speed;
        this.playerJumpForce = jumpForce;
        this.yVelocity = 0;
        this.onGround = false;
    }
    
    public void act()
    {
        //Timer used so the death particles finish processing before switching
        //Worlds
        if (waiting)
        {
            waitTimer--;

            if (waitTimer <= 0)
            {
                waiting = false;
                Greenfoot.setWorld(new DeathScreen());
            }

            return;
        }
        //Animates the character.
        animateCharacter();
        
        //Checks to see if getWorld is of type MainGame.
        if (getWorld() instanceof MainGame)
        {
            playerMovement();
            jump();
            playerPhysics();
            checkForCollision();
            deathCheck();
            spawnRunParticles();
        }
    }
    
    //Checks for the collisions and adjusts players position if needed.
    public void checkForCollision()
    {
        //Checks to see if there is a Block below you.
        Actor blockBelow = getOneObjectAtOffset(0, getImage().getHeight() / 2, Block.class);
        
        //Checks if there is a block below you and if you're falling.
        //Coyote frames and coyote time are used to cut the player some slack
        //For example if they run off the block they have a certain amount of time to jump
        //when they coyote time is done they can no longer jump.
        if (blockBelow != null && yVelocity >= 0)
        {
            //sets on ground to true so you can jump
            onGround = true;
            coyoteTime = coyoteFrames;
            yVelocity = 0;

            int blockTop = blockBelow.getY() - blockBelow.getImage().getHeight() / 2;
            int playerHalfHeight = getImage().getHeight() / 2;

            setLocation(getX(), blockTop - playerHalfHeight);
        }
        else
        {
            if (onGround)
            {
                coyoteTime = coyoteFrames;
            }

            onGround = false;

            if (coyoteTime > 0)
            {
                coyoteTime--;
            }
        }
    }
    
    //Handles the players physics.
    public void playerPhysics()
    {
        yVelocity += gravity;

        if (yVelocity > 7)
        {
            yVelocity = 7;
        }

        setLocation(getX(), (int)(getY() + yVelocity));
    }
    
    //Handles the players movement
    public void playerMovement()
    {
        move(playerSpeed);
        
        //When the player reaches the edge of the screen it flips their
        //direction and adds to the score.
        if (isAtEdge())
        {
            playerSpeed *= -1;

            addScore();
            ((MainGame)getWorld()).spawnRandomMiddle();

        }
    }
    
    //Handles jumping
    public void jump()
    {
        //Checks for the spacebar being down, player being onGround and the coyote timer is
        //Greater than 0
        if (Greenfoot.isKeyDown("space") && (onGround || coyoteTime > 0))
        {
            //Applies jumping physics
            yVelocity = -playerJumpForce;
            onGround = false;
            coyoteTime = 0;
        }
    }

    public void deathCheck()
    {
        //If a player gets below a certain height they die
        if (getY() > 1050)
        {
            reset();
        }
        
        //Variables used to check the hitboxes to the left and right of them.
        Actor spikeCheckRight = getOneObjectAtOffset(getImage().getWidth() / 2 - 5, 0, Spike.class);
        Actor spikeCheckLeft = getOneObjectAtOffset(getImage().getWidth() / 2 * -1 + 5, 0, Spike.class);
        Actor blockInFrontCheckPermRight = getOneObjectAtOffset(getImage().getWidth() / 2 - 5, 10, Block.class);
        Actor blockInFrontCheckPermLeft = getOneObjectAtOffset(getImage().getWidth() / 2 * -1 + 5, 10, Block.class);


        if (spikeCheckRight != null 
        || spikeCheckLeft != null 
        || blockInFrontCheckPermLeft != null 
        || blockInFrontCheckPermRight != null)
        {
            reset();
        }
    }
    
    //Resets score and sets highScore
    //Initializes death particles
    public void reset()
    {   
        if (score > HighScore.getHighScore())
        {
            HighScore.highScore = score;
        }
        
        if (waiting) return;
        
        World world = getWorld();
        int x = getX();
        int y = getY();

        for (int i = 0; i < 12; i++)
        {
            world.addObject(new DeathParticle(), x, y);
        }

        waitSeconds(1);
    }
    //Adds score
    public void addScore()
    {
        score++;
    }
    //Returns score
    public int getScore()
    {
        return score;
    }
    //Sets the characters sprite
    public void setCharacter(String imageName)
    {
        currentCharacter = imageName;
        currentFrame = 0;
        animationCounter = 0;
        updateAnimationFrame();
    }
    //Animates the character by switching between two different sprites
    public void animateCharacter()
    {
        if (currentCharacter == null)
        {
            return;
        }

        animationCounter++;

        if (animationCounter >= animationDelay)
        {
            animationCounter = 0;
            currentFrame++;

            if (currentFrame >= totalFrames)
            {
                currentFrame = 0;
            }

            updateAnimationFrame();
        }
    }
    //Splits the images we have into half and sets the top or bottom to the sprite
    //depending on what frame the game is on
    public void updateAnimationFrame()
    {
        GreenfootImage spriteSheet = new GreenfootImage(currentCharacter);

        int frameWidth = spriteSheet.getWidth();
        int frameHeight = spriteSheet.getHeight() / totalFrames;

        GreenfootImage singleFrame = new GreenfootImage(frameWidth, frameHeight);

        singleFrame.drawImage(spriteSheet, 0, -currentFrame * frameHeight);

        setImage(singleFrame);
    }
    //Spawns the running effect particles
    public void spawnRunParticles()
    {
        particleTimer++;

        if (particleTimer >= 4)
        {
            getWorld().addObject(new DustParticle(), getX(), getY() + getImage().getHeight() / 2);
            particleTimer = 0;
        }
    }
    //Used to initial the wait timer
    public void waitSeconds(int seconds)
    {
        waitTimer = seconds * 60;
        waiting = true;
    }
}
