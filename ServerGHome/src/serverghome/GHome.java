/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverghome;
import api.Api;
import util.JpaUtil;

/**
 *
 * @author anisbenyoub
 */
public class GHome {
    public static void main(String args[])
    {
        ServerGHome myServer = new ServerGHome();
        myServer.start();
        Api api = new Api(myServer);
        api.start();        
    }
    
}
