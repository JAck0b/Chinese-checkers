package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Administrator extends Thread {

  private Server server;
  private Socket socket;

  public Administrator(Server server, Socket socket) {
    this.server = server;
    this.socket = socket;
  }

  public void run() {
    try {
      String input = null;
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println("ADMINISTRATOR");
      System.out.println("Administrator is connected.");
      input = in.readLine();
      System.out.println(input);
      if(input.startsWith("KILL")){
        try {
          System.out.println("Server is closed BY ADMIN");
          server.getSocket().close();
          logic.Server.setFinished(true);
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("I cannot close socket.");
        }
      } else if (input.startsWith("NEW")) {
      int bootsNumber;
      int playersNumber;
      int longhop;
      playersNumber = Integer.parseInt(input.substring(4, 5));
      bootsNumber = Integer.parseInt(input.substring(6, 7));
      longhop = Integer.parseInt(input.substring(8, 9));
      if(longhop == 1)  //longhop == true
        server.createGame(playersNumber, bootsNumber,true);
      else
        server.createGame(playersNumber, bootsNumber,false);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("logic.Administrator is disconnected.");
      }
    }
  }
}
