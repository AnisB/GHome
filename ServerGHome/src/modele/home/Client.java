/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author anisbenyoub
 */
@Entity
public class Client   implements Serializable {

        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected   String id;
    protected        String mdp;

    public Client() 
    {

    }

    public Client(String id, String mdp)
    {
        this.id=id;
        this.mdp=mdp;
    }
}

