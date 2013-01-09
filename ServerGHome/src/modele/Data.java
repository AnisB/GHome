/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author anisbenyoub
 */
@Entity
public class Data {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected   Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected        Date mHeure;

    public Date getmHeure() {
        return mHeure;
    }

    public void setmHeure(Date mHeure) {
        this.mHeure = mHeure;
    }
    
    public Data()
    {
    }
    
}
