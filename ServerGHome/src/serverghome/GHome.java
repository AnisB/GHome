/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;
import api.Api;

/**
 *
 * @author anisbenyoub
 */
public class GHome {
    public static void main(String args[])
    {
        System.out.println("Application begin");
        ServerGHome myServer = new ServerGHome();
        myServer.start();
        Api api = new Api(myServer);
        api.start();
        System.out.println("Serveur lancé");

        
        
    }
    
}
