import _server_classes.MultiThreadedServer;

public class _main_server_loop {

    public static void main(String[] args){
        MultiThreadedServer.MultiThreadServerStart();
    }


    private static void showMsg(String message){
        System.out.println(message);
    }
}

