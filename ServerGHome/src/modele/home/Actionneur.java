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
    String id;
    Piece lieu;
    Capteur.Type monType;
    public Actionneur(String theid, String type,Conteneur p)
    {
        this.id=theid;
    }
    public String getId() {
        return id;
    }

    public Capteur.Type getMonType() {
        return monType;
    }
    
    public void print()
    {
        System.out.println("Actionneur");
    }
}
