package logic;

public class LargeBoard implements Board {
  private int[][] fields;

  public LargeBoard() {
    fields = new int[0][0];
  }

  @Override
  public void changeFields(int previous) {

  }

  @Override
  public void prepareFields(int numberOfPlayers) {

  }

  @Override
  public int[][] getFields() {
    return fields;
  }
}
