import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Game {
  // TODO  Na koniec gry, gdy się skończy trzeba wywołać metodę listener.
  /**
   * Main server.
   */
  private Server server;
  /**
   * Number of players in created game.
   */
  private int numberOfPlayers;
  /**
   * Number of boots in created game.
   */
  private int numberOfBoots;

  /**
   * List of players in this game.
   */
  private List<Player> playerList;

  public Game(Server server, int numberOfPlayers, int numberOfBoots) {
    this.server = server;
    this.numberOfPlayers = numberOfPlayers;
    this.numberOfBoots = numberOfBoots;
    playerList = new ArrayList<>();
    addPlayers();
  }

  /**
   * This method adds players to created game.
   */
  private void addPlayers() {
//    System.out.println("Player = " + numberOfPlayers);
//    System.out.println("Boots = " + numberOfBoots);
    System.out.println("Size of list = " + playerList.size());
    System.out.println("Adding players.");
    try {
      while (true) {
        System.out.println(playerList.size() == numberOfPlayers);
        if (playerList.size() == numberOfPlayers) {
          break;
        }
        System.out.println("Creating player");
          playerList.add(new Player(this, server.getSocket().accept()));

      }
      for (int i = 0; i < playerList.size(); i++) {
        playerList.get(i).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        server.getSocket().close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("I cannot close socket.");
      }
    }

  }

}
