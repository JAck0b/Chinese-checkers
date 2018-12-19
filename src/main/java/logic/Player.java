package logic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Player extends Thread {
  /**
   * The game in which player is playing.
   */
  private Game game;

  /**
   * logic.Player's socket.
   */
  private Socket socket;

  /**
   * Input into particular player.
   */
  private BufferedReader in;

  /**
   * Output into particular player.
   */
  PrintWriter out;

  /**
   * ID of particular player.
   */
  private int id; // 2 -7


  Player(Game game, Socket socket, int id, int id2_stepeps) {
    this.game = game;
    this.socket = socket;
    this.id = id;
    int id2_stepeps1 = id2_stepeps;
  }

  @Override
  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    out.println("NORMAL BOARD");

    while (!game.isAllConnected()) {
      try {
        sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    out.println("BOARD");
    out.println(game.arrayToString(game.fields));
    int help = game.totalsteps / (game.numberOfPlayers + game.numberOfBots);
    int help2 = game.totalsteps % (game.numberOfPlayers + game.numberOfBots);
    out.println("PLAYER " + String.valueOf(help2) + " " + String.valueOf(game.current_player));
    out.println("STEPS " + String.valueOf(help) + "  5");
    out.println("YOURID " + id);

    String input;
    //String output;
    int firstX = 0, firstY = 0;
    int[][] possible_move = null;
    String[] parts;
    boolean first_already_pick = false; //wybór początkowej pozycji z której skaczemy
    try {
      while (game.game_run) {
        input = in.readLine();

        if (input != null) {
          parts = input.split(" ");
          if (input.startsWith("KILL")) {
            System.out.println("Server is closed BY USER");
            game.send_to_everyone("KILL");
            game.game_run = false;
            game.kill();
            break;
          } else if (id == game.current_player && input.substring(0, 4).equals("SKIP")) {
            System.out.println("SKIP");
            game.next_player(id);
          } else if (id == game.current_player && input.substring(0, 3).equals("COR")) {
            if (!first_already_pick) {
              while (game.fields[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] != id) { //dopóki nie wybierze swojego pionka
                input = in.readLine();
                parts = input.split(" ");
              }
              firstX = Integer.parseInt(parts[1]);
              firstY = Integer.parseInt(parts[2]);
              game.checkMove.setXY(firstX, firstY);
              possible_move = game.checkMove.getPossible_move_array();
              out.println("POS");//wysłam possible move
              out.println(game.arrayToString(possible_move));
              first_already_pick = true;

            } else {
              int x = Integer.parseInt(parts[1]);
              int y = Integer.parseInt(parts[2]);
              if (possible_move[x][y] == 1 && !(firstX == x && firstY == y)) { //czy możliwy ruch na pole wybrane przez kllienta
                ArrayList<Integer> path;
                path = game.checkMove.getPath(x, y);
                game.fields[path.get(0)][path.get(1)] = id * 100; //pkt docelowy
                for (int i = 2; i < path.size(); i = i + 2) //ścieżka
                  game.fields[path.get(i)][path.get(i + 1)] = id * 10;

                //todo po co help
                help = game.totalsteps / (game.numberOfPlayers + game.numberOfBots);
                help2 = game.totalsteps % (game.numberOfPlayers + game.numberOfBots);
                game.send_to_everyone("BOARD");
                game.send_to_everyone(game.arrayToString(game.fields));
                game.send_to_everyone("PLAYER " + String.valueOf(help2) + " " + String.valueOf(game.current_player));
                game.send_to_everyone("STEPS " + String.valueOf(help));

                sleep(2000);

                game.fields[path.get(0)][path.get(1)] = id; //plansza wraca jako normalne pole
                for (int i = 2; i < path.size(); i = i + 2) {
                  game.fields[path.get(i)][path.get(i + 1)] = 1;
                }


                game.next_player(id); // next move
                first_already_pick = false;

                //pusta z aktualiziowane
                help = game.totalsteps / (game.numberOfPlayers + game.numberOfBots);
                help2 = game.totalsteps % (game.numberOfPlayers + game.numberOfBots);
                game.send_to_everyone("BOARD");
                game.send_to_everyone(game.arrayToString(game.fields));
                game.send_to_everyone("PLAYER " + String.valueOf(help2) + " " + String.valueOf(game.current_player));
                game.send_to_everyone("STEPS " + String.valueOf(help));


              } else if (game.fields[x][y] == id) { //użytkownik wybrał innego swojego pionka
                game.checkMove.setXY(x, y);
                firstX = x;
                firstY = y;
                possible_move = game.checkMove.getPossible_move_array();
                out.println("POS");//wysłam nowe possible move
                out.println(game.arrayToString(possible_move));
              } else
                first_already_pick = false;
            }
          } else if (id != game.current_player) {
            out.println("YO NIE TWOJ RUCH");
          }
        }
      }


    } catch (IOException e) {
      System.out.println("EX");
    } catch (InterruptedException e) {  //do sleepa
      System.out.println("EX");
      System.exit(0);
      e.printStackTrace();
    } finally {
      try {
        System.out.println("Socket is closed. RUN");
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
