package logic;

public class Off implements StateServer {

  @Override
  public boolean isRun() {
    return false;
  }

  @Override
  public StateServer nextState() {
    return new Run();
  }
}
