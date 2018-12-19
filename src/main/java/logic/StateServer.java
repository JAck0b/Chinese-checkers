package logic;

public interface StateServer {
  public boolean isRun();
  public StateServer nextState();
}
