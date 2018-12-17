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
  private PrintWriter out;

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
    String input;
    //String output;
    int firstX=0,firstY=0;
    int [][] possible_move = null;
    boolean first_already_pick = false; //wybór początkowej pozycji z której skaczemy
    try {
      while (game.still_game){
        input = in.readLine();
        if(input.substring(0, 4).equals("SKIP")){
          game.next_player(id);
//          out.println("BOARD");
//          out.println(game.arrayToString(game.fields));
//          out.println(game.current_player);
//          out.println(game.totalsteps/(game.numberOfPlayers + game.numberOfBots));
        }
        if(id == game.current_player && input.substring(0, 3).equals("COR")){
          if(!first_already_pick) {
            //COR x,y - wiadomosc od playera
            while (game.fields[Integer.parseInt(input.substring(4,5))][Integer.parseInt(input.substring(6,7))] != id ) //dopóki nie wybierze swojego pionka
              input = in.readLine();

            firstX = Integer.parseInt(input.substring(4,5));
            firstY = Integer.parseInt(input.substring(6,7));
            game.checkMove.setXY(firstX,firstY);
            possible_move = game.checkMove.getPossible_move_array();
            out.println("POS");//wysłam possible move
            out.println(game.arrayToString(possible_move));
            first_already_pick = true;

          } else {
            int x = Integer.parseInt(input.substring(4,5));
            int y = Integer.parseInt(input.substring(6,7));
            if(possible_move [x][y] == 1 && !(firstX == x && firstY == y)){ //czy możliwy ruch na pole wybrane przez kllienta
              ArrayList<Integer> path;
              path = game.checkMove.getPath(x,y);

              game.fields[path.get(0)][path.get(1)] = id *100; //pkt docelowy
              for(int i=2; i< path.size();i=i+2) //ścieżka
                game.fields[path.get(i)][path.get(i+1)] = id*10;

              game.send_to_everyone("BOARD");
              game.send_to_everyone(game.arrayToString(game.fields));
              game.send_to_everyone("PLAY" + String.valueOf(game.current_player));
              int help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
              game.send_to_everyone("STEPS" + String.valueOf(help));
//              out.println("BOARD");
//              out.println(game.arrayToString(game.fields));
//              out.println(game.current_player);
//              int help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
//              out.println(help);

              sleep(5000);

              //plansza wraca jako normalne pole
              game.fields[path.get(0)][path.get(1)] = id;
              for(int i=2; i< path.size();i=i+2){
                game.fields[path.get(i)][path.get(i+1)] = 1;
              }

              // next move
              game.next_player(id);


            }
            else if (game.fields[x][y] == id){ //użytkownik wybrał innego swojego pionka
              game.checkMove.setXY(firstX,firstY);
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
//
//GRA BOTÓW
//  game.bot = new Bot(game.fields,true);
//        game.bot.setBases(game.bases);
////        player_by_step();
//
//        System.out.println(id);
//        if(game.still_in_game[id-2]){
//    game.bot.setId(id);
//    game.bot.setSteps_in_game(game.totalsteps/number_of_players);
//    game.bot.calculate_best_move();
//    //czy ruch pomijany
//    if(!game.bot.isBot_skip_move()){
//      ArrayList<Integer> p; //path
//      p = game.bot.getPath_best_move();
//
//
//      //aktualizacja pola na które się ruszył pionkek ustalamy id pionka na tym polu
//      //ustawianie kolorów ścieżki
//      game.fields[p.get(0)][p.get(1)] = id;
//      for(int i=2; i< p.size();i=i+2)
//        game.fields[p.get(i)][p.get(i+1)] = (id)*10;
//
//
//      //refresh
//      //todo refresh
////            for (int i = 0; i < 17; i++) {
////              int k = 0;
////              while (game.fields[k][i] == 0) {
////                k++;
////              }
////              for (int j = 0; j <game.myCircles[i].length; j++) {
////                myCircles[i][j].setColor(fields[k][i]);
////                k++;
////              }
////            }
//      //ścieżka wraca jako normalne pole
//      for(int i=2; i< p.size();i=i+2){
//        game.fields[p.get(i)][p.get(i+1)] = 1;
//      }
//      if(game.all_in_base(id)){
//        game.still_in_game[id-2] = false;
//        game.winners.add(id);
//      }
//    }
//    else {
//      game.number_of_skip_by_id [id-2] = game.number_of_skip_by_id [id-2] + 1;
//      // gdy 3 skipy dany plater nie gra
//      if(game.number_of_skip_by_id [id-2] >= 3) {
//        game.still_in_game[id-2] = false;
//      }
//    }
//  }
//  game.totalsteps++;
//  //todo testy
//
//        for(int i = 0; i < 6; i++)
//    System.out.print(game.number_of_skip_by_id[i] + " ");
//        System.out.println();
//}