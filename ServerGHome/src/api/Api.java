
package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import modele.home.Capteur;
import modele.home.Lieu;
import modele.home.Piece;
import serverghome.ServerGHome;

public class Api extends Thread{
    
    List<Capteur> listeCapteurs = new ArrayList<Capteur>();
    
    protected ServerGHome myServer;
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

            Lieu lieu = null;
            while(lieu==null){
                lieu = myServer.getMonLieu();
            }
            
            while (true) 
            {
                //mise à jour de la liste des capteurs
                //listeCapteurs.clear();
                //for(Piece piece : lieu.getListPieces()){
                //    listeCapteurs.addAll(piece.getMesCapteurs());
                //}
                
                //ecoute de la nouvelle trame entrante

                char[] testChar = new char[28];
                int message_distant = in.read(testChar);
                
                
                String test = new String(testChar);
               
                System.out.println(test);
               
                test = CorrigerTrame(test);
                
                //analyse de cette trame
                //AnalyseTrame(test);
                
                

            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    
    String CorrigerTrame(String trame){
        int index = trame.indexOf("A55A0B");

        if(index!=0){ //remettre la trame dans le bon sens en cas de problème au niveau du proxy
            String newString=trame.substring(index);
            newString+=trame.substring(0, index);
            trame=newString;
        }
        
        return trame;
        
    }
    
    public boolean AnalyseTrame(String trame){
        for(Capteur capteur : listeCapteurs) {
            if(trame.contains(capteur.getId())){
                if(capteur.getMonType()==Capteur.Type.INTERRUPTEUR){
                    AnalyseInterrupteur(capteur.getId(), trame);
                }
                else if(capteur.getMonType()==Capteur.Type.TEMPERATURE){
                    AnalyseTemperature(capteur.getId(), trame);
                }
                else return false;
                return true;
            }
         }
         return false;
    }
    
    private void AnalyseInterrupteur(String id, String trame){
        int buttonNumber = trame.charAt(8);
        switch(buttonNumber){
            case (0) :
                //capteur au repos
                break;
            case (1) :
                //Switch left up
                break;
            case (3) : 
                //Switch left down”
                break;
            case (5) :
                //switch right up
                break;
            case (7) :
                //switch right down
                break;
            default :
                System.out.println("Mauvaise valeur pour la valeur d'un interrupteur");
                break;
        }
    }
    
    private void AnalyseTemperature(String id, String trame){
        
    }
}
