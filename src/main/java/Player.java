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

  /**
   * Input into particular player.
   */
  private BufferedReader in;

  /**
   * Output into particular player.
   */
  private PrintWriter out;

  /**
   * ID of particular player.
   */
  private int id;

  public Player(Game game, Socket socket, int id) {
    this.game = game;
    this.socket = socket;
    this.id = id;
  }

  public void confirm() {
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      out.println("NORMAL BOARD");
      System.out.println(in.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Player #" + id + " connected to game.");
  }

  @Override
  public void run() {
    String input;
    String output;
    try {
      out.println("All players are connected");
      input = in.readLine();
      System.out.println(input);
      output = String.valueOf(Integer.parseInt(input.substring(0, 1)) +1);
      out.println(output);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        System.out.println("Socket is closed. RUN");
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
