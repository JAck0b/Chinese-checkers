package logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

//  private static volatile boolean finished;
  private ServerSocket socket;
  public volatile static StateServer stateServer = new Off();

  public static void main(String[] args) {
//    finished = true;
    while (true) {
//      System.out.println(stateServer.isRun());
      if (!stateServer.isRun()) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
//        setFinished(false);
        System.out.println(stateServer.isRun());
        stateServer = stateServer.nextState();
        Server server = new Server();
        server.initial();
      }
    }
  }

  private void initial() {
    try {
      System.out.println("Server is running.");
      int PORT = 8888;
      socket = new ServerSocket(PORT);
      Administrator administrator = new Administrator(this, socket.accept());
      administrator.start();
    } catch (IOException e) {
      System.out.println("ERRROR PORT ZAJETY");
//      e.printStackTrace();
    }
  }

void createGame(int playersNumber, int bootsNumber, boolean longhop) {
  new Game(this, playersNumber, bootsNumber, longhop);
  }

  ServerSocket getSocket() {
    return socket;
  }

  static void changeState() {
    stateServer = stateServer.nextState();
  }

//  static void setFinished(boolean condition) {
//    finished = condition;
//  }


}
