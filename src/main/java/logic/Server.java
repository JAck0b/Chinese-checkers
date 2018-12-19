package logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

  private ServerSocket socket;
  volatile static StateServer stateServer = new Off();

  public static void main(String[] args) {
    while (true) {
      if (!stateServer.isRun()) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
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
    }
  }

void createGame(int playersNumber, int bootsNumber, boolean longhop, int maxhop, String boardMode) {
  new Game(this, playersNumber, bootsNumber, longhop, maxhop, boardMode);
  }

  ServerSocket getSocket() {
    return socket;
  }

  static void changeState() {
    stateServer = stateServer.nextState();
  }

}
