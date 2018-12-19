package logic;

public class Run implements StateServer {

  @Override
  public boolean isRun() {
    return true;
  }

  @Override
  public StateServer nextState() {
    return new Off();
  }
}
