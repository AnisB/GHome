/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author anisbenyoub
 */
public class AdminClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Login lg = new Login();
        lg.setVisible(true);
        


    }

    public AdminClient() throws UnknownHostException, IOException {
        socket = new Socket("127.0.0.1", 4500);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        boolean v=true;
        String D=null;
        while(v)
        {
            D=cin.readLine();
            if(D.equals("D"))
            {
            v=false;
            }
            else
            {
               sendData(D);
               String lol =in.readLine();
               System.out.println("Recieved "+lol);
               System.out.println("Decrypted "+recieveData(lol));
            }
            
        }
                       sendData(D);
                       socket.close();

    }

    public String recieveData(String rec) {
        try {
            String plainText = AESencrp.decrypt(rec.replace(' ','\n'));

            return plainText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sendData(String message) {
        try {
            String cipherText = AESencrp.encrypt(message);
            cipherText=cipherText.replace( ' ', '\n');
            out.println(cipherText);
            out.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Failed");
            return false;
        }
    }
}
