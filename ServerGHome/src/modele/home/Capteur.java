/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.home;

/**
 *
 * @author anisbenyoub
 */
public class Capteur {

    public enum Type {

        INTERRUPTEUR,
        TEMPERATURE,
        POSITION,
        LUMINOSITE,
        AUTRE;
    }
    Integer id;
    Piece lieu;
    Type monType;

    public Capteur(int theid, String type,Conteneur p)
    {
        this.id=theid;
        monType=Type.AUTRE;
    }
    public Integer getId() {
        return id;
    }

    public Type getMonType() {
        return monType;
    }
    public void print()
    {
        System.out.println("Catpeur");
    }
}
