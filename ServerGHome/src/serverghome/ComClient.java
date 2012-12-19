/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anisbenyoub
 */
public class ComClient extends Thread{
    
    public int threadID;
    ServerSocket myServer;
    Socket myClient;
    private PrintWriter out;
    
    public ComClient(ServerSocket server, Socket client)
    {
        myServer=server;
        myClient=client;
    }
    @Override
    public void run ()
    {
        System.out.println("Associated thread launched");
                            System.out.println("Un client s'est connecté");
        try {
            out = new PrintWriter(myClient.getOutputStream());
        } catch (IOException ex) {
        }
                            out.println("Vous êtes connecté zéro !");
                            out.flush();
    }
}
