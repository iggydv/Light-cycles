import java.awt.Color;

public class Player
{
    private String name;
    private int x;
    private int y; 
    private Color color;
    private int left;
    private int right;
    private int down;
    private int up;
    private int rigting=0;
    private boolean dood=false;
    
    // Creates a new player with the specified name, at the given x
    // and y coordinates. The player leaves a trail of the specified
    // colour.
    
    public Player(String name, int x, int y, Color color)
    {
        this.name=name;
        this.x=x;
        this.y=y;
        this.color=color;
    }
    
    // Return the player's name
    
    public String getName()
    {
        return name;
    }
    
    // Return the player's trail colour.
    
    public Color getColor()
    {
        return color;
    }
    
    // Return the player's current x position on the playing field.
    
    public int getX()
    {
        return x;
    }
    
    // Return the player's current y position on the playing field.
    
    public int getY()
    {
        return y;
    }
    
    // Store the keyboard key codes to which the player responds for
    // going up, down, left and right.
    
    public void setKeys(int up, int down, int left, int right)
    {
        this.up=up;
        this.down=down;
        this.left=left;
        this.right=right;
    }
    
    // Check the keyboard for whether any keys to which the player responds
    // have been pressed. You can use StdDraw.isKeyPressed() to do this.
    // If so, change the direction in which the player is going.
    
    public void handleKeys()
    {
        if(StdDraw.isKeyPressed(up))
        {
            if(rigting!=1)
            {
                rigting=0;
            }
        }
        else if(StdDraw.isKeyPressed(down))
        {
            if(rigting!=0)
            {
                rigting=1;
            }
        }
        else if(StdDraw.isKeyPressed(left))
        {
            if(rigting!=3)
            {
                rigting=2;
            }
        }
        else if(StdDraw.isKeyPressed(right))
        {
            if(rigting!=2)
            {
                rigting=3;
            }
        }  
    }
    
    // Had the player died?
    
    public boolean isDead()
    {
        return dood;
    }
    
    // If the player is not dead:
    // * calculate its new x/y location based on its current direction
    // * if the map is blocked at this new location, the player dies,
    // otherwise, set the player's current position to the new
    // coordinates.
    
    public void step(Map map)
    {
        int xx=0;
        int yy=0;
        if(!isDead())
        {
            if(rigting==0)
            {
                yy=y+1;
                xx=x;
            }
            else
                if(rigting==1)
            {
                yy=y-1;
                xx=x;
            }
            else
                if(rigting==2)
            {
                xx=x-1;
                yy=y;
            }
            else
                if(rigting==3)
            {
                xx=x+1;
                yy=y;
            }
        }
        if(map.isBlocked(xx, yy))
        {
            dood = true;
        }
        else
        {
            x = xx;
            y = yy;
        }
    }
    
    // Checks whether this player has collided with any of the
    // players in the array. Note that we must ensure not to test against
    // the player themself, otherwise players will always crash instantly!
    // If this player shares an x and y position with another
    // player in the array, mark this player as dead.
    
    public void testPlayerCollision(Player[] players)
    {
        for(int a=0;a<players.length;a++)
        {
            for(int b=0;b<players.length;b++)
            {
                if((players[a].getX()==players[b].getX())&&(players[a].getY()==players[b].getY())&&(a!=b))
                {
                    dood=true;
                }
            }
        }
    }
}

