package logic;


import java.util.ArrayList;

public class PossibleMove {
  public boolean possible;
  private int previousX, previousY;
  public int number_of_step; //number of hop to get to this possition (only for hop!!!)
  PossibleMove(){
    ArrayList<Integer> path = new ArrayList<>();
  }

//  public ArrayList<Integer> getPath() {
//    return path;
//  }

  void setPreviousX(int previousX) {
    this.previousX = previousX;
  }

  void setPreviousY(int previousY) {
    this.previousY = previousY;
  }

  int getPreviousY() {
    return previousY;
  }

  int getPreviousX() {
    return previousX;
  }


  void setPossible(boolean possible) {
    this.possible = possible;
  }
}
