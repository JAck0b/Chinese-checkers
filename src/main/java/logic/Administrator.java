package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Administrator extends Thread {

  private Server server;
  private Socket socket;

  Administrator(Server server, Socket socket) {
    this.server = server;
    this.socket = socket;
  }

  public void run() {
    try {
      String input;
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println("ADMINISTRATOR");
      System.out.println("Administrator is connected.");
      input = in.readLine();
      System.out.println(input);
      if(input.startsWith("KILL")){
        try {
          logic.Server.changeState();
          System.out.println(Server.stateServer.isRun());
          System.out.println("Server is closed BY ADMIN");
          server.getSocket().close();
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("I cannot close socket.");
        }
      } else if (input.startsWith("NEW")) {
      int botsNumber, playersNumber, longhop, maxhop;
      playersNumber = Integer.parseInt(input.substring(4, 5));
      botsNumber = Integer.parseInt(input.substring(6, 7));
      longhop = Integer.parseInt(input.substring(8, 9));
      maxhop = Integer.parseInt(input.substring(10,11));
      if(longhop == 1)  //longhop == true
        server.createGame(playersNumber, botsNumber,true, maxhop,"normal");
      else
        server.createGame(playersNumber, botsNumber,false,maxhop, "normal");
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
