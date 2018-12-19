package logic;

import java.util.ArrayList;

public class BotThread extends Thread {
  private int id; // 2 - 7
  private int id2_stepeps;  // 0 -5
  private Game game;
  BotThread(Game game, int id, int id2_stepeps){
    this.game = game;
    this.id = id;
    this.id2_stepeps = id2_stepeps;
  }

  @Override
  public void run() {
    while (game.game_run ){
      if(id == game.current_player){
//        System.out.println("RUCH BOTA: " + id + " id2 " + id2_stepeps);
        if(!game.still_in_game[id2_stepeps]){
//          System.out.println("ZWEIKSZAM ID W BOT DLA ID : " + id + " " + id2_stepeps);
          game.next_player(id);
          break;
        }
        try {
          sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        game.bot.setId(id);
        game.bot.setBases(game.bases);
        int help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
        int help2 = game.totalsteps % (game. numberOfPlayers + game.numberOfBots);
        game.bot.setSteps_in_game(help);
        game.bot.calculate_best_move();
        ArrayList<Integer> path;
        if(!game.bot.isBot_skip_move()) {
          path = game.bot.getPath_best_move();

          game.fields[path.get(0)][path.get(1)] = id *100; //pkt docelowy
          for(int i=2; i< path.size();i=i+2) //ścieżka
            game.fields[path.get(i)][path.get(i+1)] = id*10;

          help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
          help2 = game.totalsteps % (game. numberOfPlayers + game.numberOfBots);
          game.send_to_everyone("BOARD");
          game.send_to_everyone(game.arrayToString(game.fields));
          game.send_to_everyone("PLAYER " + String.valueOf(help2) + " " + String.valueOf(game.current_player));
          game.send_to_everyone("STEPS " + String.valueOf(help));

          try {
            sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          //plansza wraca jako normalne pole
          game.fields[path.get(0)][path.get(1)] = id;
          for(int i=2; i< path.size();i=i+2){
            game.fields[path.get(i)][path.get(i+1)] = 1;
          }
//          System.out.println("ZWIEKSZAM W SREODKU !!!!!!!!!!!!!!!!!!!!!!!!!!!");
          game.next_player(id);

          help = game.totalsteps/(game.numberOfPlayers + game.numberOfBots);
          help2 = game.totalsteps % (game. numberOfPlayers + game.numberOfBots);
          game.send_to_everyone("BOARD");
          game.send_to_everyone(game.arrayToString(game.fields));
          game.send_to_everyone("PLAYER " + String.valueOf(help2) + " " + String.valueOf(game.current_player));
          game.send_to_everyone("STEPS " + String.valueOf(help));

        }





        else{
          game.number_of_skip_by_id [id2_stepeps] = game.number_of_skip_by_id [id2_stepeps] + 1;
          // gdy 3 skipy dany plater nie gra
          if(game.number_of_skip_by_id [id2_stepeps] >= 3) {
            game.still_in_game[id2_stepeps] = false;
          }
          game.next_player(id);
        }



      }
    }
  }
}
