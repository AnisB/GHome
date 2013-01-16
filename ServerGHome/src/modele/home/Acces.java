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
public class Acces extends Conteneur{
    public
    List<Piece> mesVoisins;
    public int taillex;
    public int tailley;
        public Acces(Integer id, String nom, Integer x, Integer y, Float etage, int taillex, int tailley) {
            super();
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.etage = etage;
        this.mesVoisins= new ArrayList<Piece>();
        this.taillex=taillex;
        this.tailley=tailley;
    }

    public List<Piece> getMesVoisins() {
        return mesVoisins;
    }

    public void addVoisin(Piece unVoisin) {
        this.mesVoisins.add(unVoisin);
    }
        
}
