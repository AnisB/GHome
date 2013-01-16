/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;


/**
 *
 * @author anisbenyoub
 */
public class Piece extends Conteneur{

        public Piece(Integer id, String nom) {
        this.id = id;
        this.nom = nom;

    }
    public Piece(Integer id, String nom, Integer x, Integer y, Float etage) {
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.etage = etage;
    }



}
