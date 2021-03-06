/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;

import Interface.MapLieu;
import Interface.Window;
import XMLParser.Crafter;
import api.Api;
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
import modele.admin.Client;
import modele.home.*;
import org.dom4j.DocumentException;
import service.Service;

/**
 *
 * @author anisbenyoub
 */
public class ServerGHome extends Thread {

    protected int nbClick;
    protected boolean serverOn;
    protected Map<InetAddress, ComClient> myClientMap;
    protected Service serviceManager;
    protected Lieu monLieu;
    protected List<Association> mesAssociations = new ArrayList<Association>();
    protected Api monApi;

    public ServerGHome() {
        nbClick = 0;
        myClientMap = new HashMap<InetAddress, ComClient>();
        serverOn = true;
        serviceManager = new Service();
    }

    public void setAPI(Api api) {
        monApi = api;
    }

    public Api getApi() {
        return monApi;
    }

    public Service getServiceManager() {
        return serviceManager;
    }

    @Override
    public void run() {

        try {
            Crafter aHomeCrafter = new Crafter("../map.xml");
            Window uneMap = new Window(aHomeCrafter.getLieu(), this);
            monLieu = aHomeCrafter.getLieu();
            uneMap.setVisible(true);
        } catch (DocumentException ex) {
            System.out.println("Problème d'accès au fichier map");
        }
        try {
            loadAssociations("../asso.txt");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }


        try {
            serviceManager.addClient("id", "1234",Client.ClientType.ADMIN);
        } catch (Exception ex) {
        }
        ServerSocket socketserver = null;
        BufferedReader in;
        PrintWriter out;

        try {

            socketserver = new ServerSocket(4500);
            System.out.println("Le serveur est à l'écoute du port " + socketserver.getLocalPort());
            while (serverOn) {
                System.out.println("remise en attente");
                Socket client;
                client = socketserver.accept();
                System.out.println("trucrecu");
                if (myClientMap.get(client.getInetAddress()) != null) {
                    out = new PrintWriter(client.getOutputStream());
                    out.println("C0");
                    out.flush();
                    System.out.println("Deja repertorie");

                } else {
                    System.out.println("Nouveau");
                    ComClient aNewComClient = new ComClient(socketserver, client, this);
                    myClientMap.put(client.getInetAddress(), aNewComClient);
                    aNewComClient.start();
                }


            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            socketserver.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerGHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public synchronized void removeClient(InetAddress address) {
        System.out.println("Client supprime");
        System.out.println(myClientMap.size());
        myClientMap.remove(address);
        System.out.println(myClientMap.size());

    }

    public Lieu getMonLieu() {
        return monLieu;
    }

    public String getMap() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("../map.xml"));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        return stringBuilder.toString().replace('\r', ' ');
    }

    public void addAssociation(String msg) throws FileNotFoundException, IOException {
        List<String> attributes = new ArrayList<String>();
        for (String s : msg.split(" ")) {
            attributes.add(s);
        }
        Association newAsso = new Association(attributes, this);
        mesAssociations.add(newAsso);
        saveAssociations("../asso.txt");

    }

    public String getAssoMsg() {
        String msg = "K";
        msg += " " + mesAssociations.size();
        for (Association a : mesAssociations) {
            msg += " " + a.toString();
        }
        return msg;
    }

    public void getFromAPI(String msg) {
        String[] msg2 = msg.split(" ");
        try {
            serviceManager.manageData(msg2[0], msg2[1], msg2[2]);
        } catch (Exception ex) {
            Logger.getLogger(ServerGHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Association a : mesAssociations) {
            if (a.test()) {
                System.out.println("Asso vérifiée");
                a.execute();
            }
        }
    }

    public synchronized boolean deleteAssociation(String assoc) throws FileNotFoundException, IOException {
        String[] msg2 = assoc.split(" ");
        String value = "";

        for (int i = 2; i != msg2.length - 1; i++) {
            value += msg2[i] + " ";
        }
        value += msg2[msg2.length - 1];

        for (Association a : mesAssociations) {
            if (a.toString().equals(value)) {
                mesAssociations.remove(a);
                saveAssociations("../asso.txt");
                return true;
            }
        }

        return false;
    }

    public boolean capteurExists(String ID) {
        List<Capteur> listeCapteurs = new ArrayList<Capteur>();

        for (Piece piece : monLieu.getListPieces()) {
            listeCapteurs.addAll(piece.getMesCapteurs());
        }
        for (Acces acc : monLieu.getListAcces()) {
            listeCapteurs.addAll(acc.getMesCapteurs());
        }

        for (Capteur c : listeCapteurs) {
            if (c.getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void obsMap() {
        for (InetAddress i : myClientMap.keySet()) {
            myClientMap.get(i).setObsMap();
        }

    }

    protected void loadAssociations(String s) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        try {
            String line = br.readLine();

            while (line != null) {
                addAssociation(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    protected void saveAssociations(String s) throws FileNotFoundException, IOException {
        PrintWriter fichier;
        try {
            fichier = new PrintWriter(new File(s));
            for (Association a : mesAssociations) {
                fichier.println("A " + a.toString());
            }
            fichier.close();
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}