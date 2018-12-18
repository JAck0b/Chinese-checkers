package logic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Player extends Thread{
  /**
   * The game in which player is playing.
   */
  private Game game;

  /**
   * logic.Player's socket.
   */
  Socket socket;

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
  private int id;


  public Player(Game game, Socket socket, int id) {
    this.game = game;
    this.socket = socket;
    this.id = id;
  }

  public void confirm() {
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      out.println("NORMAL BOARD");
      System.out.println(in.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("logic.Player #" + id + " connected to game.");
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
    System.out.println("NORMALBOARD " + id);
    while (!game.isAllConnected()) {
//      try {
//        in.readLine();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
    }
    System.out.println("Jestem za");
//    yield();
    System.out.println("BOARD " + id);
    out.println("BOARD");
    System.out.println("Jestem za");
    out.println(game.arrayToString(game.fields));
    out.println("PLAYER " + String.valueOf(game.current_player));
//    game.send_to_everyone("PLAY " + String.valueOf(game.current_player));
    int help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
    out.println("STEPS " + String.valueOf(help));
//    game.send_to_everyone("STEPS " + String.valueOf(help));
    String input;
    //String output;
    int firstX=0,firstY=0;
    int [][] possible_move = null;
    boolean first_already_pick = false; //wybór początkowej pozycji z której skaczemy
    try {
      while (game.still_game){
        input = in.readLine();
        String [] parts = input .split(" ");
//        System.out.println("OTRZYMUJE " + input);
//        System.out.println("ODBEIRAM DLA : " + id);
//        System.out.println("CURRENT: " +game.current_player );
        if(input.substring(0, 4).equals("SKIP")){
          // game.number_of_skip_by_id[] todo czy wywalamy użytkownika po 3 skipach?
          game.next_player(id);
//          out.println("BOARD");
//          out.println(game.arrayToString(game.fields));
//          out.println(game.current_player);
//          out.println(game.totalsteps/(game.numberOfPlayers + game.numberOfBots));
        }
        if(id == game.current_player && input.substring(0, 3).equals("COR")){

          //game.nb.printArray();
          if(!first_already_pick) {
           // System.out.println("PRZED =  " + game.fields[Integer.parseInt(input.substring(4,5))][Integer.parseInt(input.substring(6,7))]);
            //COR x,y - wiadomosc od playera
            while (game.fields[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] != id ) { //dopóki nie wybierze swojego pionka
              //System.out.println("ID W: " + game.fields[Integer.parseInt(input.substring(4,5))][Integer.parseInt(input.substring(6,7))]);
              ;
              input = in.readLine();
              parts = input .split(" ");
//              System.out.println("odbieram w srdku: " + input);
            }
//            System.out.println("MAM PIERWSZE");
            firstX = Integer.parseInt(parts[1]);
            firstY = Integer.parseInt(parts[2]);
            game.checkMove.setXY(firstX,firstY);
            possible_move = game.checkMove.getPossible_move_array();
            out.println("POS");//wysłam possible move
            out.println(game.arrayToString(possible_move));
            first_already_pick = true;

          } else {
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            if(possible_move [x][y] == 1 && !(firstX == x && firstY == y)){ //czy możliwy ruch na pole wybrane przez kllienta
              ArrayList<Integer> path;
              path = game.checkMove.getPath(x,y);
              //game.checkMove.printPath(path);

              game.fields[path.get(0)][path.get(1)] = id *100; //pkt docelowy
              for(int i=2; i< path.size();i=i+2) //ścieżka
                game.fields[path.get(i)][path.get(i+1)] = id*10;

//              System.out.println("WYSYLANIE");

              game.send_to_everyone("BOARD");
              game.send_to_everyone(game.arrayToString(game.fields));
              game.send_to_everyone("PLAYER " + String.valueOf(game.current_player));
              //help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
              game.send_to_everyone("STEPS " + String.valueOf(help));
//              out.println("BOARD");
//              out.println(game.arrayToString(game.fields));
//              out.println(game.current_player);
//              int help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
//              out.println(help);

              sleep(2000);

              //plansza wraca jako normalne pole
              game.fields[path.get(0)][path.get(1)] = id;
              for(int i=2; i< path.size();i=i+2){
                game.fields[path.get(i)][path.get(i+1)] = 1;
              }

              // next move
              game.next_player(id);
              first_already_pick = false;

            }
            else if (game.fields[x][y] == id){ //użytkownik wybrał innego swojego pionka
              game.checkMove.setXY(x,y);
              firstX = x;
              firstY = y;
              possible_move = game.checkMove.getPossible_move_array();
              out.println("POS");//wysłam nowe possible move
              out.println(game.arrayToString(possible_move));
            }else
              first_already_pick = false;
          }
        }
      }


//      System.out.println(input);
//      output = String.valueOf(Integer.parseInt(input.substring(0, 1)) +1);
//      out.println(output);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {  //do sleepa
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
