package logic;

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
    for(int[] x: fields) {
      for (int y: x) {
        System.out.print(y + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    NormalBoard nb = new NormalBoard(4);
    nb.printArray();
  }
}