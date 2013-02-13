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
public class Temperature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date mHeure;
    protected String capID;
    protected Float tempValue;

    public Temperature() {
        super();
        mHeure = new Date();
        capID = "-1";

    }

    public Temperature(String capID, Float value) {
        
        this.capID = capID;
        mHeure = new Date();
        this.tempValue = value;

    }

    public void setButtonID(String capID) {
        this.capID = capID;
    }

    public Date getmHeure() {
        return mHeure;
    }

    public String getCapID() {
        return capID;
    }

    public Float getValue() {
        return tempValue;
    }
}
