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
public class Click implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date mHeure;
    protected String buttonID;
    protected int clickValue;

    public Click() {
        mHeure = new Date();
        buttonID = "-1";

    }

    public Click(String aButtonID, int aValue) {
        buttonID = aButtonID;
        mHeure = new Date();
        clickValue = aValue;

    }

    public String getButtonID() {
        return buttonID;
    }

    public void setButtonID(String buttonID) {
        this.buttonID = buttonID;
    }

    public Date getmHeure() {
        return mHeure;
    }

    public void setmHeure(Date mHeure) {
        this.mHeure = mHeure;
    }

    public int getValue() {
        return clickValue;
    }
}
