import greenfoot.*;

public class Player extends Actor
{
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
    
        animateCharacter();
    
        if (getWorld() instanceof MyWorld)
        {
            playerMovement();
            jump();
            playerPhysics();
            checkForCollision();
            deathCheck();
            spawnRunParticles();
        }
    }

    public void checkForCollision()
    {
        Actor blockBelow = getOneObjectAtOffset(0, getImage().getHeight() / 2, Block.class);

        if (blockBelow != null && yVelocity >= 0)
        {
            onGround = true;
            yVelocity = 0;

            int blockTop = blockBelow.getY() - blockBelow.getImage().getHeight() / 2;
            int playerHalfHeight = getImage().getHeight() / 2;

            setLocation(getX(), blockTop - playerHalfHeight);
        }
        else
        {
            onGround = false;
        }
    }

    public void playerPhysics()
    {
        yVelocity += gravity;

        if (yVelocity > 7)
        {
            yVelocity = 7;
        }

        setLocation(getX(), (int)(getY() + yVelocity));
    }

    public void playerMovement()
    {
        move(playerSpeed);

        if (isAtEdge())
        {
            playerSpeed *= -1;
            
            addScore();
            ((MyWorld)getWorld()).spawnRandomMiddle();
            System.out.println("Switched Middle");
        }
    }

    public void jump()
    {
        if (Greenfoot.isKeyDown("space") && onGround)
        {
            yVelocity = -playerJumpForce;
            onGround = false;
        }
    }

    public void deathCheck()
    {
        if (getY() > 1050)
        {
            reset();
        }
        Actor spikeCheck = getOneObjectAtOffset(getImage().getWidth() / 2 - 5, 0, Spike.class);
        Actor blockInFrontCheckMiddle = getOneObjectAtOffset(getImage().getWidth() / 2 - 5, 15, MiddleBlock.class);
        Actor blockInFrontCheckPerm = getOneObjectAtOffset(getImage().getWidth() / 2 - 5, 15, Block.class);
        
        
        if (spikeCheck != null || blockInFrontCheckMiddle != null || blockInFrontCheckPerm != null)
        {
            reset();
        }
    }

    public void reset()
    {
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

    public void addScore()
    {
        score++;
    }

    public int getScore()
    {
        System.out.println(score);
        return score;
    }

    public void setCharacter(String imageName)
    {
        currentCharacter = imageName;
        currentFrame = 0;
        animationCounter = 0;
        updateAnimationFrame();
    }

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
    
    public void updateAnimationFrame()
    {
        GreenfootImage spriteSheet = new GreenfootImage(currentCharacter);

        int frameWidth = spriteSheet.getWidth();
        int frameHeight = spriteSheet.getHeight() / totalFrames;

        GreenfootImage singleFrame = new GreenfootImage(frameWidth, frameHeight);

        singleFrame.drawImage(spriteSheet, 0, -currentFrame * frameHeight);

        setImage(singleFrame);
    }
    
    public void spawnRunParticles()
    {
        particleTimer++;
    
        if (particleTimer >= 4)
        {
            getWorld().addObject(new DustParticle(), getX(), getY() + getImage().getHeight() / 2);
            particleTimer = 0;
        }
    }
    
    public void waitSeconds(int seconds)
    {
        waitTimer = seconds * 60;
        waiting = true;
    }
}