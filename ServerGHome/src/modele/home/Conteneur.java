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
    protected List<Capteur> mesCapteurs;
    protected List<Actionneur> mesActionneurs;


    public Conteneur() {
        this.mesCapteurs = new ArrayList<Capteur>();
        this.mesActionneurs = new ArrayList<Actionneur>();

    }

    public Integer getId() {
        return id;
    }

     public  void print()
    {
        System.out.print("Contenueur : nom "+nom);
        for(Capteur c : mesCapteurs)
        {
            c.print();
        }
        
        for(Actionneur a : mesActionneurs)
        {
            a.print();
        }
    }
    public List<Actionneur> getMesActionneurs() {
        return mesActionneurs;
    }

    public List<Capteur> getMesCapteurs() {
        return mesCapteurs;
    }
    
        public void addActionneur(Actionneur act) {
         mesActionneurs.add(act);
    }

    public void addCapteur(Capteur capt) {
        mesCapteurs.add(capt);
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
    
    public boolean deleteCapteur(String id)
    {
        boolean found=false;
        Capteur cx=null;
        for(Capteur c: mesCapteurs)
        {
            if(c.getId().equals(id))
            {
                found=true;
                cx=c;
                break;
            }
        }
        if(found)
        {
            mesCapteurs.remove(cx);
            return true;
        }
        else
        {
            return false;
        }
    }
}
