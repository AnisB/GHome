
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
    PrintWriter out;
    
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

        try {
            socket = new Socket("134.214.105.28", 5000);
            //socket = new Socket("127.0.0.1", 5000);
            System.out.println("Demande de connexion");
            

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            Lieu lieu = null;
            while(lieu==null){
                System.out.print("");
                lieu = myServer.getMonLieu();
            }
            System.out.println("");
            
            while (true) 
            {
                //mise à jour de la liste des capteurs
                listeCapteurs.clear();
                for(Piece piece : lieu.getListPieces()){
                    listeCapteurs.addAll(piece.getMesCapteurs());
                }
                
                //ecoute de la nouvelle trame entrante

                char[] testChar = new char[28];
                
                
                String test;
                in.read(testChar);
                test =  new String(testChar);
                //int message_distant = in.read(testChar);
                
                test = CorrigerTrame(test);
                
                //System.out.println(test);
                
                //analyse de cette trame
                AnalyseTrame(test);
                
                

            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    
    String CorrigerTrame(String trame){
        int index = trame.indexOf("A55A0B");

        if(trame.length()>=28 && index <=28){        
            if(index>0){ //remettre la trame dans le bon sens en cas de problème au niveau du proxy
                String newString=trame.substring(index);
                newString+=trame.substring(0, index);
                trame=newString;
            }
        }
        return trame;
        
    }
    
    public boolean AnalyseTrame(String trame){
        
        String id = trame.substring(16, 24);
        
        for(Capteur capteur : listeCapteurs) {
            if(id.equals(capteur.getId())){
                System.out.println(capteur.getId());
                if(capteur.getMonType()==Capteur.Type.INTERRUPTEUR){
                    
                    AnalyseInterrupteur(capteur.getId(), trame);
                }
                else if(capteur.getMonType()==Capteur.Type.TEMPERATURE){
                    AnalyseTemperature(capteur.getId(), trame);
                }
                else if(capteur.getMonType()==Capteur.Type.CONTACT){
                    AnalyseContact(capteur.getId(), trame);
                }
                else if(capteur.getMonType()==Capteur.Type.PRESENCE){
                    AnalysePresence(capteur.getId(), trame);
                    Actionner("FF9F1E08", true);
                }
                else return false;
                return true;
            }
         }
         return false;
    }
    
    private void AnalyseInterrupteur(String id, String trame){
        int buttonNumber = trame.charAt(8)-48; //-48 parceque le code ascii de 0 est 48
        
        System.out.println(trame);
        
        switch(buttonNumber){
            case (0) :
                myServer.getFromAPI("C "+id+" 0");
                System.out.println("capteur au repos");
                break;
            case (1) :
                myServer.getFromAPI("C "+id+" 1");
                System.out.println("Switch left up");
                break;
            case (3) : 
                myServer.getFromAPI("C "+id+" 3");
                System.out.println("Switch left down");
                break;
            case (5) :
                myServer.getFromAPI("C "+id+" 5");
                System.out.println("switch right up");
                break;
            case (7) :
                myServer.getFromAPI("C "+id+" 7");
                System.out.println("switch right down");
                break;
            default :
                System.out.println("Mauvaise valeur pour la valeur d'un interrupteur");
                break;
        }
    }
    
    private void AnalyseTemperature(String id, String trame){
        String temperature = new String(trame.substring(12, 14));
        temperature = ""+ ((float)40-(float)40*(float)((float)Integer.parseInt(temperature, 16) / (float)255));
        System.out.println("Temperature calculée : " + temperature);
        myServer.getFromAPI("T "+id+" "+ temperature);
    }

    private void AnalysePresence(String id, String trame){

        String voltage = trame.substring(8, 10);
        String luminosite = trame.substring(10, 12);
        String presence = trame.substring(15, 16);

        double valeurVoltage = Integer.parseInt(voltage, 16) * 5.1 / 255;
        double valeurLuminosite = Integer.parseInt(luminosite, 16) * 510 / 255;
        Integer valeurPresence = Integer.parseInt(presence, 16);

        if (valeurPresence == 0){
            //Pas de présence
            System.out.println("Pas de presence");
            myServer.getFromAPI("P " + id + " 0");
        }
        else if (valeurPresence > 0){
            //Presence
            System.out.println("Presence");
            myServer.getFromAPI("P " + id + " 1");
        }
        else{
            System.out.println("Mauvaise valeur pour la valeur du capteur de présence");
            //Mauvaise valeur
        }
        System.out.println("Tension : " + valeurVoltage + " V");
        myServer.getFromAPI("T " + id + " " + valeurVoltage);
        System.out.println("luminosite : " + valeurLuminosite + " lux");
        myServer.getFromAPI("L " + id + " " + valeurLuminosite);
    }

    private void AnalyseContact(String id, String trame){
    //valeur intéressante sur le bit1
        int valueContact = trame.charAt(8)-48;
        if (valueContact == 8){
            //Capteur ouvert
            myServer.getFromAPI("O "+id+" 0");
            System.out.println("capteur ouvert");
        }
        else if (valueContact == 9){
            //Capteur fermé
            myServer.getFromAPI("O "+id+" 1");
            System.out.println("capteur fermé");
        }
        else{
            System.out.println("Mauvaise valeur pour la valeur du capteur de contact");
            //Mauvaise valeur
        }
    }
    
    public void Actionner(String idActionneur, boolean enclenche) {
        
        String trame;
        if(enclenche==true){
            trame= new String("A55A0B0500000000"+idActionneur+"30");
        }
        else {
            trame= new String("A55A0B0700000000"+idActionneur+"30");
        }
        String addition = new String();
        addition = trame.substring(4, 26);
        
        long sommeOctets=0;
        for ( int i=0 ; i<addition.length() ; i+=2){
            sommeOctets += Long.parseLong(addition.substring(i, i+2), 16);
        }
        String check = new String(Long.toHexString(sommeOctets));
        check=check.substring(check.length()-2, check.length());

        check = check.toUpperCase();
        trame+=check;
        
        System.out.println("trame : \n"+trame);
        
        out.print(trame);
        out.flush();
    }
}