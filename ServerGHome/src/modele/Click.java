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
public class Click   implements Serializable {

        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected   Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected        Date mHeure;

    protected int buttonID;

    public Click() {
        mHeure= new Date();
                buttonID=-1;

    }


    public Click(int aButtonID)
    {
        buttonID=aButtonID;
                mHeure= new Date();

    }

    public int getButtonID() {
        return buttonID;
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }
        public Date getmHeure() {
        return mHeure;
    }

    public void setmHeure(Date mHeure) {
        this.mHeure = mHeure;
    }
}

