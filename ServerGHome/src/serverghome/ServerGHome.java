/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package serverghome;
import Interface.MapLieu;
import Interface.Window;
import XMLParser.Crafter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Click;
import modele.home.Lieu;
import org.dom4j.DocumentException;
import service.Service;
    /**
    *
    * @author anisbenyoub
    */
    public class ServerGHome extends Thread{
        
        protected 
            int nbClick;
            boolean serverOn;
            Map<InetAddress,ComClient> myClientMap;
            Service serviceManager;
            Lieu monLieu;
            
        public ServerGHome()
        {
            nbClick=0;
            myClientMap=new HashMap<InetAddress,ComClient>();
            serverOn=true;
            serviceManager= new Service();
        }

    public Service getServiceManager() {
        return serviceManager;
    }
    @Override
        public  void run() 
        {
        try {
            Crafter aHomeCrafter = new Crafter("../map.xml");
            
            Window uneMap = new Window(aHomeCrafter.getLieu());
            monLieu=aHomeCrafter.getLieu();
            
            uneMap.setVisible(true);
        } catch (DocumentException ex) {
            System.out.println("Problème d'accès au fichier map");
        }
        try {
            serviceManager.addClient("id","1234");
        } catch (Exception ex) {
            Logger.getLogger(ServerGHome.class.getName()).log(Level.SEVERE, null, ex);
        }
                ServerSocket socketserver = null  ;
		BufferedReader in;
		PrintWriter out;
		
		try {
		
			socketserver = new ServerSocket(4500);
			System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
                        while(serverOn)
                        {
                            System.out.println("remise en attente");
                            Socket client ;
                            client = socketserver.accept();
                            System.out.println("trucrecu");
                            if(myClientMap.get(client.getInetAddress())!=null)        
                            {
                                 out = new PrintWriter(client.getOutputStream());
                                 out.println("C0");
                                 out.flush();
                                 System.out.println("Deja repertorie");
                                
                            }
                            else
                            {
                                 System.out.println("Nouveau");
                                 ComClient aNewComClient =  new ComClient(socketserver,client,this);
                                 myClientMap.put(client.getInetAddress(), aNewComClient);
                                 aNewComClient.start();
                            }


                        }
		        
		}catch (IOException e) {
			
			e.printStackTrace();
		}
        try {
            socketserver.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerGHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public synchronized void removeClient(InetAddress address)
    {
        System.out.println("Client supprime");
        System.out.println(myClientMap.size());
        myClientMap.remove(address);
        System.out.println(myClientMap.size());

    }
        public synchronized void sendClick(int buttonID)
    {
        Click aClick = new Click(buttonID);
        try {
            serviceManager.addClick(aClick);
        } catch (Exception ex) {
            Logger.getLogger(ServerGHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Click recu");


    }
        public synchronized int getNbClick()
        {
            return serviceManager.getNbClick();
        }

    public synchronized Lieu getMonLieu() {
        return monLieu;
    }
        
    }