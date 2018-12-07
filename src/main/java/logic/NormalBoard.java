package logic;

import java.util.ArrayList;

public class NormalBoard {
  private final int size = 4;
  private int[][] fields;

  NormalBoard(int number_of_players) {
    fields = new int[][]{
      {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {4, 4, 4, 4, 1, 1, 1, 1, 1, 6, 6, 6, 6, 0, 0, 0, 0},
      {0, 4, 4, 4, 1, 1, 1, 1, 1, 1, 6, 6, 6, 0, 0, 0, 0},
      {0, 0, 4, 4, 1, 1, 1, 1, 1, 1, 1, 6, 6, 0, 0, 0, 0},
      {0, 0, 0, 4, 1, 1, 1, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0},
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
      {0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 7, 0, 0, 0},
      {0, 0, 0, 0, 3, 3, 1, 1, 1, 1, 1, 1, 1, 7, 7, 0, 0},
      {0, 0, 0, 0, 3, 3, 3, 1, 1, 1, 1, 1, 1, 7, 7, 7, 0},
      {0, 0, 0, 0, 3, 3, 3, 3, 1, 1, 1, 1, 1, 7, 7, 7, 7},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}
    };
    prepare_fields(number_of_players);
  }
  public void change_fields(int previous, int succesor){
    for (int i = 0; i< fields.length ;i ++)
      for (int j = 0; j< fields.length; j++ )
        if(fields[i][j] == previous)
          fields[i][j] = succesor;
  }
  // TODO do sprawdzenia w zasadach którzy gracze grają
  public void prepare_fields(int number_of_players){
    switch (number_of_players){
      case 2: {
        change_fields(7,1);
        change_fields(6,1);
        change_fields(4,1);
        change_fields(3,1);
      }
      case 3: {
        change_fields(6,1);
        change_fields(4,1);
        change_fields(3,1);
      }
      case 4: {
        change_fields(6,1);
        change_fields(3,1);
      }
      case 5: {
        change_fields(6,1);
      }
    }
  }

  public void printArray() {
    for (int y = 0; y < fields.length; y++) {
      for (int x = 0; x < fields.length; x++) {
        System.out.print(fields[x][y] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    NormalBoard nb = new NormalBoard(6);
    nb.fields[6][6] =9;
    nb.fields[12][11] =9;
    nb.fields[10][10] =9;

    nb.printArray();
    CheckMove checkMove = new CheckMove();
    checkMove.setFields(nb.fields);
    checkMove.setXY(12,14);
    System.out.println("Czy można na : " + checkMove.check_move(4,4));
    checkMove.printArray();
    checkMove.printPath(checkMove.getPath(4,4));

    /*nb.fields[6][6] =1;
    nb.fields[12][11] =1;
    nb.fields[10][10] =1;
    checkMove.printArray();
    checkMove.setXY(12,14);
    System.out.println("Czy można na : " + checkMove.check_move(4,4));
    checkMove.printArray();*/
  }
}