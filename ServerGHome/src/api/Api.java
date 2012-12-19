
package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import serverghome.ServerGHome;


public class Api extends Thread{

    protected
             ServerGHome myServer;
    /**
     * @param args
     */
    public Api(ServerGHome server)
    {
        myServer=server;
    }

    @Override
    public void run() {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

        try {

            socket = new Socket("134.214.105.28", 5000);
            System.out.println("Demande de connexion");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                char[] testChar = new char[28];
                int message_distant = in.read(testChar);

                String test = new String(testChar);
                if (test.startsWith("CBE2")) {
                    System.out.println("clic !");
                    myServer.sendClick();
                }
                else if(test.startsWith("B291")){
                    System.out.println("Fenêtre 1 !");
                }
                else if(test.startsWith("B292")) {
                    System.out.println("Fenêtre 2 !");
                }
            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}