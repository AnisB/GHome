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
    }

    public String recieveData(String rec) {
        try {
            if (rec == null) {
                return null;
            }
            rec.replace(' ', '\n');
            String plainText = AESencrp.decrypt(rec);

            return plainText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sendData(String message) {
        try {
            String cipherText = AESencrp.encrypt(message);
            out.println(cipherText.replace('\n', ' '));
            out.flush();
            return true;
        } catch (Exception e) {
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
            String contenu = recieveData(in.readLine());

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
            sendData("C1");
            while (myClientConnected) {

                in = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
                String predecoded = in.readLine();
                String message = recieveData(predecoded);

                if (mapUpdate == false) {
                    String map = myHost.getMap();
                    sendData("OBS " + map.replace('\n', ' '));
                    mapUpdate = true;
                }
                if (message == null || message.isEmpty()) {
                    sendData("ERPROTOCOL");
                    myClientConnected = false;
                    myClient.close();
                    myHost.removeClient(myClient.getInetAddress());
                } else {
                    try {
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
                                sendData("M " + map.replace('\n', ' '));
                                break;
                            // The terminal asks for the nb of clicks been done 
                            case 'A':
                                myHost.addAssociation(message);
                                sendData("A1");
                                break;
                            case 'G':
                                String info = "";
                                if (myHost.capteurExists(message.split(" ")[1])) {
                                    info = serviceManager.getData(message.split(" ")[1], message.split(" ")[2]);
                                } else {
                                    info = "G0 NOTFOUND";
                                }
                                sendData(info);
                                break;
                            case 'K':
                                String v = myHost.getAssoMsg();
                                sendData(v);
                                break;
                            case 'O':
                                serviceManager.sendOrder(message.split(" ")[1], message.split(" ")[2], message.split(" ")[3], myHost);
                                sendData("O1");
                                break;
                            case 'V':
                                String fl=serviceManager.getMusic();
                                sendData(fl);
                                break;
                            case 'P':
                                String pm=serviceManager.playMusic(message.split("\"")[1]);
                                sendData(pm);
                                break; 
                             case 'W':
                                String wm=serviceManager.getWeather();
                                sendData(wm);
                                break;
                            case 'B':
                                if (myHost.deleteAssociation(message)) {
                                    v = "B1";
                                } else {
                                    v = "B0 NOTFOUND";
                                }
                                sendData(v);
                                break;
                            default:
                                sendData("ERPROTOCOL");
                                myClientConnected = false;
                                myClient.close();
                                myHost.removeClient(myClient.getInetAddress());
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        sendData("ERPROTOCOL");
                        myClientConnected = false;
                        myClient.close();
                        myHost.removeClient(myClient.getInetAddress());
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ComClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void setObsMap() {
        mapUpdate = false;
    }
}
