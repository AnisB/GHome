/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anisbenyoub
 */
public class Conteneur {
    protected Integer id;
    protected String nom;
    protected Integer x;
    protected Integer y;
    protected Integer etage;
    protected List<Capteur> mesCapteurs;
    protected List<Actionneur> mesActionneurs;
        public Integer getEtage() {
        return etage;
    }

    public Conteneur() {
        this.mesCapteurs = new ArrayList<Capteur>();
        this.mesActionneurs = new ArrayList<Actionneur>();

    }

    public Integer getId() {
        return id;
    }

    public List<Actionneur> getMesActionneurs() {
        return mesActionneurs;
    }

    public List<Capteur> getMesCapteurs() {
        return mesCapteurs;
    }

    public String getNom() {
        return nom;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
