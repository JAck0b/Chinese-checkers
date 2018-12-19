package logic;

public interface Board {

  void changeFields(int previous);
  void prepareFields(int numberOfPlayers);
  int[][] getFields();
}
