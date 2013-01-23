/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Service;

/**
 *
 * @author anisbenyoub
 */
public class ComClient extends Thread{
    
    public int threadID;
    boolean myClientConnected;
    ServerSocket myServer;
    Socket myClient;
    private PrintWriter out;
    ServerGHome myHost;
    
    public ComClient(ServerSocket server, Socket client,ServerGHome host)
    {
        myClientConnected=true;
        myServer=server;
        myClient=client;
        myHost= host;
    }
    @Override
    public void run ()
    {
        BufferedReader in;
        try {
            System.out.println("Associated thread launched");
            System.out.println("Un client s'est connecté");
            out = new PrintWriter(myClient.getOutputStream());   
            System.out.println("Attente de l'authentification ");
            in = new BufferedReader (new InputStreamReader (myClient.getInputStream()));
            String contenu = in.readLine(); 
            Service service = new Service();
            System.out.println(contenu.split(" ")[1]+" "+contenu.split(" ")[2]);
            boolean isOk=false;
            try {
                isOk=service.testClient(contenu.split(" ")[1],contenu.split(" ")[2]);
            } catch (Exception ex) {
                Logger.getLogger(ComClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!isOk)
            {
                out.println("C0");
                out.flush(); 
                myClient.close();
                myHost.removeClient(myClient.getInetAddress());
                return;
            }
            out.println("C1");
            out.flush();    
            while(myClientConnected)
            {   
                in = new BufferedReader (new InputStreamReader (myClient.getInputStream()));
                String message = in.readLine();            
                System.out.println("message recu"+message);
                System.out.println(message.compareTo("D"));
                switch (message.charAt(0))
                {
                    // We are in the case of a disconnection
                    case 'D':
                        myClientConnected=false;
                        myClient.close();
                        myHost.removeClient(myClient.getInetAddress());
                        break;
                    // The terminal asks for the nb of clicks been done 
                    case 'A':
                        out.println("A"+myHost.getNbClick());
                        out.flush();
                        break;
                    // THe termianl asks for the room temprature
                    case 'T':
                        out.println("T"+myHost.getNbClick());
                        out.flush();
                        break;    
                    default:
                        break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ComClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
