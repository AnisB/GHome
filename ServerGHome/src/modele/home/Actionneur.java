/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

/**
 *
 * @author anisbenyoub
 */
public class Actionneur {
        public enum Type {

        LUMIERE,
        CHAUFFAGE,
        AUTRE;
    }
    Integer id;
    Piece lieu;
    Capteur.Type monType;
    public Actionneur(int theid, String type,Conteneur p)
    {
        this.id=theid;
    }
    public Integer getId() {
        return id;
    }

    public Capteur.Type getMonType() {
        return monType;
    }
}
