package testy;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class Main {
//  public static List<User> users;
  public static void main(String[] args) throws Exception {
//    String string = "kuba 1";
//    System.out.println(string.substring(3, 6));
    System.out.println("logic.Server is running");
//    users = new LinkedList<>();
    int clientNumber = 0;
    ServerSocket listener = new ServerSocket(8888);
    try {
      while(true) {
        CapitalizeServer capilalizeServer = new CapitalizeServer(listener.accept(), clientNumber++);
        capilalizeServer.start();
      }

    } finally {
        listener.close();
    }
  }
}
