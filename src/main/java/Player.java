import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread{
  /**
   * The game in which player is playing.
   */
  private Game game;

  /**
   * Player's socket.
   */
  private Socket socket;


  public Player(Game game, Socket socket) {
    this.game = game;
    this.socket = socket;
  }

  @Override
  public void run() {
    String input;
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println("PLAYER");
      System.out.println("Player connected to game");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
