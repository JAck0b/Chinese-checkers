package logic;

public class CheckMove {
  int [][] fields;
  int x,y; //pozycja obecna
  boolean [][] possible_move; //gracz może się odwoływać do wszystkich poł
  // mając tą tablicę jesteśmy w stanie stwierdzić poprawność ruchu
  //unikając dużej ilości sprawdzeń   - todo nwm czy najlepsze rozwiązanie ???
  //int tab z wagą ruchu dla botów
  CheckMove(int x, int y){
    this.x=x;
    this.y=y;
  }

  public int[][] getFields() {
    return fields;
  }

  public void setFields(int[][] fields) {
    this.fields = fields;
  }

  public void horizontal_move (){
    if(x-1 >= 0 && fields [x-1][y] == 1)
      possible_move [x-1][y] = true;
    if(x+1 < fields.length && fields [x+1][y] == 1)
      possible_move [x+1][y] = true;

    int i=1;
    while(x-i>=0){  //poszukiwanie pierwszej kuli do przeskoczenia
      //todo dokonczyc rekurencja ?
      if(fields [x-i][y] >= 1)
        i++;
    }
  }
  public void vertical_move (int x, int y, boolean [][] possible_move){

  }
  public void cross_move (int x, int y, boolean [][] possible_move){

  }

  public void preapare_array_possible_move (){
    int size = fields.length;
    possible_move = new boolean[size][size];
    for (int i = 0; i< fields.length ;i ++)
      for (int j = 0; j< fields.length; j++ )
          possible_move[i][j] = false;
  }



  /**
   *
   * @param x position of ball
   * @param y position of ball
   * @return array of possiblie move for current ball
   * 1 : possible move
   * 0 : not possible move
   */
  public boolean [][] getMove_option(int x, int y) {
    int size = fields.length;
    possible_move = new boolean[size][size];
    preapare_array_possible_move();




    return possible_move;
  }
  public boolean check_move (int x, int y, int check_x, int check_y){
    return true;
  }

}
