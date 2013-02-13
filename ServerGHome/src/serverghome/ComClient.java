/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Service;

/**
 *
 * @author anisbenyoub
 */
public class ComClient extends Thread {

    protected int threadID;
    boolean myClientConnected;
    ServerSocket myServer;
    Socket myClient;
    protected PrintWriter out;
    ServerGHome myHost;
    boolean mapUpdate = true;

    public ComClient(ServerSocket server, Socket client, ServerGHome host) {
        myClientConnected = true;
        myServer = server;
        myClient = client;
        myHost = host;

        // Check if the pair of keys are present else generate those.
        if (!EncryptionUntil.areKeysPresent()) {
            // Method generates a pair of keys using the RSA algorithm and stores it
            // in their respective files
            EncryptionUntil.generateKey();
        }
    }

    public String recieveData(byte[]  rec) {
        try {
            ObjectInputStream inputStream = null;


            // Decrypt the cipher text using the private key.
            inputStream = new ObjectInputStream(new FileInputStream(EncryptionUntil.PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = EncryptionUntil.decrypt(rec, privateKey);

            // Printing the Original, Encrypted and Decrypted Text
            System.out.println("Encrypted Text: " + new String(rec));
            System.out.println("Decrypted Text: " + plainText);
            return plainText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sendData(String message) {
        try {
            ObjectInputStream inputStream = null;

            // Encrypt the string using the public key
            inputStream = new ObjectInputStream(new FileInputStream(EncryptionUntil.PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] cipherText = EncryptionUntil.encrypt(message, publicKey);
            out.println(cipherText);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void run() {
        BufferedReader in;
        try {
            // Ouverture du buffer d'envoi
            out = new PrintWriter(myClient.getOutputStream());
            // Ouverture du buffer de reception
            in = new BufferedReader(new InputStreamReader(myClient.getInputStream()));

            //String de reception
            String contenu = in.readLine();

            //Gestion des services de persistance
            Service serviceManager = new Service();

            boolean isOk = false;
            try {
                isOk = serviceManager.testClient(contenu.split(" ")[1], contenu.split(" ")[2]);
            } catch (Exception ex) {
                System.out.println("Erreur d'authentification");
            }


            if (!isOk) {
                sendData("C0 UNKNOWN");
                myClient.close();
                myHost.removeClient(myClient.getInetAddress());
                return;
            }
            out.println("C1");
            out.flush();
            while (myClientConnected) {
                
                in = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
                String message = in.readLine();
                
                if(mapUpdate==false)
                {
                        String map = myHost.getMap();
                        out.println("OBS " + map.replace('\n', ' '));
                        out.flush();
                        mapUpdate=true;
                }
                if(message.isEmpty())
                {
                    out.println("ERROR");
                    out.flush();
                }
                else
                {
                switch (message.charAt(0)) {
                    // We are in the case of a disconnection
                    case 'D':
                        myClientConnected = false;
                        myClient.close();
                        myHost.removeClient(myClient.getInetAddress());
                        break;
                    // The client is asking for getting the map
                    case 'M':
                        String map = myHost.getMap();
                        out.println("M " + map.replace('\n', ' '));
                        out.flush();
                        break;
                    // The terminal asks for the nb of clicks been done 
                    case 'A':
                        myHost.addAssociation(message);
                        out.println("A1");
                        out.flush();
                        break;
                    case 'G':
                        String info = "";
                        if (myHost.capteurExists(message.split(" ")[1])) {
                            info = serviceManager.getData(message.split(" ")[1], message.split(" ")[2]);
                        } else {
                            info="G0 NOTFOUND";
                        }
                        out.println(info);
                        out.flush();
                        break;
                    case 'K':
                        String v = myHost.getAssoMsg();
                        out.println(v);
                        out.flush();
                        break;
                    case 'O':
                        serviceManager.sendOrder(message.split(" ")[1], message.split(" ")[2], message.split(" ")[3], myHost);
                        out.println("O1");
                        out.flush();
                        break;
                    case 'B':
                        v = "";
                        if (myHost.deleteAssociation(message)) {
                            v = "B1";
                        } else {
                            v = "B0 NOTFOUND";
                        }
                        out.println(v);
                        out.flush();
                    default:
                        break;
                }
            }
            }

        } catch (IOException ex) {
            Logger.getLogger(ComClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void setObsMap()
    {
        mapUpdate=false;
    }
}
