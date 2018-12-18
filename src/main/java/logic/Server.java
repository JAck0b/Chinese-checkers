package logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

  private static Game game;
  private static volatile boolean finished;
  private static final int PORT = 8888;
  private static ServerSocket socket;

  public static void main(String[] args) {
    finished = true;
    while (true) {
      if (finished) {
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
        setFinished(false);
        Server server = new Server();
        server.initial();
      }
    }
  }

  public Server() {}

  public void initial() {
    try {
      System.out.println("logic.Server is running.");
      socket = new ServerSocket(PORT);
      Administrator administrator = new Administrator(this, socket.accept());
      administrator.start();
    } catch (IOException e) {
      System.out.println("Bład IOException");
      e.printStackTrace();
    } finally {
//      try {
//        System.out.println("Socket is closed. logic.Server");
//        socket.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//        System.out.println("I cannot close socket.");
//      }
    }
  }
//todo lonhop
  public void createGame(int playersNumber, int bootsNumber, boolean longhop) {
    game = new Game(this, playersNumber, bootsNumber,longhop);
  }

  public ServerSocket getSocket() {
    return socket;
  }

  public static void setFinished(boolean condition) {
    finished = condition;
  }


}
