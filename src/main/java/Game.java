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
  // TODO przemyślec ustawienie start()
  /**
   * This method adds players to created game.
   */
  private void addPlayers() {
    int counter = 0;
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
        Player player = new Player(this, server.getSocket().accept(), counter++);
        playerList.add(player);
        player.confirm();
      }
//      for (int i = 0; i < playerList.size(); i++) {
//        playerList.get(i).confirm();
//      }
      for (int i = 0; i < playerList.size(); i++) {
        playerList.get(i).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
//      try {
//        System.out.println("Server is closed. GAME");
////        server.getSocket().close();
////        Server.setFinished(true);
//      } catch (IOException e) {
//        e.printStackTrace();
//        System.out.println("I cannot close socket.");
//      }
    }

  }

}
