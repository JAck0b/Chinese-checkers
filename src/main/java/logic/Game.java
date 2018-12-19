package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game {
  // TODO  Na koniec gry, gdy się skończy trzeba wywołać metodę listener.
  /**
   * Main server.
   */
  private Server server;
  /**
   * Number of players in created game.
   */
  int numberOfPlayers;  //klienci
  /**
   * Number of boots in created game.
   */
  int numberOfBots;
  /**
   * List of players in this game.
   */
  private List<Player> playerList;

  private volatile boolean allConnected = false;

  synchronized boolean isAllConnected() {
    return allConnected;
  }

  volatile int current_player = 2;
  // boolean longhop;
  boolean game_run = true;
  CheckMove checkMove;
  Bot bot;
  int[][] fields;
  int[] number_of_skip_by_id = {0, 0, 0, 0, 0, 0};
  boolean[] still_in_game = {true, true, true, true, true, true};
  volatile int totalsteps = 0;
  ArrayList<int[][]> bases;

  public Game(Server server, int numberOfPlayers, int numberOfBoots, boolean longhop) {
    this.server = server;
    this.numberOfPlayers = numberOfPlayers;
    this.numberOfBots = numberOfBoots;
    add_bases();
    playerList = new ArrayList<>();
    NormalBoard nb = new NormalBoard(numberOfPlayers + numberOfBots);
    checkMove = new CheckMove(longhop);
    bot = Bot.getInstance();
//    bot = new Bot(nb.fields, longhop);
    bot.setFields(nb.fields);
    bot.setLonghop(longhop);
    this.fields = nb.fields;
    checkMove.setFields(fields);
    addPlayers();

    List<BotThread> botThreadList = new ArrayList<>();
    for (int i = 0; i < numberOfBoots; i++) {
      BotThread botThread = new BotThread(this, id_by_step(i + numberOfPlayers, numberOfPlayers + numberOfBots), i + numberOfPlayers );
      botThreadList.add(botThread);
      botThread.setDaemon(true);
      botThread.start();

    }
  }

  private int id_by_step(int steps, int number_of_all_players) {

    //rusza botami w kolejności zegara
    int id = 0;
    switch (number_of_all_players) {
      case (6): {
        id = steps + 2;
        break;
      }
      case (5): {
        if (steps == 4)
          id = 7;
        else
          id = steps + 2;
        break;
      }
      case (4): {
        if (steps == 0)
          id = 2;
        else if (steps == 3)
          id = 7;
        else
          id = steps + 3;
      }
      break;
      case (3): {
        if (steps == 0)
          id = 2;
        else if (steps == 1)
          id = 5;
        else
          id = 7;
        break;
      }
      case (2): {
        if (steps == 0)
          id = 2;
        else
          id = 5;
      }
    }
    return id;
  }

  String arrayToString(int[][] tab) {
    StringBuilder result = new StringBuilder();
    for (int[] aTab : tab) {
      for (int j = 0; j < tab.length; j++) {
        result.append(aTab[j]).append(" ");
      }
    }
    return result.toString();
  }

  private void add_bases() {
    bases = new ArrayList<>();
    int[][] base2 = new int[][]{ //pionki z nr2 dążą do ... i symetrycznie 7 do x=y y=x
      {4, 0}, {4, 1}, {5, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 3}, {4, 3}, {6, 3}, {5, 3}
    };
    bases.add(base2);

    int[][] base3 = new int[][]{   //symetrai x=y y=x dla 6
      {12, 4}, {11, 4}, {12, 5}, {11, 5}, {10, 4}, {12, 6}, {9, 4}, {12, 7}, {10, 5}, {11, 6}
    };
    bases.add(base3);

    int[][] base4 = new int[][]{   //symetrai x=y y=x dla 5
      {16, 12}, {15, 12}, {15, 11}, {14, 12}, {14, 11}, {14, 10}, {13, 12}, {13, 9}, {13, 11}, {13, 10}
    };
    bases.add(base4);
  }

  private boolean all_in_base(int id) {
    int checkX = 0, checkY = 0;
    for (int i = 0; i < 10; i++) {
      switch (id) {
        case 2: {
          checkX = bases.get(0)[i][0];
          checkY = bases.get(0)[i][1];
          break;
        }
        case 3: {
          checkX = bases.get(1)[i][0];
          checkY = bases.get(1)[i][1];
          break;
        }
        case 4: {
          checkX = bases.get(2)[i][0];
          checkY = bases.get(2)[i][1];
          break;
        }
        case 5: {
          checkX = bases.get(2)[i][1];
          checkY = bases.get(2)[i][0];
          break;
        }
        case 6: {
          checkX = bases.get(1)[i][1];
          checkY = bases.get(1)[i][0];
          break;
        }
        case 7: {
          checkX = bases.get(0)[i][1];
          checkY = bases.get(0)[i][0];
          break;
        }
      }
      if (fields[checkX][checkY] != id)
        return false;

    }
    return true;

  }

  /**
   * This method adds players to created game.
   */
  private void addPlayers() {
    int counter = 0;
    System.out.println("Size of list = " + playerList.size());
    System.out.println("Adding players.");
    try {
      while (true) {
        System.out.println(playerList.size() == numberOfPlayers);
        if (playerList.size() == numberOfPlayers) {
          allConnected = true;
          break;
        }
        System.out.println("Creating player");
        Player player = new Player(this, server.getSocket().accept(), id_by_step(counter, numberOfPlayers + numberOfBots), counter);
        counter++;
        playerList.add(player);
        player.setDaemon(true);
        player.start();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean end_of_game() {  //kończy nawet gdy jeden gracz został tylko (potrzebne do utknięcia)
    boolean onlyoneplayerleft = false;
    for (int i = 0; i < numberOfBots + numberOfPlayers; i++)
      if (still_in_game[i]) {
        if (!onlyoneplayerleft)
          onlyoneplayerleft = true;
        else
          return false;
      }
    return true;
  }

  void send_to_everyone(String s) {
    for (Player aPlayerList : playerList) {
      (aPlayerList.out).println(s);
    }
  }

  void kill() {
    try {
      System.out.println("Server is closed. GAME");
      //send_to_everyone("KILL");
      server.getSocket().close();
      if (logic.Server.stateServer.isRun())
        logic.Server.changeState();
      System.out.println("Kill state = " + logic.Server.stateServer.isRun());
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("I cannot close socket.");
    }
  }

  synchronized void next_player(int id) { // id 2-7
    if (all_in_base(id)) {
      still_in_game[totalsteps % (numberOfPlayers + numberOfBots)] = false; //todo NWM ??????
    }

    if (end_of_game()) {

      send_to_everyone("BOARD");
      send_to_everyone(arrayToString(fields));
      int help = totalsteps / (numberOfPlayers + numberOfBots);
      int help2 = totalsteps % (numberOfPlayers + numberOfBots);
      send_to_everyone("PLAYER " + String.valueOf(help2) + " " + String.valueOf(current_player));
      send_to_everyone("STEPS " + String.valueOf(help));

      try {
        sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      send_to_everyone("KILL");
      kill();
    }
    else {


      totalsteps++;
      int steps = totalsteps % (numberOfPlayers + numberOfBots);

      if (!still_in_game[steps]) {
        next_player((id+1)%(numberOfPlayers+numberOfBots));
      }
      else{
        current_player = id_by_step(steps, numberOfPlayers + numberOfBots);
        send_to_everyone("PLAYER " + String.valueOf(steps) + " " + String.valueOf(current_player));
      }
    }

  }
}
