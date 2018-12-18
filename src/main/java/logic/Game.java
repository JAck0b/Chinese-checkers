package logic;

import logic.CheckMove;
import logic.NormalBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
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

  public synchronized boolean isAllConnected() {
    return allConnected;
  }

  public int current_player = 2;
 // boolean longhop;
  boolean still_game = true;
  NormalBoard nb;
  CheckMove checkMove;
  Bot bot;
  int[][] fields;
  int [] number_of_skip_by_id = {0,0,0,0,0,0};
  boolean [] still_in_game = {true,true,true,true,true,true};
  boolean [] play_bot = {true,true,true,true,true,true};
  int totalsteps =0;
  ArrayList<int[][]> bases;
 // ArrayList <Integer> winners = new ArrayList<>();

  public Game(Server server, int numberOfPlayers, int numberOfBoots, boolean longhop) {
    this.server = server;
    this.numberOfPlayers = numberOfPlayers;
    this.numberOfBots = numberOfBoots;
    playerList = new ArrayList<>();
    nb = new NormalBoard(numberOfPlayers + numberOfBots);
    //this.longhop = longhop;
    System.out.println("HOOOP = " + longhop);
    checkMove = new CheckMove(longhop);
    bot = new Bot(nb.fields,longhop);
//    nb.printArray();
    this.fields = nb.fields;
    checkMove.setFields(fields);
    addPlayers();

    add_bases();
    for(int i = 0; i < numberOfPlayers; i++ )
      play_bot[i] = false;
  }
  public int id_by_step(int steps,int  number_of_all_players){

    //rusza botami w kolejności zegara
    int id = 0;
    switch (number_of_all_players){
      case (6):{
        id=steps +2;
        break;
      }
      case (5):{
        if(steps ==4)
          id = 7;
        else
          id = steps +2;
        break;
      }
      case (4):{
        if(steps ==0)
          id = 2;
        else if(steps == 3)
          id = 7;
        else
          id = steps +3;
      }
      break;
      case (3): {
        if(steps ==0)
          id = 2;
        else if(steps == 1)
          id = 5;
        else
          id = 7;
        break;
      }
      case (2): {
        if(steps ==0)
          id = 2;
        else
          id = 5;
      }
    }
    return id;
  }
  public String arrayToString(int [][] tab){
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < tab.length; i++){
      for (int j = 0 ; j < tab.length ; j++){
        result.append(tab[i][j]).append(" ");
      }
    }
    return result.toString();
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

  public synchronized boolean legalMove(int x, int y, int check_x, int check_y, int player_id) {
    if(player_id != current_player)
      return false;
    else {
      checkMove.setFields(fields);
      checkMove.setXY(x,y);
      if(checkMove.check_move(check_x,check_y))
        return true;
      return false;
    }
  }

  private void add_bases(){
    bases = new ArrayList<>();
    int [][] base2 = new int[][]{ //pionki z nr2 dążą do ... i symetrycznie 7 do x=y y=x
      {4,0},{4,1},{5,1},{4,2},{5,2},{6,2},{7,3},{4,3},{6,3},{5,3}
    };
    bases.add(base2);

    int [][] base3 = new int[][]{   //symetrai x=y y=x dla 6
      {12,4},{11,4},{12,5},{11,5},{10,4},{12,6},{9,4},{12,7},{10,5},{11,6}
    };
    bases.add(base3);

    int [][] base4 = new int[][]{   //symetrai x=y y=x dla 5
      {16,12},{15,12},{15,11},{14,12},{14,11},{14,10},{13,12},{13,9},{13,11},{13,10}
    };
    bases.add(base4);
  }
  boolean all_in_base(int id){
    int checkX =0, checkY =0;
    for(int i=0; i < 10 ; i++ ){
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
        Player player = new Player(this, server.getSocket().accept(), id_by_step(counter,numberOfPlayers+numberOfBots));
        counter++;
        playerList.add(player);
        player.setDaemon(true);
        player.start();
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
//      try {
//        System.out.println("logic.Server is closed. GAME");
////        server.getSocket().close();
////        logic.Server.setFinished(true);
//      } catch (IOException e) {
//        e.printStackTrace();
//        System.out.println("I cannot close socket.");
//      }
    }
  }
  public boolean end_of_game (){
    for (int i = 0; i< 6 ;i++ )
      if(still_in_game[i])
        return false;
    return true;
  }
  public void send_to_everyone(String s){
    for(int i = 0; i< playerList.size(); i++) {
      //out = playerList.get(i).out;
//      System.out.println("WYSYŁANIE W SEND TO EVER : " + s);
      (playerList.get(i).out).println(s);
    }
  }
  public void time_for_bot(int id){
//    System.out.println("BOT PEZED");
    bot.setId(id);
    bot.setBases(bases);
//    System.out.println("BOT PO USTALENIU ID");
    int help = totalsteps/(numberOfPlayers+numberOfBots);
    bot.setSteps_in_game(help);
    bot.calculate_best_move();
    //czy ruch pomijany
    ArrayList<Integer> path;
    if(!bot.isBot_skip_move()) {
      path = bot.getPath_best_move();

      fields[path.get(0)][path.get(1)] = id *100; //pkt docelowy
      for(int i=2; i< path.size();i=i+2) //ścieżka
        fields[path.get(i)][path.get(i+1)] = id*10;

      send_to_everyone("BOARD");
      send_to_everyone(arrayToString(fields));
      send_to_everyone("PLAYER " + String.valueOf(current_player));
      send_to_everyone("STEPS " + String.valueOf(help));


      try {
        sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      //plansza wraca jako normalne pole
      fields[path.get(0)][path.get(1)] = id;
      for(int i=2; i< path.size();i=i+2){
        fields[path.get(i)][path.get(i+1)] = 1;
      }
    }
    else{
      number_of_skip_by_id [id-2] = number_of_skip_by_id [id-2] + 1;
      // gdy 3 skipy dany plater nie gra
      if(number_of_skip_by_id [id-2] >= 3) {
        still_in_game[id-2] = false;
      }
    }


  }
  public synchronized void next_player(int id){
    if(all_in_base(id))
      still_in_game[id-2] = false;

    if(end_of_game()){
      System.out.println("KONIEC");
      //todo koniec
      try {
      System.out.println("Server is closed. GAME");
      server.getSocket().close();
      logic.Server.setFinished(true);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("I cannot close socket.");
      }
    }

    int new_id,steps;

    totalsteps++;
    steps = totalsteps % (numberOfPlayers + numberOfBots);
    while(!still_in_game[steps] || play_bot[steps] ){
      if(play_bot[steps]) {
        current_player = id_by_step(steps, numberOfPlayers + numberOfBots);
        time_for_bot(id_by_step(steps  , numberOfPlayers + numberOfBots));
      }
      totalsteps++;
      steps = totalsteps % (numberOfPlayers + numberOfBots);
    }
    new_id = id_by_step(steps, numberOfPlayers + numberOfBots);

//todo invalid, winnersy wysyłane
    // todo 2 klientow
    //todo wlasciwe zakonczenie

    //System.out.println("UPADTE OBECNY " + current_player + " " + new_id);
    current_player = new_id;
    send_to_everyone("BOARD");
    send_to_everyone(arrayToString(fields));
    send_to_everyone("PLAYER " + String.valueOf(current_player));
    int help = totalsteps/(numberOfPlayers+numberOfBots);
    send_to_everyone("STEPS " + String.valueOf(help));
  }
}
