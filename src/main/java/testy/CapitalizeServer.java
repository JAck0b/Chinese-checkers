package testy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CapitalizeServer extends Thread {
  private Socket socket;
  private int clientNumber;

  CapitalizeServer(Socket socket, int clientNumber) {
    this.socket = socket;
    this.clientNumber = clientNumber;
  }

  public void run() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println("Hello, you are client #" + clientNumber + ".");
      System.out.println("Hello, you are client #" + clientNumber + ".");
      while (true) {
        String input = in.readLine();
        if (input == null || input.equals(".")) {
          break;
        }
        out.println(input.toUpperCase());
      }
    } catch (IOException ie) {
      System.out.println("Couldn't close a socket, what's going on?");
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        System.out.println("Couldn't close a socket, what's going on?");
      }
      System.out.println("Connection with client# " + clientNumber + " closed");

    }
  }

}
