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
        Server server = new Server();
        server.initial();
      }
    }
  }

  public Server() {}

  public void initial() {
    try {
      System.out.println("Server is running.");
      socket = new ServerSocket(PORT);
      Administrator administrator = new Administrator(this, socket.accept());
      administrator.run();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("I cannot close socket.");
      }
    }
  }

  public void createGame(int playersNumber, int bootsNumber) {
    game = new Game(this, playersNumber, bootsNumber);
  }

  public ServerSocket getSocket() {
    return socket;
  }

  public static void setFinished(boolean condition) {
    finished = condition;
  }


}
