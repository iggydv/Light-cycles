import java.awt.event.KeyEvent;


public class Lightcycles
{
  public static void gameLoop() {
    int X = 32;
    int Y = 32;
    
    Map map = new Map(X,Y);
    
    Player player1 = new Player("Red Player", X / 4, Y / 2, StdDraw.RED);
    player1.setKeys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
    Player player2 = new Player("Blue Player", (3 * X) / 4, Y / 2, StdDraw.BLUE);
    player2.setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    Player[] players = {player1, player2};

    int aliveCount = players.length;
    Player livingPlayer = null;
    
    StdDraw.show(0);
    while (aliveCount > 1) {
      map.drawPlayers(players);
      map.draw();
      StdDraw.show(100);
      for (int i = 0 ; i < players.length ; i++) {
        Player player = players[i];
        player.handleKeys();
        player.step(map);
      }      
      
      aliveCount = 0;
      livingPlayer = null;
      for (int i = 0 ; i < players.length ; i++) {
        Player player = players[i];
        player.testPlayerCollision(players);
        if (!player.isDead()) {
          livingPlayer = player;
          aliveCount++;
        }
      }
    }    
    
    map.drawPlayers(players);
    map.draw();

    StdDraw.setPenColor(StdDraw.YELLOW);
    if (livingPlayer == null) {
      StdDraw.text(X / 2, Y / 2, "Tie, all players are dead.");
    } else {
      StdDraw.text(X / 2, Y / 2, livingPlayer.getName() + " won!");
    }
    StdDraw.show(1500);
  }
  
  public static void main(String args[]) {
    while (true) {
      gameLoop();
    }
  }
}