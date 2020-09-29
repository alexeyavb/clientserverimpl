package _server_classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {
    static ExecutorService executeIt = Executors.newFixedThreadPool(24);
    public static void MultiThreadServerStart(){
        try{
            ServerSocket server  = new ServerSocket(55399);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Server socket created and already wait accept connections...");
            while(!server.isClosed()){
                if (br.ready()){
                    System.out.println("Server found messages in channel, let`s look at them");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("qUiT")){
                        System.out.println("QUIT command received, shutting down...");
                        server.close();
                        server = null;
                        break;
                    }
                }
                Socket client = null;
                try {
                    client = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                executeIt.execute(new MonoThreadClientHandler(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
