/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package serverghome;
import Interface.MapLieu;
import Interface.Window;
import XMLParser.Crafter;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Click;
import modele.home.Association;
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
            List <Association> mesAssociations= new ArrayList<Association>();
            
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
        try {
            Crafter aHomeCrafter = new Crafter("../map.xml");
            
            Window uneMap = new Window(aHomeCrafter.getLieu());
            monLieu=aHomeCrafter.getLieu();
            uneMap.setVisible(true);
        } catch (DocumentException ex) {
            System.out.println("Problème d'accès au fichier map");
        }
                serviceManager.addClient("id", "1234");
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
        } catch (Exception ex) {
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

        public synchronized int getNbClick()
        {
            return serviceManager.getNbClick();
        }

    public Lieu getMonLieu() {
        return monLieu;
    }
        public String getMap() throws FileNotFoundException, IOException
        {
        BufferedReader reader = new BufferedReader( new FileReader ("../map.xml"));
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    while( ( line = reader.readLine() ) != null ) {
        stringBuilder.append( line );
        stringBuilder.append( ls );
    }

    return stringBuilder.toString();
}
        public void addAssociation(String msg)
        {
            List<String> attributes = new ArrayList<String>();
            for(String s :msg.split(" "))
            {
                attributes.add(s);
            }
            Association newAsso= new Association(attributes);
            mesAssociations.add(newAsso);
        }
        public String getAssoMsg()
        {
            String msg="K";
            msg+=" "+mesAssociations.size();
            for(Association a : mesAssociations)
            {
                msg+=a.toString();
            }
            return msg;
        }
        
        
        public void getFromAPI(String msg)
        {
            String [] msg2=msg.split(" ");
            serviceManager.manageData(msg2[0],msg2[1],msg2[2]);
            for(Association a: mesAssociations)
            {
                if(a.test())
                {
                    a.execute();
                }
            }
        }
    }