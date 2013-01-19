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

        public Integer largeurX;
        public Integer largeurY;
        public Integer etage;

    public Piece(Integer id, String nom, Integer x, Integer y, Integer etage, Integer lX, Integer lY) {
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.etage = etage;
        this.largeurX=lX;
        this.largeurY=lY;
                
    }



}
