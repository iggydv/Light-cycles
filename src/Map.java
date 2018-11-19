import java.awt.Color;

public class Map
{
    private Color map[][];
    private int sizeX;
    private int sizeY;
    
    // Constructs a new playing field with width sizeX and height sizeY.
    
    public Map(int sizeX, int sizeY)
    {
        map=new Color[sizeX][sizeY];
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        
        StdDraw.setCanvasSize();
        StdDraw.setXscale(0,sizeX);
        StdDraw.setYscale(0,sizeY);
        
        for(int o=0;o<sizeY;o++)
        {
            for(int p=0;p<sizeX;p++)
            {
                map[o][p]=StdDraw.GRAY;
            }
        }
    }
    
    // Returns true if this x,y location on the playing field is
    // either blocked (due to a previous visit by a player), or this
    // location is off the playing field.
    
    public boolean isBlocked(int x,int y)
    {
        try
        {
            if(!map[x][y].equals(StdDraw.GRAY))
            {
                return true;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
        }
        if(x>=sizeX||(x<0)||(y>=sizeY)||(y<0))
        {
            return true;
        }
        return false;
    }
    
    // Mark the location of each player in the array on the playing field
    // using the color associated with that player, or orange if
    // the player is dead (this represents an explosion). Note that this
    // method does not call StdDraw, it actually just changes
    // the internal representation of the playing field. The actual
    // drawing to StdDraw is done within the draw() method.
    
    public void drawPlayers(Player[] players)
    {
        for(int t=0;t<players.length;t++)
        {
            if(isBlocked(players[t].getX(),players[t].getY()))
            {
                map[players[t].getX()][players[t].getY()]=StdDraw.ORANGE;
            }
            else
            {
                map[players[t].getX()][players[t].getY()]=players[t].getColor();
            }
        }
    }
    
    // Output the internal representation of the playing field to StdDraw.
    
    public void draw()
    {
        for(int q=0;q<sizeY;q++)
        {
            for(int w=0;w<sizeX;w++)
            {
                StdDraw.setPenColor(map[q][w]);
                StdDraw.filledSquare(q,w,0.5);
            }
        }
        StdDraw.show(3);
    }
}
