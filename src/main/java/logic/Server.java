package logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

  private static volatile boolean finished;
  private ServerSocket socket;

  public static void main(String[] args) {
    finished = true;
    while (true) {
      if (finished) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        setFinished(false);
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

  static void setFinished(boolean condition) {
    finished = condition;
  }


}
