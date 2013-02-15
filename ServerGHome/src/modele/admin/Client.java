/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.admin;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;
import javax.persistence.*;

/**
 *
 * @author anisbenyoub
 */
@Entity
public class Client implements Serializable {

    public enum ClientType
    {
        ADMIN,
        NORMAL
    }
    @Id
    protected String id;
    protected String mdp;
    protected ClientType type;

    public Client() {
    }

    public Client(String id, String mdp,ClientType t) throws NoSuchAlgorithmException {
        this.id = id;
        this.mdp = MD5(mdp);
        this.type=t;
    }
    public Client(String id, String mdp,String tp) {
        this.id = id;
        this.mdp = mdp;
        if(tp.toLowerCase().equals("admin"))
        {
            this.type=ClientType.ADMIN;
        }
        else
        {
            this.type=ClientType.NORMAL;
        }
        
    }

    public String getId() {
        return id;
    }

    public String getMdp() {
        return mdp;
    }

    public ClientType getType() {
        return type;
    }
    
    public String MD5(String md5) {
   try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
        System.out.println("Error while hashing");
    }
    return null;
}
}
