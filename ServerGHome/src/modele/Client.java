/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author anisbenyoub
 */
@Entity
public class Client implements Serializable {
    
    
    @Id
    protected   String login;
    
    protected   String mdp;

    public Client() {
    }

    
    public Client(String id, String mdp)
    {
        this.login=id;
        this.mdp=mdp;
    }
    
}
