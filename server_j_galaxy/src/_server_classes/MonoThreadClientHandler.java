package _server_classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements  Runnable {
    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        this.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream inp = new DataInputStream(clientDialog.getInputStream());
            System.out.println("Created client I/O Streams.");
            while (!clientDialog.isClosed()) {
                System.out.println("Accepting for client channel data, wait...");
                String entry = inp.readUTF();
                if (entry.equalsIgnoreCase("qUiT")) {
                    System.out.println("Client quit!");
                    out.writeUTF("Server closing connection " + entry + " OK");
                    //Thread.sleep(30);
                    break;
                }
                String replyStr = "Server writing to channel " + entry + " OK";
                System.out.println(replyStr);
                out.writeUTF(replyStr);
                out.flush();
            }
            System.out.println("client disconnected");
            inp.close(); out.close();
            clientDialog.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
