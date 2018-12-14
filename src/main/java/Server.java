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
        setFinished(false);
        Server server = new Server();
        server.initial();
      }
    }
//    Game game = new Game(null,0,0);
//    String help = game.fieldsToString();
//    System.out.println(help);
//    int[][] f = new int[][]{
//      {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//      {0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//      {0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//      {0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//      {5, 5, 5, 5, 1, 1, 1, 1, 1, 3, 3, 3, 3, 0, 0, 0, 0},
//      {0, 5, 5, 5, 1, 1, 1, 1, 1, 1, 3, 3, 3, 0, 0, 0, 0},
//      {0, 0, 5, 5, 1, 1, 1, 1, 1, 1, 1, 3, 3, 0, 0, 0, 0},
//      {0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0},
//      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2222, 1, 0, 0, 0, 0},
//      {0, 0, 0, 0, 6, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 0},
//      {0, 0, 0, 0, 6, 6, 1, 1, 1, 1, 1, 1, 1, 2, 2, 0, 0},
//      {0, 0, 0, 0, 6, 6, 6, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0},
//      {0, 0, 0, 0, 6, 6, 6, 6, 1, 1, 1, 1, 1, 2, 2, 2, 2},
//      {0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0, 0, 0},
//      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0, 0},
//      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0},
//      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4444, 0, 0, 0, 0}
//    };
//    for (int y = 0; y < f.length; y++) {
//      for (int x = 0; x < f.length; x++)
//        System.out.print(f[x][y] + " ");
//      System.out.println();
//    }
//    System.out.println();
//    game.setFieldsfromString(f,help);
//    for (int y = 0; y < f.length; y++) {
//      for (int x = 0; x < f.length; x++) {
//        System.out.print(f[x][y] + " ");
//      }
//      System.out.println();
//    }

  }

  public Server() {}

  public void initial() {
    try {
      System.out.println("Server is running.");
      socket = new ServerSocket(PORT);
      Administrator administrator = new Administrator(this, socket.accept());
      administrator.start();
    } catch (IOException e) {
      System.out.println("Bład IOException");
      e.printStackTrace();
    } finally {
//      try {
//        System.out.println("Socket is closed. Server");
//        socket.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//        System.out.println("I cannot close socket.");
//      }
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
