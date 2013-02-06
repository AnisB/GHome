/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author anisbenyoub
 */
@Entity
public class Contact   implements Serializable {

        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected   Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected        Date mHeure;
    protected String capID;
    protected int value;

    public Contact() {
        mHeure= new Date();
                capID="-1";

    }


    public Contact(String capID, int aValue)
    {
        this.capID=capID;
        mHeure= new Date();
        value=aValue;

    }

    public String getCapID() {
        return capID;
    }

    public void setButtonID(String capID) {
        this.capID = capID;
    }
        public Date getmHeure() {
        return mHeure;
    }

    public void setmHeure(Date mHeure) {
        this.mHeure = mHeure;
    }

    public int getValue() {
        return value;
    }
    
}

