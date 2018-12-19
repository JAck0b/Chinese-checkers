package logic;

public class BoardFactory implements BoardFactoryInterface {
  @Override
  public Board createBoart(String boardMode) {
    if (boardMode.equals("normal")) {
      return new NormalBoard();
    } else {
      return new LargeBoard();
    }
  }
}
