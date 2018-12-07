package logic;

public class Bot {
  int [][] fields;
  PossibleMove [][] possible_move;
  int id; //ID of player
  Bot(int [][] fields,PossibleMove [][] possible_move, int id){
    this.fields = fields;
    this.possible_move = possible_move;
    this.id = id;
  }
  //todo znalesc pole do ktorego dążą pionki
  //todo stworzyc zmienne lub osobna klase (który pionek, gdzie się ma ruszyć, ile pól do zyskuje do mety [aktualna odleglosc - odleglosc ppo ruch])
  //todo wykorzystac powyzsze do znalezienia najwiekszej wartosci pamietajaca maksymalne wartosci
}
