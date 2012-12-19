    /*
    * To change this template, choose Tools | Templates
    * and open the template in the editor.
    */
    package serverghome;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.net.InetAddress;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
    /**
    *
    * @author anisbenyoub
    */
    public class ServerGHome extends Thread{
        
        public 
            boolean serverOn;
            
        public ServerGHome()
        {
            serverOn=true;
        }
    @Override
        public  void run() 
        {
                ServerSocket socketserver = null  ;
		BufferedReader in;
		PrintWriter out;
		
		try {
		
			socketserver = new ServerSocket(8880);
			System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
                        while(serverOn)
                        {
                            Socket socketduserveur ;
                            socketduserveur = socketserver.accept();
                            ComClient aNewComClient =  new ComClient(socketserver,socketduserveur);
                            aNewComClient.start();
        

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
    }
