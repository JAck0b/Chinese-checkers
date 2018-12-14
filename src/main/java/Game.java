import logic.NormalBoard;

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
  private int numberOfPlayers = 0;
  /**
   * Number of boots in created game.
   */
  private int numberOfBoots = 2;

  /**
   * List of players in this game.
   */
  private List<Player> playerList;

  NormalBoard nb;
  private static int[][] fields;
  private int [] number_of_skip_by_id = {0,0,0,0,0,0};
  private boolean [] still_in_game = {true,true,true,true,true,true};
  private int totalsteps =0;  //number of move in game
  private ArrayList<int[][]> bases;
  private ArrayList <Integer> winners = new ArrayList<>();
  static int number_of_players;

  public Game(Server server, int numberOfPlayers, int numberOfBoots) {
    this.server = server;
    this.numberOfPlayers = numberOfPlayers;
    this.numberOfBoots = numberOfBoots;
    playerList = new ArrayList<>();
    addPlayers();
    nb = new NormalBoard(this.numberOfPlayers);
    fields = nb.fields;
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
  public String fieldsToString(){
    String result = "";
    for (int i = 0; i < fields.length; i++){
      for (int j = 0 ; j < fields.length ; j++){
        result = result + fields [i][j] + " ";
      }
    }
    return result;
  }

  public void setFieldsfromString(int[][] fields,String receive) {
    int index=0, distance;
    for (int i = 0; i < fields.length; i++){
      for (int j = 0 ; j < fields.length ; j++){
        distance = 1 ;
        while(receive.charAt(index + distance) != ' '){
          distance ++;
        }
        fields [i][j] = Integer.parseInt(receive.substring(index,(index + distance)));
        index = index + distance + 1;
      }
    }


  }
}
