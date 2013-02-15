/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.admin;

import java.io.Serializable;
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

    public Client(String id, String mdp,ClientType t) {
        this.id = id;
        this.mdp = mdp;
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
}
